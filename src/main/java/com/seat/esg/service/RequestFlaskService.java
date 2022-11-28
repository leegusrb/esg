package com.seat.esg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.esg.form.GetAbstractiveForm;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestFlaskService {

    ObjectMapper objectMapper = new ObjectMapper();

    public GetAbstractiveForm.response getAbstractive(GetAbstractiveForm.request abstractiveForm) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Integer> extractive = new ArrayList<>();
        abstractiveForm.setExtractive(extractive);
        abstractiveForm.setAbstractive("");
        String params2 = objectMapper.writeValueAsString(abstractiveForm);

        HttpEntity entity = new HttpEntity(params2, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/flask", HttpMethod.POST, entity, String.class);

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        GetAbstractiveForm.response response = objectMapper.readValue(
                responseEntity.getBody(), GetAbstractiveForm.response.class
        );
        return response;
    }
}
