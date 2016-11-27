# sales-tax [![Build Status](https://travis-ci.org/xelamanster/sales-tax.svg?branch=master)](https://travis-ci.org/xelamanster/sales-tax)

Based on Play Framework, using Scala. For build uses Sbt.
Application assumes that requests contains js array with sales.

Controller converts Json from the request to the Seq[SaleItem], then creates Receipt containing items with tax,
computed for each item (tax for each unit is rounded up to the nearest 0.05 amount). And finally sends back respond with full tax
value from the Receipt. Also uses BigDecimal for prices and taxes with purpose to save constant precision in results.

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