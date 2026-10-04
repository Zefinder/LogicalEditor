package com.logical.laf;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LogicalEditorLookAndFeel extends MetalLookAndFeel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5762579546762971054L;

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public String getID() {
		return "Test";
	}

	@Override
	public String getDescription() {
		return "Test";
	}

	protected void initClassDefaults(UIDefaults table) {
		super.initClassDefaults(table);
		table.put(BlockUI.uiClassID, BlockUI.class.getName());
		table.put(InputUI.uiClassID, InputUI.class.getName());
		table.put(OutputUI.uiClassID, OutputUI.class.getName());
	}
	
	@Override
	protected void initComponentDefaults(UIDefaults table) {
		super.initComponentDefaults(table);
		
		Object[] defaults = {
			"Block.font", getUserTextFont()
		};
		
		table.putDefaults(defaults);
	}

}
