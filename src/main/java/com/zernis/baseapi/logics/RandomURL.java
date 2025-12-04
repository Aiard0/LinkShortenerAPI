package com.zernis.baseapi.logics;

import java.security.SecureRandom;

public class RandomURL {

    public String generateURL() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
           sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

}
