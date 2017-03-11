/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Felipe // Eduardo Borges
 */
public class Filosofo extends Thread {
    public int ID;
    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;
    //public String nome;
    
    public Filosofo(int ID, String nome){
      super(nome); 
      this.ID = ID;
       
    }
   
    //O Filosofo está com fome
    public void comFome(){
        Grade.estado[this.ID] = 1;
        System.out.println("O Filosofo " + getName() + "está FAMINTO");
    }
    
    //O Filosofo está comendo
    public void Come(){  
        Grade.estado[this.ID] = 2;
        System.out.println("O Filosofo " + getName() + "Está COMENDO!");
        
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());
            
        }
    }
    
    //Filosofo pensa
    public void Pensa(){
        Grade.estado[this.ID] = 3;
        System.out.println("O Filosofo " + getName() + "Está A PENSAR!");
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());  
        }
    }
    
    public void largarGarfo(){ 
    // Decrementa o semáforo mutex principal da classe, isso permite
    // informar que o atual método está operando na mesa dos filósofos
        Grade.mutex.decrementar();
    //Vamos pensar um pouco? Dormir...
        Pensa();
    //Vizinhos, meus garfos já estão disponíveis    
        Grade.filosofo[vizinhoEsquerda()].tentarGarfo();
        Grade.filosofo[vizinhoDireita()].tentarGarfo();
    //Voltamos ao normal
        Grade.mutex.incrementar();
    }
    
    public void pegarGarfo(){
       
        Grade.mutex.decrementar();
        
        //Vamos ficar com fome?
        comFome();
        //Será que eu posso comer?
        tentarGarfo();
        
        Grade.mutex.incrementar();
        
        Grade.semaforos[this.ID].decrementar();
        
    }
    public void tentarGarfo(){
        if(Grade.estado[this.ID] == 1 && Grade.estado[vizinhoEsquerda()] !=2 && Grade.estado[vizinhoDireita()] !=2){
            Come();
            Grade.semaforos[this.ID].incrementar();
        }
    }
    
    public void run(){ 
        try{
            Pensa();
            do{
                pegarGarfo();
                Thread.sleep(1000L);
                largarGarfo();
            }while(true);
        }
        catch (InterruptedException ex){
            System.out.println("ERRO" + ex.getMessage());
            return;
        }
    }
    
    public int vizinhoDireita(){
        // Rationa o valor em 5 posições, ou seja, se o ID deste filósofo acrescentado
        // de um for maior que quatro, passa a ser zero
        return (this.ID + 1) % 5;
    }
    
    public int vizinhoEsquerda(){
        if (this.ID == 0){
            // Retorna a ultima posição
            return 4;
        }
        else{
            // Rationa o valor em 5 posições, ou seja, se o ID deste filósofo decrescido
            // de um for menor que zero, passa a ser quatro
            return (this.ID - 1) % 5;
        }
    }
    
}
