package com.seat.esg.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.esg.domain.RequestMessageForm;
import com.seat.esg.domain.SeatStatus;
import com.seat.esg.service.MessageService;
import com.seat.esg.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final MessageService messageService;
    private final SeatService seatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = textMessage.getPayload();
//        log.info("payload : {}", payload);
//
//        MessageForm messageForm = objectMapper.readValue(payload, MessageForm.class);
//        sessions.forEach((sessionId, sessionInMap) -> {
//            try {
//                sessionInMap.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageForm)));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        String msg = message.getPayload();

        RequestMessageForm requestMessageForm = objectMapper.readValue(msg, RequestMessageForm.class);
        int seatNum = requestMessageForm.getSeatNum();
        String messageType = requestMessageForm.getMessageType();

        if (messageType.equals("REQUEST")) {
            messageService.createAwayRequestMessage(seatNum);
        } else if (messageType.equals("DELETE")) {
            messageService.deleteClearSeatMessage(seatNum);
            seatService.changeSeatStatus(seatNum, SeatStatus.EMPTY);
        }

        sessions.forEach((sessionId, sessionInMap) -> {
            try {
                sessionInMap.sendMessage(new TextMessage(""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
