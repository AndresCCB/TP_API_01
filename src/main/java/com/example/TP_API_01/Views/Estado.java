package com.example.TP_API_01.Views;


public enum Estado {

    nuevo, abierto, enProceso, desestimado, anulado, terminado;

    public static Estado fromString(String estado) {
        for (Estado e : Estado.values()) {
            if (e.name().equalsIgnoreCase(estado)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}
