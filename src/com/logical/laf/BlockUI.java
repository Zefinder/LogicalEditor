package com.logical.laf;

import static javax.swing.SwingConstants.CENTER;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.ComponentUI;

import com.logical.gui.block.GraphicalBlock;
import com.logical.gui.element.BlockMouseListener;

public class BlockUI extends LogicalEditorUI {

	public static final String uiClassID = "BlockUI";
	public static final String PROPERTY_PREFIX = "Block.";

	// Same rectangles for every blocks, update each time you will paint
	private static Rectangle componentRect = new Rectangle();
	private static Rectangle viewRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();

	private static final BlockUI INSTANCE = new BlockUI();

	private BlockUI() {
	}

	public static ComponentUI createUI(JComponent c) {
		return INSTANCE;
	}

	@Override
	protected void installListeners(JComponent c) {
		c.addMouseListener(new BlockMouseListener((GraphicalBlock) c) {
			
		});
	}

	@Override
	protected void uninstallListeners(JComponent c) {
		// Unset mouse listener
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		GraphicalBlock b = (GraphicalBlock) c;
		return new Dimension(b.gridWidth() * b.gridSize(), b.gridHeight() * b.gridSize());
	}

	public void paint(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D) g.create();
		GraphicalBlock gBlock = (GraphicalBlock) c;

		// Get text and init view rectangle
		String text = layout(gBlock, g2d.getFontMetrics(), gBlock.getWidth(), gBlock.getHeight());

		Stroke old = g2d.getStroke();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));

		// Paint up and down sides
		g2d.drawLine(viewRect.x, viewRect.y, viewRect.x + viewRect.width, viewRect.y);
		g2d.drawLine(viewRect.x, viewRect.y + viewRect.height, viewRect.x + viewRect.width,
				viewRect.y + viewRect.height);

		// Paint sides with input and output placements
		int startXInput = viewRect.x;
		int startXOutput = viewRect.x + viewRect.width;
		int currentY = viewRect.y;
		int lineLength = InputUI.SIDE_LENGTH;
		int cornerLineLength = gBlock.subgridSize() - (lineLength / 2);
		g2d.drawLine(startXInput, currentY, startXInput, currentY + cornerLineLength);
		g2d.drawLine(startXOutput, currentY, startXOutput, currentY + cornerLineLength);
		currentY += cornerLineLength;

		for (int i = 0; i < gBlock.subgridHeight() - 1; i++) {
			if (!gBlock.hasInputAtSubgridLocation(i)) {
				g2d.drawLine(startXInput, currentY, startXInput, currentY + lineLength);
			}

			if (!gBlock.hasOutputAtSubgridLocation(i)) {
				g2d.drawLine(startXOutput, currentY, startXOutput, currentY + lineLength);
			}
			currentY += lineLength;
		}
		g2d.drawLine(startXInput, currentY, startXInput, currentY + cornerLineLength);
		g2d.drawLine(startXOutput, currentY, startXOutput, currentY + cornerLineLength);

		g2d.setStroke(old);

		// Paint text (is not null)
		paintText(g2d, gBlock, textRect, text);

		// Keep in case painting focus
//		if (b.isFocusPainted() && b.hasFocus()) {
//			// paint UI specific focus
//			paintFocus(g, b, viewRect, textRect, iconRect);
//		}
	}

	private String layout(GraphicalBlock b, FontMetrics fm, int width, int height) {
		// Compute using insets (based on BasicButtonUI)
		Insets i = b.getInsets();
		componentRect.x = 0;
		componentRect.y = 0;
		componentRect.width = width;
		componentRect.height = height;

		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = width - (i.right + viewRect.x);
		viewRect.height = height - (i.bottom + viewRect.y);

		textRect.x = textRect.y = textRect.width = textRect.height = 0;

		// Layout text using SwingUtilities: no icon and text is not null, also
		// alignment is always center
		return SwingUtilities.layoutCompoundLabel(b, fm, b.text(), null, CENTER, CENTER, CENTER, CENTER, viewRect,
				new Rectangle(), textRect, 0);
	}

	private void paintText(Graphics g, GraphicalBlock b, Rectangle textRect, String text) {
		FontMetrics fm = g.getFontMetrics();
		g.setColor(b.getForeground());
		g.drawString(text, textRect.x, textRect.y + fm.getAscent());
	}

	@Override
	protected String getPropertyPrefix() {
		return PROPERTY_PREFIX;
	}

}
