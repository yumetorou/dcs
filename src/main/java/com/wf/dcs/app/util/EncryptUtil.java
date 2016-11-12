package com.wf.dcs.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ddevera
 */
public class EncryptUtil {

    public static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
