package com.logical.gui.editor;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4915329519814787797L;

	public EditorPanel() {
		this.setLayout(new BorderLayout());
		this.add(new EditorPlaygroundPanel());
		this.add(new JButton("NOT HTML"), BorderLayout.SOUTH);
	}

}
