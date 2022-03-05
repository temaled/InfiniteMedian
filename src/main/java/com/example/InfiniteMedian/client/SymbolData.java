package com.example.InfiniteMedian.client;

public class SymbolData {
    String name;
    int numberOfData;
    double median;
    double latestPrice;
    double highestPrice;
    double lowestPrice;

    public SymbolData(String name, int numberOfData, double median, double latestPrice){
        this.name = name;
        this.numberOfData =numberOfData;
        this.median = median;
        this.latestPrice = latestPrice;
        this.highestPrice = latestPrice;
        this.lowestPrice = latestPrice;
    }

    public SymbolData(){

    }
}
