package com.group.iibrayapp.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CalcResponse {
    int addition;
    int min;
    int multiplication;

    private int addition(int num1,int num2){
        return num1 + num2;
    }

    private int min(int num1,int num2){
        return num1 - num2;
    }

    private int multiplication(int num1,int num2){
        return num1 * num2;
    }

    public CalcResponse(int num1, int num2) {
         this.addition = addition(num1, num2);
         this.min = min(num1, num2);
         this.multiplication = multiplication(num1, num2);
    }
}
