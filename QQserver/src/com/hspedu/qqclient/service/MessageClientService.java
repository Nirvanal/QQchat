package com.hspedu.qqclient.service;

/*
* 该类/对象，提供和消息相关的服务方法
* */

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageClientService {

    /*
     * content 内容
     * senderId 发送者
     * */
    public void sendMessageToAll(String content,String senderId){
        //构建Message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//群发消息这种类型
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());//发送时间设置到message 对象
        System.out.println(senderId+" 对大家说 "+content);
        //发送给服务端

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * content 内容
    * senderId 发送用户id
    * getterId 接收用户id
    * */


    public void sendMessageToOne(String content,String senderId,String getterId){
        //构建Message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());//发送时间设置到message 对象
        System.out.println(senderId+" 对 "+getterId+" 说 "+content);
        //发送给服务端
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
