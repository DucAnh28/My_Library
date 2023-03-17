package org.example.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Crawler {

//    public static void main(String[] args) throws IOException {
//        String urlRes = "https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-8k-wallpaper-uhdpaper.com-836@0@h.jpg";
//        String test = "https://www.google.com.vn/url?sa=i&url=https%3A%2F%2Fxoso.com.vn%2Fxo-so-mien-bac%2Fxsmb-p1.html&psig=AOvVaw0UDwyc7tfoZ-dvQto-moBk&ust=1674830558079000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCNDs7ZW85fwCFQAAAAAdAAAAABAD";
////        try(InputStream in = new URL(url).openStream()){
////            Files.copy(in, Paths.get("image.jpg"));
////        }
//        try {
//            URL url = new URL(test);
//            InputStream in = url.openStream();
//            OutputStream out = new BufferedOutputStream(new FileOutputStream("test2.jpg"));
//            for (int b; (b = in.read()) != -1; ) {
//                out.write(b);
//            }
//            out.close();
//            in.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
//        }
//
//    }

    public static void main(String[] args) throws IOException {
        String imageUrl = "https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-4k-wallpaper-uhdpaper.com-836@0@h.jpg";
        String destinationFile = "itzy-cheshire-all-members-4k-wallpaper-uhdpaper.com-836.jpg";

        URL url = new URL(imageUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpURLConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();
            inputStream.close();
            System.out.println("Image saved to " + destinationFile);
        } else {
            System.out.println("Error: " + httpURLConnection.getResponseMessage());
        }
    }
}

