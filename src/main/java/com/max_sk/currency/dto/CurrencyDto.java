package com.max_sk.currency.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.*;

/*Класс ДТО для работы с JSON списком текущей валюты из openexchangerate*/
@Data
@JsonAutoDetect
public class CurrencyDto {

    public String disclaimer;
    public String license;
    public String timestamp;
    public String base;
    public Map<String, Double> rates;

    public CurrencyDto(@JsonProperty(value = "disclaimer") String disclaimer,
                     @JsonProperty(value = "license") String license,
                     @JsonProperty(value = "timestamp") String timestamp,
                     @JsonProperty(value = "base") String base,
                     @JsonProperty(value = "rates") Map<String, Double> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

}
