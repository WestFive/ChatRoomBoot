package com.dub.spring.chat;


import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.dub.spring.services.ChatRoomService;

/** This ApplicationListener is used for logging only */

@Component
public class StompSubscribeEvent implements ApplicationListener<SessionSubscribeEvent> {
	 
    private static final Logger log = LoggerFactory.getLogger(StompSubscribeEvent.class);
 
	@Autowired
    private SimpMessagingTemplate template;
    
    @Autowired 
    private ChatRoomService chatRoomService;
	
    
	@Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
 
        String username =  sha.getUser().getName();
         
        log.info("Connect event sessionId: " + sha.getSessionId());
        
        String destination = sha.getDestination();
         
        String time = new SimpleDateFormat("HH:mm").format(new Date());
    	
        OutputMessage connMessage 
			= new OutputMessage(username, "", time, OutputMessage.Code.JOINED);
        
        String[] parts = destination.split("/");
        String chatRoomId = parts[3];
        
        ChatRoom chatRoom 
					= chatRoomService.getChatRoom(Integer.valueOf(chatRoomId));
         
        // add new subscriber username
        chatRoom.addUser(sha.getSessionId(), username);

        chatRoom.display();
      
        log.info("New user has joined: " + username);
        
        template.convertAndSend(destination, connMessage);
    	
        
    }
}