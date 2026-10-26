package com.practice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String QUEUE = "rabbitmqqueue";
    public static final String EXCHANGE = "rmqexchange";
    public static final String ROUTING_KEY = "rmqroutingkey";
}