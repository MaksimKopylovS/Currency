package com.max_sk.currency.service;

import com.max_sk.currency.CurrencyApplication;
import com.max_sk.currency.client.DevelopersGiphy;
import com.max_sk.currency.client.Openexchangerates;
import com.max_sk.currency.dto.ErrorDto;
import com.max_sk.currency.dto.GiphyUrlDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = CurrencyApplication.class)
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private Openexchangerates openexchangerates;
    @Autowired
    private DevelopersGiphy developersGiphy;

    @Test
    void getCurrency() throws IOException {

        /*Так как URL приходит всегда разный, проверяем на то, что возвращается корректная DTO, если, что-то сломается ошибку сразу, будет видно */
        Assertions.assertEquals(GiphyUrlDto.class, currencyService.getCurrency("RUB").getClass());
        /*Проверяем если на вход передаётся значение которого нет в JSON, должен вернуть ошибочный URL*/
        Assertions.assertEquals(ErrorDto.getERROR(), currencyService.getCurrency("AAA").getUrl());
    }
}