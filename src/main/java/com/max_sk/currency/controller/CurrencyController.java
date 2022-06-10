package com.max_sk.currency.controller;

import com.max_sk.currency.dto.GiphyUrlDto;
import com.max_sk.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/*Класс контроллер Валюты*/
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    /*GET запрос принимает на вход строку и отправляет её в сервис, отправляет полученную DTO */
    @GetMapping("/{codeCurrency}")
    public GiphyUrlDto getCurrency(@PathVariable String codeCurrency) throws IOException {
        return currencyService.getCurrency(codeCurrency);
    }
}
