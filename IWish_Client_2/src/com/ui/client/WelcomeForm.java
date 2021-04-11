package com.ui.client;

import com.ui.client.panels.FriendListPanel;
import com.ui.client.panels.ItemPanel;
import com.ui.client.panels.OthersPanel;
import com.ui.client.panels.WishListPanel;
import com.ui.client.panels.ProfilePanel;
import com.client.helper.Helper;
import com.client.helper.ServerConnection;
import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.dto.ItemDTO;
import com.dto.ShowItemDTO;
import com.dto.TagType;
import com.dto.UserDTO;
import com.google.gson.Gson;
import com.ui.client.panels.FriendRequestPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JPanel;

public class WelcomeForm extends javax.swing.JFrame {

    CardLayout cardLayout;
    public static ItemPanel itemPanel;
    public static WishListPanel wishListPanel;
    public static FriendListPanel friendListPanel;
    public static FriendRequestPanel friendRequestPanel;
    public static ProfilePanel profilePanel;
    public static OthersPanel othersPanel;
    public static ShowWishListForm showWishListForm;
    public static WelcomeForm welcomeForm;
    ShowItemDTO itemDTO;
    ServerConnection connection;
    

    public WelcomeForm(ServerConnection connection) {
        initComponents();
        setLocationRelativeTo(null);

        itemPanel = new ItemPanel(connection);
        profilePanel = new ProfilePanel(connection);
        wishListPanel = new WishListPanel(connection);
        friendListPanel = new FriendListPanel(connection);
        friendRequestPanel = new FriendRequestPanel(connection);
        othersPanel = new OthersPanel(connection);
        showWishListForm = new ShowWishListForm(connection, 0);
        cardLayout = (CardLayout) Panel_container.getLayout();
        welcomeForm = this;
        this.registerPanels();
        //this.connection = connection;
        //login =this;

       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        DefaultPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        Panel_container = new javax.swing.JPanel();
        HomePanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_makeup = new javax.swing.JButton();
        btn_electronics = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_wishList = new javax.swing.JButton();
        btn_showItems = new javax.swing.JButton();
        btn_friendList = new javax.swing.JButton();
        btn_friendRequests = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_showOthers = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DefaultPanelLayout = new javax.swing.GroupLayout(DefaultPanel);
        DefaultPanel.setLayout(DefaultPanelLayout);
        DefaultPanelLayout.setHorizontalGroup(
            DefaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DefaultPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        DefaultPanelLayout.setVerticalGroup(
            DefaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DefaultPanelLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        Panel_container.setLayout(new java.awt.CardLayout());

        HomePanel.setPreferredSize(new java.awt.Dimension(480, 420));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/fashionn.jpg"))); // NOI18N
        jButton1.setText("Fashion");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(10);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        jLabel1.setText(" What are you searching for?");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_makeup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/makeup (1).png"))); // NOI18N
        btn_makeup.setText("Make Up");
        btn_makeup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_makeup.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_makeup.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btn_makeup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_makeupActionPerformed(evt);
            }
        });

