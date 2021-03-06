package com.max_sk.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*Класс ДТО для отправки URL Giphy*/
@Data
@AllArgsConstructor
public class GiphyUrlDto {

    private String url;
    private double money;

    public GiphyUrlDto(String url) {
        this.url = url;
    }

}
