package com.logical.gui.editor;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4915329519814787797L;

	public static boolean showGrid = false;
	
	public EditorPanel() {
		this.setLayout(new BorderLayout());
		JPanel playgroundPanel = new EditorPlaygroundPanel();
		this.add(playgroundPanel);
		
		JButton gridButton = new JButton("Show Grid");
		gridButton.addActionListener(_ -> {
			showGrid = !showGrid;
			playgroundPanel.repaint();
		});
		this.add(gridButton, BorderLayout.SOUTH);
	}

}
