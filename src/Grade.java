
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Felipe
 */
public class Grade extends JPanel implements Runnable{
    final int PENSANDO = 0;
    final int COMENDO = 1;
    final int FAMINTO = 2;
    public String msg = "";
    Thread animador;
    public static Semaforo mutex = new Semaforo(1); //(1)
    public static Semaforo semaforos[] = new Semaforo[5];
    public static int estado[] = new int[5]; //5
    public static Filosofo filosofo[] = new Filosofo[5]; //5
    
    Grade(){
        // Define o foco para este JPanel
        setFocusable(true);

        // Define um tamanho para a tela
        setSize(400, 400);
        
        // Seta a cor do fundo
        setBackground(Color.white);
        
        init();
    }
    public void init(){
        // Inicializa todos estados para zero
        for (int i = 0; i < estado.length; i++){
            estado[i] = 0;
        }
        // Verifica se o Thread de animação é vazio
        if(animador == null)
        {
            // Então cria um novo Thread
            animador = new Thread(this);
            // Inicia sua execução
            animador.start();
        }
        // Define a prioridade principal para este atual Thread
        Thread.currentThread().setPriority(1);

        // Inicializa todos filósofos
        filosofo[0] = new Filosofo(0, "Platao");
        filosofo[1] = new Filosofo(1, "Socrates");
        filosofo[2] = new Filosofo(2, "Aristoteles");
        filosofo[3] = new Filosofo(3, "Tales");
        filosofo[4] = new Filosofo(4, "Sofocles");

        // Inicializa todos semáforos
        semaforos[0] = new Semaforo(0);
        semaforos[1] = new Semaforo(0);
        semaforos[2] = new Semaforo(0);
        semaforos[3] = new Semaforo(0);
        semaforos[4] = new Semaforo(0);

        // Inicia a execução de todos filósofos
        filosofo[0].start();
        filosofo[1].start();
        filosofo[2].start();
        filosofo[3].start();
        filosofo[4].start();
    }
    public void paint(Graphics g){
         super.paint(g);
        // Define a cor azul
        g.setColor(Color.blue);
        // Cria um circulo na posição (50,50) do plano cartesiano com tamanho
        // 300x300
        g.drawOval(50, 50, 300, 300);

        // Para cada um dos filósofos será feito um desenho
        for(int i = 0; i < 5; i++)
        {            
            // Define a cor para cara tipo de estado
            if(estado[i] == 0)
            {
                g.setColor(Color.gray);
                msg = "PENSANDO";
            }
            if(estado[i] == 1)
            {
                g.setColor(Color.yellow);
                msg = "FAMINTO";
            }
            if(estado[i] == 2)
            {
                g.setColor(Color.green);
                msg= "COMENDO";
            }
            // Desenha o filósofo, sua carinha e seu nome na tela
            // Define os planos (x,y) e posteriormente o tamanho do objeto a ser desenhado
            g.fillOval((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 15, 30, 30);
            g.setColor(Color.black);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawString(filosofo[i].getName(), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 25);
            g.drawString(msg, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 40);
        }

        // ATIVA A SINCRONIA
        Toolkit.getDefaultToolkit().sync();
        // PAUSA
        g.dispose();
    }
    public void run(){
         // Uma execução infinita
        do{
        // Redesenha a tela
            repaint();

            // Dorme durante um tempo para redesenhar novamente
            try{
                Thread.sleep(1000L);
            }catch (InterruptedException ex){
                // Exibe uma mensagem de controle de erro
                System.out.println("ERROR>" + ex.getMessage());
            }
        }
        while (true);
    }
}
