/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Felipe // Eduardo Borges
 */
public class Semaforo {
    protected int contador;
    private int valor;
    
    Semaforo(){
        this.contador = 0;
    }
    
    Semaforo(int valor){
        this.contador = valor;
    }
    
    synchronized void decrementar(){
        while (this.contador == 0){
            try{
                // Espera uma nova solicitação
                wait();
            }
            catch (InterruptedException ex){
                
                System.out.println("ERRO" + ex.getMessage());
            }
        }
    this.contador--; 
    }
    
    synchronized void incrementar(){
    //Incrementa o contador da classe
    this.contador++;
    // Notifica que a solicitação já foi executado
    notify();
    }
}
