package com.ar.Grupo3.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

    //Descomentar si se quiere encriptar pass word para su uso
//	public static void main(String[] args) {
//
//        var password = "admin";
//        System.out.println("password: " + password);
//        System.out.println("password encriptado:" + encriptarPassword(password));
//
//	}
    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
