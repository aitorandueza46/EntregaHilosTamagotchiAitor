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


            
            
            
            
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println(tamagotchi.nombre + " ha muerto.");
    }
    
    
    
    
    
    
    	
    
    
    
    

    
    
    
    
    
    
}
