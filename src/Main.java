import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
//        String url = "https://www.uhdpaper.com/search?q=itzy&by-date=true";
//        request(url);

        saveImage("https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-8k-wallpaper-uhdpaper.com-836@0@h.jpg","downloaded.jpg");
    }

    public static void request(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            ArrayList<Element> getClass = document.getElementsByClass("wp_box");
            System.out.println(getClass.get(0).getElementsByTag("a").first().absUrl("href"));
            String imageUrl = getClass.get(0).getElementsByTag("a").first().absUrl("href");

            Document document1 = Jsoup.connect(imageUrl).get();
            Element page2Class = document1.getElementById("Desktop");
            System.out.println(page2Class.getElementsByTag("a").get(0).absUrl("href"));
            String image = page2Class.getElementsByTag("a").get(0).absUrl("href");


            saveImg(image, "test2.jpg");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveImg(String src, String destinationFile) throws IOException {
        try {
            URL url = new URL(src);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream("image" + "\\" + destinationFile));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}