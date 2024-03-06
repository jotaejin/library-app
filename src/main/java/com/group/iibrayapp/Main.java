package com.group.iibrayapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int repeatNum = inputNum();
        diceCount(repeatNum);
    }

    private static void diceCount(int repeatNum) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < repeatNum; i++){

            int dice = (int) (Math.random() * 6) + 1;
            Integer result = map.put(dice, map.getOrDefault(dice, 0) + 1);
        }

        for(int key : map.keySet()){
            Integer value = map.get(key);
            System.out.println(key + "번은 " + value+"번 나왔습니다.");
        }
    }

    public static int inputNum(){
        System.out.print("주사위를 몇번 굴릴것인가요? : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        return num;
    }
}
