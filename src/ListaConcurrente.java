
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** @author franklinvelasquezfuentes
 */
public class ListaConcurrente {
    
    private final LinkedList<Integer> lista;
    ReentrantLock candado ;
    ReentrantReadWriteLock candadoLecturaEscritura = new ReentrantReadWriteLock(true);
    

    public ListaConcurrente() {
        this.lista = new LinkedList<>();
        
        this.candado = new ReentrantLock();
    }
    
    
    // synchronized
    public  void insertar(int valor){

        try {
            
            this.candadoLecturaEscritura.writeLock().lock(); // adquiere el bloqueo
        
            this.lista.add(valor);
            
        } catch (Exception e) {
            
        } finally {
            this.candadoLecturaEscritura.writeLock().unlock(); // libera el bloqueo
        }
    }
    
    
    public  boolean tieneValor(int valor_busqueda){
        
        try{
            this.candadoLecturaEscritura.readLock().lock();
            
            return this.lista.contains(valor_busqueda);
        }finally{
            this.candadoLecturaEscritura.readLock().unlock();
        }
        
        
    }
    
    
    public int getSize(){
        return this.lista.size();
    }
    
    public void imprimirLista(){
        for (Integer valor : lista) {
            System.out.print( valor + " , ");
        }
        System.out.println("\n\n");
    }

}
