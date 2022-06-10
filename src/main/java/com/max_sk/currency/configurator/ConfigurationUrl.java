package com.max_sk.currency.configurator;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/*Класс для добавления в конфигурационный файл даты за предыдущий день*/
@Component
public class ConfigurationUrl {

    /*Метод редактирования конфигурационного файла*/
    public void editConf() throws IOException {

        /*Получения даны за вчерашний день*/
        String dateForYesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        /*Считывание конфигурационного файла*/
        Path input = Paths.get("./application.conf");
        /*Создание временного конфига для обработки*/
        Path temp = Files.createTempFile("temp", ".conf");

        try (BufferedWriter writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
            /*Отфильтровывает список удаляя из него поле CURRENCY_OF_THE_EVENING*/
            List<String> lines = Files.readAllLines(input)
                    .stream()
                    .filter(str -> !str.startsWith("CURRENCY_OF_THE_EVENING"))
                    .collect(Collectors.toList());
            /*Добавляет в конец списка поле CURRENCY_OF_THE_EVENING с вчерашней датой, и записывает в буфер */
            lines.add("CURRENCY_OF_THE_EVENING =/historical/" + dateForYesterday + ".json?app_id=");
            lines.forEach(s -> {
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Копирует в основной конфигурационный файл информацию с новй датой*/
        Files.copy(temp, input, StandardCopyOption.REPLACE_EXISTING);
    }

}
