/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** @author franklinvelasquezfuentes
 */


public class TareaOperacion implements  Runnable{
    
    private final int tipo_operacion;
    private final ListaConcurrente lista;
    private final int value;

    public TareaOperacion(int tipo_operacion, ListaConcurrente lista, int value) {
        this.tipo_operacion = tipo_operacion;
        this.lista = lista;
        this.value = value;
    }
    
    

    @Override
    public void run() {
        switch(tipo_operacion){
            case 0:
                this.lista.insertar(value);
                break;
            case 1:
                boolean founded = this.lista.tieneValor(value);
                System.out.println(this.value + " : " + (founded?"encontrado":"no existe"));
        }
    }
    
    

}
