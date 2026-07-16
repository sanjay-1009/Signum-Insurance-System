package service;

import dao.RegisterDAO;
import utils.EmailUtil;
import utils.OTPManager;
import utils.OTPStore;

public class EmailService {

    RegisterDAO dao = new RegisterDAO();

    public void sendRegistrationOTP(
            String username,
            String password,
            String phone,
            String email) throws Exception {

        // Check duplicate username
        if (dao.usernameExists(username)) {
            throw new Exception("Username already exists");
        }

        // Check duplicate email
        if (dao.emailExists(email)) {
            throw new Exception("Email already registered");
        }

        // Generate OTP
        String otp = OTPManager.generateOTP(email);

        // Store user details temporarily
        OTPStore.userMap.put(
                email,
                new String[]{
                        username,
                        password,
                        phone,
                        email
                });

        // Send OTP to email
        EmailUtil.sendOTP(email, otp);

        System.out.println("------------------------------------");
        System.out.println("Registration OTP Generated");
        System.out.println("Username : " + username);
        System.out.println("Email    : " + email);
        System.out.println("------------------------------------");
    }

}