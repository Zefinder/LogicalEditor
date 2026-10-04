package com.logical;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.logical.gui.editor.EditorFrame;
import com.logical.laf.LogicalEditorLookAndFeel;

public class Main {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
//		System.out.println("Hello World!");
//		
//		Generator generator = new Generator();
//		Sensor sensor = new Sensor();
//		generator.connectTo(sensor, 0, 0);
//		
//		System.out.println("Updating outputs...");
//		generator.updateOutputs();
//		sensor.updateOutputs();
//		
//		System.out.println("Updating inputs...");
//		generator.updateInputs();
//		sensor.updateInputs();
//		
//		System.out.println("Performing inside logic...");
//		generator.performLogic();
//		sensor.performLogic();
//		
//		System.out.println("Done!");
		
		// Set custom laf for custom components
		UIManager.setLookAndFeel(new LogicalEditorLookAndFeel());		
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EditorFrame frame = new EditorFrame();
				frame.initFrame();
			}
		});
	}

}