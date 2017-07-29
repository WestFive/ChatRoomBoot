package com.dub.spring.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/** 
 * Holds all users connected to a chat room
 * */

@Service
public class ChatRoom {
	
	private String name;
	private Integer chatRoomId;
	
	private Map<String, String> connectedUsers = new HashMap<>();
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public Map<String, String> getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(Map<String, String> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
	
	public void addUser(String sessionId, String username) {
		connectedUsers.put(sessionId, username);
	}
	
	public void removeUserBySessionId(String sessionId) {
		connectedUsers.remove(sessionId);
	} 
	
	// for degugging only
	public void display() {
		for (String key : connectedUsers.keySet()) {
			System.out.println(key + ": " + connectedUsers.get(key));
		}
	} 
}
