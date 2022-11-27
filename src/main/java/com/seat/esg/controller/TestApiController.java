package com.seat.esg.controller;

import com.seat.esg.form.RequestCleanUpForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestApiController {

    @PostMapping
    public ResponseEntity<String> testApi(@RequestBody RequestCleanUpForm requestCleanUpForm) {
        String responseMsg = "clean up " + requestCleanUpForm.getSeatNum();
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }
}
