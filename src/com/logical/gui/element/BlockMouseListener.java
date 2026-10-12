package com.logical.gui.element;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.logical.gui.block.GraphicalBlock;

public class BlockMouseListener extends MouseAdapter {

	private GraphicalBlock block;
	private int beforeDraggedMouseScreenX;
	private int beforeDraggedMouseScreenY;
	private int beforeDraggedBlockX;
	private int beforeDraggedBlockY;

	public BlockMouseListener(GraphicalBlock block) {
		this.block = block;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Component clicked: " + block.text());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Save mouse and block coordinates for delta computation
		Point mouseScreenLocation = e.getLocationOnScreen();
		beforeDraggedMouseScreenX = mouseScreenLocation.x;
		beforeDraggedMouseScreenY = mouseScreenLocation.y;

		Point blockLocation = block.getLocation();
		beforeDraggedBlockX = blockLocation.x;
		beforeDraggedBlockY = blockLocation.y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("AAAAAAAAAAA\n");
		block.setDragged(false);
		block.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Add test border
//		block.setBorder(BorderFactory.createLineBorder(Color.black));
//		block.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Remove test border
//		block.setBorder(BorderFactory.createEmptyBorder());
//		block.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!block.isDragged()) {
			block.setDragged(true);
		}

		// Check if left click
//		if (e.getButton() == MouseEvent.) {
		// Compute delta and set new location
		int deltaX = e.getXOnScreen() - beforeDraggedMouseScreenX;
		int deltaY = e.getYOnScreen() - beforeDraggedMouseScreenY;

		block.setLocation(beforeDraggedBlockX + deltaX, beforeDraggedBlockY + deltaY);
		block.repaint();
//		}
	}

}
