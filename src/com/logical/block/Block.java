package com.logical.block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Block {

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

	private final int id;
	private final String name;
	private final Input[] inputs;
	private final Output[] outputs;
	private final Map<String, Input> nameToInput;
	private final Map<String, Output> nameToOutput;

	public Block(String name, String[] inputs, String[] outputs) {
		this.id = ID_SUPPLIER.get();
		this.name = name == null ? "null" : name;
		this.inputs = new Input[inputs.length];
		this.outputs = new Output[outputs.length];

		nameToInput = new HashMap<String, Input>();
		nameToOutput = new HashMap<String, Output>();

		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = new Input(inputs[i]);
			nameToInput.put(inputs[i], this.inputs[i]);
		}

		for (int i = 0; i < outputs.length; i++) {
			this.outputs[i] = new Output(outputs[i]);
			nameToOutput.put(outputs[i], this.outputs[i]);
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

	public Input getInput(String name) {
		if (!nameToInput.containsKey(name)) {
			System.err.println("Cannot get input for %s: name %s does not exist".formatted(this.toString(), name));
			return null;
		}

		return nameToInput.get(name);
	}

	public Output getOutput(String name) {
		if (!nameToOutput.containsKey(name)) {
			System.err.println("Cannot get output for %s: name %s does not exist".formatted(this.toString(), name));
			return null;
		}

		return nameToOutput.get(name);
	}

	public Input[] inputs() {
		return inputs;
	}

	public abstract void performLogic();

	/**
	 * <p>
	 * Value for the related component that gives a hint on the block width. This is
	 * in block grid unit.
	 * </p>
	 * 
	 * <p>
	 * A value of zero (or less) is considered as no hint.
	 * </p>
	 * 
	 * <p>
	 * Note that this value is a <em>hint</em>, meaning that the component is not
	 * forced to use this value. For example, if the name is too long for the hinted
	 * width, the actual width will be increased.
	 * </p>
	 * 
	 * @return the width hint
	 */
	public int getWidthHint() {
		return 0;
	}

	/**
	 * <p>
	 * Value for the related component that gives a hint on the block height. This
	 * is in block grid unit.
	 * </p>
	 * 
	 * <p>
	 * A value of zero (or less) is considered as no hint and the component will
	 * compute it.
	 * </p>
	 * 
	 * <p>
	 * Note that this value is a <em>hint</em>, meaning that the component is not
	 * forced to use this value. For example, if there is more input (or output)
	 * than the UI component can hold, the actual height will be increased.
	 * </p>
	 * 
	 * @return the height hint
	 */
	public int getHeightHint() {
		return 0;
	}

	// TODO Change index by name
	public void connectTo(Block other, String inputName, String outputName) {
		if (other == null) {
			System.err.println("Cannot connect wire to null block!");
			return;
		}

		// Check if input and output exist
		if (!this.nameToOutput.containsKey(outputName)) {
			System.err.println(
					"Cannot set output wire for %s: name %s does not exist".formatted(this.toString(), outputName));
			return;
		}
		Output output = nameToOutput.get(outputName);

		if (!other.nameToInput.containsKey(inputName)) {
			System.err.println(
					"Cannot set input wire for %s: name %s does not exist".formatted(other.toString(), inputName));
			return;
		}
		Input input = other.nameToInput.get(inputName);

		// Check if output already has a wire, else create and assign it
		Wire outputWire = output.wire();
		if (outputWire == Wire.NO_WIRE) {
			outputWire = new Wire(output.name(), input);
			output.setWire(outputWire);
		}

		input.setWire(outputWire);
	}

	protected State getInputState(String name) {
		if (!nameToInput.containsKey(name)) {
			System.err
					.println("Cannot get input state for %s: name %s does not exist".formatted(this.toString(), name));
			return State.OPEN;
		}

		return nameToInput.get(name).state();
	}

	protected void setOutputState(String name, State state) {
		if (!nameToOutput.containsKey(name)) {
			System.err
					.println("Cannot set output state for %s: name %s does not exist".formatted(this.toString(), name));
			return;
		}

		nameToOutput.get(name).setState(state);
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
