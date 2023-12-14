package com.example.demo.service;

import java.util.List;

import com.example.demo.model.chat;

public interface chatService{
	List<chat> findByVanishMode(boolean val);
	List<chat> findByVanishModeAndChatId(boolean val,String cid);
}