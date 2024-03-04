package com.example.clothes_2nd.util;

import com.example.clothes_2nd.model.File;
import org.springframework.stereotype.Component;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;

@Component
public class UploadUtil {
    public static final String IMAGE_UPLOAD_FOLDER = "product";
    public Map buildImageUploadParams(File file) {
        if (file == null || file.getId() == null)
            throw new RuntimeException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, file.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

}
