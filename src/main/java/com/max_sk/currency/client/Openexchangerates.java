package com.max_sk.currency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*Интерфейс для подключения к сайту openexchangerates.org*/
@FeignClient(name = "openexchangerates.org", url = "${EXCHANGE_RATES}")
public interface Openexchangerates {

    /*Конечная точка получения Json курса валюты за сегодня*/
    @GetMapping("${CURRENCY_TODAY}" + "${APP_ID}")
    public String getLatest();

    /*Конечная точка получения Json курса валюты за вчера*/
    @GetMapping("${CURRENCY_OF_THE_EVENING}" + "${APP_ID}")
    public String currencyForYesterday();

}
