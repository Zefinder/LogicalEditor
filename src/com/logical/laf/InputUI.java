package com.logical.laf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import com.logical.gui.element.GraphicalInput;

public class InputUI extends LogicalEditorUI {

	public static final String uiClassID = "InputUI";
	public static final String PROPERTY_PREFIX = "Input.";

	public static final int SIDE_LENGTH = 10;
	private static final int[] TRIANGLE_X = { 0, (int) Math.sqrt((SIDE_LENGTH * 1.5) * (SIDE_LENGTH * 0.5)), 0 };
	private static final int[] TRIANGLE_Y = { 0, SIDE_LENGTH / 2, SIDE_LENGTH };

	private static final int CIRCLE_RADIUS = 5;
	public static final int MAX_X = TRIANGLE_X[1] + 2 * CIRCLE_RADIUS;
	public static final int MAX_Y = Math.max(SIDE_LENGTH, CIRCLE_RADIUS);

	private static final InputUI INSTANCE = new InputUI();

	private InputUI() {
	}

	public static ComponentUI createUI(JComponent c) {
		c.setSize(INSTANCE.getPreferredSize(c));
		return INSTANCE;
	}

	@Override
	protected void installListeners(JComponent c) {
		// Set mouse listener
	}

	@Override
	protected void uninstallListeners(JComponent c) {
		// Unset mouse listener
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return new Dimension(TRIANGLE_X[1], SIDE_LENGTH);
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g.create();
		GraphicalInput gInput = (GraphicalInput) c;
		Insets insets = gInput.getInsets();

		int x = gInput.getInternalX() + insets.left;
		int y = gInput.getInternalY() + insets.top;

		// Draw arrow
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
		g2d.setColor(Color.black);
		g2d.drawLine(x + TRIANGLE_X[0], y + TRIANGLE_Y[0], x + TRIANGLE_X[1], y + TRIANGLE_Y[1]);
		g2d.drawLine(x + TRIANGLE_X[1], y + TRIANGLE_Y[1], x + TRIANGLE_X[2], y + TRIANGLE_Y[2]);
	}

	@Override
	protected String getPropertyPrefix() {
		return PROPERTY_PREFIX;
	}

}
