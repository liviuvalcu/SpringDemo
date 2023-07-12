package com.reply.springdemo.service;

import com.reply.springdemo.Status;
import org.springframework.stereotype.Service;

@Service
public class DecidingStatus {

    public Status getAcceptedValue(){
        return Status.ACCEPTED;
    }
}
