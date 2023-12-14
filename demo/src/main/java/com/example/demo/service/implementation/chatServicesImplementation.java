package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.chat;
import com.example.demo.repository.chatrepository;
import com.example.demo.service.chatService;

@Service
class chatServicesImplementation implements chatService{
	
	@Autowired
	chatrepository crep;
	
	public List<chat> findByVanishMode(boolean val){
		return crep.findByVanishMode(val);
	}
	
	public List<chat> findByVanishModeAndChatId(boolean val,String cid){
		return crep.findByVanishModeAndChatId(val, cid);
	}
}