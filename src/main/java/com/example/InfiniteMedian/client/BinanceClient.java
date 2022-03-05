package com.example.InfiniteMedian.client;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BinanceClient {

    /**
     *
     * @return List of symbols supported by binance
     * @throws IOException
     */
    List<String> fetchSymbolList() throws IOException {
        System.out.println("Fetching supported symbols list . . .\n");
        URL url = new URL("https://api.binance.com/api/v3/exchangeInfo");
        URLConnection yc = url.openConnection();
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

        for (Object symbol: symbols){
            symbolNameList.add(((String) ((JSONObject) symbol).get("symbol")).toLowerCase());
        }
        return symbolNameList;
    }

    public void startBinanceClient() throws IOException, URISyntaxException {


        List<String> supportedSymbols = fetchSymbolList();
        Globals.supportedSymbols = new JSONArray(supportedSymbols);
        System.out.println("Supported symbols list fetched!\n");

        String uriBase = "wss://stream.binance.com:9443/stream?streams=";
        List<String> uriList = new ArrayList<>();



        String tmpUri = uriBase;
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
