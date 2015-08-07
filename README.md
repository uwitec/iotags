#IO Tags Server
Input Output Tags Service. Generic service to kick off a [SCADA](https://en.wikipedia.org/wiki/SCADA) system.

##Motivation
Develop a "standarized" service to interconnect **[IoT](https://en.wikipedia.org/wiki/Internet_of_Things) Standards Communication Protocols**, **Persist** and **Present** Layers. This project is inspired in how works proxies used by commercials HMIs (OPC Server for example), but as cloud server.

##How it works??
This service consists in **three** main parts (Layers): **Persistence and Queries**, **Tag Configuration** and **IoT Communication Protocols**.

###Persistence and Queries
Layer dedicated to persist data. Here are some options:

 **RDMS (not ready):**
 I build a simple JPA schema to save and it could be expanded to fix your needs:
 + **User:** Information about a user
 + **Session:** A set of tags to be acquired. It could be associated to zero or more users.
 + **Tags:** An input or output point.
 + **XXX_data:** Tag acquired data.

 In addition, I have generated the following queries help kickoff fast in your SCADA system:
  + (not added yet)

**NoSQL (not ready):**
(not implemented yet)
 + PouchDB (for mobile)??
 + CouchDB or MongoDB (forCloud)??

**File (not ready)**:
 + Some Apache CSV or ODS implementation??

###Tag Configuration
Layer consist in configure different remote points (Sensors, AI, AO, DI, DO), Tags user privileges and Tag Communication Protocol.

###IoT Communication Protocols
Layer that **uses owner and third party Standard Protocols to communicate with [RTUs](https://en.wikipedia.org/wiki/Remote_Terminal_Unit) and [HMI](https://en.wikipedia.org/wiki/Human%E2%80%93machine_interface) systems**. My contribution is to try simplify integration with implementations of many people that collaborate to make easier and happier to other the communicate with electronic things. Some protocols integrated are:

 + MODBUS (not integrated yet)
 + DCON (not integrated yet)
 + Serial Communication (not integrated yet)
 + ZigBee (not integrated yet)
 + MQTT (not integrated yet)


## How to use
###1.- Configure persistence layer (it shoud be more simple in future versions)

I used [JPA standard](https://en.wikipedia.org/wiki/Java_Persistence_API), so it should be easy setup, change provider or RDBMS if you know how to use this standard.
The next are some tips to setup it and get work it:
 + Install [MySQL](https://www.mysql.com)
 + Create a database, a user and grant permissions to him. If you don't want used default values you can change **META-INF/persistence.xml**.

     mysql> CREATE DATABASE jpa_test;
     mysql> CREATE USER 'jpa_user'@'localhost' IDENTIFIED BY 'jpa_pass'
     mysql> GRANT ALL ON jpa_test.* to 'jpa_user'@'localhost'

 + Import Gradle project to Eclipse an run class **com.molavec.XXX**.
**Note:** In persistence.xml file I added the next properties to create tables and you should comment it or delete it after the first run:
	  <property name="eclipselink.ddl-generation" value="drop-and-create-tables" />
    <property name="eclipselink.ddl-generation.output-mode" value="both" />

#### MYSQL Tune Up:
Add **View Object** to Join **session** data:

    (not implemented yet)

###2.- Configure Tags
(Not implemented yet)

###3.- Start IOTags Service
(Not implemented yet)
