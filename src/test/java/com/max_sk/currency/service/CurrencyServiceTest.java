package com.max_sk.currency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max_sk.currency.CurrencyApplication;
import com.max_sk.currency.client.DevelopersGiphy;
import com.max_sk.currency.client.Openexchangerates;
import com.max_sk.currency.dto.ErrorDto;
import com.max_sk.currency.dto.GiphyUrlDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

@SpringBootTest(classes = CurrencyApplication.class)
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;
    @MockBean
    private Openexchangerates openexchangerates;
    @MockBean
    private DevelopersGiphy developersGiphy;

    @Test
    void getCurrency() throws IOException {
        /*Создаём MOCK получения списка курса валют за сегодня*/
        Mockito
                .doReturn(latest)
                .when(openexchangerates)
                .getLatest();

        /*Создаём MOCK получения списка курса валют за вчера*/
        Mockito
                .doReturn(currencyForYesterday)
                .when(openexchangerates)
                .currencyForYesterday();

        /*Создаём MOCK получения GIPHY broke*/
        Mockito
                .doReturn(broke)
                .when(developersGiphy)
                .getRandomBroke();
        /*Создаём MOCK получения GIPHY RICH*/
        Mockito
                .doReturn(rich)
                .when(developersGiphy)
                .getRandomRich();

        /*Создаём объект с которым будем сравнивать результат*/
        GiphyUrlDto giphyUrlDto = new GiphyUrlDto(
                new ObjectMapper()
                        .readTree(developersGiphy.getRandomBroke())
                        .get("data")
                        .get("images")
                        .get("original")
                        .get("url")
                        .asText());

        /*Проверяем будут ли URL адреса одинаковые */
        Assertions.assertEquals(giphyUrlDto.getUrl(), currencyService.getCurrency("RUB").getUrl());
        /*Проверяем если на вход передаётся значение которого нет в JSON, должен вернуть ошибочный URL*/
        Assertions.assertEquals(ErrorDto.getERROR(), currencyService.getCurrency("AAA").getUrl());
    }

    /*JSON BROKE*/
    private String broke = "{\"data\":{\"type\":\"gif\",\"id\":\"3ov9jUoOaeN6YnKM7e\",\"url\":\"https:\\/\\/giphy.com\\/gifs\\/loop-3d-wall-3ov9jUoOaeN6YnKM7e\",\"slug\":\"loop-3d-wall-3ov9jUoOaeN6YnKM7e\",\"bitly_gif_url\":\"http:\\/\\/gph.is\\/2gA4dsF\",\"bitly_url\":\"http:\\/\\/gph.is\\/2gA4dsF\",\"embed_url\":\"https:\\/\\/giphy.com\\/embed\\/3ov9jUoOaeN6YnKM7e\",\"username\":\"konczakowski\",\"source\":\"\",\"title\":\"vanish prison break GIF by Feliks Tomasz Konczakowski\",\"rating\":\"g\",\"content_url\":\"\",\"source_tld\":\"\",\"source_post_url\":\"\",\"is_sticker\":0,\"import_datetime\":\"2017-09-05 00:52:36\",\"trending_datetime\":\"0000-00-00 00:00:00\",\"images\":{\"hd\":{\"height\":\"1024\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-hd.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-hd.mp4&ct=g\",\"mp4_size\":\"514934\",\"width\":\"712\"},\"fixed_width_still\":{\"height\":\"287\",\"size\":\"14394\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w_s.gif&ct=g\",\"width\":\"200\"},\"fixed_height_downsampled\":{\"height\":\"200\",\"size\":\"59672\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200_d.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200_d.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200_d.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200_d.webp&ct=g\",\"webp_size\":\"27840\",\"width\":\"139\"},\"preview_gif\":{\"height\":\"147\",\"size\":\"45124\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-preview.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-preview.gif&ct=g\",\"width\":\"102\"},\"preview\":{\"height\":\"274\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-preview.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-preview.mp4&ct=g\",\"mp4_size\":\"17578\",\"width\":\"190\"},\"fixed_height_small\":{\"height\":\"100\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100.mp4&ct=g\",\"mp4_size\":\"11917\",\"size\":\"149212\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100.webp&ct=g\",\"webp_size\":\"34196\",\"width\":\"70\"},\"downsized\":{\"height\":\"480\",\"size\":\"1945381\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.gif&ct=g\",\"width\":\"334\"},\"fixed_width_downsampled\":{\"height\":\"287\",\"size\":\"111585\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w_d.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w_d.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w_d.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w_d.webp&ct=g\",\"webp_size\":\"49430\",\"width\":\"200\"},\"fixed_width\":{\"height\":\"287\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w.mp4&ct=g\",\"mp4_size\":\"50424\",\"size\":\"718075\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200w.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200w.webp&ct=g\",\"webp_size\":\"150306\",\"width\":\"200\"},\"downsized_still\":{\"height\":\"480\",\"size\":\"1945381\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy_s.gif&ct=g\",\"width\":\"334\"},\"downsized_medium\":{\"height\":\"480\",\"size\":\"1945381\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.gif&ct=g\",\"width\":\"334\"},\"original_mp4\":{\"height\":\"690\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.mp4&ct=g\",\"mp4_size\":\"200065\",\"width\":\"480\"},\"downsized_large\":{\"height\":\"480\",\"size\":\"1945381\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.gif&ct=g\",\"width\":\"334\"},\"preview_webp\":{\"height\":\"310\",\"size\":\"49262\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-preview.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-preview.webp&ct=g\",\"width\":\"216\"},\"original\":{\"frames\":\"53\",\"hash\":\"7edb70abca0768b41737ad275ea626b4\",\"height\":\"480\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.mp4&ct=g\",\"mp4_size\":\"200065\",\"size\":\"1945381\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy.webp&ct=g\",\"webp_size\":\"240890\",\"width\":\"334\"},\"original_still\":{\"height\":\"480\",\"size\":\"91437\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy_s.gif&ct=g\",\"width\":\"334\"},\"fixed_height_small_still\":{\"height\":\"100\",\"size\":\"3611\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100_s.gif&ct=g\",\"width\":\"70\"},\"fixed_width_small\":{\"height\":\"144\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100w.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100w.mp4&ct=g\",\"mp4_size\":\"19521\",\"size\":\"250736\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100w.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100w.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100w.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100w.webp&ct=g\",\"webp_size\":\"58002\",\"width\":\"100\"},\"looping\":{\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-loop.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-loop.mp4&ct=g\",\"mp4_size\":\"1281570\"},\"downsized_small\":{\"height\":\"480\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/giphy-downsized-small.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=giphy-downsized-small.mp4&ct=g\",\"mp4_size\":\"152074\",\"width\":\"334\"},\"fixed_width_small_still\":{\"height\":\"144\",\"size\":\"5540\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/100w_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=100w_s.gif&ct=g\",\"width\":\"100\"},\"fixed_height_still\":{\"height\":\"200\",\"size\":\"11229\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200_s.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200_s.gif&ct=g\",\"width\":\"139\"},\"fixed_height\":{\"height\":\"200\",\"mp4\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200.mp4?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200.mp4&ct=g\",\"mp4_size\":\"30506\",\"size\":\"558580\",\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200.gif?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200.gif&ct=g\",\"webp\":\"https:\\/\\/media2.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/200.webp?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=200.webp&ct=g\",\"webp_size\":\"105804\",\"width\":\"139\"},\"480w_still\":{\"url\":\"https:\\/\\/media3.giphy.com\\/media\\/3ov9jUoOaeN6YnKM7e\\/480w_s.jpg?cid=74747ef0d7a1ecdcf573512f575d05fca168d0b84cb6081d&rid=480w_s.jpg&ct=g\",\"width\":\"480\",\"height\":\"690\"}},\"user\":{\"avatar_url\":\"https:\\/\\/media3.giphy.com\\/avatars\\/konczakowski\\/9uZChMJFacgy.gif\",\"banner_image\":\"https:\\/\\/media3.giphy.com\\/headers\\/konczakowski\\/zMtcquwDt3tH.gif\",\"banner_url\":\"https:\\/\\/media3.giphy.com\\/headers\\/konczakowski\\/zMtcquwDt3tH.gif\",\"profile_url\":\"https:\\/\\/giphy.com\\/konczakowski\\/\",\"username\":\"konczakowski\",\"display_name\":\"Feliks Tomasz Konczakowski\",\"description\":\"I am a Polish visual artist.\\r\\ni_m_a_g_o@outlook.com\",\"is_verified\":true,\"website_url\":\"http:\\/\\/konczakowski.tumblr.com\",\"instagram_url\":\"https:\\/\\/instagram.com\\/konczakowski\"}},\"meta\":{\"msg\":\"OK\",\"status\":200,\"response_id\":\"d7a1ecdcf573512f575d05fca168d0b84cb6081d\"}}";
    /*JSON RICH*/
    private String rich = "{\"data\":{\"type\":\"gif\",\"id\":\"jVIALYcQQt76eWw8fj\",\"url\":\"https:\\/\\/giphy.com\\/gifs\\/guardian-school-power-education-jVIALYcQQt76eWw8fj\",\"slug\":\"guardian-school-power-education-jVIALYcQQt76eWw8fj\",\"bitly_gif_url\":\"https:\\/\\/gph.is\\/g\\/ZkNn19A\",\"bitly_url\":\"https:\\/\\/gph.is\\/g\\/ZkNn19A\",\"embed_url\":\"https:\\/\\/giphy.com\\/embed\\/jVIALYcQQt76eWw8fj\",\"username\":\"guardian\",\"source\":\"https:\\/\\/www.youtube.com\\/watch?v=pN36jVSp1x0\",\"title\":\"Public School GIF by guardian\",\"rating\":\"g\",\"content_url\":\"\",\"source_tld\":\"www.youtube.com\",\"source_post_url\":\"https:\\/\\/www.youtube.com\\/watch?v=pN36jVSp1x0\",\"is_sticker\":0,\"import_datetime\":\"2019-09-04 15:24:19\",\"trending_datetime\":\"0000-00-00 00:00:00\",\"images\":{\"fixed_width_still\":{\"height\":\"113\",\"size\":\"15073\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w_s.gif&ct=g\",\"width\":\"200\"},\"preview_gif\":{\"height\":\"50\",\"size\":\"47226\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy-preview.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy-preview.gif&ct=g\",\"width\":\"89\"},\"fixed_height_downsampled\":{\"height\":\"200\",\"size\":\"180668\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200_d.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200_d.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200_d.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200_d.webp&ct=g\",\"webp_size\":\"124680\",\"width\":\"356\"},\"preview\":{\"height\":\"146\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy-preview.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy-preview.mp4&ct=g\",\"mp4_size\":\"39734\",\"width\":\"259\"},\"fixed_height_small\":{\"height\":\"100\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100.mp4&ct=g\",\"mp4_size\":\"50809\",\"size\":\"150968\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100.webp&ct=g\",\"webp_size\":\"85504\",\"width\":\"178\"},\"downsized\":{\"height\":\"270\",\"size\":\"893965\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.gif&ct=g\",\"width\":\"480\"},\"fixed_width_downsampled\":{\"height\":\"113\",\"size\":\"85814\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w_d.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w_d.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w_d.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w_d.webp&ct=g\",\"webp_size\":\"48442\",\"width\":\"200\"},\"fixed_width\":{\"height\":\"113\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w.mp4&ct=g\",\"mp4_size\":\"54445\",\"size\":\"206399\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200w.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200w.webp&ct=g\",\"webp_size\":\"99740\",\"width\":\"200\"},\"downsized_still\":{\"height\":\"270\",\"size\":\"893965\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy_s.gif&ct=g\",\"width\":\"480\"},\"downsized_medium\":{\"height\":\"270\",\"size\":\"893965\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.gif&ct=g\",\"width\":\"480\"},\"original_mp4\":{\"height\":\"270\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.mp4&ct=g\",\"mp4_size\":\"167283\",\"width\":\"480\"},\"downsized_large\":{\"height\":\"270\",\"size\":\"893965\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.gif&ct=g\",\"width\":\"480\"},\"preview_webp\":{\"height\":\"88\",\"size\":\"47820\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy-preview.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy-preview.webp&ct=g\",\"width\":\"156\"},\"original\":{\"frames\":\"18\",\"hash\":\"ccbc5f228d1d26dd49fe007614c49f06\",\"height\":\"270\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.mp4&ct=g\",\"mp4_size\":\"167283\",\"size\":\"893965\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy.webp&ct=g\",\"webp_size\":\"298872\",\"width\":\"480\"},\"original_still\":{\"height\":\"270\",\"size\":\"67964\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy_s.gif&ct=g\",\"width\":\"480\"},\"fixed_height_small_still\":{\"height\":\"100\",\"size\":\"12223\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100_s.gif&ct=g\",\"width\":\"178\"},\"fixed_width_small\":{\"height\":\"57\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100w.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100w.mp4&ct=g\",\"mp4_size\":\"23521\",\"size\":\"53327\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100w.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100w.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100w.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100w.webp&ct=g\",\"webp_size\":\"36378\",\"width\":\"100\"},\"looping\":{\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy-loop.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy-loop.mp4&ct=g\",\"mp4_size\":\"1847301\"},\"downsized_small\":{\"height\":\"270\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/giphy-downsized-small.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=giphy-downsized-small.mp4&ct=g\",\"mp4_size\":\"167283\",\"width\":\"480\"},\"fixed_width_small_still\":{\"height\":\"57\",\"size\":\"4611\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/100w_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=100w_s.gif&ct=g\",\"width\":\"100\"},\"fixed_height_still\":{\"height\":\"200\",\"size\":\"30765\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200_s.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200_s.gif&ct=g\",\"width\":\"356\"},\"fixed_height\":{\"height\":\"200\",\"mp4\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200.mp4?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200.mp4&ct=g\",\"mp4_size\":\"117796\",\"size\":\"403479\",\"url\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200.gif?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200.gif&ct=g\",\"webp\":\"https:\\/\\/media1.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/200.webp?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=200.webp&ct=g\",\"webp_size\":\"216832\",\"width\":\"356\"},\"480w_still\":{\"url\":\"https:\\/\\/media2.giphy.com\\/media\\/jVIALYcQQt76eWw8fj\\/480w_s.jpg?cid=74747ef02253c17be7ed921e4b23facb6073d8ef75762a71&rid=480w_s.jpg&ct=g\",\"width\":\"480\",\"height\":\"270\"}},\"user\":{\"avatar_url\":\"https:\\/\\/media3.giphy.com\\/avatars\\/guardian\\/4IjmDHf5Vaue.png\",\"banner_image\":\"\",\"banner_url\":\"\",\"profile_url\":\"https:\\/\\/giphy.com\\/guardian\\/\",\"username\":\"guardian\",\"display_name\":\"The Guardian\",\"description\":\"Our uniques stories in gif form.\",\"is_verified\":true,\"website_url\":\"https:\\/\\/www.theguardian.com\",\"instagram_url\":\"https:\\/\\/instagram.com\\/guardian\"}},\"meta\":{\"msg\":\"OK\",\"status\":200,\"response_id\":\"2253c17be7ed921e4b23facb6073d8ef75762a71\"}}";
    /*JSON Валюты за вчера*/
    private String currencyForYesterday = "{\n" +
            "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "  \"license\": \"https://openexchangerates.org/license\",\n" +
            "  \"timestamp\": 1654905582,\n" +
            "  \"base\": \"USD\",\n" +
            "  \"rates\": {\n" +
            "    \"AED\": 3.673,\n" +
            "    \"AFN\": 88.999995,\n" +
            "    \"ALL\": 112.95,\n" +
            "    \"AMD\": 434.50877,\n" +
            "    \"ANG\": 1.803037,\n" +
            "    \"AOA\": 433.3094,\n" +
            "    \"ARS\": 121.783408,\n" +
            "    \"AUD\": 1.418179,\n" +
            "    \"AWG\": 1.8005,\n" +
            "    \"AZN\": 1.7,\n" +
            "    \"BAM\": 1.841084,\n" +
            "    \"BBD\": 2,\n" +
            "    \"BDT\": 93.025375,\n" +
            "    \"BGN\": 1.859065,\n" +
            "    \"BHD\": 0.376878,\n" +
            "    \"BIF\": 2031,\n" +
            "    \"BMD\": 1,\n" +
            "    \"BND\": 1.381837,\n" +
            "    \"BOB\": 6.888033,\n" +
            "    \"BRL\": 4.985435,\n" +
            "    \"BSD\": 1,\n" +
            "    \"BTC\": 0.000034393948,\n" +
            "    \"BTN\": 77.830813,\n" +
            "    \"BWP\": 11.918938,\n" +
            "    \"BYN\": 3.377059,\n" +
            "    \"BZD\": 2.016565,\n" +
            "    \"CAD\": 1.278377,\n" +
            "    \"CDF\": 2005,\n" +
            "    \"CHF\": 0.987982,\n" +
            "    \"CLF\": 0.03061,\n" +
            "    \"CLP\": 844.63,\n" +
            "    \"CNH\": 6.7317,\n" +
            "    \"CNY\": 6.7089,\n" +
            "    \"COP\": 3879.558846,\n" +
            "    \"CRC\": 686.914474,\n" +
            "    \"CUC\": 1,\n" +
            "    \"CUP\": 25.75,\n" +
            "    \"CVE\": 103.875,\n" +
            "    \"CZK\": 23.485,\n" +
            "    \"DJF\": 178.5375,\n" +
            "    \"DKK\": 7.0716,\n" +
            "    \"DOP\": 55.15,\n" +
            "    \"DZD\": 146.508724,\n" +
            "    \"EGP\": 18.707929,\n" +
            "    \"ERN\": 15.000001,\n" +
            "    \"ETB\": 51.71,\n" +
            "    \"EUR\": 0.950638,\n" +
            "    \"FJD\": 2.1692,\n" +
            "    \"FKP\": 0.812018,\n" +
            "    \"GBP\": 0.812018,\n" +
            "    \"GEL\": 2.915,\n" +
            "    \"GGP\": 0.812018,\n" +
            "    \"GHS\": 7.9,\n" +
            "    \"GIP\": 0.812018,\n" +
            "    \"GMD\": 54.05,\n" +
            "    \"GNF\": 8827.5,\n" +
            "    \"GTQ\": 7.728354,\n" +
            "    \"GYD\": 209.419445,\n" +
            "    \"HKD\": 7.8497,\n" +
            "    \"HNL\": 24.545,\n" +
            "    \"HRK\": 7.1518,\n" +
            "    \"HTG\": 114.550275,\n" +
            "    \"HUF\": 378.493996,\n" +
            "    \"IDR\": 14614.95,\n" +
            "    \"ILS\": 3.39424,\n" +
            "    \"IMP\": 0.812018,\n" +
            "    \"INR\": 78.1778,\n" +
            "    \"IQD\": 1460.5,\n" +
            "    \"IRR\": 42300,\n" +
            "    \"ISK\": 131.86,\n" +
            "    \"JEP\": 0.812018,\n" +
            "    \"JMD\": 153.441337,\n" +
            "    \"JOD\": 0.709,\n" +
            "    \"JPY\": 134.43498488,\n" +
            "    \"KES\": 117.1,\n" +
            "    \"KGS\": 79.5048,\n" +
            "    \"KHR\": 4061,\n" +
            "    \"KMF\": 463.449689,\n" +
            "    \"KPW\": 900,\n" +
            "    \"KRW\": 1279.53,\n" +
            "    \"KWD\": 0.306722,\n" +
            "    \"KYD\": 0.833711,\n" +
            "    \"KZT\": 436.446749,\n" +
            "    \"LAK\": 14405,\n" +
            "    \"LBP\": 1523.57605,\n" +
            "    \"LKR\": 358.655797,\n" +
            "    \"LRD\": 151.99996,\n" +
            "    \"LSL\": 15.41,\n" +
            "    \"LYD\": 4.78,\n" +
            "    \"MAD\": 9.9575,\n" +
            "    \"MDL\": 19.018722,\n" +
            "    \"MGA\": 4015,\n" +
            "    \"MKD\": 58.000144,\n" +
            "    \"MMK\": 1852.306067,\n" +
            "    \"MNT\": 3103.3804,\n" +
            "    \"MOP\": 8.088452,\n" +
            "    \"MRU\": 36.52,\n" +
            "    \"MUR\": 44.095836,\n" +
            "    \"MVR\": 15.44,\n" +
            "    \"MWK\": 1019.5,\n" +
            "    \"MXN\": 19.97592,\n" +
            "    \"MYR\": 4.3935,\n" +
            "    \"MZN\": 63.899991,\n" +
            "    \"NAD\": 15.41,\n" +
            "    \"NGN\": 415.13,\n" +
            "    \"NIO\": 35.78,\n" +
            "    \"NOK\": 9.69488,\n" +
            "    \"NPR\": 124.529718,\n" +
            "    \"NZD\": 1.572317,\n" +
            "    \"OMR\": 0.384978,\n" +
            "    \"PAB\": 1,\n" +
            "    \"PEN\": 3.7785,\n" +
            "    \"PGK\": 3.525,\n" +
            "    \"PHP\": 53.032001,\n" +
            "    \"PKR\": 202,\n" +
            "    \"PLN\": 4.38145,\n" +
            "    \"PYG\": 6863.484957,\n" +
            "    \"QAR\": 3.641,\n" +
            "    \"RON\": 4.6991,\n" +
            "    \"RSD\": 111.651456,\n" +
            "    \"RUB\": 57.624893,\n" +
            "    \"RWF\": 1030.5,\n" +
            "    \"SAR\": 3.7493,\n" +
            "    \"SBD\": 8.113698,\n" +
            "    \"SCR\": 13.351835,\n" +
            "    \"SDG\": 456,\n" +
            "    \"SEK\": 10.031442,\n" +
            "    \"SGD\": 1.38794,\n" +
            "    \"SHP\": 0.812018,\n" +
            "    \"SLL\": 13130,\n" +
            "    \"SOS\": 582,\n" +
            "    \"SRD\": 21.771,\n" +
            "    \"SSP\": 130.26,\n" +
            "    \"STD\": 23263,\n" +
            "    \"STN\": 23.45,\n" +
            "    \"SVC\": 8.753588,\n" +
            "    \"SYP\": 2512.53,\n" +
            "    \"SZL\": 15.41,\n" +
            "    \"THB\": 34.70907,\n" +
            "    \"TJS\": 11.004845,\n" +
            "    \"TMT\": 3.5,\n" +
            "    \"TND\": 3.0445,\n" +
            "    \"TOP\": 2.314136,\n" +
            "    \"TRY\": 17.1158,\n" +
            "    \"TTD\": 6.799259,\n" +
            "    \"TWD\": 29.662,\n" +
            "    \"TZS\": 2331,\n" +
            "    \"UAH\": 29.559063,\n" +
            "    \"UGX\": 3757.776284,\n" +
            "    \"USD\": 1,\n" +
            "    \"UYU\": 39.391842,\n" +
            "    \"UZS\": 11022.5,\n" +
            "    \"VES\": 5.191,\n" +
            "    \"VND\": 23182.5,\n" +
            "    \"VUV\": 115.058332,\n" +
            "    \"WST\": 2.619417,\n" +
            "    \"XAF\": 623.577755,\n" +
            "    \"XAG\": 0.04568305,\n" +
            "    \"XAU\": 0.00053436,\n" +
            "    \"XCD\": 2.70255,\n" +
            "    \"XDR\": 0.730724,\n" +
            "    \"XOF\": 623.577755,\n" +
            "    \"XPD\": 0.00051692,\n" +
            "    \"XPF\": 113.441308,\n" +
            "    \"XPT\": 0.0010225,\n" +
            "    \"YER\": 250.249937,\n" +
            "    \"ZAR\": 15.87673,\n" +
            "    \"ZMW\": 16.932324,\n" +
            "    \"ZWL\": 322\n" +
            "  }\n" +
            "}";
    /*JSON валюты за сегодня*/
    private String latest = "{\n" +
            "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "  \"license\": \"https://openexchangerates.org/license\",\n" +
            "  \"timestamp\": 1654977609,\n" +
            "  \"base\": \"USD\",\n" +
            "  \"rates\": {\n" +
            "    \"AED\": 3.6731,\n" +
            "    \"AFN\": 88.999995,\n" +
            "    \"ALL\": 112.95,\n" +
            "    \"AMD\": 429.910968,\n" +
            "    \"ANG\": 1.803037,\n" +
            "    \"AOA\": 433.3094,\n" +
            "    \"ARS\": 121.78921,\n" +
            "    \"AUD\": 1.418038,\n" +
            "    \"AWG\": 1.8005,\n" +
            "    \"AZN\": 1.7,\n" +
            "    \"BAM\": 1.847074,\n" +
            "    \"BBD\": 2,\n" +
            "    \"BDT\": 93.651536,\n" +
            "    \"BGN\": 1.85946,\n" +
            "    \"BHD\": 0.376878,\n" +
            "    \"BIF\": 2071.295103,\n" +
            "    \"BMD\": 1,\n" +
            "    \"BND\": 1.381837,\n" +
            "    \"BOB\": 6.888033,\n" +
            "    \"BRL\": 4.987385,\n" +
            "    \"BSD\": 1,\n" +
            "    \"BTC\": 0.000034932614,\n" +
            "    \"BTN\": 77.830813,\n" +
            "    \"BWP\": 12.075306,\n" +
            "    \"BYN\": 3.377059,\n" +
            "    \"BZD\": 2.016565,\n" +
            "    \"CAD\": 1.278377,\n" +
            "    \"CDF\": 2005,\n" +
            "    \"CHF\": 0.987862,\n" +
            "    \"CLF\": 0.03061,\n" +
            "    \"CLP\": 832.424895,\n" +
            "    \"CNH\": 6.7317,\n" +
            "    \"CNY\": 6.7089,\n" +
            "    \"COP\": 3879.53985,\n" +
            "    \"CRC\": 686.914474,\n" +
            "    \"CUC\": 1,\n" +
            "    \"CUP\": 25.75,\n" +
            "    \"CVE\": 103.875,\n" +
            "    \"CZK\": 23.485,\n" +
            "    \"DJF\": 179.302154,\n" +
            "    \"DKK\": 7.07145,\n" +
            "    \"DOP\": 55.394566,\n" +
            "    \"DZD\": 146.504086,\n" +
            "    \"EGP\": 18.707929,\n" +
            "    \"ERN\": 15.000001,\n" +
            "    \"ETB\": 51.71,\n" +
            "    \"EUR\": 0.95057,\n" +
            "    \"FJD\": 2.1692,\n" +
            "    \"FKP\": 0.811919,\n" +
            "    \"GBP\": 0.811919,\n" +
            "    \"GEL\": 2.915,\n" +
            "    \"GGP\": 0.811919,\n" +
            "    \"GHS\": 7.981841,\n" +
            "    \"GIP\": 0.811919,\n" +
            "    \"GMD\": 54.05,\n" +
            "    \"GNF\": 8915.9099,\n" +
            "    \"GTQ\": 7.728354,\n" +
            "    \"GYD\": 209.419445,\n" +
            "    \"HKD\": 7.8498,\n" +
            "    \"HNL\": 24.74615,\n" +
            "    \"HRK\": 7.1518,\n" +
            "    \"HTG\": 115.321323,\n" +
            "    \"HUF\": 378.415,\n" +
            "    \"IDR\": 14614.95,\n" +
            "    \"ILS\": 3.39424,\n" +
            "    \"IMP\": 0.811919,\n" +
            "    \"INR\": 78.1768,\n" +
            "    \"IQD\": 1469.985201,\n" +
            "    \"IRR\": 42300,\n" +
            "    \"ISK\": 130.918436,\n" +
            "    \"JEP\": 0.811919,\n" +
            "    \"JMD\": 154.474248,\n" +
            "    \"JOD\": 0.709,\n" +
            "    \"JPY\": 134.415,\n" +
            "    \"KES\": 117.1,\n" +
            "    \"KGS\": 79.504802,\n" +
            "    \"KHR\": 4091.092543,\n" +
            "    \"KMF\": 463.449689,\n" +
            "    \"KPW\": 900,\n" +
            "    \"KRW\": 1279.28,\n" +
            "    \"KWD\": 0.306722,\n" +
            "    \"KYD\": 0.833711,\n" +
            "    \"KZT\": 439.380555,\n" +
            "    \"LAK\": 14496.894795,\n" +
            "    \"LBP\": 1522.814675,\n" +
            "    \"LKR\": 361.069746,\n" +
            "    \"LRD\": 151.99996,\n" +
            "    \"LSL\": 15.41,\n" +
            "    \"LYD\": 4.78,\n" +
            "    \"MAD\": 9.982791,\n" +
            "    \"MDL\": 19.191577,\n" +
            "    \"MGA\": 4015,\n" +
            "    \"MKD\": 58.000144,\n" +
            "    \"MMK\": 1852.306067,\n" +
            "    \"MNT\": 3103.3804,\n" +
            "    \"MOP\": 8.088452,\n" +
            "    \"MRU\": 36.52,\n" +
            "    \"MUR\": 44.098334,\n" +
            "    \"MVR\": 15.44,\n" +
            "    \"MWK\": 1028.920109,\n" +
            "    \"MXN\": 19.97682,\n" +
            "    \"MYR\": 4.3935,\n" +
            "    \"MZN\": 63.899991,\n" +
            "    \"NAD\": 15.41,\n" +
            "    \"NGN\": 417.979028,\n" +
            "    \"NIO\": 36.106676,\n" +
            "    \"NOK\": 9.71408,\n" +
            "    \"NPR\": 124.529718,\n" +
            "    \"NZD\": 1.569243,\n" +
            "    \"OMR\": 0.384978,\n" +
            "    \"PAB\": 1,\n" +
            "    \"PEN\": 3.78437,\n" +
            "    \"PGK\": 3.594505,\n" +
            "    \"PHP\": 53.059992,\n" +
            "    \"PKR\": 202,\n" +
            "    \"PLN\": 4.38115,\n" +
            "    \"PYG\": 6863.484957,\n" +
            "    \"QAR\": 3.641,\n" +
            "    \"RON\": 4.6991,\n" +
            "    \"RSD\": 111.55179,\n" +
            "    \"RUB\": 57.624893,\n" +
            "    \"RWF\": 1035.974208,\n" +
            "    \"SAR\": 3.752019,\n" +
            "    \"SBD\": 8.113698,\n" +
            "    \"SCR\": 13.351835,\n" +
            "    \"SDG\": 456,\n" +
            "    \"SEK\": 10.0292,\n" +
            "    \"SGD\": 1.38789,\n" +
            "    \"SHP\": 0.811919,\n" +
            "    \"SLL\": 13130,\n" +
            "    \"SOS\": 582.620984,\n" +
            "    \"SRD\": 21.771,\n" +
            "    \"SSP\": 130.26,\n" +
            "    \"STD\": 23263,\n" +
            "    \"STN\": 23.45,\n" +
            "    \"SVC\": 8.753588,\n" +
            "    \"SYP\": 2512.53,\n" +
            "    \"SZL\": 15.41,\n" +
            "    \"THB\": 34.70907,\n" +
            "    \"TJS\": 11.078817,\n" +
            "    \"TMT\": 3.5,\n" +
            "    \"TND\": 3.0445,\n" +
            "    \"TOP\": 2.314136,\n" +
            "    \"TRY\": 17.1158,\n" +
            "    \"TTD\": 6.799259,\n" +
            "    \"TWD\": 29.662,\n" +
            "    \"TZS\": 2331,\n" +
            "    \"UAH\": 29.559063,\n" +
            "    \"UGX\": 3716.499615,\n" +
            "    \"USD\": 1,\n" +
            "    \"UYU\": 39.391842,\n" +
            "    \"UZS\": 11022.5,\n" +
            "    \"VES\": 5.191,\n" +
            "    \"VND\": 23182.5,\n" +
            "    \"VUV\": 115.058332,\n" +
            "    \"WST\": 2.619417,\n" +
            "    \"XAF\": 623.533276,\n" +
            "    \"XAG\": 0.04568305,\n" +
            "    \"XAU\": 0.00053436,\n" +
            "    \"XCD\": 2.70255,\n" +
            "    \"XDR\": 0.729915,\n" +
            "    \"XOF\": 623.533276,\n" +
            "    \"XPD\": 0.00051692,\n" +
            "    \"XPF\": 113.433216,\n" +
            "    \"XPT\": 0.0010225,\n" +
            "    \"YER\": 250.249937,\n" +
            "    \"ZAR\": 15.85881,\n" +
            "    \"ZMW\": 17.046301,\n" +
            "    \"ZWL\": 322\n" +
            "  }\n" +
            "}";
}