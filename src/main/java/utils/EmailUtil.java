package utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    // Your Gmail
    private static final String EMAIL =
            "saha.2k25@gmail.com";

    // Gmail App Password (NOT your Gmail password)
    private static final String PASSWORD =
            "umra lfba mqao ckyt";

    public static void sendOTP(
            String to,
            String otp) throws Exception {

        Properties props =
                new Properties();

        props.put(
                "mail.smtp.auth",
                "true");

        props.put(
                "mail.smtp.starttls.enable",
                "true");

        props.put(
                "mail.smtp.host",
                "smtp.gmail.com");

        props.put(
                "mail.smtp.port",
                "587");

        Session session =
                Session.getInstance(

                        props,

                        new Authenticator() {

                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {

                                return new PasswordAuthentication(
                                        EMAIL,
                                        PASSWORD);

                            }

                        });

        Message message =
                new MimeMessage(session);

        message.setFrom(
                new InternetAddress(EMAIL));

        message.setRecipients(

                Message.RecipientType.TO,

                InternetAddress.parse(to)

        );

        message.setSubject(
                "Signum Insurance - Registration OTP");

        message.setText(

                "Welcome to Signum Insurance!\n\n"

                + "Your One-Time Password (OTP) is:\n\n"

                + otp

                + "\n\nThis OTP is valid for 5 minutes."

                + "\n\nPlease do not share this OTP with anyone."

                + "\n\nRegards,\n"

                + "Signum Insurance Team"

        );

        Transport.send(message);

        System.out.println("-------------------------------------");
        System.out.println("OTP Email Sent Successfully");
        System.out.println("To : " + to);
        System.out.println("OTP: " + otp);
        System.out.println("-------------------------------------");

    }

}