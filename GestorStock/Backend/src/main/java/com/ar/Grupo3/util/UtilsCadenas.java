package com.ar.Grupo3.util;

public class UtilsCadenas {

	public static boolean containsStringIgnoreCase(String obj1, String obj2) {
		boolean valor = false;
		
		String aux1 = obj1.toLowerCase();
		String aux2 = obj2.toLowerCase();
		
		if (aux1.length() > aux2.length()) {
			valor = aux1.contains(aux2);
		}else {
			valor = aux2.contains(aux1);
		}

		return valor;
	}
}
