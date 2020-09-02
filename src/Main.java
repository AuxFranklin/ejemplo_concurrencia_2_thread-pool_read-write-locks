
import java.util.concurrent.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franklinvelasquezfuentes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Thread Pool
        
        ExecutorService ejecutorInsertar = Executors.newCachedThreadPool();
        
        ListaConcurrente lista = new ListaConcurrente();
        
        
        long start_insertion = System.nanoTime();

        for (int i = 0; i < 500; i++) {
            ejecutorInsertar.execute( new TareaOperacion(0, lista, i) );
        }
        
        ejecutorInsertar.shutdown();
        
        
        try {
            
            ejecutorInsertar.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            
            long finish_insertion = System.nanoTime();
            long delta_insertion_time = finish_insertion - start_insertion;
            
            System.out.println("Tiempo Insertar : " + delta_insertion_time);
            System.out.println("Tamanio de la lista : " + lista.getSize());
            
            System.out.println("Ojo, Notar que no fueron insertados en orden, son concurrentes: ");
            lista.imprimirLista();
            
            
            
            // Leer     
            
            ExecutorService ejecutorLectura = Executors.newCachedThreadPool();

            long start_reading = System.nanoTime();

            for (int i = 0; i < 500; i++) {
                // leer = 1
                ejecutorLectura.execute( new TareaOperacion(1, lista, i) );
            }

            ejecutorLectura.shutdown();
            
            ejecutorLectura.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            
            long finish_reading = System.nanoTime();
            
            long delta_reading = finish_reading - start_reading;
            
            System.out.println("Notar que no fueron leidas en orden, son concurrentes ");
            System.out.println("Tiempo Lectura : " + delta_reading);
            
        } catch (Exception e) {
            
            System.out.println("error " + e.getMessage());
        }
        
        
        
    }
    
}
