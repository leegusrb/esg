package com.seat.esg.controller;

import com.seat.esg.domain.Seat;
import com.seat.esg.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SeatRepository seatRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Seat> seats = seatRepository.findAll();
        model.addAttribute("seats", seats);
        return "home";
    }
}
