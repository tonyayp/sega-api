# Sega interview task

## Run and test
- inspect code in github, written in Java Springboot https://github.com/tonyayp/sega-api/tree/master/src/main/java/com/han/sega
- application already deployed and running on AWS EC2 and RDS
- api can be tested with e.g. Postman

    ```
    GET
    http://segainterviewtask-env.eba-ckxdk42m.us-west-1.elasticbeanstalk.com/api/list
    
    
    POST
    http://segainterviewtask-env.eba-ckxdk42m.us-west-1.elasticbeanstalk.com/api/add  
    
    with Body - raw - JSON
    {
        "userId":"1234567891234567890", 
        "product":"new product 14",
        "amount":"12.34"
    }
    ```
    
## Design requirement

create two api endpoints

    GET /api/list
    list all entries in mysql database
    return json serialised GameTransaction entity with id, userId, transactionId, product, amount
    [
        {"id":"1","userId":"1234567891234567890","transactionId":"93424599-f977-40b8-a851-146eeff04334","product":"new product 1","amount":1.23},
        {"id":"2","userId":"1234567891234567890","transactionId":"cd34be24-30ee-4bce-baf8-1bba77e7bc36","product":"new product 13","amount":13.32},
        {"id":"3","userId":"1234567891234567890","transactionId":"c24df7d9-9b8d-493e-a46c-e879c207ae42","product":"new product 14","amount":14.32}
    ]
    
    
    POST /api/add
    input json to save record to mysql persistence database
    json format, basic requirement userId 19 digits, amount in 2 decimals
    {
        "userId":"1234567891234567890", 
        "product":"new product 14",
        "amount":"12.34"
    }


## Note

As a springboot project, there are many things still not properly considered in this quick deployment.
For example, the basic authentication and security of accessing the api and database is not properly done.

Lack of overall design for example like a common module that handles all return format, or all business exception cases.

Also no use of microservices and RPC due to such a short exercise task. No need to add extra complexity.

This is only a very simple (and limited) demonstration of Springboot RESTful API structure with controller - service - mapper design. And, simple deployment of Springboot on AWS EC2, with the database MySQL running on AWS RDS.
