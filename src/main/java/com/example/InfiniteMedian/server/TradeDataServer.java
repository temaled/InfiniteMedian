package com.example.InfiniteMedian.server;

import com.example.InfiniteMedian.client.Globals;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * A controller class to manage routes and serve data
 * about supported symbols and latest trade info for
 * a particular Binance trade symbol
 */
@SpringBootApplication
@RequestMapping("/")
@RestController
public class TradeDataServer {
    /**
     * Gets list of all the supported symbols
     * @return a json response containing all the supported symbols
     * @see Globals#supportedSymbols
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<String> getSupportedSymbols() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (Globals.supportedSymbols == null){
            return new ResponseEntity<>("[]", httpHeaders, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Globals.supportedSymbols.toString(), httpHeaders, HttpStatus.OK);
        }
    }

    /**
     * Gets info about the requested trade symbol
     * @param symbolName name of the trade symbols
     * @return a json response containing info about the particular symbol
     * @see Globals#getMedianInfo(String)
     */
    @RequestMapping(
            value = "/{symbolName}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity<String> getSymbolMedian(@PathVariable String symbolName) {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(Globals.getMedianInfo(symbolName).toString(), httpHeaders, HttpStatus.OK);
    }

}
