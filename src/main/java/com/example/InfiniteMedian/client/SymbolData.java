package com.example.InfiniteMedian.client;

/**
 * Class to hold data about Binance trade symbols
 */
public class SymbolData {
    /**
     * Name of the symbol
     */
    String name;
    /**
     * Holds the number of data received so far from the Binance streaming API
     */
    int numberOfData;
    /**
     * Holds the latest calculated median for a particular symbol
     */
    double median;
    /**
     * Holds the latest price received from the Binance steraming API
     */
    double latestPrice;
    /**
     * Holds the highest price received so far from the
     * Binance streaming API for a particular symbol
     */
    double highestPrice;
    /**
     * Holds the lowest price received so far from the
     * Binance streaming API for a particular symbol
     */
    double lowestPrice;

    /**
     * Creates a new SymbolData object
     * The {@link SymbolData#highestPrice} and {@link SymbolData#lowestPrice}
     * are created the same as the {@link SymbolData#latestPrice} because
     * a symbol has to be created only once, afterward its info will be updated
     * from {@link Globals#consumeData(String)}
     * @param name Name of the symbol
     * @param numberOfData Number of data received so far for this particular symbol
     * @param median Latest median info
     * @param latestPrice Latest price
     */
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
