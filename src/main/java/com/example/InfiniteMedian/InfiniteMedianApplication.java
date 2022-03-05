
package com.example.InfiniteMedian;
import com.example.InfiniteMedian.client.BinanceClient;
import com.example.InfiniteMedian.server.TradeDataServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Starts the Binance Client to initiate connection to
 * the binance API and Starts the Data Serve
 */
@SpringBootApplication
@RestController
public class InfiniteMedianApplication {


	public static void main(String[] args) throws IOException, URISyntaxException {
		// starting a Binance client to fetch supported symbols data and connect to
		// a stream for all the supported symbols
		new BinanceClient().startBinanceClient();
		System.out.println("Starting trade data server . . .\n");

		// starting SpringBoot server for the trade data
		SpringApplication.run(TradeDataServer.class, args);
		System.out.println("Trade data server started . . .\n");
	}

}
