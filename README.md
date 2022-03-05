# Infinite Median

A simple java code that calculates median of stream of data from [Binance](https://www.binance.com/) streaming [API](wss://stream.binance.com:9443)


## Run App

```bash
git clone keybase://team/temaled_yisfa/temaled_yisfa

cd InfiniteMedian

./mvnw spring-boot:run
```

## REST API

The REST API with examples

### Get list of supported trade symbols

#### Request

`GET /`

    curl -i -H 'Accept: application/json' http://localhost:8080

#### Sample Response

    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 92
    Date: Sat, 05 Mar 2022 07:06:48 GMT

```json
["ethbtc","ltcbtc","bnbbtc","neobtc","qtumeth","eoseth","snteth","bnteth","bccbtc","gasbtc"]
```
##
### Get latest info about a particular symbol

#### Request

`GET /btcusdt`

    curl -i -H 'Accept: application/json' http://localhost:8080/btcusdt

#### Sample Response

    HTTP/1.1 200 
    Content-Type: application/json
    Content-Length: 101
    Date: Sat, 05 Mar 2022 07:12:12 GMT


```json
{"symbol": "btcusdt","latestPrice": "39064.16","median": "39013.255000000005","numberOfData": "4256"}
```