package com.dub.spring.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.dub.spring.services.ChatRoomService;

/** Main class of the chat room 
 * It supports several chat groups identified by chatRoomId variable annotated with @DestinationVariable
 * Users subscribed to a given group can exchange messages with users subscribed to this group only */

@Controller
public class ChatRoomController {
	
	@Autowired
	ChatRoomService chatRoomService;
	
	private static final Logger log = LoggerFactory.getLogger(StompDisconnectEvent.class);

	
	@MessageMapping("/chat/{chatRoomId}") // like a request mapping with @DestinationVariable instead of @PathVariable
	@SendTo("/topic/messages/{chatRoomId}") // destination URL
	public OutputMessage send1(@DestinationVariable("chatRoomId") int chatRoomId, MyMessage message, 
											StompHeaderAccessor sha) throws Exception {
	    
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		
		String sessionId = sha.getSessionId();
		
		if (message.isFirst()) {// new user just joined, send a notification to the group
					
			ChatRoom chatRoom 
						= chatRoomService.getChatRoom(chatRoomId);
				
			chatRoom.addUser(sessionId, message.getFrom());
			
			chatRoom.display();
			
			log.info("New user has joined: " + message.getFrom());
				
			return new OutputMessage(message.getFrom(), "", time, OutputMessage.Code.JOINED);
		}
		

	    return new OutputMessage(message.getFrom(), message.getText(), time, 
	    														OutputMessage.Code.NORMAL);
	}
		
}
