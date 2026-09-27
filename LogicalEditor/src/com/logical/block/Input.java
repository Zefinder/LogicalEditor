package com.logical.block;

import java.util.function.Supplier;

public class Input {

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

	private final int id;
	private final String name;

	private Wire wire;
	private State state;

	public Input(String name) {
		this.id = ID_SUPPLIER.get();
		this.name = name;
		this.wire = Wire.NO_WIRE;
		this.state = State.OPEN;
	}

	public void setWire(Wire wire) {
		if (wire == null) {
			System.err.println("Trying to set null to Input");
			return;
		}

		this.wire = wire;
	}

	public void update() {
		if (wire == null) {
			state = State.OPEN;
		} else {
			state = wire.state();
		}
	}

	public State state() {
		return state;
	}

	@Override
	public String toString() {
		return "Input[id=%d, name=%s, wire=%s, state=%s]".formatted(id, name, wire, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Input other) {
			return this.id == other.id;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return ("Input:" + id).hashCode();
	}

}
