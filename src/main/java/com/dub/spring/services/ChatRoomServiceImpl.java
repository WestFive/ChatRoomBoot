package com.dub.spring.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dub.spring.chat.ChatRoom;

/** Two chat groups are provided here, more can be added
 * */

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
		 
    private static final Logger log = LoggerFactory.getLogger(ChatRoomServiceImpl.class);
 
	private Map<Integer, ChatRoom> chatRooms = new HashMap<>();
	
	@PostConstruct
	public void init() {
		
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setName("Woody Allen");
		chatRoom.setChatRoomId(1);
		chatRooms.put(chatRoom.getChatRoomId(), chatRoom);
		
		ChatRoom chatRoom2 = new ChatRoom();
		chatRoom2.setName("Brad Pitt");
		chatRoom2.setChatRoomId(2);
		chatRooms.put(chatRoom2.getChatRoomId(), chatRoom2);
		
		log.info("ChatRoomServiceImpl init completed " 
								+ chatRooms.size());
	}

	@Override
	public Map<Integer, ChatRoom> getChatRooms() {
		return chatRooms;
	}

	@Override
	public ChatRoom getChatRoom(int id) {
		return chatRooms.get(id);
	}

	@Override
	public String getChatRoomName(int id) {
		return chatRooms.get(id).getName();
	}

}

