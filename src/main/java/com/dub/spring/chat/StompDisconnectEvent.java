package com.dub.spring.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.dub.spring.services.ChatRoomService;

@Component
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {
	 
	private static final Logger log = LoggerFactory.getLogger(StompDisconnectEvent.class);
	 
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
    private SimpMessagingTemplate template;
	 
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		 
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		
        log.info("Disconnect event sessionId: " + sha.getSessionId());
        
        for (ChatRoom chatRoom : chatRoomService.getChatRooms().values()) {
        	 Map<String, String> connectedUsers = chatRoom.getConnectedUsers();
        	 
        	 // send disconnect notification to all chat group subscribers 
        	 if (connectedUsers.containsKey(sha.getSessionId())) {
        		 String username = connectedUsers.get(sha.getSessionId());
        		 OutputMessage discMessage 
        		 			= new OutputMessage(username, "", time, OutputMessage.Code.LEFT );
        	      
        		 template.convertAndSend("/topic/messages/" + chatRoom.getChatRoomId(), discMessage);
        		 
        	 } 
        }
        
        
	}
}