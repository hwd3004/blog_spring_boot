package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// @EnableGlobalMethodSecurity(prePostEnabled = true) - 특정 주소로 접근을 하면, 권한 및 인증을 미리 체크하도록 함
// @EnableWebSecurity - 시큐리티 필터로 등록, 스프링 시큐리티가 활성화 되어 있을 때, 설정을 여기서 하도록 함
// @Configuration - 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 (IoC)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
//	빈 등록 (IoC), 리턴되는 값을 스프링이 관리함
//	시큐리티 내장 함수
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

//	시큐리티가 로그인 작업을 하면서, 패스워드가 뭘로 해쉬가 되어있는지 확인
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		authorizeRequests - 요청이 들어오는지 확인
//		antMatchers("주소") - 이 주소로 들어오는 사용자에게는 permitAll, 모두 허락
//		anyRequest - antMatchers("주소") 외의 다른 주소로 들어오는 사용자에게는 인증된 사용자만 허락
//		formLogin().loginPage("주소") - 인증이 필요한 곳으로 사용자가 접근하려는데, 인증이 안되어있으면 여기로 이동함

//		http.authorizeRequests().antMatchers("/auth/loginForm", "/auth/joinForm").permitAll();

//		loginProcessingUrl("/auth/loginProc") - 스프링 시큐리티가 로그인 작업을 수행함

//		http.csrf().disable().authorizeRequests()... - 개발 중 테스트시에는 csrf().disable()을 해주면 편함  
		http.csrf().disable().authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/");
	}

}
