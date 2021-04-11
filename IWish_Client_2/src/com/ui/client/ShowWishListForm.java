/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.client;

import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.ContributeDTO;
import com.dto.FriendListDTO;
import com.dto.FriendRquestDTO;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.ItemDTO;
import com.dto.Show_friend_wish_listDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Reem
 */
public class ShowWishListForm extends javax.swing.JFrame {

    /**
     * Creates new form ShowWishListForm
     */
    DefaultTableModel tableModel;
    ServerConnection connection;
    String[] columnNames;
    UserDTO userDTO;
    int friend_id = 0;

    public ShowWishListForm(ServerConnection connection, int friendId) {
        initComponents();
        setLocationRelativeTo(null);
        this.connection = connection;
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
        
    }
    
    public void showWishList(int friendId){
        columnNames = new String[]{"Id", "Item Name", "Price", "Remaining Price"};
        tableModel = new DefaultTableModel(null, columnNames);

        if (friendId > 0) {
            this.friend_id = friendId;
            getFriendWishList(friendId);
        }
    }
    public int getFriendWishList(int friend_id) {

        HeaderDTO showHeader = new HeaderDTO();
        showHeader.tag = TagType.show_friend_wishList;
        showHeader.fromId = userDTO.id;

        Show_friend_wish_listDTO show_friendDTO = new Show_friend_wish_listDTO();
        show_friendDTO.header = showHeader;
        show_friendDTO.friend_list_id = friend_id;
        connection.sendReqest(show_friendDTO);
        String[][] items = new String[][]{};

        return show_friendDTO.friend_list_id;
    }

    public void showFriendWishList(String response) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // map header which is come from server
                Show_friend_wish_listDTO showObj = new Gson().fromJson(response, Show_friend_wish_listDTO.class);
                if (showObj.header.toId == userDTO.id && showObj.header.tag == TagType.show_friend_wishList) {
                    int rows = showObj.listOfFriendWishes.size();
                    String[][] items = new String[rows][4];
                    for (int i = 0; i < rows; i++) {
                        items[i][0] = String.valueOf(showObj.listOfFriendWishes.get(i).id);
                        items[i][1] = String.valueOf(showObj.listOfFriendWishes.get(i).name);
                        items[i][2] = String.valueOf(showObj.listOfFriendWishes.get(i).price);
                        items[i][3] = String.valueOf(showObj.listOfFriendWishes.get(i).remaining_price);
                    }
                    tableModel = new DefaultTableModel(items, columnNames);
                    wishListTable.setModel(tableModel);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        wishListTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        wishListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        wishListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onItemRowCliecked(evt);
            }
        });
        jScrollPane1.setViewportView(wishListTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 181, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onItemRowCliecked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onItemRowCliecked
        int rowIndex = wishListTable.getSelectedRow();
        // friend_id = Integer.parseInt(wishListTable.getValueAt(rowIndex, 0).toString().split("/")[0]);
        int item_id = Integer.parseInt(wishListTable.getValueAt(rowIndex, 0).toString());
        String[] options = new String[]{
            "Confirm", "Cancel"
        };
        JPanel panel = new JPanel();
        panel.add(new JLabel("Do you want to buy this item for your friend?\n Type amount of money."));
        JTextField textField = new JTextField(10);
        textField.setText("0");
        panel.add(textField);
        int opt = JOptionPane.showOptionDialog(null, panel, "Contribute", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        int paid = Integer.parseInt(textField.getText());

        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.tag = TagType.contribute;
        headerDTO.fromId = userDTO.id;
        ContributeDTO contDTO = new ContributeDTO();
        contDTO.header = headerDTO;
        contDTO.friend_id = friend_id;
        contDTO.item_id = item_id;
        contDTO.paid_price = paid;

        if (opt == 0) {
            connection.sendReqest(contDTO);
        }

    }//GEN-LAST:event_onItemRowCliecked

    public void contribute(String response) {
        // map header which is come from server
        //RemoveItemDTO wishitem = new Gson().fromJson(response, RemoveItemDTO.class);
        HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);
        if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.contribute && headerMap.header.flag == 0) {
            JOptionPane.showMessageDialog(null, "Successful");
            getFriendWishList(friend_id);

        } else if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.contribute && headerMap.header.flag == 1) {
            JOptionPane.showMessageDialog(null, "Your entered amount exceed the required price");
        } else if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.contribute && headerMap.header.flag == 2) {
            JOptionPane.showMessageDialog(null, "You do not have enough money in your wallet!");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowWishListForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowWishListForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowWishListForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowWishListForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowWishListForm(null,0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable wishListTable;
    // End of variables declaration//GEN-END:variables
//private javax.swing.JButton inc;
    //private javax.swing.JTextField textField;
}
