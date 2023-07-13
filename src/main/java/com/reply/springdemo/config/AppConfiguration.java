package com.reply.springdemo.config;

import com.reply.springdemo.custom.CustomBeanFactoryPostProcessor;
import com.reply.springdemo.custom.CustomBeanPostProcessor;
import com.reply.springdemo.service.PostCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfiguration {

    //@Bean(initMethod = "init", destroyMethod = "destroy_2")
   // @Lazy
    public PostCommentService postCommentService(){
        return new PostCommentService();
    }

   // @Bean
    public static CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor(){
        return new CustomBeanFactoryPostProcessor();
    }

   // @Bean
    public static CustomBeanPostProcessor customBeanPostProcessor(){
        return new CustomBeanPostProcessor();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
