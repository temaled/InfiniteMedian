package com.example.InfiniteMedian.server;

import com.example.InfiniteMedian.client.Globals;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RequestMapping("/")
@RestController
public class TradeDataServer {
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
