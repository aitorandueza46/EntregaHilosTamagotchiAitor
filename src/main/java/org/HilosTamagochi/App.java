package org.HilosTamagochi;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        int numTamagochis = 3;

        
        
        
        
        Tamagotchi[] tamagotchis = new Tamagotchi[numTamagochis];
        for (int i = 0; i < numTamagochis; i++) {
            tamagotchis[i] = new Tamagotchi("Tamagotchi-" + (i + 1));
            new Thread(new HiloTamagochi(tamagotchis[i])).start();
        }

        
        
        
        
        Scanner console = new Scanner(System.in);

        
        while (true) {
        	
            //El programa tiene que seguir mientras que minimo uno siga vivo
            boolean vivos = false;
            for (Tamagotchi t : tamagotchis) {
                if (t.vivo) {
                    vivos = true;
                }
            }
            
            
            
            
            //he decidicio que ver el estado en vez de una accion del ususario se vea siempre al llegar al menu
            System.out.println("--- ESTADO ---");
            for (Tamagotchi t : tamagotchis) {
                System.out.println(t.estadoTexto());
            }
            
            
            
            
            if (!vivos) {
                System.out.println("Todos los tamagotchis han muerto.");
                break;
            }
            
            



            System.out.println("");
            System.out.println("1 matar  0 salir");
            System.out.println("Opcion:");

            int opcion;
            try {
                String opcionStr = console.nextLine();
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida.");
                continue;
            }
            if (opcion == 0) {
            	//Unica forma de salir del bucle infinito
                break;
            }
            if (opcion != 1) {
                System.out.println("Opcion no valida.");
                continue;
            }

            
            
            
            

            System.out.println("Numero de tamagotchi (1-" + numTamagochis + "):");

            int numerodeTamagotchiselegido;
            
            
            try {
                String numeroStr = console.nextLine();
                numerodeTamagotchiselegido = Integer.parseInt(numeroStr);
            } catch (NumberFormatException e) {
                System.out.println("Numero no valido.");
                continue;
            }
            int indice = numerodeTamagotchiselegido - 1;
            if (indice < 0 || indice >= numTamagochis) {
                System.out.println("Ese tamagotchi no existe.");
                continue;
            }

            
            //Aqui el usuario intenta mandar una accion al tamagotchi, si la accion cambia el bucle de vida del hilo lo detecta y hace la accion
            if (opcion == 1) {
                System.out.println(tamagotchis[indice].matar());
            }
        }

        console.close();
        System.out.println("Fin del programa.");
    }
}
