package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.SocioairSendSmsRequest;
import com.example.hamromistiri.Dto.SocioairSendSmsResponse;
import com.example.hamromistiri.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SociairSmsService implements SmsService {

    @Override
    public void sendSms(String phoneNo, String message) {

        SocioairSendSmsRequest request = new SocioairSendSmsRequest(message, phoneNo);
        SocioairSendSmsResponse response = WebClient.create("https://sms.sociair.com/api/sms")
                .post()
                .headers(httpHeaders -> {
                    httpHeaders.set("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMmQ5ZGJmM2M3NDhkZWZiNjEyOTRkMTA4NDcyZTk1YjdkOTA2ZDg4YzY4NTM0MzM0ZWEyMTBiYmExYjBhYzJkNTU2NjMwNjEzOTZiMjAxMzMiLCJpYXQiOjE2NjY2OTA0MjEuODI5MDU0LCJuYmYiOjE2NjY2OTA0MjEuODI5MDYyLCJleHAiOjE2OTgyMjY0MjEuODE2Mzk5LCJzdWIiOiI2MzkiLCJzY29wZXMiOltdfQ.QVOpQpM8eZIDsVORVk0-OPfxliBr-ihxOqnhgsayalaP3hW3L1kSn4wSPsX22TsMh-_Sm8HVYxBsBKQIwfypwQ");
                    httpHeaders.set("Content-Type", "application/json");
                    httpHeaders.set("Accept", "application/json");
                })
                .body(Mono.just(request), SocioairSendSmsRequest.class)
                .retrieve()
                .bodyToMono(SocioairSendSmsResponse.class)
                .block();

        if(!response.getMessage().equals("Success! SMS has been sent")){
            System.out.println(response);
            throw new AppException("SMS cannot be sent", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("Sending Sms");
    }
}
