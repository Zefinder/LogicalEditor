package com.logical.gui.editor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class EditorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4102495138811270118L;

	private static final String FRAME_TITLE = "Logical Editor";
	
	public EditorFrame() {
		this.setTitle(FRAME_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
//		this.setLayout(new BorderLayout());
		this.add(new EditorPanel(), BorderLayout.CENTER);
	}
	
	public void initFrame() {
		this.setVisible(true);
	}

}
