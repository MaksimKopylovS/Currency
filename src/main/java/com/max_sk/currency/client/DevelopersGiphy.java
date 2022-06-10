package com.max_sk.currency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*Интерфейс для подключения к сайту developers.giphy*/
@FeignClient(name = "developers.giphy",url = "${DEVELOPERS_GIPHY_URL}")
public interface DevelopersGiphy {

    /*Рандомная конечная точка для получения анимации в стиле RICH*/
    @GetMapping("${API_KEY}" + "${DEVELOPERS_GIPHY_URL_RICH}")
    String getRandomRich();

    /*Рандомная конечная точка для получения анимации в стиле BROKE*/
    @GetMapping("${API_KEY}" + "${DEVELOPERS_GIPHY_URL_BROKE}")
    String getRandomBroke();
}
