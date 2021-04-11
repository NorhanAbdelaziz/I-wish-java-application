package com.ui.server;

import com.DTOs.ItemDTO;
import com.db.DbHelper;
import com.db.Item;
import java.io.IOException;

public class ServerForm extends javax.swing.JFrame {

    Thread t;
    Server server;
    boolean isStart = false;

    public ServerForm() {
        initComponents();
        setLocationRelativeTo(null);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                server = new Server();
            }
        });
        // t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_addItem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_send1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_addItem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_addItem.setText("Add Item");
        btn_addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addItemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Welecome Server");

        btn_send1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_send1.setText("Start");
        btn_send1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel1)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_send1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addItem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_send1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addItemActionPerformed
        AddItemForm itemForm = new AddItemForm();
        itemForm.setVisible(true);
    }//GEN-LAST:event_btn_addItemActionPerformed

    private void btn_send1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send1ActionPerformed
        if (this.isStart == false) { // start server

            if (t.getState() == Thread.State.RUNNABLE) {
                try {
                    Server.server.accept();
                    t.resume();

                } catch (IOException ex) {
                    System.out.println("that has been error when start server");
                }

            } else if (t.getState() == Thread.State.TERMINATED) {
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        server = new Server();
                    }
                });
                t.start();

            } else {
                t.start();
            }
            this.btn_send1.setText("Stop");
            this.isStart = true;
        } else { // stop server
            this.btn_send1.setText("Start");
            stopServer();
            t.stop();
            this.isStart = false;
        }
    }//GEN-LAST:event_btn_send1ActionPerformed
    public void stopServer() {  //add
        try {
            Server.server.close();
            for (ClientHandler client : ClientHandler.vector) {
                client.stop();
                client.sc.close();
            }
        } catch (IOException ex) {
            System.out.println("that has been error when close server");
        }
    }

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
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addItem;
    private javax.swing.JButton btn_send1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
