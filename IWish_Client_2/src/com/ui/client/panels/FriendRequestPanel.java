package com.ui.client.panels;

import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.FriendListDTO;
import com.dto.FriendRquestDTO;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import com.ui.client.WelcomeForm;
import java.awt.Button;
import java.awt.List;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class FriendRequestPanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    ServerConnection connection;
    String[] columnNames;
    //DefaultListModel model;
    UserDTO userDTO;

    public FriendRequestPanel(ServerConnection connection) {
        initComponents();
        columnNames = new String[]{"Id", "Name"};
        tableModel = new DefaultTableModel(null, columnNames);
        this.connection = connection;
        //model = new DefaultListModel();
       // this.requestList.setModel(model);
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
    }

    public void getFreindRequest() {
        HeaderDTO header = new HeaderDTO();
        header.tag = TagType.friend_requests;
        header.fromId = userDTO.id;

        connection.sendReqest(header);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        ReqTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        ReqTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ReqTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReqTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ReqTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Friend Requests");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReqTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReqTableMouseClicked
int rowIndex = ReqTable.getSelectedRow();
        int user_id = Integer.parseInt(ReqTable.getValueAt(rowIndex, 0).toString());
        int opt = JOptionPane.showConfirmDialog(this, "Press Yes for Accept \nPress No for Decline",
                "Accept Reqest", JOptionPane.YES_NO_CANCEL_OPTION);
        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.tag = TagType.accept_friend;
        headerDTO.fromId = userDTO.id;
        FriendRquestDTO rquestDTO = new FriendRquestDTO();
        rquestDTO.header = headerDTO;
        rquestDTO.requestOwnerId = user_id;
        if (opt == 0) {
            rquestDTO.status = -1;
            connection.sendReqest(rquestDTO);
        } else {
            if (opt == 1) {
                rquestDTO.status = 1;
                connection.sendReqest(rquestDTO);
            }
        }

        if (opt == 1 || opt == 0) {
            getFreindRequest();
        }
    }//GEN-LAST:event_ReqTableMouseClicked

    public void getRequests(String response) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);
                // check status and if there are user id or not 
                if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.friend_requests) {
                    FriendListDTO friendListDTO = new Gson().fromJson(response, FriendListDTO.class);
                    
                            int rows = friendListDTO.usersList.size();
                        String[][] reqs = new String[rows][2];
                        for (int i = 0; i < rows; i++) {
                            reqs[i][0] = String.valueOf(friendListDTO.usersList.get(i).id);
                            reqs[i][1] = String.valueOf(friendListDTO.usersList.get(i).firstName+" "+friendListDTO.usersList.get(i).lastName);
                        }
                        tableModel = new DefaultTableModel(reqs, columnNames);
                        ReqTable.setModel(tableModel);
                    
                    
                }

            }
        });
    }

    public void acceptDeclineRequest(String response) {
        if (response.contains("header")) {
            HeaderMapDTO headerMap = new Gson().fromJson(response, HeaderMapDTO.class);
            if (headerMap.header.toId == userDTO.id && headerMap.header.tag == TagType.accept_friend) {
                getFreindRequest();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ReqTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
