package com.f1v3.urlshortener.util;

import org.springframework.stereotype.Component;

/**
 * @author 정승조
 * @version 2024. 12. 06.
 */
@Component
public class Base62Conversion {

    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RADIX = 62;

    public String encode(long param) {
        StringBuffer sb = new StringBuffer();
        while (param > 0) {
            sb.append(BASE62.charAt((int) (param % RADIX)));
            param /= RADIX;
        }
        return sb.toString();
    }

    public long decode(String param) {
        long sum = 0;
        long power = 1;

        for (int i = 0; i < param.length(); i++) {
            sum += BASE62.indexOf(param.charAt(i)) * power;
            power *= RADIX;
        }
        return sum;
    }
}
