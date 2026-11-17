package com.logical.gui.element;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import com.logical.block.Wire;
import com.logical.laf.WireUI;

public class GraphicalWire extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1839346588791286760L;

	private final GraphicalOutput output;
	private final List<GraphicalInput> inputs;

	public GraphicalWire(Wire wire, GraphicalOutput output, GraphicalInput... inputs) {
		this.output = output;
		this.inputs = new ArrayList<GraphicalInput>(List.of(inputs));
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		updateUI();
	}

	@Override
	public Insets getInsets() {
		return new Insets(2, 2, 0, 2);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(100, 100, getWidth(), getHeight());
	}

	@Override
	public void doLayout() {
		this.setBounds(this.getBounds());
		repaint();
	}

	@Override
	public void updateUI() {
		setUI((WireUI) UIManager.getUI(this));
	}

	@Override
	public ComponentUI getUI() {
		return (WireUI) ui;
	}

	public void setUI(WireUI newUI) {
		super.setUI(newUI);
	}

	@Override
	public String getUIClassID() {
		return WireUI.uiClassID;
	}

}
