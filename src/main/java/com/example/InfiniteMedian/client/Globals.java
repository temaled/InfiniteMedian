package com.example.InfiniteMedian.client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Globals {
    public static volatile HashMap<String, SymbolData> medianData = new HashMap<>();
    public static JSONArray supportedSymbols;

    public static JSONObject getMedianInfo(String symbolName){
        if (medianData.containsKey(symbolName.toLowerCase())){
            SymbolData target = medianData.get(symbolName.toLowerCase());

            String median = String.valueOf(target.median);
            String latestPrice = String.valueOf(target.latestPrice);
            String numberOfData = String.valueOf(target.numberOfData);
            String response =  String.format("{\"symbol\":\"%s\", \"median\":\"%s\", \"latestPrice\":\"%s\", \"numberOfData\":\"%s\"}",symbolName, median, latestPrice, numberOfData);
            return new JSONObject(response);

        }else{
            return new JSONObject();
        }
    }

    public static void consumeData(String message){
        JSONObject newData = new JSONObject(message).getJSONObject("data");
        String symbolName = newData.get("s").toString().toLowerCase();
        if (Globals.medianData.containsKey(symbolName)){
            SymbolData data =  Globals.medianData.get(symbolName);
            double newPrice = newData.getDouble("p");
            if (newPrice < data.lowestPrice){
                data.lowestPrice = newPrice;
            }else if (newPrice > data.highestPrice){
                data.highestPrice = newPrice;
            }
            data.latestPrice = newPrice;
            data.median = (data.highestPrice + data.lowestPrice)/2;
            data.numberOfData++;

        }else{
            Globals.medianData.put(symbolName, new SymbolData(symbolName, 1, newData.getDouble("p"), newData.getDouble("p") ));
        }
    }


}
