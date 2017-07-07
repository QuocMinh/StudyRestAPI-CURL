package com.example.wss.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.wss.model.Greeting;
import com.example.wss.repository.GreetingRespository;

@Controller
public class GreetingController {

	private final String tempate = "Hello, %s !";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	GreetingRespository greetingRespository;
	
	@GetMapping("/greeting")
	@ResponseBody
	public Greeting greeting(
			@RequestParam(value="name", defaultValue="World") String name) {
		
		return new Greeting(counter.incrementAndGet(), 
				String.format(tempate, name));
	}
	
	@PutMapping("/greeting")
	@ResponseBody
	public String handleGreeting(@RequestBody Greeting jsonGreeting) {
		
		System.out.println(jsonGreeting);
		/*
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Greeting greeting = mapper.readValue(jsonGreeting, Greeting.class);
			
			System.out.println(greeting.toString());
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return "redirect:/greeting";
	}
	
}
