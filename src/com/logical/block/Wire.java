package com.logical.block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Wire {

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

//	private final Output output;
	private final String name;
	private final Map<String, Input> nameToInput;

	/**
	 * Wire that symbolises no wire connected. Setting state won't do anything. It
	 * always has id 0.
	 */
	public static final Wire NO_WIRE = new Wire(null) {
		@Override
		public void setState(State state) {
		}
	};

	private final int id;

	private State state;

//	public Wire(Output output, Input... inputs) {
	public Wire(String name, Input... inputs) {
		this.id = ID_SUPPLIER.get();
		this.state = State.OPEN;
//		this.output = output;
//		this.name = output.name();
		this.name = name;
		this.nameToInput = new HashMap<String, Input>();
		for (Input input : inputs) {
			addInput(input);
		}
	}

	public String name() {
		return name;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	public State state() {
		return state;
	}

	// TODO Check if can't be prettier
	public boolean addInput(Input input) {
		if (nameToInput.containsKey(input.toString())) {
			return false;
		}

		nameToInput.put(input.toString(), input);
		return true;
	}

	public boolean removeInput(Input input) {
		return nameToInput.remove(input.toString(), input);
	}

	@Override
	public String toString() {
		return "Wire[id=%d, state=%s]".formatted(id, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Wire other) {
			return this.id == other.id;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return ("Wire:" + id).hashCode();
	}

}
