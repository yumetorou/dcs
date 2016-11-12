Quick Start Guide
==========================

### Things you need to install

* JDK 1.8 or above
* Maven 3.2.0 or above
* IDE of your choice
* PostgreSQL 9.4 or above


### Building the Application

* Create **wf_dcs** database with user/password set to postgres/postgres
* Run `mvn clean install -U` on root directory

### Running the web app

* Run `mvn spring-boot:run` on root directory

### Deploy default data

* Pre-requisite : able to run the app at least once (See Running the web app section).
* Run `mvn spring-boot:run -Pinsert` on root directory to deploy default data (Liquibase)