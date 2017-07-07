package com.example.wss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.wss.model.Greeting;

public interface GreetingRespository extends MongoRepository<Greeting, Long> {
	
}
