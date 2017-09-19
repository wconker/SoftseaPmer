package com.android.softsea.newwork;

import android.util.Log;

import com.android.softsea.callback.MessageCallBack;
import com.android.softsea.utils.SharedPrefsUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

import static okhttp3.ws.WebSocket.TEXT;

/**
 * Created by conker on 2017/8/31.
 */

public class HttpCenter {
    public static OkHttpClient okHttpClient;
    public static WebSocket webSocket = null;
    public static MessageCallBack messageCallBack;

    public static synchronized void InstancesOkhttp() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder().
                    readTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(3000, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
            if (webSocket == null) {
                initWebsocket();
            }
        }
    }

    public void setCallBack(MessageCallBack callBack) {
        this.messageCallBack = callBack;
    }

    public void send(String str) {
        final String m = str;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (HttpCenter.webSocket != null)
                        HttpCenter.webSocket.sendMessage(RequestBody.create(TEXT, m));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void initWebsocket() {
        String url = "ws://180.153.88.58:5699";
        final Request request = new Request.Builder()
                .url(url).build();
        WebSocketCall webSocketCall = WebSocketCall.create(okHttpClient, request);
        webSocketCall.enqueue(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                HttpCenter.webSocket = webSocket;

            }

            @Override
            public void onFailure(IOException e, Response response) {
            }

            @Override
            public void onMessage(ResponseBody message) throws IOException {
                String msg = message.string();
                if (messageCallBack == null) {
                } else {
                    messageCallBack.onMessage(msg);
                }
            }

            @Override
            public void onPong(Buffer payload) {

            }

            @Override
            public void onClose(int code, String reason) {
                System.out.println("MESSAGE: onClose" + code);
                initWebsocket();
            }
        });
    }
}
