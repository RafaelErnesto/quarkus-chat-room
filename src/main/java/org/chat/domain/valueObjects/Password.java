package org.chat.domain.valueObjects;

import jakarta.ws.rs.ProcessingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Password {
    private String value;

    public Password(String password) {
        if(password == null || password.length() < 8) {
            throw new ProcessingException("Invalid password, it must have at least length 8");
        }
        this.value = hashPassword(password);
    }

    private String hashPassword(String pwd) {
        try {

            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashbytes);
        } catch (Exception ex) {
            throw new ProcessingException("Error hashing the password");
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean match(String valueToMatch) {
        String hashedValueToMatch = hashPassword(valueToMatch);
        return hashedValueToMatch.equalsIgnoreCase(this.value);
    }
}
