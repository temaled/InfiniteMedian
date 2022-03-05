package com.example.InfiniteMedian.client;

import com.example.InfiniteMedian.helpers.CustomHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Globals {
    /**
     * Holds the median data for all the supported symbols
     */
    public static volatile CustomHashMap<String, SymbolData> medianData = new CustomHashMap<>();

    /**
     * Supported symbols by Binance, in turn by the App.
     * Fetched at the start of the program
     */
    public static JSONArray supportedSymbols;

    /**
     * Gets the up tod ate info about a particular info
     * @param symbolName the name of the symbol from Binance trade data
     * @return a JSON object containing the symbol name, the median, the latest price
     * and number of data used calculating the median so far
     * @see Globals#medianData
     */
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

    /**
     * Performs calculation and updates {@link Globals#medianData}
     * for a particular symbol
     * @param message message from {@link SocketClient#onMessage(String)}
     * to be consumed
     */
    public static void consumeData(String message){
        JSONObject newData = new JSONObject(message).getJSONObject("data");
        String symbolName = newData.get("s").toString().toLowerCase();
        if (Globals.medianData.containsKey(symbolName)){
            // updating info about a particular info
            // this will be executed when the 2nd or later data arrives
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
            // adding new symbol to medianData, this will be executed only once for a particular symbol
            Globals.medianData.put(symbolName, new SymbolData(symbolName, 1, newData.getDouble("p"), newData.getDouble("p") ));
        }
    }


}
