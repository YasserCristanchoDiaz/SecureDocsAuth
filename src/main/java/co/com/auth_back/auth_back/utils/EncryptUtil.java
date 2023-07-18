package co.com.auth_back.auth_back.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class EncryptUtil {

    /**
     *
     * @param value valor inicial a encriptar
     * @return Devuelve el valor encriptado
     */
    public static String encryptValue(String value){
        return BCrypt.withDefaults().hashToString(6,value.toCharArray());
    }

    /**
     *
     * @param value valor inicial almacenado
     * @param hashedValue valor con el hash
     * @return comprobacion de igualdad
     */
    public static boolean checkValues(String value,String hashedValue){
        return BCrypt.verifyer().verify(value.toCharArray(),hashedValue.toCharArray()).verified;

    }
}
