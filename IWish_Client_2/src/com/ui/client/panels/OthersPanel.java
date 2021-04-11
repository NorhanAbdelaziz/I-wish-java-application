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
import com.dto.NotifcationDTO;
import com.dto.NotificationDTO;
import com.dto.NotificationListDTO;
import com.dto.ShowItemDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import com.ui.client.WelcomeForm;
import static com.ui.client.WelcomeForm.btn_showOthers;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Notification;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ahmed
 */
public class OthersPanel extends javax.swing.JPanel {

    ServerConnection connection;
    UserDTO userDTO;
    String notifiMsg = "";

    public OthersPanel(ServerConnection connection) {
        initComponents();
        this.connection = connection;
        userDTO = new UserDTO();
        userDTO = Helper.mainUserObj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea_notific = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Others List");

        txtArea_notific.setEditable(false);
        txtArea_notific.setColumns(20);
        txtArea_notific.setRows(5);
        jScrollPane1.setViewportView(txtArea_notific);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 283, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
public void showNotifcation(String response) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // map header which is come from server
                HeaderMapDTO mapDTO = new Gson().fromJson(response, HeaderMapDTO.class);
                NotificationListDTO dTO = new Gson().fromJson(response, NotificationListDTO.class);
                // check status and if there are user id or not 
                if (mapDTO.header.toId == userDTO.id && mapDTO.header.tag == TagType.complete_Notification) {
                    notifiMsg += (mapDTO.header.msg + "\n");
                    // txtArea_notific.append(mapDTO.header.msg + "\n");
                    if (WelcomeForm.btn_showOthers.getForeground() != Color.red) {
                        WelcomeForm.btn_showOthers.setForeground(Color.red);
                        btn_showOthers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/bell_red_128467.png"))); // NOI18N
                        btn_showOthers.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btn_showOthers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

                    }
                } else if (mapDTO.header.tag == TagType.contribute_notification) {
                    txtArea_notific.setText("");
                    for (NotificationDTO ndto : dTO.notificationsList) {
                        if (ndto.id == userDTO.id) {
                            if (WelcomeForm.btn_showOthers.getForeground() != Color.red) {
                                WelcomeForm.btn_showOthers.setForeground(Color.red);
                                btn_showOthers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/bell_red_128467.png"))); // NOI18N
                                btn_showOthers.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btn_showOthers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                            }
                            txtArea_notific.append(ndto.msg + "\n");
                        }
                    }
                }
                txtArea_notific.append(notifiMsg+"\n" );
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea_notific;
    // End of variables declaration//GEN-END:variables
}
