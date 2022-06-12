package com.max_sk.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

/*Добрый день, уважаемые разработчики Альфа Банка.
Спасибо вам за увлекательное задание, с большим удовольствием выполнил его для вас.
Благодаря полученному IT опыту в разных направлениях и прохождению обучения
по специальности JAVA разработчик могу быстро втянутся в ваш проект и начать выполнять
поставленные задачи, уверен, что смогу быть максимально полезным для вашей команды.*/

@SpringBootApplication
@EnableFeignClients
@PropertySource("file:application.conf")
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}

}
