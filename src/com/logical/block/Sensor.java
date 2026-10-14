package com.logical.block;

public class Sensor extends Block {

	public static final String IN_NAME = "in";
	public static final String OUT_NAME = "out";

	private State previousState;

	public Sensor() {
		super("Sensor", new String[] { IN_NAME }, new String[] { OUT_NAME });
		this.previousState = State.OFF;
	}

	@Override
	public void performLogic() {
		if (previousState == State.OFF && super.getInputState(IN_NAME) == State.ON) {
			System.out.println("Sensor ON");
		} else if (previousState == State.ON && super.getInputState(IN_NAME) == State.OFF) {
			System.out.println("Sensor OFF");
		}
	}

}
