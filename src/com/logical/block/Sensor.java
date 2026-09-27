package com.logical.block;

public class Sensor extends Block {

	private State previousState;

	public Sensor() {
		super("Sensor", new String[] { "in" }, new String[] { "out" });
		this.previousState = State.OFF;
	}

	@Override
	public void performLogic() {
		if (previousState == State.OFF && super.getInputState(0) == State.ON) {
			System.out.println("Sensor ON");
		} else if (previousState == State.ON && super.getInputState(0) == State.OFF) {
			System.out.println("Sensor OFF");
		}
	}

}
