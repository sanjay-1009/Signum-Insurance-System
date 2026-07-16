package utils;

import java.util.HashMap;
import java.util.Map;

public class OTPStore {

    // email -> otp
    public static Map<String, String> otpMap =
            new HashMap<>();

    // email -> {username,password,phone}
    public static Map<String, String[]> userMap =
            new HashMap<>();

}