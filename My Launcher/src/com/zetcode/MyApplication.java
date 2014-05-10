package com.zetcode;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sun.awt.*;

public class MyApplication {
	
	// Data members
	private String name;
	private String exePath;
	private String iconName;
	private BufferedImage iconImage;
	
	private final static String PATH_G = "/usr/share/applications";
	private final static String PATH_L = "/home/perevera/.local/share/applications";
	private final static String PATH_I = "/usr/share/pixmaps";
	
	// Getters
    public String getName()
    {
            return name;
    }
    
    public String getExePath()
    {
            return exePath;
    }
    
    public String getIconName()
    {
            return iconName;
    }
    
    public BufferedImage getIconImage()
    {
            return iconImage;
    }
	
	/**
	 * Constructor de la clase MyApplication
	 *
	 * @param  name  		Nombre de la aplicación
	 */
	public MyApplication(String name) {
		
		this.name = name;		// asigna el nombre de la aplicación
		
		readFile();				// lee los parámetros principales del fichero .desktop de la aplicación
		
		// Carga el icono de la aplicación
		try {

			int widget = -1; // Go with the default
			String id = "gtk-new";    
			int dir = 1; // NONE=0 ; LTR=1; RTL=2;
			int size = 1;

//			UNIXToolkit utk = (UNIXToolkit)Toolkit.getDefaultToolkit();
//			iconImage = utk.getStockIcon(-1, id, size, dir, null);

			// PENDIENTE: Obtener el path a los ficheros .png de forma dinámica
			Path path  = FileSystems.getDefault().getPath(PATH_I, this.iconName + ".png");
			iconImage = ImageIO.read(new File(path.toString()));		// crea la imagen a partir del icono

//		} catch (NoClassDefFoundError e) {
//			// Ok, the API is not available at all.

		} catch (IOException ex) {	

			System.err.println("Caught IOException: " + ex.getMessage());

		}
		
	}
	
	/**
	 * Obtener el fichero .desktop de la aplicación y extraer los parámetros necesarios
	 */
	private void readFile() {
		
		InputStream in = null;
		HashMap<String, String> pairs = new HashMap<String, String>();
		
		try {
			
			// PENDIENTE: Obtener el path a los ficheros .desktop de forma dinámica
			Path path = FileSystems.getDefault().getPath(PATH_G, this.name + ".desktop");
			BufferedReader br = Files.newBufferedReader(path, Charset.forName("UTF-8"));
			String strLine;

			// Lee el fichero línea a línea
			while ((strLine = br.readLine()) != null)   {
				String[] split = strLine.split("="); 		// separa la línea en dos campos separados por '='
				if (split.length == 2 ) {
					pairs.put(split[0], split[1]);
					System.out.println ("Key-value added: " + split[0] + ", " + split[1]);
				}
			}
			
			// Guarda los valores deseados
			this.iconName = pairs.get("Icon");
			this.exePath = pairs.get("Exec");

		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
