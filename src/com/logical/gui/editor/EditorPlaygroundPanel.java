package com.logical.gui.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLayeredPane;

import com.logical.block.Block;
import com.logical.block.Generator;
import com.logical.block.Sensor;
import com.logical.block.Wire;
import com.logical.gui.block.GraphicalBlock;
import com.logical.gui.element.GraphicalOutput;
import com.logical.gui.element.GraphicalWire;

public class EditorPlaygroundPanel extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 975961292883764187L;

	public static final int INOUT_GRID_SIZE = 15;
	public static final int BLOCK_GRID_SUBDIVISION_NUMBER = 2;
	public static final int BLOCK_GRID_SIZE = BLOCK_GRID_SUBDIVISION_NUMBER * INOUT_GRID_SIZE;

	public static final int BLOCK_PANE = 0;
	public static final int WIRE_PANE = 1;

	private final List<GraphicalBlock> blocks;
	private final Map<Block, GraphicalBlock> blockToGraphical;
	private final List<GraphicalWire> wires;
	private int gridX = 0;
	private int gridY = 0;

	/**
	 * We represent it as a grid so we can easily snap and align components.
	 * Components are blocks or wires that are stored in a list to draw.
	 */
	public EditorPlaygroundPanel() {
		blocks = new ArrayList<GraphicalBlock>();
		blockToGraphical = new HashMap<Block, GraphicalBlock>();
		wires = new ArrayList<GraphicalWire>();
		// Set absolute positioning and white background
		this.setLayout(null);
		this.setBackground(Color.white);

		Generator generator = new Generator();
		Sensor sensor = new Sensor();
		generator.connectTo(sensor, Sensor.IN_NAME, Generator.OUT_NAME);

		// TODO Remove when tests finished
		gridY = 3;
		gridX = 2;
		addBlock(generator);
		gridX = 8;
		addBlock(sensor);

		addWire(new Wire(generator.getOutput(Generator.OUT_NAME), sensor.getInput(Sensor.IN_NAME)));
	}

	public void addBlock(Block block) {
		GraphicalBlock graphicalBlock = new GraphicalBlock(gridX, gridY, block);
		this.add(graphicalBlock, BLOCK_PANE, 0);
		blocks.add(graphicalBlock);
		blockToGraphical.put(block, graphicalBlock);
		graphicalBlock.doLayout();
	}

	public void addWire(Wire wire) {
		
		GraphicalWire graphicalWire = new GraphicalWire(wire);
		this.add(graphicalWire, WIRE_PANE, 0);
		wires.add(graphicalWire);
		graphicalWire.doLayout();
		System.out.println(graphicalWire.getSize());
		System.out.println(graphicalWire.getLocation());
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
