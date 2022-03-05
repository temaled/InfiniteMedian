package com.example.InfiniteMedian.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A WebSocket client class customized for InfiniteMedian app
 */
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

    /**
     * An overridden method to run when message is received from Binance Streaming API
     * updates the {@link SocketServiceData} object attribute then
     * calls the {@link Globals#consumeData(String)} method to perform median calculation
     * on the new arrived message
     * @param message message received
     * @see Globals#consumeData(String)
     * @see SocketServiceData
     */
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
