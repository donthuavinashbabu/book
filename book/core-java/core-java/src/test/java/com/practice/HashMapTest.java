package com.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HashMapTest {

    @Test
    public void create(){
        Map<Integer, String> map = new HashMap<>();
        log.info("map={}", map);
    }

}