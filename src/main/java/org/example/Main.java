package org.example;

import org.example.service.ImageDownloaderV2;
import org.example.service.LinkDownloader;

import java.io.File;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {
//        for (int i = 1; i <= 6; i++) {
//            String url = String.format("http://localhost:63342/DataCrawler/resource/page%s.html?_ijt=1nrclr3dke0bclmke5q1imsh78&by-date=true", i);
//            var links = LinkDownloader.getLinks(url);
//            links.forEach(System.out::println);
//        }

        var url = String.format("https://www.uhdpaper.com/search?q=ITZY+-+Kpop&max-results=50&start=%s&by-date=true", 200);
        var links = LinkDownloader.getLinks(url);
        int i = 0;
        for (var link : links) {
            System.out.println(i++ + ": " + link);
            File downloadedImage = ImageDownloaderV2.downloadImage(link, "./image/" + UUID.randomUUID() + ".jpg");
        }


//        var links = LinkDownloader.getLinks(url);
//        links.forEach(System.out::println);
//        File downloadedImage = AdvancedImageDownloader.downloadImage(
//                imageUrl,
//                "./image/itzy_wallpaper.jpg"
//        );
    }
}