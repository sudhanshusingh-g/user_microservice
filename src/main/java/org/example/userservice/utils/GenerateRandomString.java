package org.example.userservice.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class GenerateRandomString {
    public static String generate(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }


}
