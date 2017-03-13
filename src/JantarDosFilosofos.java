
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Felipe // Eduardo Borges
 */
public class JantarDosFilosofos extends JFrame{
    
	private static final long serialVersionUID = 1L;

	public JantarDosFilosofos (){ // CRIA UMA NOVA GRADE NA TELA
        add(new Janela());
        
        
        setTitle("Jantar dos Filósofos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(410, 410);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main (String []args){
        new JantarDosFilosofos();
    }
}
