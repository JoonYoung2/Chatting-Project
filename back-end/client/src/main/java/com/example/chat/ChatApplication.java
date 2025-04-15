package com.example.chat;

import com.example.chat.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ChatApplication.class, args);
		Client client = new Client();
		client.start();
	}
}
