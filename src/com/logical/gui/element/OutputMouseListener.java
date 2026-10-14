package com.logical.gui.element;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutputMouseListener extends MouseAdapter {

	private GraphicalOutput output;
	
	public OutputMouseListener(GraphicalOutput output) {
		this.output = output;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(output.getText());
	}
	
}
