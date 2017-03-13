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
        System.out.println("O Filosofo " + getName() + "est� FAMINTO");
    }
    
    //O Filosofo esta comendo
    public void Come(){  
        Janela.estado[this.ID] = 2;
        System.out.println("O Filosofo " + getName() + "est� COMENDO!");
        
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());
            
        }
    }
    
    //Filosofo pensa
    public void Pensa(){
        Janela.estado[this.ID] = 3;
        System.out.println("O Filosofo " + getName() + "est� A PENSAR!");
        try{
            Thread.sleep(1000L);
        }catch(InterruptedException ex){
            System.out.println("ERRO " + ex.getMessage());  
        }
    }
    
    public void largarGarfo(){ 
    // Decrementa o sem�foro mutex principal da classe, isso permite
    // informar que o atual m�todo est� operando na mesa dos fil�sofos
    //Vamos pensar um pouco? Dormir...
        Pensa();
    //Vizinhos, meus garfos j� est�o dispon�veis    
        Janela.filosofo[vizinhoEsquerda()].tentarGarfo();
        Janela.filosofo[vizinhoDireita()].tentarGarfo();
    //Voltamos ao normal
    }
    
    public void pegarGarfo(){
        //Vamos ficar com fome?
        comFome();
        //Ser� que eu posso comer?
        tentarGarfo();
    }
    public void tentarGarfo(){
        if(Janela.estado[this.ID] == 1 && Janela.estado[vizinhoEsquerda()] !=2 && Janela.estado[vizinhoDireita()] !=2){
            Come();
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
        // Rationa o valor em 5 posi��es, ou seja, se o ID deste fil�sofo acrescentado
        // de um for maior que quatro, passa a ser zero
        return (this.ID + 1) % 5;
    }
    
    public int vizinhoEsquerda(){
        if (this.ID == 0){
            // Retorna a ultima posi��o
            return 4;
        }
        else{
            // Rationa o valor em 5 posi��es, ou seja, se o ID deste fil�sofo decrescido
            // de um for menor que zero, passa a ser quatro
            return (this.ID - 1) % 5;
        }
    }
    
}
