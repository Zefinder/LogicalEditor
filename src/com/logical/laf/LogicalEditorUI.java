package com.logical.laf;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;

public abstract class LogicalEditorUI extends ComponentUI {

	public LogicalEditorUI() {
	}

	@Override
	public void installUI(JComponent c) {
		installDefaults(c);
		installListeners(c);
	}
	
	protected void installDefaults(JComponent c) {
		String pp = getPropertyPrefix();
        LookAndFeel.installColorsAndFont(c, pp + "background",
                pp + "foreground", pp + "font");
	}

	protected void installListeners(JComponent c) {
	}

	@Override
	public void uninstallUI(JComponent c) {
		uninstallDefaults(c);
		uninstallListeners(c);
	}

	protected void uninstallDefaults(JComponent c) {
		// TODO Needed?
	}
	
	protected void uninstallListeners(JComponent c) {
	}
	
	protected abstract String getPropertyPrefix();
	
}
