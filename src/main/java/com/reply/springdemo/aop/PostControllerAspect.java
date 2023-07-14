package com.reply.springdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PostControllerAspect {

   // @Before("this(com.reply.springdemo.controller.PostController)")
    public void before(){
        System.out.println("Before public class PostController");
    }
    //@Before("getAll()")
   // @AfterReturning("getAll()")
   // @AfterThrowing("getAll()")
    //@After("getAll()")
    public void beforeGetAllComments(JoinPoint joinPoint){
        System.out.println("before " + joinPoint.getSignature());
    }

    //@Pointcut("execution(* com.reply.springdemo.controller.PostController.getAll*(..))")
   // @Pointcut("args(Integer, Integer)")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getAll(){}

    @Pointcut("execution(* com.reply.springdemo.controller.PostController.findAll*(..))")
    public void findAll(){}


    @Around("getAll() || findAll()")
    public  void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Method");
        joinPoint.proceed();
       try {
           joinPoint.proceed();
       }catch (Throwable t){

       }
        System.out.println("After Method");
    }

}
