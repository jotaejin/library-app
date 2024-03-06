package com.group.iibrayapp.response;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.Month;

@Getter
public class DayResponse {
    DayOfWeek dayOfTheWeek;

    public DayResponse(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
