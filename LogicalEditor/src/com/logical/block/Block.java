package com.logical.block;

import java.util.Arrays;
import java.util.function.Supplier;

public abstract class Block {

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

	private final int id;
	private final String name;
	private final Input[] inputs;
	private final Output[] outputs;

	public Block(String name, String[] inputs, String[] outputs) {
		this.id = ID_SUPPLIER.get();
		this.name = name == null ? "null" : name;
		this.inputs = new Input[inputs.length];
		this.outputs = new Output[outputs.length];

		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = new Input(inputs[i]);
		}

		for (int i = 0; i < outputs.length; i++) {
			this.outputs[i] = new Output(outputs[i]);
		}
	}

	public void updateOutputs() {
		for (Output output : outputs) {
			output.update();
		}
	}

	public void updateInputs() {
		for (Input input : inputs) {
			input.update();
		}
	}
	
	public String name() {
		return name;
	}
	
	public Output[] outputs() {
		return outputs;
	}
	
	public Input[] inputs() {
		return inputs;
	}	

	public abstract void performLogic();

	// TODO Change index by name
	public void connectTo(Block other, int inputIndex, int outputIndex) {
		if (other == null) {
			System.err.println("Cannot connect wire to null block!");
			return;
		}

		// Check if input and output exist
		if (outputIndex >= this.outputs.length) {
			System.err.println("Cannot set output wire for %s: index %d out of bounds".formatted(this.toString()));
			return;
		}
		Output output = this.outputs[outputIndex];

		if (inputIndex >= other.inputs.length) {
			System.err.println("Cannot set input wire for %s: index %d out of bounds".formatted(other.toString()));
			return;
		}
		Input input = other.inputs[inputIndex];

		// Check if output already has a wire, else create and assign it
		Wire outputWire = output.wire();
		if (outputWire == Wire.NO_WIRE) {
			outputWire = new Wire();
			output.setWire(outputWire);
		}

		input.setWire(outputWire);
	}

	// TODO Change index by name
	protected State getInputState(int index) {
		if (index >= this.inputs.length) {
			System.err.println("Cannot get input state for %s: index %d out of bounds".formatted(this.toString()));
			return State.OPEN;
		}

		return inputs[index].state();
	}

	// TODO Change index by name
	protected void setOutputState(int index, State state) {
		if (index >= this.outputs.length) {
			System.err.println("Cannot set output state for %s: index %d out of bounds".formatted(this.toString()));
			return;
		}

		outputs[index].setState(state);
	}

	@Override
	public String toString() {
		return "Block[id=%d, name=%s, inputs=%s, outputs=%s]".formatted(id, name, Arrays.toString(inputs),
				Arrays.toString(outputs));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Block other) {
			return this.id == other.id;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return ("Block:" + id).hashCode();
	}

}
