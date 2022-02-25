# Manufacturing Process Management System (MPMS)

## Purpose

MPMS provides end-to-end (i.e., from order reception until product delivery) manufacturing process management and orchestration of activities by:

 - modeling processes and agents
 - executing in automated way the processes by assigning activities to agents (either human or automated agents)
 - providing process monitoring for a complete status overview of the manufacturing processes

## Overview
A process application for a Manufacturing Process Management System for [SHOP4CF](https://www.shop4cf.eu/) project.
Developed by [Information Systems](https://www.tue.nl/en/research/research-groups/industrial-engineering/information-systems-ieis/) research group of [TUe](https://www.tue.nl/en/) and [European Dynamics](https://www.eurodyn.com/).

*This project has been generated as a Spring Boot application (Spring Boot version 2.4.3) embedding [Camunda Platform](https://camunda.com/products/camunda-platform/), Community Edition v7.15.0. 
(More info can be found on [Camunda Spring Boot Integration](https://docs.camunda.org/manual/7.15/user-guide/spring-boot-integration//))

## Architecture and modules
In general, MPMS includes modules for both Design and Execution phases. The overall architecture is shown below: 
![MPMS Architecture and modules](https://surfdrive.surf.nl/files/index.php/s/7H1p1Kf9VsFq6g1/download)	The steps to use the above modules are:

 - Based on your scenario requirements (process flow, list of tasks, involved agents, rules, etc.), model your processes in [BPMN 2.0](https://surfdrive.surf.nl/files/index.php/s/7H1p1Kf9VsFq6g1) notation with the **Modeler** module. Download the Modeler from [Camunda Modeler](https://camunda.com/download/modeler/).
 (Requirements and any other relevant info can also be stored in a Definitions/Business DB)
 - The **Core application** (a Spring Boot Java application) is built (in any IDE) to implement the business logic behind the process models (e.g., write the delegate code that a BPMN Service task calls). 
 The source code you get in this repository has the basic functionality, which you can adapt (as explained in the next section). 
 - When the application is deployed and running, the **Process Engine** module enacts the process models by invoking the implemented business logic. The Process Engine is started within the Spring Boot application.
 - When processes are running, three types of web applications are provided: i) *Tasklist UI* for task items for humans, ii) *Cockpit* for process status, iii) *Admin* for users configuration.

Regarding the Execution (runtime) bundles that this repository generates (as Docker containers/images), the overview is the following:![MPMS Runtime Docker bundles](https://surfdrive.surf.nl/files/index.php/s/cJwQP3MFfUNDguY/download) (PostgreSQL DBs can be changed with your DB of your preference)

With respect  to interfacing to other components, MPMS is primarily built to communicate through [FIWARE](https://www.fiware.org/). MPMS posts task assignments on FIWARE, which are eventually picked-up by device controllers, any resources, or any component who is interested in these tasks (Note that these tasks refer to ones directed to automated agents, opposed to the tasks for human agents can be directly provided in the built-in Tasklist web application). Accordingly, MPMS receives task statuses. Resource statuses and alerts are also picked-up by MPMS through FIWARE.
Of course, communication to/from MPMS can be done through the REST/JAVA API, or (HTTP) Connectors, or web services. 

 ![MPMS_interfaces](https://surfdrive.surf.nl/files/index.php/s/0hPc0IzQ1Q035jA/download)

In SHOP4CF, the chosen Context Broker component of the FIWARE platform is the [Orion-LD](https://github.com/FIWARE/context.Orion-LD), which implements the [NGSI-LD](https://en.wikipedia.org/wiki/NGSI-LD) information model and API. 

## How to adapt it?

More info on the source code structure...

[Camunda 7.15 Documentation](https://docs.camunda.org/manual/7.15/introduction/)

The structure of data exchanged between MPMS and FIWARE is based on the [SHOP4CF Data models](https://shop4cf.github.io/data-models/) (which is a SHOP4CF_project-elaboration of the generic FIWARE data models).

See examples for creating subscriptions and performing CRUD operations on
[NGSI-LD Linked Data](https://documenter.getpostman.com/view/513743/SVYjSMgh) tutorial and [NGSI-LD Operations](https://documenter.getpostman.com/view/513743/TVCb5B6F) tutorial


## How to deploy it?

**Prerequisites:**
 - Deployment requires [Docker Compose](https://docs.docker.com/compose/install/) v3.8 or above.
 - During initial installation, internet access is required in order to download:
	 - ***openjdk:11.0.13-jre-slim***  (appears in `Dockerfile`)
	 - ***image: postgres:14-alpine***  (for both `shop4cf-mpms-core-postgres` and `shop4cf-mpms-app-postgres` containers)
	 - ***image: adminer:4.8.1*** (optional image)


**Package the application and Deploy the docker services:**
 - In your IDE environment, run
	> `mvn clean package spring-boot:repackage`
 
	A **`mpms_7-15-1.0.0-SNAPSHOT.jar`**  file is generated in **`target`** folder.
	So, you basically need the following file structure: 
![docker package file explorer](https://surfdrive.surf.nl/files/index.php/s/sv1Unera5sZwOtY/download)
 - Configure parameters in **`.env`** file
	 - e.g., OrionLD CB url:port, DB ports, passwords
 - Open a terminal, navigate to the **`docker`** folder and run:
	> `docker-compose up -d --build`
![docker-compose up console](https://surfdrive.surf.nl/files/index.php/s/oXUH6icbvH6z56w/download)
 - To see the console of the *`shop4cf-mpms`* application, run:
	> `docker logs shop4cf-mpms -f`
	![camunda-platform logs console](https://surfdrive.surf.nl/files/index.php/s/iE1n0PkpUP4nGHs/download)
	 - when you see the following at the end, everything is ready (or search for the red-highlighted logs):
	 ![camunda-platform logs console ready](https://surfdrive.surf.nl/files/index.php/s/A7k5683H19F5d81/download)

## How to use it?

**Access MPMS web applications on:**
[http://\[host_address\]:\[port\]/camunda/app/welcome/default/#!/login](http://%5Bhost_address%5D:%5Bport%5D/camunda/app/welcome/default/#!/login)
![MPMS welcome](https://surfdrive.surf.nl/files/index.php/s/yynJ1lijOD7xCF1/download)

 - Configure users of the web applications on **Admin**
 - *Start Processes* or react on *Tasks* on **Tasklist**:
![MPMS Tasklist](https://surfdrive.surf.nl/files/index.php/s/ohMYfdjCTrBSoxu/download)

 - Monitor processes on **Cockpit**:
 ![MPMS Cockpit](https://surfdrive.surf.nl/files/index.php/s/4PajvrZfOwrYYY9/download)
 ![MPMS Cockpit](https://surfdrive.surf.nl/files/index.php/s/NqztPQ9G1h94xSG/download)
 - [Optional] Manage DB data on **Adminer**:
 ![MPMS Cockpit](https://surfdrive.surf.nl/files/index.php/s/RDZNATlSUFkF383/download)

## Environment Restrictions
Built and tested against Camunda Platform version 7.15.0, Spring Boot version 2.4.3.

## Known Limitations

## Improvements Backlog

## License
Provided under various open source licenses (mainly [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) and [MIT](http://opensource.org/licenses/MIT)). Third-party libraries or application servers included are distributed under their respective licenses. Full list including optional dependencies can be found on [Camunda - Third party libraries](https://docs.camunda.org/manual/7.15/introduction/third-party-libraries/).

## Version History
	 
- **Version 1.0** (10-Feb-2022)
       
    Initial version of MPMS with:
	 - Core functionality
	    - FIWARE  integration
		    - Subscriptions to entities
		    - Receive notifications from subscriptions
		    - Post entities
	    - DB integration
		    - Postgres "Process Engine" DB (for persisting engine's runtime data)
		    - Postgres "Application" DB (for storing definitions and relevant business data)

