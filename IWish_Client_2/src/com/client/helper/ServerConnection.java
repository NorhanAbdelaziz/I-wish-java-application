package com.client.helper;

import com.dto.HeaderDTO;
import com.dto.HeaderMapDTO;
import com.google.gson.Gson;
import com.ui.client.ClientForm;
import com.ui.client.LoginForm;
import com.ui.client.ShowWishListForm;
import com.ui.client.SignUpForm;
import com.ui.client.WelcomeForm;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerConnection {

    public Socket server;
    public DataInputStream dis;
    public PrintStream pos;
    JOptionPane jop;
    Thread thread;

    public ServerConnection() {
        try {
            server = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(server.getInputStream());
            pos = new PrintStream(server.getOutputStream());
            handelRespnse();

        } catch (ConnectException ex) {
            jop.showMessageDialog(ClientForm.clientForm, "server is slept");
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendReqest(Object request) {
        pos.println(Helper.convertToJson(request));
    }

    public void handelRespnse() {
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                HeaderDTO header = new HeaderDTO();
                try {
                    while (true) {
                        String str = dis.readLine();

                        HeaderMapDTO data = new Gson().fromJson(str, HeaderMapDTO.class);
                        if (data == null) { //add
                            jop.showMessageDialog(ClientForm.clientForm, "server is slept");
                            System.exit(0);
                            break;
                        }
                        if (data.header == null) {
                            data.header = new Gson().fromJson(str, HeaderDTO.class);
                            header = data.header;
                        }

                        switch (data.header.tag) {

                            case signup:
                                SignUpForm.signUpForm.signUp(str);
                                break;
                            case login:
                                if (LoginForm.login != null) {
                                    LoginForm.login.loginResponse(str);
                                }
                                break;
                            case show_items:
                                if (WelcomeForm.itemPanel != null) {
                                    WelcomeForm.itemPanel.showitems(str);
                                }
                                break;
                            case show_wish_list:

                                if (WelcomeForm.wishListPanel != null) {
                                    WelcomeForm.wishListPanel.showMyWishList(str);
                                }
                                break;
                            case add_to_wish_list:

                                if (WelcomeForm.itemPanel != null) {
                                    WelcomeForm.itemPanel.addToWishList(str);
                                }
                                break;
                            case remove_from_wish_list:
                                if (WelcomeForm.wishListPanel != null) {
                                    WelcomeForm.wishListPanel.removeItem(str);
                                }
                                break;
                            case show_friend_wishList:
                                if (WelcomeForm.showWishListForm != null) {
                                    WelcomeForm.showWishListForm.showFriendWishList(str);
                                }
                                break;
                            case contribute:
                                if (WelcomeForm.showWishListForm != null) {
                                    WelcomeForm.showWishListForm.contribute(str);
                                }
                                break;
                            case show_friend_list:
                                if (WelcomeForm.friendListPanel != null) {
                                    WelcomeForm.friendListPanel.showFriendList(str);
                                }
                                break;
                            case remove_friend:
                                if (WelcomeForm.friendListPanel != null) {
                                    WelcomeForm.friendListPanel.removeFriend(str);
                                }
                                break;
                            case add_friend:
                                if (WelcomeForm.friendListPanel != null) {
                                    WelcomeForm.friendListPanel.addFriend(str);
                                }
                                break;
                            case friend_requests:
                                if (WelcomeForm.friendRequestPanel != null) {
                                    WelcomeForm.friendRequestPanel.getRequests(str);
                                }
                                break;
                            case accept_friend:

                                if (WelcomeForm.friendRequestPanel != null) {
                                    WelcomeForm.friendRequestPanel.acceptDeclineRequest(str);
                                }
                                break;
                            case contribute_notification:
                                if (WelcomeForm.othersPanel != null) {
                                    WelcomeForm.othersPanel.showNotifcation(str);
                                }
                                break;
                            case complete_Notification:
                                if (WelcomeForm.othersPanel != null) {
                                    WelcomeForm.othersPanel.showNotifcation(str);
                                }
                                break;
                            case recharge:
                                if (WelcomeForm.profilePanel != null){
                                    WelcomeForm.profilePanel.Recharg(str);
                                }
                        }
                    }
                } catch (SocketException ex) {
                    pos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        th.start();

    }

}
