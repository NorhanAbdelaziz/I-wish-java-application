/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.client.panels;

import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.ItemDTO;
import com.dto.MyWishListDTO;
import com.dto.RemoveItemDTO;
import com.dto.ShowItemDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import com.ui.client.WelcomeForm;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmed
 */
public class WishListPanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    ServerConnection connection;
    String[] columnNames;
    //DefaultListModel model;
    UserDTO userDTO;

    public WishListPanel(ServerConnection connection) {
        initComponents();
        columnNames = new String[]{"Id", "Item Name","Price", "Remaining Price"};
        tableModel = new DefaultTableModel(null, columnNames);
        this.connection = connection;
        //model = new DefaultListModel();
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
        //this.wishList.setModel(tableModel);
    }

    public void myWishList() {
        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.show_wish_list;
        header.fromId = userDTO.id;
        MyWishListDTO obj = new MyWishListDTO();
        obj.header = header;
        connection.sendReqest(obj);
        String[][] wishs = new String[][]{};
    }

    public void showMyWishList(String response) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // map header which is come from server
                HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);
                if (headerMap.header.tag == TagType.show_wish_list && headerMap.header.toId == userDTO.id) {
                    MyWishListDTO mapDTO = new Gson().fromJson(response, MyWishListDTO.class);
                    //tableModel.clear();// = new DefaultListModel();
                    if (mapDTO.listOfWishes != null) {
                        int rows = mapDTO.listOfWishes.size();
                        String[][] wishs = new String[rows][4];
                        for (int i = 0; i < rows; i++) {
                            wishs[i][0] = String.valueOf(mapDTO.listOfWishes.get(i).id);
                            wishs[i][1] = String.valueOf(mapDTO.listOfWishes.get(i).name);
                            wishs[i][2] = String.valueOf(mapDTO.listOfWishes.get(i).price)+" $";
                            wishs[i][3] = String.valueOf(mapDTO.listOfWishes.get(i).remaining_price)+" $";
                        }
                        tableModel = new DefaultTableModel(wishs, columnNames);
                        WishTable.setModel(tableModel);
                    }

                    WishTable.invalidate();
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        WishTable = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("My Wish List");

        WishTable.setModel(new javax.swing.table.DefaultTableModel(
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
        WishTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WishTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(WishTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void WishTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WishTableMouseClicked
       int rowIndex = WishTable.getSelectedRow();
        int wish_id = Integer.parseInt(WishTable.getValueAt(rowIndex, 0).toString());
        int opt = JOptionPane.showConfirmDialog(this, "Do you want to delete this item?",
                "Delete Item", JOptionPane.OK_CANCEL_OPTION);
        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.tag = TagType.remove_from_wish_list;
        headerDTO.fromId = userDTO.id;
        RemoveItemDTO removeditem = new RemoveItemDTO();
        removeditem.header = headerDTO;
        removeditem.item_id = wish_id;
        if (opt == 0) {
            connection.sendReqest(removeditem);
        }
    }//GEN-LAST:event_WishTableMouseClicked
    public void removeItem(String response) {
        HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);
       
            JOptionPane.showMessageDialog(null, "Removed successfully");
            myWishList();
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable WishTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
