Documentation for Java-based Backend Application using REST
Overview
This documentation provides an outline for a Java-based backend application using RESTful web services.
The application manages a list of stocks with various endpoints to perform CRUD operations.
The list of stocks is created in memory on application startup.


Stock Object
The Stock object contains the following fields:

id: (Number) Unique identifier for the stock.
name: (String) Name of the stock.
currentPrice: (Double) Current price of the stock.
createDate: (Timestamp) The date and time when the stock was created.
lastUpdate: (Timestamp) The date and time when the stock was last updated.


Endpoints
1. GET /api/stocks
Description: Retrieve a list of all stocks.

Request Method: GET
URL: /api/stocks
Response:
Status: 200 OK
Body: JSON array of stock objects

Request:
GET /api/stocks
Host: localhost:8092
Example Response:

json
Copy code
[
    {
        "id": 1,
        "name": "Stock A",
        "currentPrice": 150.00,
        "createDate": "2024-07-18T18:00:00.000+00:00",
        "lastUpdate": "2024-07-18T18:25:39.970+00:00"
    },
    {
        "id": 2,
        "name": "Stock B",
        "currentPrice": 250.00,
        "createDate": "2024-07-18T18:00:00.000+00:00",
        "lastUpdate": "2024-07-18T18:25:39.970+00:00"
    }
]
2. GET /api/stocks/{id}
Description: Retrieve a single stock by its ID.

Request Method: GET
URL: /api/stocks/{id}
Path Variables:
id (Number): The ID of the stock to retrieve.
Response:
Status: 200 OK
Body: JSON object of the stock

GET /api/stocks/1
Host: localhost:8080
Example Response:

json
Copy code
{
    "id": 1,
    "name": "Stock A",
    "currentPrice": 150.00,
    "createDate": "2024-07-18T18:00:00.000+00:00",
    "lastUpdate": "2024-07-18T18:25:39.970+00:00"
}
3. PUT /api/stocks/{id}
Description: Update the price of a single stock.

Request Method: PUT
URL: /api/stocks/{id}
Path Variables:
id (Number): The ID of the stock to update.
Request Body: JSON object with the updated currentPrice field.
Response:
Status: 200 OK
Body: JSON object of the updated stock
Example Request:

http
Copy code
PUT /api/stocks/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "currentPrice": 175.00
}
Response:

json
Copy code
{
    "id": 1,
    "name": "Stock A",
    "currentPrice": 175.00,
    "createDate": "2024-07-18T18:00:00.000+00:00",
    "lastUpdate": "2024-07-18T18:30:00.000+00:00"
}
4. POST /api/stocks
Description: Create a new stock.

Request Method: POST
URL: /api/stocks
Request Body: JSON object with name and currentPrice fields.
Response:
Status: 201 Created
Body: JSON object of the created stock
Request:
POST /api/stocks
Host: localhost:8092
Content-Type: application/json

{
    "name": "Stock C",
    "currentPrice": 300.00
}
Response:

json
Copy code
{
    "id": 3,
    "name": "Stock C",
    "currentPrice": 300.00,
    "createDate": "2024-07-18T18:30:00.000+00:00",
    "lastUpdate": "2024-07-18T18:30:00.000+00:00"
}
In-Memory Data Initialization
The list of stocks should be created in memory when the application starts. Here is a simple example of how to initialize the data in memory using a Spring Boot application:

java
Copy code
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class StockApplication {

    private final List<Stock> stocks = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            stocks.add(new Stock(counter.getAndIncrement(), "Stock A", 150.00, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())));
            stocks.add(new Stock(counter.getAndIncrement(), "Stock B", 250.00, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())));
        };
    }

    // Add your REST controllers here
}
REST Controllers
Here are the REST controllers to handle the endpoints:

java
Copy code
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final List<Stock> stocks;
    private final AtomicInteger counter;

    public StockController(List<Stock> stocks, AtomicInteger counter) {
        this.stocks = stocks;
        this.counter = counter;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stocks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id) {
        Optional<Stock> stock = stocks.stream().filter(s -> s.getId() == id).findFirst();
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStockPrice(@PathVariable int id, @RequestBody Stock updatedStock) {
        Optional<Stock> stockOpt = stocks.stream().filter(s -> s.getId() == id).findFirst();
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            stock.setCurrentPrice(updatedStock.getCurrentPrice());
            stock.setLastUpdate(Timestamp.from(Instant.now()));
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock newStock) {
        Stock stock = new Stock(counter.getAndIncrement(), newStock.getName(), newStock.getCurrentPrice(), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        stocks.add(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(stock);
    }
}
Stock Class
Here is a simple implementation of the Stock class:

java
Copy code
public class Stock {

    private int id;
    private String name;
    private double currentPrice;
    private Timestamp createDate;
    private Timestamp lastUpdate;

    public Stock(int id, String name, double currentPrice, Timestamp createDate, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }










