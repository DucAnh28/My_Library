package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkDownloader {

    public static List<String> getLinks(String url) {
        try {
            List<String> links = new ArrayList<>();
            Document document = Jsoup.connect(url).get();
            ArrayList<Element> getClass = document.getElementsByClass("wp_box");

            for (Element element : getClass) {
                String image = "";
                String imageUrl = element.getElementsByTag("a").first().absUrl("href");
                Document document1 = Jsoup.connect(imageUrl).get();

                Element page2Class = document1.getElementById("Desktop");
//                String image = page2Class.getElementsByTag("a").first().absUrl("href");
//                try {
//                    image = page2Class.getElementById("4k").getElementsByTag("a").first().absUrl("href");
//                } catch (Exception e) {
//                    image = page2Class.getElementById("s_4k").getElementsByTag("a").first().absUrl("href");
//                }

                Element linkWithText = null;
                Elements linksx = page2Class.getElementsByTag("a");
                for (Element link : linksx) {
                    if (link.text().contains("3840x2160")) {
                        linkWithText = link;
                        break;
                    }
                }
                if (linkWithText != null) {
                    image = linkWithText.absUrl("href");
                    System.out.println("Link gốc: " + image);
                } else {
                    System.out.println("Không tìm thấy thẻ <a> với văn bản '3840x2160'");
                }

                links.add(image);
//                URL xx = new URL(image);
//                URLConnection conn = xx.openConnection();
//                conn.getContent()
//                System.out.println("Link " + imageUrl + "goc : " + image);
//                System.out.println("Link gốc: " + image);
//                try {
//                    String tempUrl;
//                    Pattern pattern;
//
//                    if (image.contains("@1@")) {
//                        tempUrl = image.replace("https://img.uhdpaper.com/", "https://image-1.uhdpaper.com/");
//                        pattern = Pattern.compile("(\\d+)@1@(\\w+)-pc-4k.jpg");
//                        Matcher matcher = pattern.matcher(tempUrl);
//                        String newUrl = matcher.replaceAll("4k-wallpaper-uhdpaper.com-$1@1@$2.jpg").replaceAll("-pc-4k", "");
//                        System.out.println("-----------------------------");
//                        System.out.println("New link: " + newUrl);
//                        System.out.println("-----------------------------");
//                        links.add(newUrl);
//                    } else if (image.contains("@0@")) {
//                        tempUrl = image.replace("https://img.uhdpaper.com/", "https://image-0.uhdpaper.com/");
//                        pattern = Pattern.compile("(\\d+)@0@(\\w+)-pc-4k.jpg");
//                        Matcher matcher = pattern.matcher(tempUrl);
//                        String newUrl = matcher.replaceAll("4k-wallpaper-uhdpaper.com-$1@0@$2.jpg").replaceAll("-pc-4k", "");
//                        System.out.println("-----------------------------");
//                        System.out.println("New link: " + newUrl);
//                        System.out.println("-----------------------------");
//                        links.add(newUrl);
//                    } else {
//                        System.out.println("URL không khớp với bất kỳ mẫu nào.");
//                    }
//                } catch (Exception e) {
//                    System.out.println("Đã xảy ra lỗi: " + e.getMessage());
//                }

            }

            return links;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
