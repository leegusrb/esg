package com.seat.esg.service;

import com.seat.esg.domain.SeatNumber;
import com.seat.esg.domain.Seat;
import com.seat.esg.domain.SeatStatus;
import com.seat.esg.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

    public List<Seat> findSeats() {
        return seatRepository.findAll();
    }

    public Seat findOne(Long seatId) {
        return seatRepository.findOne(seatId);
    }

    public SeatNumber countSeatNumber() {
        SeatNumber seatNumber = new SeatNumber();
        seatNumber.setEmptySeat(seatRepository.findByStatus(SeatStatus.EMPTY).size());
        seatNumber.setAwaySeat(seatRepository.findByStatus(SeatStatus.AWAY).size());
        seatNumber.setFullSeat(seatRepository.findByStatus(SeatStatus.FULL).size());
        return seatNumber;
    }

    @Transactional
    public void changeSeatStatus(int seatNum, SeatStatus status) {
        Seat seat = seatRepository.findOneBySeatNum(seatNum);
        seat.setStatus(status);
    }
}
