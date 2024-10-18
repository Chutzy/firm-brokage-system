package com.example.hubs.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Constants {

    private Constants() {
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OrderConstants {

        public static final String ORDER_SIDE_BUY = "B";
        public static final String ORDER_SIDE_SELL = "S";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomerConstants {
        public static final String ROLE_USER = "ROLE_USER";
    }
}
