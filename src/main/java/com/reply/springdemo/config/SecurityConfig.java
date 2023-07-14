package com.reply.springdemo.config;

import com.reply.springdemo.custom.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

      return httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
              .permitAll()
              .anyRequest()
              //.access(new MinuteBasedAM())
              .authenticated()

              )
              .exceptionHandling((handling) -> handling.authenticationEntryPoint(restAuthenticationEntryPoint))
              .csrf((csrf) -> csrf.disable())
              .httpBasic(Customizer.withDefaults())
              .build();
    }

    //$2a$10$zP0.psEMm8qfgD7bOM7RjeOOV1E86jLZjP3g5aALlLkfJTnQY9W0C
/*
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails userDetails = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails userAdminDetails = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails, userAdminDetails);
    }
*/

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/SpringDemo");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("liviu1986");

        return dataSourceBuilder.build();

    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource){
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

        resourceDatabasePopulator.addScripts(new ClassPathResource("schema.sql"), new ClassPathResource("data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
