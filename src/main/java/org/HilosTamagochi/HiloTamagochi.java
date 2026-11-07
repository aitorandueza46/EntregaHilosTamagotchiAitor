package org.HilosTamagochi;


public class HiloTamagochi implements Runnable {

    private static final int BANO_DURACION = 5 * 1000; 

    private final Tamagotchi tamagotchi;

    public HiloTamagochi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
        
    }

    
    
    
    
    @Override
    public void run() {
        System.out.println(tamagotchi.nombre + " nace.");

        while (tamagotchi.vivo) {
            tamagotchi.verestado();

            if (!tamagotchi.vivo) {
                break;
            }


            
            
            
            
            
            //si la accion del tamagotchi cambia desde el menu y ha sido capaz de cambiar en la clase ahce esto
            if (!tamagotchi.accion.isEmpty()) {
                String a = tamagotchi.accion;

                if (a.equals("comer")) {
                    comer();
                } else if (a.equals("jugar")) {
                    jugar();
                } else if (a.equals("limpiar")) {
                    limpiar();
                }

                if (tamagotchi.vivo) {
                    tamagotchi.estado = "ocioso";
                }
                tamagotchi.accion = "";
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println(tamagotchi.nombre + " ha muerto.");
    }

    
    
    
    
    
    	//calcula tiempos y hace que coma durante el tiempo que le cuesta comer, durante este tiempo no cambia a otras acciones
    private void comer() {
        System.out.println(tamagotchi.nombre + " empieza a comer.");
        long tiempoempiezacomer = System.currentTimeMillis();

        try {
            Thread.sleep(tamagotchi.velocidadComida);
        } catch (InterruptedException e) {
            return;
        }

        tamagotchi.verestado();

        long tiempo = System.currentTimeMillis() - tiempoempiezacomer;
        if (tamagotchi.vivo) {
            System.out.println(tamagotchi.nombre + " termina de comer. Tiempo: " + tiempo + " ms");
        }
    }

    
    
    
    //jugar crea la suma que se propone y se pone a esperar hasta que el main proponga una solucion, si falla se repite
    private void jugar() {
        while (tamagotchi.vivo) {
            tamagotchi.verestado();

            if (!tamagotchi.vivo) {
                return;
            }

            int a = 1 + ((int) (Math.random() * 9));
            int b = 1 + ((int) (Math.random() * 9));
            while(a + b >= 10){
                a = 1 + ((int) (Math.random() * 9));
                b = 1 + ((int) (Math.random() * 9));
            }

            int correcta = a + b;

            // Se le asigna la suma al tamagochi para que el main sepa que tiene que jugar
            tamagotchi.sumaA = a;
            tamagotchi.sumaB = b;
            tamagotchi.respuestaDisponible = false;
            tamagotchi.esperandoRespuesta = true;

            // Espera a que respuesta exista
            while (tamagotchi.vivo && !tamagotchi.respuestaDisponible) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    tamagotchi.esperandoRespuesta = false;
                    return;
                }
                tamagotchi.verestado();
            }
            tamagotchi.esperandoRespuesta = false;

            if (!tamagotchi.vivo) {
                return;
            }

            int respuesta = tamagotchi.respuestaCuidador;

            if (respuesta == correcta) {
                System.out.println(tamagotchi.nombre + " acierta y deja de jugar.");
                return;
            }

            System.out.println(tamagotchi.nombre + " falla y sigue jugando.");
        }
    }

    
    
    
    
    
    
    //pone suciedad a 0 y calcula el tiempo que le ha costado limpiarse, durante este tiempo no cambia a otras acciones
    private void limpiar() {
        System.out.println(tamagotchi.nombre + " empieza a limpiarse.");
        long timestampBano = System.currentTimeMillis();

        try {
            Thread.sleep(BANO_DURACION);
        } catch (InterruptedException e) {
            return;
        }

        tamagotchi.verestado();

        if (!tamagotchi.vivo) {
            return;
        }

        tamagotchi.suciedad = 0;
        tamagotchi.tiempoenelqueinicialimpio = System.currentTimeMillis();

        long tiempo = System.currentTimeMillis() - timestampBano;
        System.out.println(tamagotchi.nombre + " termina de limpiarse. Tiempo: " + tiempo
                + " ms. Suciedad: 0");
    }
    
    
    
    
    
    
    	
    
    
    
    

    
    
    
    
    
    
}
