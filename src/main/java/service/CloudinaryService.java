package service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryService {

    private static final Cloudinary cloudinary =
            new Cloudinary(ObjectUtils.asMap(

                    "cloud_name",
                    System.getenv().getOrDefault(
                            "CLOUDINARY_CLOUD_NAME",
                            "lasmlkpx"
                    ),

                    "api_key",
                    System.getenv().getOrDefault(
                            "CLOUDINARY_API_KEY",
                            "YOUR_API_KEY"
                    ),

                    "api_secret",
                    System.getenv().getOrDefault(
                            "CLOUDINARY_API_SECRET",
                            "YOUR_API_SECRET"
                    ),

                    "secure",
                    true

            ));

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }

}