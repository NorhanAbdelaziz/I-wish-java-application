package com.db;

import com.DTOs.ItemDTO;
import com.DTOs.MyWishListDTO;
import com.DTOs.NotifcationDTO;
import com.DTOs.NotificationDTO;
import com.DTOs.NotificationListDTO;
import com.DTOs.UserDTO;
import static com.db.DbHelper.con;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Item {

    Connection con;

    public Item() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DbHelper.getDbCon();

        } catch (SQLException ex) {
            System.out.println("item db sql exception");
        }
    }

    public int getNextId() {
        int id = 0;
        try {
            Statement stmt = con.createStatement();
            String queryString = new String("Select NVL(MAX(ID),0)+1 from ITEM");
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public int insert(ItemDTO itemDTO) {
        int newId = this.getNextId();
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ITEM ( ID ,NAME ,PRICE ,DESCRIPTION ,CATEGORY)\n"
                    + "    VALUES (? , ? ,? , ? ,? )");

            pst.setInt(1, newId);
            pst.setString(2, itemDTO.name);
            pst.setInt(3, itemDTO.price);
            pst.setString(4, itemDTO.description);
            pst.setInt(5, itemDTO.category);

            ResultSet rs = pst.executeQuery();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return newId;

    }

    public Vector<ItemDTO> getAllItems(int category) {
        Vector<ItemDTO> items = new Vector<>();
        try {
            PreparedStatement pst = con.prepareStatement("Select * from Item WHERE CATEGORY = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, category);
            ResultSet itemsList = pst.executeQuery();

            while (itemsList.next()) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.id = itemsList.getInt("ID");
                itemDTO.name = itemsList.getString("NAME");
                itemDTO.price = itemsList.getInt("PRICE");
                itemDTO.description = itemsList.getString("DESCRIPTION");
                itemDTO.category = itemsList.getInt("CATEGORY");
                items.add(itemDTO);
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public Vector<ItemDTO> getFashion() {
        String category = "Fashion";
        Vector<ItemDTO> items = new Vector<>();
        try {
            PreparedStatement pst = con.prepareStatement("Select * from Item WHERE CATEGORY = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, category);
            ResultSet itemsList = pst.executeQuery();

            while (itemsList.next()) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.id = itemsList.getInt("ID");
                itemDTO.name = itemsList.getString("NAME");
                itemDTO.price = itemsList.getInt("PRICE");
                itemDTO.description = itemsList.getString("DESCRIPTION");
                itemDTO.quantity = itemsList.getInt("QUANTITY");
                itemDTO.category = itemsList.getInt("CATEGORY");
                items.add(itemDTO);
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public Vector<ItemDTO> myWishList(int from_id) {
        Vector<ItemDTO> wishItems = new Vector<>();

        try {
            PreparedStatement pst = con.prepareStatement("Select * from Item,wishlist where user_id = ? and item_id = id and remaining_price > 0 order by id ");
            pst.setInt(1, from_id);

            ResultSet wishList = pst.executeQuery();

            while (wishList.next()) {
                ItemDTO wishDTO = new ItemDTO();

                wishDTO.id = wishList.getInt("ID");
                wishDTO.name = wishList.getString("NAME");
                wishDTO.price = wishList.getInt("PRICE");
                wishDTO.description = wishList.getString("DESCRIPTION");
                wishDTO.quantity = 1;
                wishDTO.remaining_price = wishList.getInt("REMAINING_PRICE");
                wishItems.add(wishDTO);
            }
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }

        return wishItems;
    }

    public int insert_wish(int user_id, int item_id) {
        int flag = 0;
        int item = 0;
        int price = 0;
        try {
            PreparedStatement pst0 = con.prepareStatement("SELECT * FROM WISHLIST WHERE USER_ID = ? AND ITEM_ID = ? AND REMAINING_PRICE >0");

            pst0.setInt(1, user_id);
            pst0.setInt(2, item_id);
            ResultSet rs0 = pst0.executeQuery();
            while (rs0.next()) {
                item = rs0.getRow();
            }
            pst0.close();

            if (item == 0) {
                flag = 2;
                PreparedStatement pst1 = con.prepareStatement("SELECT PRICE FROM ITEM WHERE ID = ?");

                pst1.setInt(1, item_id);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    price = rs1.getInt(1);
                }
                pst1.close();

                PreparedStatement pst2 = con.prepareStatement("INSERT INTO WISHLIST VALUES(?,?,?)");
                pst2.setInt(1, user_id);
                pst2.setInt(2, item_id);
                pst2.setInt(3, price);
                pst2.executeQuery();
                pst2.close();

            } else {
                flag = 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return flag;
    }

    public void removeItem(int user_id, int item_id) {

        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM WISHLIST where USER_ID = ? AND ITEM_ID = ?");

            pst.setInt(1, user_id);
            pst.setInt(2, item_id);

            pst.execute();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int check_wallet(int user_id, int paid) {

        int wallet_status = 0;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT CASE WHEN ?<=WALLET THEN 0 ELSE 1 END FROM USERS WHERE ID=?");
            pst.setInt(1, paid);
            pst.setInt(2, user_id);
            ResultSet rs1 = pst.executeQuery();
            while (rs1.next()) {
                wallet_status = rs1.getInt(1);
            }
            pst.executeQuery();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wallet_status;
    }

    public int contribute(int item_id, int user_id, int friend_id, int paid) {
        int flag = 0;
        int wallet = 0;
        int remaining_price = 0;
        int wallet_status = check_wallet(user_id, paid);
        if (wallet_status == 0) {
            try {
                PreparedStatement pst1 = con.prepareStatement("SELECT REMAINING_PRICE FROM WISHLIST WHERE ITEM_ID = ? AND USER_ID=?");
                pst1.setInt(1, item_id);
                pst1.setInt(2, friend_id);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    remaining_price = rs1.getInt(1);
                }
                pst1.executeQuery();
                pst1.close();

                if (paid <= remaining_price) {

                    PreparedStatement pst2 = con.prepareStatement("INSERT INTO CONTRIBUTORS VALUES(?,?,?,?,sysdate)");
                    pst2.setInt(1, friend_id);
                    pst2.setInt(2, user_id);
                    pst2.setInt(3, item_id);
                    pst2.setInt(4, paid);
                    pst2.executeQuery();
                    pst2.close();

                    PreparedStatement pst3 = con.prepareStatement("UPDATE WISHLIST  SET REMAINING_PRICE = ? WHERE ITEM_ID = ? AND USER_ID=?");
                    pst3.setInt(1, remaining_price - paid);
                    pst3.setInt(2, item_id);
                    pst3.setInt(3, friend_id);
                    pst3.executeQuery();
                    pst3.close();

                    PreparedStatement pst4 = con.prepareStatement("SELECT WALLET FROM USERS WHERE ID = ?");
                    pst4.setInt(1, user_id);
                    ResultSet rs2 = pst4.executeQuery();
                    while (rs2.next()) {
                        wallet = rs2.getInt(1);
                    }
                    pst4.executeQuery();
                    pst4.close();

                    PreparedStatement pst5 = con.prepareStatement("UPDATE USERS SET WALLET = ? WHERE ID=?");
                    pst5.setInt(1, wallet - paid);
                    pst5.setInt(2, user_id);
                    pst5.executeQuery();
                    pst5.close();
                } else {
                    flag = 1;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            flag = 2;

        }
        return flag;
    }

    public boolean isItemCompleted(int userId, int itemId) {
        boolean flag = false;
        try {
            PreparedStatement pst = con.prepareStatement("Select REMAINING_PRICE   From WISHLIST\n"
                    + "where USER_ID =? AND ITEM_ID= ? AND REMAINING_PRICE =0");
            pst.setInt(1, userId);
            pst.setInt(2, itemId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 0) {
                    flag = true;
                }
                break;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public NotificationListDTO getContributeNotifcation() {
        NotificationListDTO notifcationDTO = new NotificationListDTO();
        try {
            PreparedStatement pst = con.prepareStatement(" Select distinct CONTRIBUTORS.CONTRIBUTOR_ID ,ITEM.NAME||' is done for user : '||USERS.USERNAME as MSG\n"
                    + " from  CONTRIBUTORS ,Item , WISHLIST ,Users\n"
                    + " where  ITEM.ID=  CONTRIBUTORS.ITEM_ID \n"
                    + " AND WISHLIST.ITEM_ID =CONTRIBUTORS.ITEM_ID \n"
                    + " AND WISHLIST.REMAINING_PRICE = 0\n"
                    + " AND USERS.ID = CONTRIBUTORS.USER_ID");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NotificationDTO ndto = new NotificationDTO();
                ndto.id = rs.getInt("CONTRIBUTOR_ID");
                ndto.msg = rs.getString("msg");
                notifcationDTO.notificationsList.add(ndto);
            }

            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifcationDTO;
    }

    public String getItemById(int itemId, int userId) {
        String itemName="";
        String contUsers="";
        String msg="";
        try {
            PreparedStatement pst = con.prepareStatement("Select NAME from Item where ID = ? ");
            pst.setInt(1, itemId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itemName = rs.getString("NAME");
            }
            
            
            PreparedStatement pst2 = con.prepareStatement("Select Distinct USERS.USERNAME from  CONTRIBUTORS , USERS\n"
                    + "where USERS.ID = CONTRIBUTORS.CONTRIBUTOR_ID\n"
                    + "AND CONTRIBUTORS.USER_ID =? AND CONTRIBUTORS.ITEM_ID = ? ");
            pst2.setInt(1, userId);
            pst2.setInt(2, itemId);
            
            ResultSet rs2 = pst2.executeQuery();
            Vector<String> contributors = new Vector<>();
            while (rs2.next()) {
                contributors.add( rs2.getString("USERNAME") );
            }
            for (String contributor : contributors) {
                contUsers+=contributor +",";
            }
            msg = "your item "+itemName+" is completed by "+contUsers;
            
            pst.close();
            pst2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
