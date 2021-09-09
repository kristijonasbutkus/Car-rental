package sda.cars.carrental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
                  httpSecurity
                  .csrf()
                  .disable();

//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("main/login")
//                .permitAll();

//        httpSecurity
//                .csrf().disable().formLogin().disable()
//                .headers().frameOptions().disable().and()
//                .authorizeRequests()
//                .antMatchers("/h2/**").permitAll()
//                .antMatchers("/branch/**").permitAll()
//                .antMatchers("/employee/**").permitAll()
//                .antMatchers("/reservation/**").permitAll()
//                .antMatchers("/car/**").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/invoice/**").permitAll()
//                .anyRequest().authenticated();
//        httpSecurity
//        .authorizeRequests()
//                .antMatchers("/", "/branches/branch-list", "/cars/car-list").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

}
