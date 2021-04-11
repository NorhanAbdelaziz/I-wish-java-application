/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.client.panels;

import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.AddFriendDTO;
import com.dto.AddItemDTO;
import com.dto.FriendListDTO;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.ItemDTO;
import com.dto.MyWishListDTO;
import com.dto.RemoveFriendDTO;
import com.dto.Show_friend_wish_listDTO;
import com.dto.Show_friend_wish_listDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import com.ui.client.ShowWishListForm;
import com.ui.client.WelcomeForm;
import java.awt.CardLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmed
 */
public class FriendListPanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    ServerConnection connection;
    String[] columnNames;
    //DefaultListModel model;
    CardLayout cardLayout;
    UserDTO userDTO;

    
    public FriendListPanel(ServerConnection connection) {
         initComponents();
         //setLocationRelativeTo(null);
        columnNames = new String[]{"Id", "Friend Name"};
        tableModel = new DefaultTableModel(null, columnNames);
        this.connection = connection;
        //model = new DefaultListModel();
        //this.friendlist.setModel(model);
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_addFriend = new javax.swing.JButton();
        txt_addFriend = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        FriendTable = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("  Friend List");

        btn_addFriend.setText("Add");
        btn_addFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addFriendActionPerformed(evt);
            }
        });

        txt_addFriend.setText("Enter friend email");
        txt_addFriend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_addFriendMouseClicked(evt);
            }
        });
        txt_addFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_addFriendActionPerformed(evt);
            }
        });

        FriendTable.setModel(new javax.swing.table.DefaultTableModel(
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
        FriendTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FriendTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(FriendTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_addFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addFriend)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_addFriend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addFriend))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addFriendActionPerformed

        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.add_friend;
        header.fromId = userDTO.id;

        AddFriendDTO obj = new AddFriendDTO();
        obj.search_email = txt_addFriend.getText();
        obj.header = header;
        connection.sendReqest(obj);
    }//GEN-LAST:event_btn_addFriendActionPerformed

    private void txt_addFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_addFriendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addFriendActionPerformed

    private void txt_addFriendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_addFriendMouseClicked
        txt_addFriend.setText("");
    }//GEN-LAST:event_txt_addFriendMouseClicked

    private void FriendTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FriendTableMouseClicked
        /**if (friendlist.getSelectedValue() == null) {
            return;
        }*/
        int rowIndex = FriendTable.getSelectedRow();
        int friend_id = Integer.parseInt(FriendTable.getValueAt(rowIndex, 0).toString());
        Object[] options = {
            "Remove_friend", "Show wish list", "Cancel"
        };
        int opt = JOptionPane.showOptionDialog(this, "HELLO!", "option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        HeaderDTO removeheaderDTO = new HeaderDTO();
        removeheaderDTO.tag = TagType.remove_friend;
        removeheaderDTO.fromId = userDTO.id;
        RemoveFriendDTO removeFriendDTO = new RemoveFriendDTO();
        removeFriendDTO.header = removeheaderDTO;
        removeFriendDTO.Remove_id = friend_id;

        if (opt == 0) {
            connection.sendReqest(removeFriendDTO);

        } else if (opt == 1) {
            WelcomeForm.showWishListForm.showWishList(friend_id);
            WelcomeForm.showWishListForm.setVisible(true);
        }
    }//GEN-LAST:event_FriendTableMouseClicked

    public void getMyFriendList() {
        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.show_friend_list;
        header.fromId = userDTO.id;
        connection.sendReqest(header);
        String[][] friends = new String[][]{};
    }

    public void removeFriend(String response) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HeaderDTO removeObj = new Gson().fromJson(response, HeaderDTO.class);
                if (removeObj.toId == userDTO.id && removeObj.tag == TagType.remove_friend) {
                    JOptionPane.showMessageDialog(null, "Friend removed successfully");
                    getMyFriendList();
                }
            }
        });
    }

    public void showFriendList(String response) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // map header which is come from server
                HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);

                // check status and if there are user id or not 
                if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.show_friend_list) {
                    FriendListDTO friendListDTO = new Gson().fromJson(response, FriendListDTO.class);
                  
                        
                        int rows = friendListDTO.usersList.size();
                    String[][] items = new String[rows][2];
                    for (int i = 0; i < rows; i++) {
                        items[i][0] = String.valueOf(friendListDTO.usersList.get(i).id);
                        items[i][1] = String.valueOf(friendListDTO.usersList.get(i).firstName+" "+friendListDTO.usersList.get(i).lastName);
                    }
                    tableModel = new DefaultTableModel(items, columnNames);
                    FriendTable.setModel(tableModel);
                    
                }
            }
        });
    }

    public void addFriend(String response) {
        // map header which is come from server
        HeaderDTO mapDTO = new Gson().fromJson(response, HeaderDTO.class);
        if (mapDTO.tag == TagType.add_friend && mapDTO.toId == userDTO.id && mapDTO.flag == 0) {
            JOptionPane.showMessageDialog(null, "Request send successfully ");
            txt_addFriend.setText("");

        } else if (mapDTO.tag == TagType.add_friend && mapDTO.toId == userDTO.id && mapDTO.flag == 1) {
            JOptionPane.showMessageDialog(null, "You are friends");
            txt_addFriend.setText("");

        } else if (mapDTO.tag == TagType.add_friend && mapDTO.toId == userDTO.id && mapDTO.flag == -1) {
            JOptionPane.showMessageDialog(null, "request already sent");
            txt_addFriend.setText("");

        } else if (mapDTO.tag == TagType.add_friend && mapDTO.toId == userDTO.id && mapDTO.flag == 2) {
            JOptionPane.showMessageDialog(null, "User doesnt exist");
            txt_addFriend.setText("");

        }
        
        else if (mapDTO.tag == TagType.add_friend && mapDTO.toId == userDTO.id && mapDTO.flag == 4) {
            JOptionPane.showMessageDialog(null, "You can't add yourself!");
            txt_addFriend.setText("");

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable FriendTable;
    private javax.swing.JButton btn_addFriend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_addFriend;
    // End of variables declaration//GEN-END:variables
}
