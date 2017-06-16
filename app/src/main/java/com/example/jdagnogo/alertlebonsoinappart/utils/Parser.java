package com.example.jdagnogo.alertlebonsoinappart.utils;

import com.example.jdagnogo.alertlebonsoinappart.models.Appart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class Parser {
    private final static int MAX_NB_APPART = 10;

    public static List<Appart> parseHtml(Response<ResponseBody> response) throws IOException {
        Document document = Jsoup.parse(response.body().string());
        Elements ensemble = document.getElementsByClass("list_item");
        List<Appart> apparts = new ArrayList<Appart>();
        for (int i = 0; i < MAX_NB_APPART; i++) {

            String title = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_title").get(0).text();
            String imageUrl = "";
            if (0 == ensemble.get(i).getElementsByClass("item_image").get(0).getElementsByClass("lazyload").size()) {
                continue;
            }
            String price = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_price").get(0).text();
            if (0 == ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_absolute").size()) {
                continue;
            }
            String date = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_absolute").get(0).getElementsByClass("item_supp").get(0).text();

            Appart appart = new Appart(imageUrl, price, title, date, false);
            apparts.add(appart);
        }
        return apparts;
    }
}
