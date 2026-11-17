package com.logical.gui.element;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.Rectangle;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
	
	private String text;

	public GraphicalInput(GraphicalBlock block, Input input, int internalGridX) {
		this.internalGridX = internalGridX;
		this.internalGridY = 0;
		this.gridSize = EditorPlaygroundPanel.INOUT_GRID_SIZE;
		
		this.text = input.name();

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
		this.setBorder(new LineBorder(Color.blue));
	}

	protected void paintBorder(Graphics g) {
		Border border = getBorder();
		if (border != null) {
			border.paintBorder(this, g, getX(), getY(), getWidth(), getHeight());
		}
	}

	public String getText() {
		return text;
	}

	
//	@Override
//	public Insets getInsets() {
//		return new Insets(-InputUI.SIDE_LENGTH / 2 + GraphicalBlock.TOP_INSET, InputUI.MAX_X, 0, 0);
//	}
	
	@Override
	public Rectangle getBounds() {
		Dimension size = getPreferredSize();
		Insets insets = getInsets();
		return new Rectangle(getInternalX() + insets.left, getInternalY() + insets.top, size.width, size.height);
	}

	@Override
	public void doLayout() {
		this.setBounds(getBounds());
		repaint();
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
