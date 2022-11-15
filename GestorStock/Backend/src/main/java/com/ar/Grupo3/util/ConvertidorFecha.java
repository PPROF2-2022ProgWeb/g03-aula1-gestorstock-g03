package com.ar.Grupo3.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertidorFecha {

    public static Date SumarXDias(int unNumero) {
        // Manejo de Fechas.
        LocalDate entrega = LocalDate.now().plusDays(unNumero);
        Date fechaFinal = Date.from(entrega.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return fechaFinal;
    }

    public static Date FechaActual() {
        LocalDate ahora = LocalDate.now();
        return Date.from(ahora.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
