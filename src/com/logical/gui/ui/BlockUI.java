package com.logical.gui.ui;

import static javax.swing.SwingConstants.CENTER;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;

import com.logical.gui.element.GraphicalBlock;

public class BlockUI extends ComponentUI {

	public static final String uiClassID = "BlockUI";

	// Same rectangles for every blocks, update each time you will paint
	private static Rectangle viewRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();

	private static final BlockUI INSTANCE = new BlockUI();

	private BlockUI() {
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	@Override
	public void installUI(JComponent c) {
		installListeners(c);
	}

	private void installListeners(JComponent c) {
		// Set mouse listener
	}

	@Override
	public void uninstallUI(JComponent c) {
		uninstallListeners(c);
	}

	private void uninstallListeners(JComponent c) {
		// Unset mouse listener
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		GraphicalBlock b = (GraphicalBlock) c;
		return new Dimension(b.gridWidth() * b.gridSize(), b.gridHeight() * b.gridSize());
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g;
		GraphicalBlock gBlock = (GraphicalBlock) c;

		// Get text and init view rectangle
		String text = layout(gBlock, g.getFontMetrics(), gBlock.getWidth(), gBlock.getHeight());

		// Paint view rectangle
		g2d.setColor(Color.red);
		g2d.draw(viewRect);

		// Paint text (is not null)
		paintText(g, gBlock, textRect, text);

		// Keep in case painting focus
//		if (b.isFocusPainted() && b.hasFocus()) {
//			// paint UI specific focus
//			paintFocus(g, b, viewRect, textRect, iconRect);
//		}
	}

	private String layout(GraphicalBlock b, FontMetrics fm, int width, int height) {
		// Compute using insets (based on BasicButtonUI)
		Insets i = b.getInsets();
		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = width - (i.right + viewRect.x);
		viewRect.height = height - (i.bottom + viewRect.y);

		textRect.x = textRect.y = textRect.width = textRect.height = 0;

		// Layout text using SwingUtilities: no icon and text is not null, also
		// alignment is always center
		return SwingUtilities.layoutCompoundLabel(b, fm, b.getText(), null, CENTER, CENTER, CENTER, CENTER, viewRect,
				new Rectangle(), textRect, 0);
	}

	private void paintText(Graphics g, GraphicalBlock b, Rectangle textRect, String text) {
		FontMetrics fm = g.getFontMetrics();
		g.setColor(b.getForeground());
		g.drawString(text, textRect.x, textRect.y + fm.getAscent());
	}

}
