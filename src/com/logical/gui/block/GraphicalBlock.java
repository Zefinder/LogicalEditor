package com.logical.gui.block;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import com.logical.block.Block;
import com.logical.block.Input;
import com.logical.block.Output;
import com.logical.gui.editor.EditorPlaygroundPanel;
import com.logical.gui.element.GraphicalInput;
import com.logical.gui.element.GraphicalOutput;
import com.logical.laf.BlockUI;
import com.logical.laf.InputUI;
import com.logical.laf.OutputUI;

public class GraphicalBlock extends JComponent implements ItemSelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8832677813671858741L;

	public static final int TOP_INSET = 3;
	private static final int BOTTOM_INSET = 3;

	private final Block block;

	// Block input and outputs
	private final GraphicalInput[] inputs;
	private final GraphicalOutput[] outputs;

	// Cache block text
	private String text;

	// Current block position
	private int gridX;
	private int gridY;

	// Current block size
	private int gridWidth;
	private int gridHeight;
	private int subgridWidth;
	private int subgridHeight;

	// Keep grid size cached
	private int gridSize;
	private int subgridSize;

	private List<Optional<GraphicalInput>> inputLocations;
	private List<Optional<GraphicalOutput>> outputLocations;

	public GraphicalBlock(int gridX, int gridY, Block block) {
		this.block = block;
		this.gridSize = EditorPlaygroundPanel.BLOCK_GRID_SIZE;
		this.subgridSize = EditorPlaygroundPanel.INOUT_GRID_SIZE;
		this.text = block.name();

		// Use block hint size to get a first value
		this.gridWidth = block.getWidthHint();
		this.gridHeight = block.getHeightHint();

		this.gridX = gridX;
		this.gridY = gridY;

		// Check height of component to check if it is because of that
//		this.setBorder(BorderFactory.createLineBorder(Color.black));

		// Create panels for inputs and outputs
		Input[] inputs = block.inputs();
		this.inputs = new GraphicalInput[inputs.length];
//		int inputGridX = gridX * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER;
		for (int i = 0; i < inputs.length; i++) {
			GraphicalInput input = new GraphicalInput(inputs[i], 0);
			this.inputs[i] = input;
			this.add(input);
		}

		Output[] outputs = block.outputs();
		this.outputs = new GraphicalOutput[outputs.length];
//		int outputGridX = (gridX + gridWidth) * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER;
		for (int i = 0; i < outputs.length; i++) {
			GraphicalOutput output = new GraphicalOutput(outputs[i],
					gridWidth * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER);
			this.outputs[i] = output;
			this.add(output);
		}

		// Set UI
		updateUI();

		// Check if size is enough for text, else update
		// TODO Cache value for later checks
		FontMetrics fm = getFontMetrics(getFont());
		int textWidth = fm.stringWidth(block.name());
		int minGridWidth = (int) Math.ceil((float) textWidth / gridSize);
		if (minGridWidth > gridWidth) {
			gridWidth = minGridWidth;

			// Update outputs positioning
			for (GraphicalOutput output : this.outputs) {
				output.setGridX(gridWidth * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER);
			}
		}

		// Check if size is enough for max of input and outputs, else update
		int maxInOut = Math.max(block.inputs().length, block.outputs().length);

		// You don't want to place an input or output on the edge
		int minGridHeight = (maxInOut / EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER) + 1;
		if (minGridHeight > gridHeight) {
			gridHeight = minGridHeight;
		}

		subgridHeight = gridHeight * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER;
		subgridWidth = gridWidth * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER;

		// TODO Replace with positioning strategy call
		inputLocations = new ArrayList<Optional<GraphicalInput>>();
		outputLocations = new ArrayList<Optional<GraphicalOutput>>();
		for (int i = 0; i < gridHeight * EditorPlaygroundPanel.BLOCK_GRID_SUBDIVISION_NUMBER - 1; i++) {
			inputLocations.add(Optional.empty());
			outputLocations.add(Optional.empty());
		}
		// Set inputs and outputs gridY
		int inputGridY = 1;
		for (GraphicalInput input : this.inputs) {
			inputLocations.set(inputGridY - 1, Optional.of(input));
			input.setGridY(inputGridY++);
		}

		int outputGridY = 1;
		for (GraphicalOutput output : this.outputs) {
			outputLocations.set(outputGridY - 1, Optional.of(output));
			output.setGridY(outputGridY++);
		}

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

	public int subgridSize() {
		return subgridSize;
	}

	public String text() {
		return text;
	}

	public boolean hasInputAtSubgridLocation(int subgrid) {
		return inputLocations.get(subgrid).isPresent();
	}

	public boolean hasOutputAtSubgridLocation(int subgrid) {
		return outputLocations.get(subgrid).isPresent();
	}

	public int subgridHeight() {
		return subgridHeight;
	}

	public int subgridWidth() {
		return subgridWidth;
	}

	@Override
	public void doLayout() {
		this.setBounds(gridX * gridSize - InputUI.MAX_X, gridY * gridSize - TOP_INSET,
				gridWidth * gridSize + InputUI.MAX_X + OutputUI.MAX_X,
				gridHeight * gridSize + TOP_INSET + BOTTOM_INSET);
	}

	public int getLeftRectXOffset() {
		return InputUI.MAX_X;
	}

	public int getRightRectXOffset() {
		return OutputUI.MAX_X;
	}

	@Override
	public Insets getInsets() {
		return new Insets(TOP_INSET, InputUI.MAX_X, BOTTOM_INSET, OutputUI.MAX_X);
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

	/**
	 * Overrides paintChildren to ensure that inputs and outputs are drawn on the
	 * block. No need to check clip, and we are sure that they are JComponents. Also
	 * they need to be painted at the same time with their parents, so using paint
	 * method.
	 */
	@Override
	protected void paintChildren(Graphics g) {
		// Draw inputs first
		for (GraphicalInput input : inputs) {
			input.paint(g);
		}

		// Draw outputs then
		for (GraphicalOutput output : outputs) {
			output.paint(g);
		}
	}

	@Override
	public Object[] getSelectedObjects() {
		Object[] items = new Object[1];
		items[0] = text;
		return items;
	}

	@Override
	public void addItemListener(ItemListener l) {
		listenerList.add(ItemListener.class, l);
	}

	@Override
	public void removeItemListener(ItemListener l) {
		listenerList.remove(ItemListener.class, l);
	}

	@Override
	public String getUIClassID() {
		return BlockUI.uiClassID;
	}

}
