package com.logical.gui.element;

import java.awt.Color;
import java.awt.ItemSelectable;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import com.logical.block.Block;
import com.logical.block.Input;
import com.logical.block.Output;
import com.logical.gui.ui.BlockUI;

public class GraphicalBlock extends JComponent implements ItemSelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8832677813671858741L;

	private final Block block;

	// Block input and outputs
	private final GraphicalInput[] inputs;
	private final GraphicalOutput[] outputs;

	// Current block position
	private int gridX;
	private int gridY;

	// Current block size
	private int gridWidth;
	private int gridHeight;

	// Keep grid size cached
	private int gridSize;

	public GraphicalBlock(int gridSize, int gridX, int gridY, Block block) {
		this.block = block;
		this.gridSize = gridSize;
		this.gridWidth = 3;
		this.gridHeight = 3;

		this.gridX = gridX;
		this.gridY = gridY;

		this.setBorder(BorderFactory.createLineBorder(Color.black));

		// Create panels for inputs and outputs
		Input[] inputs = block.inputs();
		this.inputs = new GraphicalInput[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = new GraphicalInput(inputs[i]);
		}

		Output[] outputs = block.outputs();
		this.outputs = new GraphicalOutput[outputs.length];
		for (int i = 0; i < outputs.length; i++) {
			this.outputs[i] = new GraphicalOutput(outputs[i]);
		}
		
		// Set UI
		updateUI();
	}

	public int gridX() {
		return gridX;
	}

	public int gridY() {
		return gridY;
	}

	public int gridWidth() {
		return gridWidth;
	}

	public int gridHeight() {
		return gridHeight;
	}
	
	public int gridSize() {
		return gridSize;
	}

	public String getText() {
		return block.name();
	}

	public void layout() {
		this.setBounds(gridX * gridSize, gridY * gridSize, gridWidth * gridSize, gridHeight * gridSize);
	}

	@Override
	public void updateUI() {
		setUI((BlockUI) UIManager.getUI(this));
	}

	@Override
	public ComponentUI getUI() {
		return (BlockUI) ui;
	}
	
	public void setUI(BlockUI newUI) {
		super.setUI(newUI);
	}

	@Override
	public Object[] getSelectedObjects() {
		Object[] items = new Object[1];
		items[0] = this.block.name();
		return items;
	}

	@Override
	public void addItemListener(ItemListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItemListener(ItemListener l) {
		// TODO Auto-generated method stub

	}

	public String getUIClassID() {
		return BlockUI.uiClassID;
	}
}
