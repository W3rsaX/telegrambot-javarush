package com.github.javarushcommunity.tbjr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotJavaRushApplication {

	@Value("${bot.token}")
	private static String botToken;

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotJavaRushApplication.class, args);
	}

}
