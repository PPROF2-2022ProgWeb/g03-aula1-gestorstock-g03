package com.ar.Grupo3.util;

import java.util.*;

import org.apache.logging.log4j.LogManager;

public class Utilidades {

    public static <T> boolean existe(T t) {
        boolean valor = false;
        try {
            if (t != null) {
                valor = true;
            } else {
                valor = false;
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
        return valor;
    }

    public static <T> boolean tieneDatos(List<T> t) {
        boolean valor = false;
        try {
            if (!t.isEmpty()) {
                valor = true;
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
        return valor;
    }

}
