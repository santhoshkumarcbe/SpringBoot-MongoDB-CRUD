package com.example.demo.repository;
import java.util.*;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.chat;

public interface chatrepository extends CrudRepository<chat, String> {
	List<chat> findByVanishMode(boolean val);
	List<chat> findByVanishModeAndChatId(boolean val,String cid);
}