package com.example.jdagnogo.alertlebonsoinappart.utils;

import com.example.jdagnogo.alertlebonsoinappart.models.Appart;
import com.example.jdagnogo.alertlebonsoinappart.models.AppartDetails;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class Parser {
    private final static int MAX_NB_APPART = 10;
    private final static String ITEM_IMAGE = "item_image";
    private final static String ITEM_INFO = "item_infos";
    private final static String ITEM_ABSOLUTE = "item_absolute";
    private final static String LAZYLOAD = "lazyload";

    public static List<Appart> parseHtml(Response<ResponseBody> response) throws IOException {
        Document document = Jsoup.parse(response.body().string());
        Elements ensemble = document.getElementsByClass("list_item");
        List<Appart> apparts = new ArrayList<Appart>();
        if (ensemble.size() > 0) {
            for (int i = 0; i < MAX_NB_APPART; i++) {

                String title = ensemble.get(i).getElementsByClass(ITEM_INFO).get(0).getElementsByClass("item_title").get(0).text();
                String imageUrl = "";
                if (0 == ensemble.get(i).getElementsByClass(ITEM_IMAGE).get(0).getElementsByClass(LAZYLOAD).size()) {
                    continue;
                } else {
                    imageUrl = ensemble.get(i).getElementsByClass(ITEM_IMAGE).get(0).getElementsByClass(LAZYLOAD).get(0).attr("data-imgsrc");
                }
                String price = ensemble.get(i).getElementsByClass(ITEM_INFO).get(0).getElementsByClass("item_price").get(0).text();
                if (0 == ensemble.get(i).getElementsByClass(ITEM_INFO).get(0).getElementsByClass(ITEM_ABSOLUTE).size()) {
                    continue;
                }
                String date = ensemble.get(i).getElementsByClass(ITEM_INFO).get(0).getElementsByClass(ITEM_ABSOLUTE).get(0).getElementsByClass("item_supp").get(0).text();
                String dataInfo = ensemble.get(i).getElementsByAttribute("href").attr("data-info");
                String appartId = "";
                try {
                    JSONObject jObject = new JSONObject(dataInfo);
                    appartId = jObject.getString("ad_listid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Appart appart = new Appart(imageUrl, price, title, date, false, appartId);
                apparts.add(appart);
            }
        }
        return apparts;
    }


    public static AppartDetails parseAppartDetailed(Response<ResponseBody> response, Appart appart) throws IOException {
        AppartDetails appartDetails = new AppartDetails(appart);

        Document document = Jsoup.parse(response.body().string());
        Elements allInformation = document.getElementsByClass("value");
        String nbPiece = allInformation.get(3).toString();
        nbPiece = nbPiece.substring(nbPiece.indexOf("\">") + 2, nbPiece.indexOf("</"));
        appartDetails.setNbRoom(String.format("%s pièce(s)", nbPiece));


        String surface = allInformation.get(5).toString();
        if(0<surface.indexOf("<su") && 0<surface.indexOf("\">")){
            surface = surface.substring(surface.indexOf("\">") + 2, surface.indexOf("<su"));
            appartDetails.setSurface(String.format("%s²", surface));
        }else {
            appartDetails.setSurface("???");
        }


//// TODO: 17/08/2017 check if it contains images
        String allImagesString = document.getElementsByTag("script").get(11).toString();
        Pattern pattern = Pattern.compile("(\\//)(.*?)(\\.jpg)");
        Matcher matcher = pattern.matcher(allImagesString);
        List<String> listMatches = new ArrayList<String>();

        while (matcher.find()) {
            String group = matcher.group(2);
            if (group.contains("ad-large")) {
                listMatches.add(String.format("http:%s.jpg", group));
            }

        }
        appartDetails.setImgsUrl(listMatches);

        return appartDetails;
    }
}