        btn_electronics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/electronis (1).png"))); // NOI18N
        btn_electronics.setText("Electronics");
        btn_electronics.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_electronics.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_electronics.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btn_electronics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_electronicsActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/kitchen (1).jpg"))); // NOI18N
        jButton2.setText("Kitchen Application");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/SHOPNOWWWW.PNG"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/off50.PNG"))); // NOI18N
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Other");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(HomePanelLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(HomePanelLayout.createSequentialGroup()
                            .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(HomePanelLayout.createSequentialGroup()
                                    .addComponent(btn_electronics, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_makeup, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(HomePanelLayout.createSequentialGroup()
                        .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_makeup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_electronics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(HomePanelLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        Panel_container.add(HomePanel, "card2");

        jPanel1.add(Panel_container, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(350, 81));

        btn_wishList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/heart (3).png"))); // NOI18N
        btn_wishList.setText("Wish List");
        btn_wishList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_wishList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_wishList.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_wishList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_wishList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_wishListActionPerformed(evt);
            }
        });

        btn_showItems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/homee.png"))); // NOI18N
        btn_showItems.setText("Home");
        btn_showItems.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_showItems.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_showItems.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_showItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showItemsActionPerformed(evt);
            }
        });

        btn_friendList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/fffffffff.png"))); // NOI18N
        btn_friendList.setText("Friend List");
        btn_friendList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_friendList.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_friendList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_friendList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_friendListActionPerformed(evt);
            }
        });

        btn_friendRequests.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/inviting_friends.png"))); // NOI18N
        btn_friendRequests.setText("Request");
        btn_friendRequests.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_friendRequests.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_friendRequests.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_friendRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_friendRequestsActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/Profile.png"))); // NOI18N
        jButton4.setText("Profile");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btn_showItems, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_wishList, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_friendList, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btn_friendRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_friendRequests, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_friendList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_wishList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_showItems, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel7.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Ravie", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("iWISH");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_showOthers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/bell_icon_128467.png"))); // NOI18N
        btn_showOthers.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_showOthers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_showOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showOthersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_showOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_showOthers))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_showItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showItemsActionPerformed
        this.HomePanel.show();
        this.registerPanels();
    }//GEN-LAST:event_btn_showItemsActionPerformed

    private void btn_wishListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_wishListActionPerformed
        this.cardLayout.show(Panel_container, "wishListPanel");
        this.wishListPanel.myWishList();
    }//GEN-LAST:event_btn_wishListActionPerformed

    private void btn_friendListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_friendListActionPerformed
        this.cardLayout.show(Panel_container, "friendListPanel");
        this.friendListPanel.getMyFriendList();
    }//GEN-LAST:event_btn_friendListActionPerformed

    private void btn_showOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showOthersActionPerformed
        this.cardLayout.show(Panel_container, "othersPanel");
        this.btn_showOthers.setForeground(Color.GRAY);
        btn_showOthers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/client/image/bell_icon_128467.png"))); // NOI18N
        btn_showOthers.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_showOthers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    }//GEN-LAST:event_btn_showOthersActionPerformed

    private void btn_friendRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_friendRequestsActionPerformed
        this.cardLayout.show(Panel_container, "friendRequestPanel");
        this.friendRequestPanel.getFreindRequest();
    }//GEN-LAST:event_btn_friendRequestsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //HeaderDTO header = new HeaderDTO();
        itemDTO = new ShowItemDTO();
        itemDTO.category = 1;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);

        //header.tag = TagType.show_items;
        //this.connection = connection;
        //login =this;
        //connection.sendReqest(itemDTO);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_makeupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_makeupActionPerformed
        itemDTO = new ShowItemDTO();
        itemDTO.category = 4;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);

    }//GEN-LAST:event_btn_makeupActionPerformed

    private void btn_electronicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_electronicsActionPerformed
        itemDTO = new ShowItemDTO();
        itemDTO.category = 2;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);

    }//GEN-LAST:event_btn_electronicsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        itemDTO = new ShowItemDTO();
        itemDTO.category = 3;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         this.cardLayout.show(Panel_container, "profilePanel");
         this.profilePanel.ViewProfile();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         itemDTO = new ShowItemDTO();
        itemDTO.category = 5;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         itemDTO = new ShowItemDTO();
        itemDTO.category = 6;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          itemDTO = new ShowItemDTO();
        itemDTO.category = 0;
        //itemDTO.header = header;
        this.cardLayout.show(Panel_container, "itemPanel");
        this.itemPanel.sendRequest(itemDTO);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeForm(null).setVisible(true);
            }
        });
    }
    


    public void registerPanels() {

        Panel_container.add(itemPanel, "itemPanel");
        Panel_container.add(wishListPanel, "wishListPanel");
        Panel_container.add(friendListPanel, "friendListPanel");
        Panel_container.add(othersPanel, "othersPanel");
        Panel_container.add(friendRequestPanel, "friendRequestPanel");
        Panel_container.add(profilePanel, "profilePanel");

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DefaultPanel;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JPanel Panel_container;
    private javax.swing.JButton btn_electronics;
    private javax.swing.JButton btn_friendList;
    private javax.swing.JButton btn_friendRequests;
    private javax.swing.JButton btn_makeup;
    private javax.swing.JButton btn_showItems;
    public static javax.swing.JButton btn_showOthers;
    private javax.swing.JButton btn_wishList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
