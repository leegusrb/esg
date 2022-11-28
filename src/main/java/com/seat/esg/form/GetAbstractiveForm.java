package com.seat.esg.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAbstractiveForm {

    @Getter
    @Setter
    public static class request {
        private Long id;
        private String abstractive;
        private List<Integer> extractive;
        private String seatStatus;
    }

    @Getter
    @Setter
    public static class response {
        private Long id;
        private String abstractive;
        private List<Integer> extractive;
        private String seatStatus;
    }
}
