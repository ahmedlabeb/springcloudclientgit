package com.springcloudclientgit.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(DemoApplication.class, args);
        // Security.setProperty("crypto.policy", "limited"); // uncomment to switch to limited crypto policies
        System.out.println("Check for unlimited crypto policies");
        System.out.println("Java version: " + Runtime.version());
        //Security.setProperty("crypto.policy", "limited"); // muss ganz am anfang gesetzt werden !
        System.out.println("restricted cryptography: " + restrictedCryptography() + " Notice: 'false' means unlimited policies"); // false mean unlimited crypto
        System.out.println("Security properties: " + Security.getProperty("crypto.policy"));
        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
        System.out.println("Max AES key length = " + maxKeyLen);

    }
    public static boolean restrictedCryptography() {
        try {
            return Cipher.getMaxAllowedKeyLength("AES/CBC/PKCS5Padding") < Integer.MAX_VALUE;
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException("The transform \"AES/CBC/PKCS5Padding\" is not available (the availability of this algorithm is mandatory for Java SE implementations)", e);
        }
    }

}
