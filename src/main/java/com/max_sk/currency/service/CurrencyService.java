package com.max_sk.currency.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.max_sk.currency.client.DevelopersGiphy;
import com.max_sk.currency.client.Openexchangerates;
import com.max_sk.currency.configurator.ConfigurationUrl;
import com.max_sk.currency.dto.ErrorDto;
import com.max_sk.currency.dto.GiphyUrlDto;
import com.max_sk.currency.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*Серверная часть обработки запросов связанных с валютой*/
@Service
@AllArgsConstructor
public class CurrencyService {

    private final Openexchangerates openexchangerates;
    private final ConfigurationUrl configurationUrl;
    private final DevelopersGiphy developersGiphy;

    /*Метод сравнивания значения валюты за сегодня с вчерашней */
    public GiphyUrlDto getCurrency(String codeCurrency) throws IOException {

        /*Редактируем конфигурационный файл для установки вчерашний даты*/
        configurationUrl.editConf();

        /*Создаём DTO курса валюты за текущий день*/
        CurrencyDto latestCurrency = getLatestCurrency();

        /*Создаём DTO курса валюты за прошлый день*/
        CurrencyDto currencyForYesterday = getCurrencyForYesterday();

        /*Проверяем, если в списке JSON валюты, нет подходящего кода валюты отправляем URL с ошибкой*/
        if (latestCurrency.getRates().get(codeCurrency) == null || currencyForYesterday.getRates().get(codeCurrency) == null) {
            return new GiphyUrlDto(ErrorDto.getERROR(), 0.0);
        }

        /*Создаём  GiphyUrlDto*/
        GiphyUrlDto rich = getRichGiphyUrlDto();
        rich.setMoney(currencyForYesterday.getRates().get(codeCurrency));

        /*Создаём GiphyUrlDto*/
        GiphyUrlDto broke = getBrokeGiphyUrl();
        broke.setMoney(currencyForYesterday.getRates().get(codeCurrency));

        System.out.println(rich.getMoney() + " " + broke.getMoney());

        /* Сравниваем изменение курса и отправляем в контролер DTO */
        return rich.getMoney() < broke.getMoney() ? rich : broke;
    }

    /*Создаём DTO курса валюты за текущий день*/
    public CurrencyDto getLatestCurrency() throws JsonProcessingException {
        return new ObjectMapper().readValue(openexchangerates.getLatest(), CurrencyDto.class);
    }

    /*Создаём DTO курса валюты за прошлый день*/
    public CurrencyDto getCurrencyForYesterday() throws JsonProcessingException {
        return new ObjectMapper().readValue(openexchangerates.currencyForYesterday(), CurrencyDto.class);
    }

    /*Ищем в Json с помощью JACKSON ссылку на GIF RICH и создаём GiphyUrlDto*/
    public GiphyUrlDto getRichGiphyUrlDto() throws JsonProcessingException {
        return new GiphyUrlDto(
                new ObjectMapper()
                        .readTree(developersGiphy.getRandomRich())
                        .get("data")
                        .get("images")
                        .get("original")
                        .get("url")
                        .asText());
    }

    /*Ищем в Json с помощью JACKSON ссылку на GIF BROKE и создаём GiphyUrlDto*/
    public GiphyUrlDto getBrokeGiphyUrl() throws JsonProcessingException {
        return new GiphyUrlDto(
                new ObjectMapper()
                        .readTree(developersGiphy.getRandomBroke())
                        .get("data")
                        .get("images")
                        .get("original")
                        .get("url")
                        .asText());
    }

}
