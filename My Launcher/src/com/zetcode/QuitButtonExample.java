package com.zetcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.*;

public class QuitButtonExample extends JFrame {
	
	// Variables miembro
	private JPanel panel;
//	private HashMap<String,MyApplication> hashApps;
	private List<MyApplication> listApps;
	private List<MyButton> listButtons;

    public QuitButtonExample() {
        
        initUI();
        initAppButtons();
        
    }

    private void initUI() {

    	panel = new JPanel();
    	getContentPane().add(panel);

    	panel.setLayout(null);

    	JButton quitButton = new JButton("Quit");
    	quitButton.setBounds(50, 60, 80, 30);

    	quitButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			System.exit(0);
    		}
    	});

    	panel.add(quitButton);

    	setTitle("Quit button");
    	setSize(600, 400);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initAppButtons() {
    	
    	// Inicializa el hash map de aplicaciones que se ofrecen
    	listApps = new ArrayList<MyApplication>();
    	listApps.add(new MyApplication("mythtv"));
    	listApps.add(new MyApplication("spotify"));
    	listApps.add(new MyApplication("tvtime"));    	
    	
    	// Instancia el array de botones
    	listButtons = new ArrayList<MyButton>();
    	
    	int i = 1;		// un índice asociado a cada botón
    	
//    	BufferedImage buttonIcon = null;
    	
    	// Crea botones de aplicaciones y los añade al panel
        for (MyApplication app : listApps) {
        	
//    		try {
//    			buttonIcon = ImageIO.read(new File(app.getIconName()));
//    		} catch (IOException ex) {	
//        		System.err.println("Caught IOException: " + ex.getMessage());
//        	}
			
//			JButton button = new JButton(new ImageIcon(buttonIcon));
        	MyButton btn = new MyButton(app.getName(), app.getExePath(), app.getIconImage(), i);
           	i++;
        	panel.add(btn);
        	listButtons.add(btn);
        	
        }
    	 	   
	}

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuitButtonExample ex = new QuitButtonExample();
                ex.setVisible(true);
            }
        });
    }
}