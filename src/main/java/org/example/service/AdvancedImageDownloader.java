package org.example.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class AdvancedImageDownloader {
    /**
     * Tải ảnh từ URL với nhiều cài đặt nâng cao
     * @param imageUrl URL của ảnh
     * @param savePath Đường dẫn lưu ảnh
     * @return File ảnh đã tải
     */
    public static File downloadImage(String imageUrl, String savePath) {
        try {
            // Tạo URL connection
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Cấu hình headers để mô phỏng trình duyệt
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            connection.setRequestProperty("Accept", "image/webp,*/*");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Referer", url.getProtocol() + "://" + url.getHost());
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            // Kiểm tra kết nối
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Lỗi kết nối: Mã trạng thái " + responseCode);
                return null;
            }

            // Xác định đuôi file từ Content-Type
            String contentType = connection.getContentType();
            String fileExtension = getFileExtension(contentType);

            // Điều chỉnh đường dẫn lưu nếu chưa có đuôi file
            if (!savePath.contains(".")) {
                savePath += fileExtension;
            }

            // Tạo thư mục nếu chưa tồn tại
            File saveFile = new File(savePath);
            Files.createDirectories(saveFile.getParentFile().toPath());

            // Tải và lưu file
            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(saveFile)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Tải ảnh thành công: " + savePath);
            return saveFile;

        } catch (Exception e) {
            System.err.println("Lỗi khi tải ảnh: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Xác định phần mở rộng file từ Content-Type
     * @param contentType Loại nội dung
     * @return Phần mở rộng file
     */
    private static String getFileExtension(String contentType) {
        if (contentType == null) return ".jpg";

        contentType = contentType.toLowerCase();
        if (contentType.contains("jpeg") || contentType.contains("jpg")) return ".jpg";
        if (contentType.contains("png")) return ".png";
        if (contentType.contains("gif")) return ".gif";
        if (contentType.contains("webp")) return ".webp";

        return ".jpg"; // Mặc định
    }

    public static void main(String[] args) {
        // URL ảnh ITZY bạn cung cấp
        String imageUrl = "https://image-1.uhdpaper.com/wallpaper/itzy-kill-my-doubt-concept-1-all-members-4k-wallpaper-uhdpaper.com-245@1@l.jpg";

        // Tải ảnh vào thư mục downloads
        File downloadedImage = downloadImage(
                imageUrl,
                "./image/itzy_wallpaper.jpg"
        );

        // Kiểm tra kết quả
        if (downloadedImage != null) {
            System.out.println("Đường dẫn ảnh: " + downloadedImage.getAbsolutePath());
        }
    }
}