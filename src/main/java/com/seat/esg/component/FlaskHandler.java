package com.seat.esg.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.esg.domain.SeatStatus;
import com.seat.esg.form.RequestMessageForm;
import com.seat.esg.form.ResponseFlaskForm;
import com.seat.esg.service.MessageService;
import com.seat.esg.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlaskHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final SeatService seatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        // {'status' : ['EMPTY', 'FULL', 'AWAY', 'EMPTY', 'AWAY']}
        ResponseFlaskForm responseFlaskForm = objectMapper.readValue(msg, ResponseFlaskForm.class);
        List<String> status = responseFlaskForm.getStatus();

        for (int i = 0; i < status.size(); i++) {
            SeatStatus seatStatus = seatService.changeStringStatusToEnum(status.get(i));
            seatService.updateStatus(i + 1, seatStatus);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId(), session);
    }
}
