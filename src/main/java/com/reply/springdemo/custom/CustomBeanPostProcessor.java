package com.reply.springdemo.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
        System.out.println("postProcessBeforeInitialization " + getClass().getSimpleName() + " " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
        System.out.println("postProcessAfterInitialization " + getClass().getSimpleName() + " " + beanName);
        return bean;
    }
}
