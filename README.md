# ChatRoomBoot
A chat room demo based on Spring Boot STOMP support using in-memory broker and Websocket

I present here a chat room demo based on the Spring Boot Websocket support of STOMP protocol.

Different chat groups are supported. Users subscribed to a given group can exchange messages with other subscribers of this group only.

A basic HTTP authentication is provided, supported by a MySql user database. Any new user can create a new account.

An internationalization support is provided for all service messages such as connection/disconnection notification.
Thymeleaf is used for internationalization and for passing variables to javascript. 

The actual deployment URL is set in the application.properties file by the custom variable:
myapp.chatroom.url=//www.dominique-ubersfeld.com:8080/chat-room/chat

The users database itself is created and populated by the SQL source file:

chatBootDBAuthData.sql

The IDE used for this design was STS.
        



Dominique Ubersfeld, Cachan    
