package service;

import java.io.File;
import java.util.Map;

public class FileUploadService {

    public String upload(File file) {

        try {

            Map<?, ?> result =
                    CloudinaryService
                            .getCloudinary()
                            .uploader()
                            .upload(
                                    file,
                                    null
                            );

            return result.get("secure_url").toString();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

}