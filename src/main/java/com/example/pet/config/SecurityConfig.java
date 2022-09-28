package com.example.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*	Security5.7から推奨されたひな形 

@Configuration
public class SecurityConfig {

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
			// ここに設定を記述
			
			return http.build();
		}
}
*/

@Configuration
public class SecurityConfig {

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
			http.formLogin(login -> login
				.loginProcessingUrl("/login")	// ユーザー名・パスワードの送信先URL
				.loginPage("/login")			// ログイン画面のURL
				.defaultSuccessUrl("index")		// ログイン成功後のリダイレクト先URL
				.failureUrl("/login?error")		// ログイン失敗後のリダイレクト先URL
				.permitAll()
			).logout(logout -> logout.logoutSuccessUrl("/"));
			
			return http.build();
		}
}











