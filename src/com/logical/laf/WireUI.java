package com.logical.laf;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import com.logical.gui.element.GraphicalWire;

public class WireUI extends LogicalEditorUI {

	public static final String uiClassID = "WireUI";
	public static final String PROPERTY_PREFIX = "Wire.";

	public static final int SIDE_LENGTH = 10;
	private static final int[] TRIANGLE_X = { 0, (int) Math.sqrt((SIDE_LENGTH * 1.5) * (SIDE_LENGTH * 0.5)), 0 };
	private static final int[] TRIANGLE_Y = { 0, SIDE_LENGTH / 2, SIDE_LENGTH };

	private static final WireUI INSTANCE = new WireUI();

	private WireUI() {
	}

	public static ComponentUI createUI(JComponent c) {
		c.setSize(INSTANCE.getPreferredSize(c));
		return INSTANCE;
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		GraphicalWire wire = (GraphicalWire) c;
		return new Dimension(100 + SIDE_LENGTH * 2, SIDE_LENGTH + 5);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		System.out.println("AAAAA");
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));

		Insets insets = c.getInsets();
		// Draw triangle for output
		int startX = insets.left;
		int startY = insets.top;
		g2d.drawLine(startX + TRIANGLE_X[0], startY + TRIANGLE_Y[0], startX + TRIANGLE_X[1], startY + TRIANGLE_Y[1]);
		g2d.drawLine(startX + TRIANGLE_X[1], startY + TRIANGLE_Y[1], startX + TRIANGLE_X[2], startY + TRIANGLE_Y[2]);

		// Draw triangle for input
		int endX = 100 - insets.right;
		int endY = insets.top;
		g2d.drawLine(endX + TRIANGLE_X[0], endY + TRIANGLE_Y[0], endX + TRIANGLE_X[1], endY + TRIANGLE_Y[1]);
		g2d.drawLine(endX + TRIANGLE_X[1], endY + TRIANGLE_Y[1], endX + TRIANGLE_X[2], endY + TRIANGLE_Y[2]);

		// Draw line between triangles
		g2d.drawLine(startX + TRIANGLE_X[1], startY + TRIANGLE_Y[1], endX + TRIANGLE_X[1], endY + TRIANGLE_Y[1]);
	}

	@Override
	protected String getPropertyPrefix() {
		return PROPERTY_PREFIX;
	}

}
