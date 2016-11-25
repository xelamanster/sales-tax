# sales-tax

Based on Play Framework, using Scala.

Application assumes that requests has js array with orders.


## How to test
1. Run tests:
    ```
        sbt test
    ```

2. Run application with
    ```
        sbt run
    ```
    and then execute curl script.

Query should look like this:

```
    curl --include --request POST --header "Content-type: application/json" --data @input1.json http://localhost:9000/taxcalculator
```

for testing purposes input1.json located in dist/