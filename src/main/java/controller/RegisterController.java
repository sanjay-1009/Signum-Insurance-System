package controller;

import utils.EmailUtil;
import utils.OTPStore;

import java.util.Random;

public class RegisterController {

    public void sendOTP(
            String username,
            String password,
            String phone,
            String email) throws Exception {

        // Generate 6 digit OTP
        String otp = String.valueOf(
                100000 + new Random().nextInt(900000));

        // Store OTP
        OTPStore.otpMap.put(email, otp);

        // Store user details temporarily
        OTPStore.userMap.put(
                email,
                new String[]{
                        username,
                        password,
                        phone
                });

        // Send OTP to email
        EmailUtil.sendOTP(email, otp);

        System.out.println(
                "Registration OTP sent to : " + email);

    }

}