package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ImageDownloader {
//        try {
//            // Replace the URL with the actual URL of the file you want to download
//            URL url = new URL("https://www.peakpx.com/en/hd-wallpaper-desktop-aeomu/download");
//            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//            int responseCode = httpConn.getResponseCode();
//
//            // Check if the response code is a success code (i.e. 200)
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                String fileName = "";
//                String disposition = httpConn.getHeaderField("Content-Disposition");
//
//                // Extract the file name from the Content-Disposition header
//                if (disposition != null) {
//                    int index = disposition.indexOf("filename=");
//                    if (index > 0) {
//                        fileName = disposition.substring(index + 10, disposition.length() - 1);
//                    }
//                }
//
//                // If the file name was not found in the Content-Disposition header, use the last part of the URL
//                if (fileName.equals("")) {
//                    fileName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
//                }
//
//                // Create an input stream to read the contents of the file
//                InputStream inputStream = httpConn.getInputStream();
//                String saveFilePath = fileName;
//
//                // Create an output stream to save the file to the local file system
//                FileOutputStream outputStream = new FileOutputStream(saveFilePath);
//
//                // Read the contents of the file from the input stream and write them to the output stream
//                int bytesRead = -1;
//                byte[] buffer = new byte[4096];
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                // Close the input and output streams
//                outputStream.close();
//                inputStream.close();
//
//                System.out.println("File downloaded successfully");
//            } else {
//                System.out.println("Failed to download file");
//            }
//            httpConn.disconnect();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

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

