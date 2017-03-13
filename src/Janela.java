
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Felipe
 */
public class Janela extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	final int PENSANDO = 0;
    final int COMENDO = 1;
    final int FAMINTO = 2;
    public String msg = "";
    Thread thread;

    public static int estado[] = new int[5]; //5
    public static Filosofo filosofo[] = new Filosofo[5]; //5
    
    public JButton runButton;
    public JTextField runText;
    
    
    Janela(){
        // Define o foco para este JPanel
        setFocusable(true);

        // Define um tamanho para a tela
        setSize(400, 400);
        
        // Seta a cor do fundo
        setBackground(Color.white);
        JButton runButton = new JButton("Run!");
        JTextField runText = new JTextField("5");
        
        this.add(new JLabel("Numero de filosofos:"));
        this.add(runText);
        this.add(runButton);
        
        runButton.addActionListener(new ButtonClickListener());
        
        init();
  
    }

    
    
    public void init(){
    	
        // Inicializa todos estados para zero
        for (int i = 0; i < estado.length; i++){
            estado[i] = 0;
        }
        // Verifica se o Thread de animacao esta vazio
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        Thread.currentThread().setPriority(1);

        // Inicializa todos filosofos
        filosofo[0] = new Filosofo(0, "Platao");
        filosofo[1] = new Filosofo(1, "Socrates");
        filosofo[2] = new Filosofo(2, "Aristoteles");
        filosofo[3] = new Filosofo(3, "Tales");
        filosofo[4] = new Filosofo(4, "Sofocles");
        

        filosofo[0].start();
        filosofo[1].start();
        filosofo[2].start();
        filosofo[3].start();
        filosofo[4].start();
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
         
        g.setColor(Color.blue);
        
        int circleHeight = 300;
        int circleWidth = 300;
        
        g.drawOval(50, 50, circleHeight, circleWidth);

        // Para cada um dos filo um desenho
        for(int i = 0; i < 5; i++){            
            
            // Define a cor para cara tipo de estado
            if(estado[i] == 0)
                g.setColor(Color.gray);
                msg = "PENSANDO";
            
            if(estado[i] == 1)
                g.setColor(Color.yellow);
                msg = "FAMINTO";

            if(estado[i] == 2)
                g.setColor(Color.green);
                msg= "COMENDO";

            int x = (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15;
            int y = (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 15;


            g.fillOval(x, y, 30, 30);
            g.setColor(Color.black);
            g.drawString(msg, x, y);
           
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose(); // pausa
    }
    public void run(){
    	int itens = 100000;
        do{
            repaint(); // Redesenha
            // Dorme durante um tempo para redesenhar novamente
            try{
                Thread.sleep(1000L);
            }catch (InterruptedException ex){
                System.out.println("ERROR>" + ex.getMessage());
            }
            itens--;
        }
        while (itens > 0);
    }
}

class ButtonClickListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        Janela janela = new Janela();
        janela.init();       
    }		
 }


