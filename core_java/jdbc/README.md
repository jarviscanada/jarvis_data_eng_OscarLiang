# Introduction
The purpose of the Java JDBC app is to learn about the SQL libraries available in Java. It uses a 
course taken from Lynda that creates an example database which is then modified and queried for 
information. The database models a company's sales: the items ordered, the customer, and the 
salesperson.

# ER Diagram
![ER diagram](./assets/er_diagram.png)

# Design Patterns
This database uses the Data Access Object, or DAO, design pattern. This design pattern is often 
contrasted with the Repository design pattern. The Repository pattern allows each class to only 
access one table, while the code handles joins. The DAO pattern allows one class to access all 
tables and lets the DBMS handle joins. 

This example database is simple, and the choice of design pattern used here would likely have very 
little effect on the speed of queries. For larger databases with different structures, the choice of 
design pattern can be crucial in improving the performance of the database.

For example, the use of distributed databases makes the Repository pattern more effective. 
Distributed databases prefer to scale horizontally rather than vertically, but joins must be done on
a single database, the processing power of which may bottleneck the application. 

Alternatively, if the data is normalized (i.e. the tables are in third normal form), then the joins 
will be simple; since DBMS applications are typically more efficient in performing joins than 
general purpose programming languages, simple joins may be better handled in the database.
