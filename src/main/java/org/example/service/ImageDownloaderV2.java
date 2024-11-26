package org.example.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import javax.imageio.ImageIO;

public class ImageDownloaderV2 {
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
            connection.setConnectTimeout(20000); // Tăng thời gian chờ kết nối
            connection.setReadTimeout(20000);    // Tăng thời gian đọc dữ liệu

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

            // Tải và lưu file tạm thời
            File tempFile = new File("temp_image" + fileExtension);
            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(tempFile)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Kiểm tra xem file tải về có phải là ảnh hợp lệ không
            BufferedImage image = ImageIO.read(tempFile);
            if (image == null) {
                System.err.println("Dữ liệu tải về không phải là hình ảnh hợp lệ.");
                return null;
            }

            // Di chuyển file tạm thời đến vị trí lưu chính thức
            Files.move(tempFile.toPath(), saveFile.toPath());

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
}
