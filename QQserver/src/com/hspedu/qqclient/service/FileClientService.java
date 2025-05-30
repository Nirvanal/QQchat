package com.hspedu.qqclient.service;

/*
* 该类/对象完成 文件的传输
* */

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.*;

public class FileClientService {

    /*
    * src源文件
    * dest 把该文件传输到对方的哪个目录
    *发送用户id
    *接收用户id
    *
    * */

    public void sendFileToOne(String src,String dest,String senderId,String getterId){
        //读取src文件 --> message
        Message message=new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        //需要将文件读取

        FileInputStream fileIntputStream=null;
        byte[] fileBytes =new byte[(int)new File(src).length()];

        try {
            fileIntputStream =new FileInputStream(src);
            fileIntputStream.read(fileBytes);//将src文件读入到程序的字节数组
            //将文件对应的字节数组设置message
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            if(fileIntputStream != null){
                try {
                    fileIntputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //提示信息
        System.out.println("\n"+senderId +" 给 "+getterId +" 发送文件: "+src+" 到对方的电脑的目录 "+dest);

        //发送
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
