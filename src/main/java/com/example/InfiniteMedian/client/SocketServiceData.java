package com.example.InfiniteMedian.client;

/**
 * A class to hold some socket connection data
 */
public class SocketServiceData {
    /**
     * URI for the socket connection, should not be longer than 16,400 in length
     * as supported by the Binance API service
     */
    String URI;

    /**
     * Holds the last received message from the stream for a socket connection
     */
    String lastMessage;
    int statusCode;
}
