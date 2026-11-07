package org.HilosTamagochi;


public class Tamagotchi {

    private static final int TIEMPO_DE_VIDA_MAXIMO = 5 * 60 * 1000;  
    private static final int TIEMPO_DESCENDENTE_QUE_AGUANTA_SUCIO = 20 * 1000;   

    final String nombre;
    final long timestampInicio;

    volatile boolean vivo = true;
    volatile String estado = "ocioso";
    volatile int suciedad = 0;
    long tiempoenelqueinicialimpio;



    
    
    
    
    
    
    
    public Tamagotchi(String nombre) {
        this.nombre = nombre;
        this.timestampInicio = System.currentTimeMillis();
        this.tiempoenelqueinicialimpio = this.timestampInicio;
    }

    
    
    
    
    
    void verestado() {
    	//se calcula el tiempo para ver si deveria seguir vivo, ya sea por los 5 minutos o por suciedad
        long ahora = System.currentTimeMillis();

        if (ahora - timestampInicio >= TIEMPO_DE_VIDA_MAXIMO) {
            System.out.println(nombre + " ha cumplido 5 minutos. Avisa y muere.");
            morir();
            return;
        }

        while (ahora - tiempoenelqueinicialimpio >= TIEMPO_DESCENDENTE_QUE_AGUANTA_SUCIO) {
            suciedad++;
            tiempoenelqueinicialimpio += TIEMPO_DESCENDENTE_QUE_AGUANTA_SUCIO;
            System.out.println(nombre + " suciedad: " + suciedad);

            if (suciedad == 5) {
                System.out.println(nombre + " esta empezando a estar muy sucio.");
            }

            if (suciedad >= 10) {
                System.out.println(nombre + " llega a suciedad 10. Muere.");
                morir();
                return;
            }
        }
    }

    // Cosa pequeña: el cuidador lo mata; solo se atiende si esta ocioso
    public String matar() {
        if (!vivo) {
            return nombre + " ya esta muerto.";
        }
        if (!estado.equals("ocioso")) {
            return nombre + " no atiende: esta " + estado + "porque esta ocioso.";
        }

        System.out.println(nombre + " muere por orden del cuidador.");
        morir();
        return nombre + " ha muerto.";
    }

    void morir() {
        vivo = false;
        estado = "muerto";
    }

    
    //para enseñar el estado de todos los tamagotchi antes del menu
    public String estadoTexto() {
        long restante = TIEMPO_DE_VIDA_MAXIMO - (System.currentTimeMillis() - timestampInicio);
        if (restante < 0 || vivo == false) {
            return nombre+" esta muy muerto.";
        }

        return nombre + " -> estado=" + estado + ", suciedad=" + suciedad
                + ", vivo=" + vivo + ", vida restante=" + (restante / 1000) + " s";
    }
}
