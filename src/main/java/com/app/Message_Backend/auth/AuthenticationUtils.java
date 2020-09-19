package com.app.Message_Backend.auth;

import graphql.GraphQLException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
public class AuthenticationUtils {
    private final String algorithm = "PBKDF2WithHmacSHA512";
    private final String passwordRegEx = "(?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16}";
    private final int iterations = 65536;
    private final int keyLength = 512;
    private SecureRandom randomGenerator;

    public AuthenticationUtils() {
        this.randomGenerator = new SecureRandom();
    }

    public Optional<String> getHash(String password, String salt) {
        char[] characters = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(characters, bytes, iterations, keyLength);

        Arrays.fill(characters, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(algorithm);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }

    }

    public Optional<String> getSalt() {
        byte[] salt = new byte[keyLength];
        randomGenerator.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public boolean isCorrectPassword(String potentialPassword, String salt, String dbPassword) {
        Optional<String> optEncrypted = getHash(potentialPassword, salt);
        if (!optEncrypted.isPresent()) {
            return false;
        }

        return optEncrypted.get().equals(dbPassword);
    }

    private boolean isValidPassword(String password) {
        return password.matches(passwordRegEx);
    }
}
