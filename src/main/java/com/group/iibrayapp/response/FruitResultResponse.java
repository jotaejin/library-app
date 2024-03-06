package com.group.iibrayapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FruitResultResponse {
    private String name;
    private LocalDate warehousingDate;
    private long price;
}
