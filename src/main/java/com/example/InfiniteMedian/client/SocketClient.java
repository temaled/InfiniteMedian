package com.example.InfiniteMedian.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

public class SocketClient extends WebSocketClient {
    SocketServiceData dataContext;


    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    public SocketClient(SocketServiceData dataContext) throws URISyntaxException {
        super(new URI(dataContext.URI));
        this.dataContext=dataContext;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("Opened Connection to "+dataContext.URI.substring(0,100) +". . .");
    }

    @Override
    public void onMessage(String message) {
        dataContext.lastMessage = message;
        Globals.consumeData(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed");
        dataContext.statusCode=code;
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error in connection "+ex.getMessage());
    }

}
