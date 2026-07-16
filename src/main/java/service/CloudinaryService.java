package service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryService {

    private static final Cloudinary cloudinary =
            new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "lasmlkpx",
                    "api_key", "126181331228455",
                    "api_secret", "gldzT0P88fi7pggbl84k4DHT_h8",
                    "secure", true
            ));

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}