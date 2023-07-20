package co.com.auth_back.auth_back.utils;

import java.util.UUID;

public class GeneratorId {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateUUID(int length) {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,Math.min(uuid.length(),length));
    }
}
