package com.max_sk.currency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max_sk.currency.client.DevelopersGiphy;
import com.max_sk.currency.client.Openexchangerates;
import com.max_sk.currency.configurator.ConfigurationUrl;
import com.max_sk.currency.dto.ErrorDto;
import com.max_sk.currency.dto.GiphyUrlDto;
import com.max_sk.currency.dto.LatestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*Серверная часть обработки запросов связанных с фалютой*/
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

        /*Проверяем, если в списке JSON валюты, нет подходящего нам значение отправляем URL с ошибкой*/
        if (new ObjectMapper()
                .readValue(openexchangerates.getLatest(), LatestDto.class)
                .getRates()
                .get(codeCurrency) == null) {
            return new GiphyUrlDto(ErrorDto.getERROR());
        }

        return
                /*Вытаскиваем из Json искомый код валюты за сегодня */
                new ObjectMapper()
                        .readValue(openexchangerates.getLatest(), LatestDto.class)
                        .getRates()
                        .get(codeCurrency)
                        <
                        /*Вытаскиваем из Json искомый код валюты за вчера и сравниваем его с текущим */
                        new ObjectMapper()
                                .readValue(openexchangerates.currencyForYesterday(), LatestDto.class)
                                .getRates()
                                .get(codeCurrency)
                        ?
                        /*Если валюта за сегодня уменьшилась значит курс поднялся возвращаем Giph с RICH */
                        new GiphyUrlDto(
                                /*Ищем в Json с помощью JACKSON необходимую нам ссылку*/
                                new ObjectMapper()
                                        .readTree(developersGiphy.getRandomRich())
                                        .get("data")
                                        .get("images")
                                        .get("original")
                                        .get("url")
                                        .asText())
                        :
                        /*Если валюта за сегодня увеличилась значит курс опустился возвращаем Giph с BROKE */
                        new GiphyUrlDto(
                                /*Ищем в Json с помощью JACKSON необходимую нам ссылку */
                                new ObjectMapper()
                                        .readTree(developersGiphy.getRandomBroke())
                                        .get("data")
                                        .get("images")
                                        .get("original")
                                        .get("url")
                                        .asText()
                        );
    }
}
