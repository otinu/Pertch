package otinu.pf.pertch.securityconfig;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@EnableWebSecurity
public class SecurityConfig {

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
			http
			.formLogin(login -> login
				.loginProcessingUrl("/login")	// ユーザー名・パスワードの送信先URL
				.loginPage("/login")			// ログイン画面のURL
				.usernameParameter("username")  // HTML上のUserNameのname属性を任意に変更可能(デフォルトは「username」)
				.passwordParameter("password")	// HTML上のPasswordのname属性を任意に変更可能(デフォルトは「password」)
				.defaultSuccessUrl("/pet/index")		// ログイン成功後のリダイレクト先URL
				.failureUrl("/login?error")		// ログイン失敗後のリダイレクト先URL
				.permitAll()
			)
			.logout(logout -> logout.logoutSuccessUrl("/")
			).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // /css/** などの静的ファイルにログインなしでもアクセス可能
                .mvcMatchers("/top", "/login", "/registration", "/js/**", "/css/**", "/img/**").permitAll() 		// 特別にアクセスを可能とする
                // .mvcMatchers("/admin").hasRole("ADMIN") 	// ADMINユーザーのみ、「/admin」にアクセス可能
                .anyRequest().authenticated() 		//その他のURLはログイン後にアクセス可能
	        );
			
			return http.build();
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
}
