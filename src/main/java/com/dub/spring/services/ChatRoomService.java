package com.dub.spring.services;

import java.util.Map;

import com.dub.spring.chat.ChatRoom;

public interface ChatRoomService {

	Map<Integer, ChatRoom> getChatRooms();
	 
	ChatRoom getChatRoom(int id);
	
	String getChatRoomName(int id);
}