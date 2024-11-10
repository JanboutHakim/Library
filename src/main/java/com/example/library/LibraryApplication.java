package com.example.library;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);


	}


	// a library helps to map objects easier than do it manually
	@Bean
	public ModelMapper getModelMapper(){return new ModelMapper();}

	@Bean
	public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); }
}
