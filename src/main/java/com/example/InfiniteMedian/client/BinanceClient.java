package com.example.InfiniteMedian.client;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The BinanceClient connects to the Binance
 * streaming api
 * @see SocketClient
 */
public class BinanceClient {

    /**
     * Fetches all the symbols supported by Binance streaming API
     * and returns it to the caller
     * @return List of symbols supported by binance
     * @throws IOException
     */
    List<String> fetchSymbolList() throws IOException {
        System.out.println("Fetching supported symbols list . . .\n");
        URL url = new URL("https://api.binance.com/api/v3/exchangeInfo");
        URLConnection yc = url.openConnection();
        // reading input stream
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String response = "";
        while ((inputLine = in.readLine()) != null)
            response+=(inputLine);
        in.close();

        JSONObject jsonObject = new JSONObject(response);
        JSONArray symbols = (JSONArray) jsonObject.get("symbols");

        List<String> symbolNameList = new ArrayList<String>();

        //extracting just the symbol names
        for (Object symbol: symbols){
            symbolNameList.add(((String) ((JSONObject) symbol).get("symbol")).toLowerCase());
        }
        return symbolNameList;
    }

    /**
     * Generates as few URIs as possible to minimize the number of
     * sockets and starts socket connections to the Binance trade data
     * streaming API service
     * @throws IOException
     * @throws URISyntaxException
     */
    public void startBinanceClient() throws IOException, URISyntaxException {
        //fetching all supported data
        List<String> supportedSymbols = fetchSymbolList();
        // Globals.supportedSymbols for global usage
        Globals.supportedSymbols = new JSONArray(supportedSymbols);
        System.out.println("Supported symbols list fetched!\n");

        String uriBase = "wss://stream.binance.com:9443/stream?streams=";
        List<String> uriList = new ArrayList<>();

        String tmpUri = uriBase;
        /**
         * generating URI to create socket connection by constraining the
         * length to the length supported by Binance API
         *
         * The number of WebSocket connections will be the same as the number
         * of URIs generated here
         *
         * for the number of currently supported symbols Mar 4/2022 2 Socket
         * connections are enough to get streams of trade data for all of them
         */
        for (String symbol: supportedSymbols){
            if(tmpUri.length() > 16370){
                uriList.add(tmpUri.substring(0, tmpUri.length()-1));
                tmpUri = uriBase;
            }else {
                tmpUri += symbol + "@trade/";
            }
        }
        uriList.add(tmpUri.substring(0, tmpUri.length()-1));

        System.out.printf("Creating %d WebSocket Connections . . .\n", uriList.size());

        // starting socket connection for each URI
        List<SocketServiceData> socketServices = new ArrayList<>();
        for (String uri: uriList){
            SocketServiceData serviceData = new SocketServiceData();
            socketServices.add(serviceData);
            serviceData.URI = uri;
            SocketClient client = new SocketClient(serviceData);
            client.connect();
        }
    }
}
