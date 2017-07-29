package com.dub.spring.chat;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/** This ApplicationListener is used for logging only */

@Component
public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {
	 
    private static final Logger log = LoggerFactory.getLogger(StompConnectEvent.class);
 
	@Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
 
        log.info("Connect event sessionId: " + sha.getSessionId());
        
    }
}