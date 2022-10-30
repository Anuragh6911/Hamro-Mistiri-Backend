package com.example.hamromistiri.Service;

import org.springframework.stereotype.Service;


public class DummySmsService implements SmsService{

    @Override
    public void sendSms(String phoneNo, String message) {
        System.out.println("Sending sms to "+phoneNo+" "+message);
    }
    }

