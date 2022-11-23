package com.seat.esg.controller;

import com.seat.esg.domain.Message;
import com.seat.esg.domain.Seat;
import com.seat.esg.domain.SeatStatus;
import com.seat.esg.repository.SeatRepository;
import com.seat.esg.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SeatRepository seatRepository;
    private final MessageService messageService;

    @GetMapping("/")
    public String home(Model model) {
        makeSeatData(model);
        return "home";
    }

    @GetMapping("/manager")
    public String manager(Model model) {
        makeSeatData(model);
        makeMassegeData(model);
        return "manager";
    }

    private void makeSeatData(Model model) {
        List<Seat> seats = seatRepository.findAll();
        int awaySize = seatRepository.findByStatus(SeatStatus.AWAY).size();
        int emptySize = seatRepository.findByStatus(SeatStatus.EMPTY).size();
        int fullSize = seatRepository.findByStatus(SeatStatus.FULL).size();
        model.addAttribute("seats", seats);
        model.addAttribute("awaySize", awaySize);
        model.addAttribute("emptySize", emptySize);
        model.addAttribute("fullSize", fullSize);
    }

    private void makeMassegeData(Model model) {
        List<Message> messages = messageService.findMessages();
        model.addAttribute("messages", messages);
    }
}
