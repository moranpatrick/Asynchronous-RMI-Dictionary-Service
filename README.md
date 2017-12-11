# Asynchronous RMI Dictionary Service

## [Contents](#contents)   
* [Introduction](#intro)
* [Project Overview](#overview)             
* [References](#References)  

## Introduction<a name = "intro"></a>   
My name is [Patrick Moran](https://www.linkedin.com/in/patrick-moran-7a349014b/) and this is my 4th Year Distributed Systems Asynchronous RMI Dictionary Project.

With Distrubuted Computing asyncronous communication is a very important issue. This project takes a hands on approach, programming an asyncronous remote software service. 

[Top](#contents) 
## Project Overview<a name = "overview"></a>   
In this project we are rewuired to use the JSP/Servlet and Java RMI Frameworks to create a remote, asynchronous dictionary
lookup service. A user enters a string and this is then matched against a dictionary. The HTML form information should be dispatched to a servlet that adds the client request
to an in-queue and then returns a job ID to the web client. The web client should poll the web server periodically
(every 10 seconds) and query if the request has been processed. Client requests in the inQueue should
be periodically removed and processed (every 10 seconds). An overview of the project can be viewed below.

<img src="Screen1.png" border="3">     

[Top](#contents) 
