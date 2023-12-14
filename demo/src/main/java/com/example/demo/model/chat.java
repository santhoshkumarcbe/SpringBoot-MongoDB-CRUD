package com.example.demo.model;


// import org.hibernate.annotations.Generated;

import jakarta.persistence.*;

@Entity
@Table(name="chat")
public class chat{

	@Id
	@Column(name="chat_id")// if we are changing the name we have to declare else not needed
	public String chatId;
	public String chat_type;
	@Column(name="vanish_mode")
	public boolean vanishMode ;
	public String settings;
	
	chat(){}
	public chat(String id,String type,boolean vm,String set){
		this.chatId=id;
		this.chat_type=type;
		this.vanishMode=vm;
		this.settings=set;
	}
}