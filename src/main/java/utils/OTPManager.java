package utils;

import java.util.Random;

public class OTPManager {

    // Generate a random 6-digit OTP
    public static String generateOTP() {

        return String.valueOf(
                100000 + new Random().nextInt(900000));

    }

    // Store OTP using email
    public static void saveOTP(
            String email,
            String otp) {

        OTPStore.otpMap.put(email, otp);

    }

    // Verify OTP
    public static boolean verifyOTP(
            String email,
            String otp) {

        if(!OTPStore.otpMap.containsKey(email)) {

            return false;

        }

        return OTPStore.otpMap
                .get(email)
                .equals(otp);

    }

    // Remove OTP after successful verification
    public static void removeOTP(
            String email) {

        OTPStore.otpMap.remove(email);

    }

}