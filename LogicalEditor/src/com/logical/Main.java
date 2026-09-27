package com.logical;

import javax.swing.UIManager;

import com.logical.gui.editor.EditorFrame;
import com.logical.gui.ui.BlockUI;

public class Main {

	public static void main(String[] args) {
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
		
		// Add custom component UI to UI manager
		UIManager.getLookAndFeelDefaults().put(BlockUI.uiClassID, BlockUI.class.getName());
//		UIManager.put(BlockUI.uiClassID, BlockUI.class.getName());

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EditorFrame frame = new EditorFrame();
				frame.initFrame();
			}
		});
	}

}

//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Insets;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class Main {
//	public static void addComponentsToPane(Container pane) {
//		pane.setLayout(null);
//
//		JButton b1 = new JButton("one");
//		JButton b2 = new JButton("two");
//		JButton b3 = new JButton("three");
//
//		pane.add(b1);
//		pane.add(b2);
//		pane.add(b3);
//
//		Insets insets = pane.getInsets();
//		Dimension size = b1.getPreferredSize();
//		b1.setBounds(25 + insets.left, 5 + insets.top, size.width, size.height);
//		size = b2.getPreferredSize();
//		b2.setBounds(55 + insets.left, 40 + insets.top, size.width, size.height);
//		size = b3.getPreferredSize();
//		b3.setBounds(150 + insets.left, 15 + insets.top, size.width + 50, size.height + 20);
//	}
//
//	/**
//	 * Create the GUI and show it. For thread safety, this method should be invoked
//	 * from the event-dispatching thread.
//	 */
//	private static void createAndShowGUI() {
//		// Create and set up the window.
//		JFrame frame = new JFrame("AbsoluteLayoutDemo");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Set up the content pane.
//		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout());
//
//		addComponentsToPane(panel);
//		frame.getContentPane().add(panel);
//
//		// Size and display the window.
//		Insets insets = frame.getInsets();
//		frame.setSize(300 + insets.left + insets.right, 125 + insets.top + insets.bottom);
//		frame.setVisible(true);
//	}
//
//	public static void main(String[] args) {
//		// Schedule a job for the event-dispatching thread:
//		// creating and showing this application's GUI.
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createAndShowGUI();
//			}
//		});
//	}
//}
