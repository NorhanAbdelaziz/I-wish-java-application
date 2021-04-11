/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.client.panels;

import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.AddItemDTO;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.ItemDTO;
import com.dto.ShowItemDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
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
public class ItemPanel extends javax.swing.JPanel {

      DefaultTableModel tableModel;
    ServerConnection connection;
    String[] columnNames;
    UserDTO userDTO;
    ItemDTO itemDTO;

    public ItemPanel(ServerConnection connection) {
        initComponents();
        columnNames = new String[]{"Id", "Item Name", "Price"};
        tableModel = new DefaultTableModel(null, columnNames);
        this.connection = connection;
        //model = new DefaultListModel();
        //this.itemList.setModel(model);
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
        
    }

    public void sendRequest(ShowItemDTO itemDTO) {
        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.show_items;
        header.category = itemDTO.category;
        connection.sendReqest(header);

    }
    
        public void sendRequestf() {
        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.show_fashion;
        connection.sendReqest(header);
    }

    public void showitems(String response) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // map header which is come from server
                HeaderMapDTO mapheaderDTO = new Gson().fromJson(response, HeaderMapDTO.class);
                if (mapheaderDTO.header.tag == TagType.show_items) {
                   
                    ShowItemDTO mapDTO = new Gson().fromJson(response, ShowItemDTO.class);
                                       if (mapDTO.listOfItems != null) {
                        int rows = mapDTO.listOfItems.size();
                        String[][] items = new String[rows][3];
                        for (int i = 0; i < rows; i++) {
                            items[i][0] = String.valueOf(mapDTO.listOfItems.get(i).id);
                            items[i][1] = String.valueOf(mapDTO.listOfItems.get(i).name);
                            items[i][2] = String.valueOf(mapDTO.listOfItems.get(i).price)+" $";
                        }
                        tableModel = new DefaultTableModel(items, columnNames);
                        ItemTable.setModel(tableModel);
                    }
                                       ItemTable.invalidate();
                    
                }
                System.out.println("res"+response);

            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        ItemTable = new javax.swing.JTable();

        ItemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ItemTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ItemTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ItemTableMouseClicked
        int rowIndex = ItemTable.getSelectedRow();
        int item_id = Integer.parseInt(ItemTable.getValueAt(rowIndex, 0).toString());
        int opt = JOptionPane.showConfirmDialog(this, "Do you want to add this item to your wishlist?",
                "Add Item", JOptionPane.OK_CANCEL_OPTION);
        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.tag = TagType.add_to_wish_list;
        headerDTO.fromId = userDTO.id;
        AddItemDTO additemDTO = new AddItemDTO();
        additemDTO.header = headerDTO;
        additemDTO.item_id = item_id;
        if (opt == 0) {
            connection.sendReqest(additemDTO);
        }
    }//GEN-LAST:event_ItemTableMouseClicked
    
    public void addToWishList(String response) {
        AddItemDTO additem = new Gson().fromJson(response, AddItemDTO.class);
        if (additem.header.toId == userDTO.id && additem.header.tag == TagType.add_to_wish_list && additem.header.flag ==2) {
            JOptionPane.showMessageDialog(null, "added successfully");
        }
        else if (additem.header.toId == userDTO.id && additem.header.tag == TagType.add_to_wish_list && additem.header.flag ==1) {
            JOptionPane.showMessageDialog(null, "You already have this item in your list!");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ItemTable;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
