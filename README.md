# SalonCoiffure
 

### creation of project by
https://start.spring.io/


# instalatins and needs of project
- Java version 11       at: https://www.oracle.com/ch-de/java/technologies/javase/jdk11-archive-downloads.html
- Wampsever 64bit (will install MySQL server)         at: https://www.wampserver.com/en/download-wampserver-64bits/
- Vs Code       at: https://code.visualstudio.com/download


# instalatin in VS code Extension
- Language Support for Java(TM)
- Extension Pack for Java
- Maven for Java
- Spring Boot Extension Pack
- XML



### How to Run---------------------------------------------------------------------------

# To create the database 
1) Run this in MySQL Command Line: 
        create database coiffure-data
OR 

2) In internet browser go to: 
        http://localhost/phpmyadmin/index.php?route=/server/databases
    and create a database whit the name of "coiffure-data"  

# ALTER USER 'root'@'localhost' IDENTIFIED BY 'NewPassword';



# To run
Right click on "SalonCoiffureApplication.java" and choose "Run java"


# To open in browser after run
http://localhost:8888/





id              Long
first_name      String
last_name       String
mobile          String
password        String
email           String
enabled         Boolean
locked          Boolean
role            Role
created_at      LocalDateTime
updated_at      LocalDateTime