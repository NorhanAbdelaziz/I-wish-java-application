package com.ui.server;

import com.DTOs.*;
import com.db.*;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream pos;
    Socket sc;
    static Vector<ClientHandler> vector = new Vector<>();

    public ClientHandler(Socket sc) {

        try {
            this.sc = sc;
            dis = new DataInputStream(this.sc.getInputStream());
            pos = new PrintStream(this.sc.getOutputStream());
            ClientHandler.vector.add(this);
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        HeaderDTO header = new HeaderDTO();
        User userdb = new User(); // user db 

        try {
            while (true) {
                String str = dis.readLine();
                UserDTO userObj;
                HeaderMapDTO data = new Gson().fromJson(str, HeaderMapDTO.class);
                if (data.header == null) {
                    data.header = new Gson().fromJson(str, HeaderDTO.class);
                    header = data.header;
                }

                switch (data.header.tag) {
                    case signup:
                        userObj = new Gson().fromJson(str, UserDTO.class);

                        userObj.id = userdb.insert(userObj);
                        header.toId = userObj.id;
                        header.tag = TagType.signup;
                        header.actionType = TagType.success;
                        userObj.header = header;
                        sendMessageToAll(Helper.convertToJson(userObj));
                        break;
                    case login:
                        userObj = new Gson().fromJson(str, UserDTO.class);
                        if (userdb.checkUserExist(userObj.userName, userObj.password)) {
                            userObj = userdb.getByUserNamePass(userObj.userName, userObj.password);
                            if (userObj != null && userObj.id > 0) {
                                header.toId = userObj.id;
                                header.tag = TagType.login;
                                header.actionType = TagType.success;
                            }
                            userObj.header = header;

                            sendMessageToAll(Helper.convertToJson(userObj));
                        } else {
                            header.toId = userObj.id;
                            header.tag = TagType.login;
                            header.actionType = TagType.fail;
                            userObj.header = header;
                            sendMessageToAll(Helper.convertToJson(userObj));
                        }

                        break;

                    case friend_requests:
                        FriendListDTO friendListDTO = new FriendListDTO();
                        HeaderDTO headerDTO = new Gson().fromJson(str, HeaderDTO.class);

                        User userDb = new User();

                        friendListDTO.header = new HeaderDTO();
                        friendListDTO.header.toId = headerDTO.fromId;
                        friendListDTO.header.tag = TagType.friend_requests;
                        friendListDTO.header.actionType = TagType.success;

                        friendListDTO.usersList = userDb.getFriendRequestsById(headerDTO.fromId);

                        sendMessageToAll(Helper.convertToJson(friendListDTO));
                        break;

                    case accept_friend:
                        FriendRquestDTO reqDTO = new Gson().fromJson(str, FriendRquestDTO.class);
                        userdb.updateFriendRequestStatus(reqDTO.requestOwnerId, reqDTO.header.fromId, reqDTO.status);

                        headerDTO = new HeaderDTO();
                        headerDTO.tag = TagType.accept_friend;
                        headerDTO.toId = reqDTO.header.fromId;
                        headerDTO.actionType = TagType.success;
                        HeaderMapDTO headerMapDTO = new HeaderMapDTO();
                        headerMapDTO.header = headerDTO;
                        sendMessageToAll(Helper.convertToJson(headerMapDTO));

                        break;

                    case add_friend:
                        AddFriendDTO friendObj = new Gson().fromJson(str, AddFriendDTO.class);

                        User user = new User();
                        int flag2 = user.addFriend(friendObj.header.fromId, friendObj.header.toId, friendObj.search_email);

                        HeaderDTO hdto = new HeaderDTO();
                        hdto.toId = friendObj.header.fromId;
                        hdto.tag = TagType.add_friend;
                        hdto.flag = flag2;

                        sendMessageToAll(Helper.convertToJson(hdto));

                        break;
                    case remove_friend:

                        RemoveFriendDTO removeObj = new Gson().fromJson(str, RemoveFriendDTO.class);
                        User rmvUser = new User();
                        rmvUser.removeFriend(removeObj.header.fromId, removeObj.Remove_id);

                        HeaderDTO rmvDTO = new HeaderDTO();
                        rmvDTO.tag = TagType.remove_friend;
                        rmvDTO.toId = removeObj.header.fromId;
                        sendMessageToAll(Helper.convertToJson(rmvDTO));

                        break;

                    case show_items:
                        Item itemObj = new Item();
                        ShowItemDTO showObj = new Gson().fromJson(str, ShowItemDTO.class);
                        HeaderDTO itemDTO = new HeaderDTO();
                        itemDTO.category = showObj.category;
                        itemDTO.tag = TagType.show_items;
                        showObj.listOfItems = itemObj.getAllItems(showObj.category);
                        showObj.header = itemDTO;
                        sendMessageToAll(Helper.convertToJson(showObj));

                        break;

                    case show_fashion:
                        Item fashionObj = new Item();
                        ShowItemDTO showfObj = new ShowItemDTO();
                        HeaderDTO fashionDTO = new HeaderDTO();
                        fashionDTO.tag = TagType.show_items;
                        showfObj.listOfItems = fashionObj.getFashion();
                        showfObj.header = fashionDTO;
                        sendMessageToAll(Helper.convertToJson(showfObj));

                        break;

                    case show_wish_list:
                        Item witemObj = new Item();
                        MyWishListDTO wishObj = new Gson().fromJson(str, MyWishListDTO.class);
                        HeaderDTO wishDTO = new HeaderDTO();
                        wishDTO.tag = TagType.show_wish_list;
                        wishObj.listOfWishes = witemObj.myWishList(wishObj.header.fromId);
                        wishDTO.toId = wishObj.header.fromId;
                        wishObj.header = wishDTO;

                        sendMessageToAll(Helper.convertToJson(wishObj));
                        break;

                    case add_to_wish_list:
                        Item addeditemObj = new Item();
                        AddItemDTO additem = new Gson().fromJson(str, AddItemDTO.class);
                        int flag3 = addeditemObj.insert_wish(additem.header.fromId, additem.item_id);

                        HeaderDTO addeditemDTO = new HeaderDTO();
                        addeditemDTO.tag = TagType.add_to_wish_list;
                        addeditemDTO.toId = additem.header.fromId;
                        addeditemDTO.flag = flag3;
                        additem.header = addeditemDTO;
                        sendMessageToAll(Helper.convertToJson(additem));
                        break;

                    case show_friend_list:
                        userDb = new User();
                        FriendListDTO friendlist = new FriendListDTO();
                        friendlist.usersList = userDb.getFriendListById(header.fromId);
                        int temp = data.header.fromId;
                        header.toId = temp;
                        friendlist.header = header;

                        sendMessageToAll(Helper.convertToJson(friendlist));
                        break;

                    case show_friend_wishList:
                        Item item = new Item();
                        Show_friend_wish_listDTO showFriendObj = new Gson().fromJson(str, Show_friend_wish_listDTO.class);
                        HeaderDTO dTO = new HeaderDTO();

                        dTO.tag = TagType.show_friend_wishList;
                        dTO.toId = showFriendObj.header.fromId;

                        showFriendObj.listOfFriendWishes = item.myWishList(showFriendObj.friend_list_id);

                        showFriendObj.header = dTO;
                        sendMessageToAll(Helper.convertToJson(showFriendObj));
                        break;

                    case remove_from_wish_list:
                        RemoveItemDTO remObj = new Gson().fromJson(str, RemoveItemDTO.class);
                        Item rmvitem = new Item();
                        rmvitem.removeItem(remObj.header.fromId, remObj.item_id);
                        HeaderDTO remDTO = new HeaderDTO();
                        remDTO.tag = TagType.remove_from_wish_list;
                        remDTO.toId = remObj.header.fromId;
                        HeaderMapDTO headerMapDTO2 = new HeaderMapDTO();
                        headerMapDTO2.header = remDTO;
                        sendMessageToAll(Helper.convertToJson(headerMapDTO2));
                        break;

                    case contribute:
                        ContributeDTO contObj = new Gson().fromJson(str, ContributeDTO.class);
                        Item contitem = new Item();
                        int flag1 = contitem.contribute(contObj.item_id, contObj.header.fromId, contObj.friend_id, contObj.paid_price);

                        HeaderDTO contDTO = new HeaderDTO();
                        contDTO.flag = flag1;
                        contDTO.tag = TagType.contribute;
                        contDTO.toId = contObj.header.fromId;
                        HeaderMapDTO headerMapDTO3 = new HeaderMapDTO();
                        headerMapDTO3.header = contDTO;
                        sendMessageToAll(Helper.convertToJson(headerMapDTO3));

                        // send notification on completing item price
                        if (contitem.isItemCompleted(contObj.friend_id, contObj.item_id)) {
                            NotificationListDTO notifcationDTO = new NotificationListDTO();

                            contDTO.toId = contObj.friend_id;
                            contDTO.msg = contitem.getItemById(contObj.item_id, contObj.friend_id);
                            notifcationDTO.header = contDTO;
                            contDTO.tag = TagType.complete_Notification;
                            sendMessageToAll(Helper.convertToJson(notifcationDTO));

                            contDTO.tag = TagType.contribute_notification;
                            notifcationDTO.notificationsList = contitem.getContributeNotifcation().notificationsList;
                            sendMessageToAll(Helper.convertToJson(notifcationDTO));

                        }
                        break;
                        
                    case recharge:
                        RechargeDTO rchDTO = new Gson().fromJson(str, RechargeDTO.class);
                       
                        userdb.recharging(rchDTO.header.fromId, rchDTO.wallet);
                        System.out.println(rchDTO.header.fromId+""+ rchDTO.wallet);
                        headerDTO = new HeaderDTO();
                        headerDTO.toId = rchDTO.header.fromId;
                        headerDTO.tag = TagType.recharge;
                        
                        UserDTO userr = userdb.getByUserNamePass(rchDTO.username, rchDTO.password);
                        userr.header = headerDTO;
                        HeaderMapDTO headermDTO = new HeaderMapDTO();
                        headermDTO.header = headerDTO;
                        sendMessageToAll(Helper.convertToJson(userr));
                        break;
                        

                }
            }
        } catch (SocketException ex) {
            pos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessageToAll(String msg) {
        for (ClientHandler ch : vector) {
            ch.pos.println(msg);
            ch.pos.flush();
        }
        System.out.println("msg from server to client : " + msg);
    }

}
