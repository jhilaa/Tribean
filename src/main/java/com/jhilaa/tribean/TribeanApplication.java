package com.jhilaa.tribean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TribeanApplication implements CommandLineRunner  {

	@Value("${jwt.secret}")
	private String jwt;
	public static void main(String[] args) {
		SpringApplication.run(TribeanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("property jwt value is: " + jwt);
	}
}
