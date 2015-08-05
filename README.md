#IO Tags Server

Input Output Tags Server. An useful implementation to  kick off a SCADA system.

##Motivation

   I try to develop a standarized way to persist data from sensors. It could be useful to start to implementing a SCADA system. I hope that it could be useful for everyone.


##How it works??
 
 This server it consist in **three** main parts: **Persistence and Queries**, **Tag Configuration** and **Acquisition**. 
 
###Persistence and Queries
 
 I build a simple JPA schema to save and it could be expanded to fix your needs:

 + **User:** Information about a user
 + **Session:** A set of tags to be acquired. It could be associated to zero or more users.
 + **Tags:** An input or output point.
 + **XXX_data:** Tag acquired data.
 
 In addition, I have generated the following queries help kickoff fast in your SCADA system:   

###Tag Configuration

 Connect with a acquisition and integrate with our system could be sometime something annoying. Thanks many people that collaborate to make easier and happier to other we could simplify communicate with acquisition system. My contribution is to try simplifies integration with the rest of software adding API to just configure them. Some protocols integrated are:

 + MODBUS
 + DCON
 + Serial Communication
 + ZigBee

##Acquisition
 Implement ways to schedule tag acquisition or receive data from tags. 

## How to use
I used [JPA standard](https://en.wikipedia.org/wiki/Java_Persistence_API), so it should be easy setup it, change provider or RDBMS if you know how to use this standard. 

The next are some tips to setup it and get work it:

1.- Install [MySQL](https://www.mysql.com)

2.- Create a database, a user and grant permissions to him. If you don't want used default values you can change **META-INF/persistence.xml**.

     mysql> CREATE DATABASE jpa_test;
     mysql> CREATE USER 'jpa_user'@'localhost' IDENTIFIED BY 'jpa_pass'
     mysql> GRANT ALL ON jpa_test.* to 'jpa_user'@'localhost'
     
3.- Import to eclipse an run class **com.molavec.XXX**.

**Note:** In persistence.xml file I added the next properties to create tables and you should comment it or delete it after the first run: 
     
	  <property name="eclipselink.ddl-generation" value="drop-and-create-tables" />
      <property name="eclipselink.ddl-generation.output-mode" value="both" />
 

colocar como  crear una base de datos en mysql y añadir permisos de usuario
donde cambiar datos de 

## MYSQL Tune Up:
Add **View Object** to Join **session** data:
