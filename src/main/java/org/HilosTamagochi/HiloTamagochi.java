package org.HilosTamagochi;


public class HiloTamagochi implements Runnable {

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
    
    
    
    
    
    
    	
    
    
    
    

    
    
    
    
    
    
}
