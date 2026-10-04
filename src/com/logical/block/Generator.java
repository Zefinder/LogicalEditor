package com.logical.block;

public class Generator extends Block {

	public Generator() {
		super("Generator", new String[0], new String[] { "out" });
		
		// Already set output to on
		super.setOutputState(0, State.ON);
	}

	@Override
	public void performLogic() {
	}

	@Override
	public int getWidthHint() {
		return 3;
	}	
	
	@Override
	public int getHeightHint() {
		return 1;
	}
		
}
