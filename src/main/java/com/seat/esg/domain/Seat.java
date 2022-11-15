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

    private Long seatNum;

    private Long emptyMinute;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
