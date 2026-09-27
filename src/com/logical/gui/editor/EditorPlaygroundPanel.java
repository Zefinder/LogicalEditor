package com.logical.gui.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.logical.block.Block;
import com.logical.block.Generator;
import com.logical.gui.element.GraphicalBlock;

public class EditorPlaygroundPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 975961292883764187L;

	private static final int GRID_SIZE = 20;

//	private final List<GraphicalElement> elements;
	private final List<GraphicalBlock> blocks;

	/**
	 * We represent it as a grid so we can easily snap and align components.
	 * Components are blocks or wires that are stored in a list to draw.
	 */
	public EditorPlaygroundPanel() {
		blocks = new ArrayList<GraphicalBlock>();
		// Set absolute positioning and white background
		this.setLayout(null);
		this.setBackground(Color.white);
		
		// TODO Remove when tests finished
		addBlock(new Generator());
	}
	
	public void addBlock(Block block) {
		GraphicalBlock graphicalBlock = new GraphicalBlock(GRID_SIZE, 3, 2, block);
		this.add(graphicalBlock);
		blocks.add(graphicalBlock);
		graphicalBlock.layout();
	}
}
