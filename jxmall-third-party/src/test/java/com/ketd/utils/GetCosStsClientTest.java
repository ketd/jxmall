package com.ketd.utils;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetCosStsClientTest {

    @Autowired
    private GetCosStsClient getCosStsClient;

    @Test
    void getUrl() {
        System.out.println(getCosStsClient.getUrl("1.png").toString());
    }
}