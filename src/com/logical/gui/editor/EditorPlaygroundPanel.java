package com.logical.gui.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.logical.block.Block;
import com.logical.block.Generator;
import com.logical.block.Sensor;
import com.logical.gui.block.GraphicalBlock;

public class EditorPlaygroundPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 975961292883764187L;

	public static final int INOUT_GRID_SIZE = 15;
	public static final int BLOCK_GRID_SUBDIVISION_NUMBER = 2;
	public static final int BLOCK_GRID_SIZE = BLOCK_GRID_SUBDIVISION_NUMBER * INOUT_GRID_SIZE;

//	private final List<GraphicalElement> elements;
	private final List<GraphicalBlock> blocks;
	private int gridY = 0;

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
		gridY = 2;
		addBlock(new Generator());
		gridY = 4;
		addBlock(new Sensor());
	}

	public void addBlock(Block block) {
//		GraphicalBlock graphicalBlock = GraphicalBlock.addBlockToComponent(this, block, 3, 2);
		GraphicalBlock graphicalBlock = new GraphicalBlock(3, gridY, block);
		this.add(graphicalBlock);
		blocks.add(graphicalBlock);
		graphicalBlock.doLayout();
	}

	// Only to show the grid for test purpose
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (EditorPanel.showGrid) {
			Graphics2D g2d = (Graphics2D) g;
			for (int i = 1; i < 40; i++) {
				if ((i % BLOCK_GRID_SUBDIVISION_NUMBER) == 0) {
					g2d.setStroke(new BasicStroke(1.5f));
				} else {
					g2d.setStroke(new BasicStroke(1f));
				}
				g2d.drawLine(0, i * INOUT_GRID_SIZE, 500, i * INOUT_GRID_SIZE); // Horizontal
				g2d.drawLine(i * INOUT_GRID_SIZE, 0, i * INOUT_GRID_SIZE, 500); // Vertical
			}
		}
	}
}
