package com.example.ws.controller;

import com.example.ws.model.Message;
import com.example.ws.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageHandlingController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message msg) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(msg.getFrom(), msg.getText(), time);
    }
}
