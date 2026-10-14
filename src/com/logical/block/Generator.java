package com.logical.block;

public class Generator extends Block {

	public static final String OUT_NAME = "out";

	public Generator() {
		super("Generator", new String[0], new String[] { OUT_NAME });

		// Already set output to on
		super.setOutputState(OUT_NAME, State.ON);
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
