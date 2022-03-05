
package com.example.InfiniteMedian;
import com.example.InfiniteMedian.client.BinanceClient;
import com.example.InfiniteMedian.server.TradeDataServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@RestController
public class InfiniteMedianApplication {


	public static void main(String[] args) throws IOException, URISyntaxException {
		new BinanceClient().startBinanceClient();
		System.out.println("Starting trade data server . . .\n");
		SpringApplication.run(TradeDataServer.class, args);
		System.out.println("Trade data server started . . .\n");
	}

}
