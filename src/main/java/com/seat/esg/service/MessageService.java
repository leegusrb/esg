package com.seat.esg.service;

import com.seat.esg.domain.Message;
import com.seat.esg.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Long createAwayRequestMessage(int seatNum) {
        Message message = Message.createMessage(seatNum);
        messageRepository.save(message);
        return message.getId();
    }

    public void deleteClearSeatMessage(int seatNum) {
        if (!messageRepository.findBySeatNum(seatNum).isEmpty()) {
            messageRepository.removeBySeatNum(seatNum);
        }
    }
}
