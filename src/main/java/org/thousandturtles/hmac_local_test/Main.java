package org.thousandturtles.hmac_local_test;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Chiaki Chikame on 8/4/15.
 * <p>
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/txt/copying/ for more details.
 */
public class Main {
    public static void main(String[] args) {
        String message = "豬頭";
        String key = "This is a password";
        String[] encodings = {"", "UTF-8", "UTF-16BE", "UTF-16LE"};
        try {
            for (String encoding : encodings) {
                SecretKeySpec keySpec = null;
                Mac mac = null;
                byte[] result = null;
                if (encoding.isEmpty()) {
                    System.out.println("Default");
                    keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
                    mac = Mac.getInstance("HmacSHA512");
                    mac.init(keySpec);
                    result = mac.doFinal(message.getBytes());
                }
                else
                {
                    System.out.printf("Encoding: %s%n", encoding);
                    keySpec = new SecretKeySpec(key.getBytes(encoding), "HmacSHA512");
                    mac = Mac.getInstance("HmacSHA512");
                    mac.init(keySpec);
                    result = mac.doFinal(message.getBytes(encoding));
                }
                System.out.println(Hex.encodeHex(result));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
