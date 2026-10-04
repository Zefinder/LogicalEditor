package com.logical.gui.element;

import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import com.logical.block.Input;
import com.logical.gui.block.GraphicalBlock;
import com.logical.gui.editor.EditorPlaygroundPanel;
import com.logical.laf.InputUI;

public class GraphicalInput extends JComponent implements ItemSelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7964609885440344466L;

	// Current output position
	private int internalGridX;
	private int internalGridY;

	// Cache grid size
	private int gridSize;

	public GraphicalInput(Input input, int internalGridX) {
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
		return new Insets(-InputUI.SIDE_LENGTH / 2 + GraphicalBlock.TOP_INSET, InputUI.MAX_X, 0, 0);
	}

	@Override
	public void updateUI() {
		setUI((InputUI) UIManager.getUI(this));
	}

	@Override
	public ComponentUI getUI() {
		return (InputUI) ui;
	}

	public void setUI(InputUI newUI) {
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
		return InputUI.uiClassID;
	}

}
