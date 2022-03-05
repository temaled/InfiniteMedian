package com.example.InfiniteMedian.client;

import org.json.JSONObject;

import java.util.*;

public class SocketServiceData {

//    public void consumeData(String message){
//        JSONObject newData = new JSONObject(message).getJSONObject("data");
//        String symbolName = newData.get("s").toString().toLowerCase();
//        if (Globals.medianData.containsKey(symbolName)){
//            SymbolData data =  Globals.medianData.get(symbolName);
//            double newPrice = newData.getDouble("p");
//            if (newPrice < data.lowestPrice){
//                data.lowestPrice = newPrice;
//            }else if (newPrice > data.highestPrice){
//                data.highestPrice = newPrice;
//            }
//            data.latestPrice = newPrice;
//            data.median = (data.highestPrice + data.lowestPrice)/2;
//            data.numberOfData++;
//
//        }else{
//            Globals.medianData.put(symbolName, new SymbolData(symbolName, 1, newData.getDouble("p"), newData.getDouble("p") ));
//        }
//    }

    String URI;
    String lastMessage;
    int statusCode;
}
