package com.logical.gui.element;

import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import com.logical.block.Output;
import com.logical.gui.block.GraphicalBlock;
import com.logical.gui.editor.EditorPlaygroundPanel;
import com.logical.laf.OutputUI;

public class GraphicalOutput extends JComponent implements ItemSelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7964609885440344466L;

	// Current output position
	private int internalGridX;
	private int internalGridY;

	// Cache grid size
	private int gridSize;

	public GraphicalOutput(Output output, int internalGridX) {
		this.internalGridX = internalGridX;
		this.internalGridY = 0;
		this.gridSize = EditorPlaygroundPanel.INOUT_GRID_SIZE;

		updateUI();
	}

	public int internalGridX() {
		return internalGridX;
	}

	public int internalGridY() {
		return internalGridY;
	}

	public int getInternalX() {
		return internalGridX * gridSize;
	}

	public int getInternalY() {
		return internalGridY * gridSize;
	}

	public void setGridX(int gridX) {
		this.internalGridX = gridX;
	}

	public void setGridY(int gridY) {
		this.internalGridY = gridY;
	}

	@Override
	public Insets getInsets() {
		return new Insets(-OutputUI.SIDE_LENGTH / 2 + GraphicalBlock.TOP_INSET, OutputUI.MAX_X, 0, 0);
	}

	@Override
	public void updateUI() {
		setUI((OutputUI) UIManager.getUI(this));
	}

	@Override
	public ComponentUI getUI() {
		return (OutputUI) ui;
	}

	public void setUI(OutputUI newUI) {
		super.setUI(newUI);
	}

	@Override
	public Object[] getSelectedObjects() {
		return null;
	}

	@Override
	public void addItemListener(ItemListener l) {

	}

	@Override
	public void removeItemListener(ItemListener l) {

	}

	@Override
	public String getUIClassID() {
		return OutputUI.uiClassID;
	}

}
