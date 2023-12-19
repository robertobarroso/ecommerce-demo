## Functionality
This program expose an endpoint to get price rate given a certain criteria. The parameters accepted are productId, brandId and date. The output is the price that matchs criteria and has the highest priority (max. value).

## Example
Given the following dataset

| BRAND_ID | START_DATE               | END_DATE                 | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|--------------------------|--------------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00     | 2020-12-31-23.59.59     | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00     | 2020-06-14-18.30.00     | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00     | 2020-06-15-11.00.00     | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00     | 2020-12-31-23.59.59     | 4          | 35455      | 1        | 38.95 | EUR  |

And those criteria
- date: 2020-06-14T10:00:00
- productId: 35455
- brandId: 1

The endpoint will retrieve the price with RATE_ID 1.

## Endpoint
```json
GET http://{server}:{port}/prices/rates?date=2020-06-14T10:00:00&productId=35455&brandId=1
```
Response
```json
{
    "id": 1,
    "productId": 35455,
    "brandId": 1,
    "rateId": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "value": "35.50",
    "currency": "EUR"
}
```
## Run
This project require Java SDK 17 installed.
Under the root directory, execute:  
Unix
```unix
mvnw spring-boot:run
```
Windows
```unix
mvnw.cmd spring-boot:run
```
## Run from Docker
```unix
docker run -p8080:8080 ghcr.io/robertobarroso/ecommerce-demo:latest
```
## Run integration tests
Execute
```unix
mvnw verify -Pfailsafe
```
