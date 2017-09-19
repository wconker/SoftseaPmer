package com.android.softsea.newwork;

import android.util.Log;

import com.android.softsea.callback.MessageCallBack;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.RequestBody;
import okhttp3.ws.WebSocket;

import static okhttp3.ws.WebSocket.TEXT;

/**
 * Created by softsea on 17/9/1.
 */

public class MessageCenter {

    private MessageCallBack MyMessage;
    private WebSocket socket;
    private CommandCenter commandCenter;
    private HttpCenter httpCenter;

    public MessageCenter(MessageCallBack messageCenter) {
        httpCenter = new HttpCenter();

        this.MyMessage = messageCenter;

        httpCenter.setCallBack(messageCenter);

        commandCenter = new CommandCenter();
    }


    public CommandCenter ChooseCommand() {

        if (commandCenter != null)
            return commandCenter;
        else {
            commandCenter = new CommandCenter();
            return commandCenter;
        }
    }

    public void SendYouMessage(String str) {


        Log.i("发送了", str);
        if (HttpCenter.webSocket != null) {
            socket = HttpCenter.webSocket;
        } else {
            HttpCenter.initWebsocket();
        }

        httpCenter.send(str);


    }

    private void setHttpCallBack(MessageCallBack messageCenter) {
        this.MyMessage = messageCenter;
        httpCenter.setCallBack(this.MyMessage);
    }

    public void setCallBackInterFace(MessageCallBack messageCenter) {
        setHttpCallBack(messageCenter);
    }

}
