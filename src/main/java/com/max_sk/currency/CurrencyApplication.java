package com.max_sk.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

/*Добрый день, уважаемые разрабочики Альфа Банка.
Спасибо, Вам за увлекательное задание, с большим удовольствием выполнил его для Вас.
Благодоря полученному IT опыту в разных направлениях, и прохождению обучения
по спецальности JAVA разрабочик могу, быстро втянутся в вашь поект и начать выполнять
поставленные задачи, уверен, что смогу быть максимально полезным для вашей команды.*/

@SpringBootApplication
@EnableFeignClients
@PropertySource("file:application.conf")
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}

}
