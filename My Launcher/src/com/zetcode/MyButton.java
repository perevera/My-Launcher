package com.zetcode;

import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class MyButton extends JButton {
	
	// Data members
//	final int i;
	
	/**
	 * Constructor de la clase MyButton
	 *
	 * @param  name  		Nombre de la aplicación asociada al botón
	 * @param	exePath		Camino relativo del ejecutable de la aplicación
	 * @param	iconPath	Camino relativo del icono de la aplicación		 
	 * @param  i 			Indice del botón en la ventana
	 */
	public MyButton(String name, String exePath, BufferedImage buttonIcon, int i) {	
		
		super(new ImageIcon(buttonIcon));		// llama al constructor de la clase de base con la imagen del icono
				
//		this.i = i;
		int width = buttonIcon.getWidth();
		int height = buttonIcon.getHeight();
		this.setBounds(i*(width + 10), i*(height + 10), width, height);
		this.setBorder(BorderFactory.createEmptyBorder());	// sin borde
		this.setContentAreaFilled(false);					// transparente
		this.addActionListener(new MyListener(exePath));

	}

}