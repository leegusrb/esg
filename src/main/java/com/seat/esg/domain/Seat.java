package com.seat.esg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Seat {

    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    private int seatNum;

    private int emptyMinute;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public void addTime(int time) {
        this.emptyMinute += time;
    }

    public void initTime() {
        this.emptyMinute = 0;
    }

    public void changeStatus(SeatStatus status) {
        this.status = status;
    }
}
