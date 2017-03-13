import javax.swing.JOptionPane;

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
   
    //O Filosofo esta com fome
    public void comFome(){
        Janela.estado[this.ID] = 1;
        System.out.println( getName() + "está FAMINTO");
    }
    
    //O Filosofo esta comendo
    public void Come(){  
        Janela.estado[this.ID] = 2;
        System.out.println( getName() + "está COMENDO!");
        
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());
            
        }
    }
    
    //Filosofo pensa
    public void Pensa(){
        Janela.estado[this.ID] = 3;
        System.out.println( getName() + "está A PENSAR!");
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());  
        }
    }
    
    public void largarGarfo(){ 
    	//Vamos pensar um pouco? Dormir...
        Pensa();
        Janela.filosofo[vizinhoEsquerda()].tentarGarfo();
        Janela.filosofo[vizinhoDireita()].tentarGarfo();
    }
    
    public void pegarGarfo(){
        //Vamos ficar com fome?
        comFome();
        //Sera que eu posso comer?
        tentarGarfo();
    }
    public void tentarGarfo(){
        if(Janela.estado[this.ID] == 1 && Janela.estado[vizinhoEsquerda()] !=2 && Janela.estado[vizinhoDireita()] !=2){
            Come();
        }
            
           if (Janela.estado[vizinhoEsquerda()] ==1 && Janela.estado[vizinhoDireita()] ==1){
        	   JOptionPane.showMessageDialog(null, "DeadLock!");
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
        // Rationa o valor em 5 posiï¿½ï¿½es, ou seja, se o ID deste filï¿½sofo acrescentado
        // de um for maior que quatro, passa a ser zero
        return (this.ID + 1) % 5;
    }
    
    public int vizinhoEsquerda(){
        if (this.ID == 0){
            // Retorna a ultima posiï¿½ï¿½o
            return 4;
        }
        else{
            // Rationa o valor em 5 posiï¿½ï¿½es, ou seja, se o ID deste filï¿½sofo decrescido
            // de um for menor que zero, passa a ser quatro
            return (this.ID - 1) % 5;
        }
    }
    
}
