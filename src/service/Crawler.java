package service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Crawler {

    public static void main(String[] args) throws IOException {
        String urlRes = "https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-8k-wallpaper-uhdpaper.com-836@0@h.jpg";
        String test = "https://www.google.com.vn/url?sa=i&url=https%3A%2F%2Fxoso.com.vn%2Fxo-so-mien-bac%2Fxsmb-p1.html&psig=AOvVaw0UDwyc7tfoZ-dvQto-moBk&ust=1674830558079000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCNDs7ZW85fwCFQAAAAAdAAAAABAD";
//        try(InputStream in = new URL(url).openStream()){
//            Files.copy(in, Paths.get("image.jpg"));
//        }
        try {
            URL url = new URL(test);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream("test2.jpg"));
            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }

    }
}
