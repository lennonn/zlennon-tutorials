package com.example.string;

import org.junit.jupiter.api.Test;


import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {
    @Test
    void testString(){
        String str1=new String("abc".getBytes(),StandardCharsets.UTF_8);
        String str2= new String("abc".getBytes());
        assertEquals(str1, str2);
    }
}
