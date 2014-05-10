package com.zetcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyListener implements ActionListener {
	
	// Data members
	final private String exePath;
	
	// Constructor
	public MyListener(String exePath) {		
		this.exePath = exePath;
	}
	
    @Override
    public void actionPerformed(ActionEvent event) {
    	try {
    		Runtime.getRuntime().exec(exePath);
    	}
    	catch (IOException ex) {
    		System.err.println("Caught IOException: " + ex.getMessage());
    	}
    }
}
