# Apache Spark


## Introduction
The main focus of this project is to get familiar with Apache Spark basic opearations. Resilent distributed dataset (RDD), is a collection of element partitioned on mutiple nodes of the cluster that can be opearate in parallel.The main application will load sales transactions which is stored in postgres database and passes to a servlet to perform RDD transformations or actions base on parameters that user provide. 

## How To Build And Run Server.java application
### Build Command
> mvn clean compile package
### Run Command
> mvn exec:java
## Docker Container Basic Commands
### Build Command
> sudo docker build -t mydata .
### Run Commmnd
> sudo docker run --name mydata --rm -d -p 5432:5432 mydata
### Kill Command
> sudo docker kill mydata
### Status Command
> sudo docker ps

## How To Access SQL Database
> sudo docker exec -it mydata psql -U mydata
### Read Infomation In Transaction Table
> select * from transaction;

## Example Of How To Operate The Application
### Example 1: Inquiry Sales Transaction For Each Country Made
#### Expected Input:
> http://localhost:8080/spark_p1/csv?filter=false&&country
#### Expected Ouput:
    (Australia,38)

    (Jersey,1)

    (Brazil,5)

    (Belgium,8)

    (Canada,76)

    (Japan,2)
    .
    .
    .
### Example 2: Inquiry Sales Transaction For Each Name Made
#### Expected Input:
> http://localhost:8080/spark_p1/csv?filter=false&&name
#### Expected Ouput:
    (Tanya,1)

    (Heidi,4)

    (elaine,1)

    (isabelle,1)

    (Ulrika,1)

    (Alexis,2)
    .
    .
    .

### Example 3: Inquiry All Sales Transaction Data For A Specific Country 'Japan'
#### Expected Input:
> http://localhost:8080/spark_p1/csv?filter=true&&country=Japan
#### Expected Ouput:
    Filter 
    1/19/09 3:25,Product1,1200,Visa,Christy,Setagaya,Tokyo,Japan,1/10/06 4:49,2/14/09 1:03,35.6333333,139.65

    1/26/09 22:45,Product1,1200,Amex,Anjan,Tokyo,Tokyo,Japan,5/15/08 19:33,2/25/09 17:34,35.685,139.7513889
### Example 4: Inquiry All Sales Transaction Data For A Specific Name 'Kate'
#### Expected Input:
> http://localhost:8080/spark_p1/csv?filter=true&&name=Kate
#### Expected Ouput:
    Filter 
    1/14/09 6:32,Product1,1200,Visa,Kate,Broadlands ,VA,United States,4/12/06 6:14,1/18/09 14:04,39.04361,-77.48778

    1/28/09 1:38,Product1,1200,Mastercard,Kate,Stockholm,Stockholm,Sweden,4/16/07 12:58,1/30/09 9:18,59.3333333,18.05

    1/22/09 6:51,Product1,1200,Visa,Kate,Rochester Hills ,MI,United States,1/22/09 5:54,2/12/09 13:01,42.68056,-83.13389
