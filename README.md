# Retailer Rewards Points Calculator based on customer's Transactions

## Rest API to get customer rewards based on the customer's Id

# Assessment Defination 
    # A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
    A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).   Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

# Project Details
  - Spring Boot is used to develope the rest api application
  - The package name is structured as com.retailer.rewards
  - Error code is handled if customer does not exists.
  - Postgres database used to store the customer and transaction information.
  - Install Postgres db locally and run it. Change the db credentials in application.properties file.
  - Run the databaseScript.sql on postgres database to prepare the data.
  - Test cases is written in to test package.

# How to Run application

    1. Open the Project into eclipse of intelliJ. (IntelliJ preferred)
    2. Opne Database window and select postgres
    3. Set the configuration like datbase name, username and password.
    4. Username and password must be match with application.properties file. (Default Username/password is postgres/admin)
    5. Open postgres console in intellij/eclipse and run the script databaseScript.sql 
    6. Please check the schema and tables are creted.
    7. Once the data inseted into table please run the RewardPointsCalculatorApplication.java from  java/com/retailer/rewards folder. 
    8. Open the postman or other similar application and call the following endpoint
        # endpoint to call api

            - http://localhost:8080/customers/1/rewards
            - http://localhost:8080/customers/2/rewards
            - http://localhost:8080/customers/3/rewards
            - http://localhost:8080/customers/4/rewards
            - http://localhost:8080/customers/5/rewards
```
