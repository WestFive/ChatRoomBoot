package com.dub.spring.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	@MessageMapping("/chat/{chatRoomId}") // like a request mapping with @DestinationVariable instead of @PathVariable
	@SendTo("/topic/messages/{chatRoomId}") // destination URL
	public OutputMessage send(@DestinationVariable("chatRoomId") int chatRoomId, MyMessage message, 
											StompHeaderAccessor sha) throws Exception {
	    
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		
	    return new OutputMessage(message.getFrom(), message.getText(), time, 
	    														OutputMessage.Code.NORMAL);
	}
		
}
