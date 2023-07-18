package co.com.auth_back.auth_back.utils;

import java.util.UUID;

public class GeneratorId {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
