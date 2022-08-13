package com.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
//        $2a$10$.blH8O8hroIgqwKc8pNWHuEXkN3k.b.7c6JNI/9qAbQ29bpj6hYxS
    }
}