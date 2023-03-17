package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
//        String imageUrl = "https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-4k-wallpaper-uhdpaper.com-836@0@h";
//        File file = new File("image.jpg");
//        URLConnection connection = new URL(imageUrl).openConnection();
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//        try (
//                InputStream in = connection.getInputStream();
//                OutputStream out = new FileOutputStream(file)) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = in.read(buffer)) != -1) {
//                out.write(buffer, 0, bytesRead);
//            }
//        }

        String imageUrl = "https://image-0.uhdpaper.com/wallpaper/itzy-cheshire-all-members-4k-wallpaper-uhdpaper.com-836@0@h.jpg";
        try {
            URL url = new URL(imageUrl);
            InputStream in = url.openStream();
            BufferedImage image = ImageIO.read(in);
            in.close();

            String filename = "itzy-wallpaper.jpg";
            File outputImage = new File(filename);

            // Lưu ảnh với độ phân giải cao nhất
            ImageIO.write(image, "jpg", outputImage);

            System.out.println("Tải ảnh thành công và lưu vào file " + filename);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
