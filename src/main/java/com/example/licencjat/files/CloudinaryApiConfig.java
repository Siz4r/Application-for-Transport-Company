package com.example.licencjat.files;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryApiConfig {
    public static Map getConnection() throws IOException {
        var cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "siz4rimag",
                "api_key", "551523122998615",
                "api_secret", "XOw2VK3j0A75TEGbZf1Z8ABjK4k"));

        return cloudinary.uploader().upload(new File("eloelo"), ObjectUtils.emptyMap());
    }
}
