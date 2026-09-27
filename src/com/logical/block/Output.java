package com.logical.block;

import java.util.function.Supplier;

public class Output {

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

	private final int id;
	private final String name;

	private Wire wire;
	private State state;

	public Output(String name) {
		this.id = ID_SUPPLIER.get();
		this.name = name;
		this.wire = Wire.NO_WIRE;
		this.state = State.OPEN;
	}

	public void setWire(Wire wire) {
		if (wire == null) {
			System.err.println("Trying to set null to Output");
			return;
		}

		this.wire = wire;
	}
	
	public Wire wire() {
		return wire;
	}

	public void update() {
		this.wire.setState(state);
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Output[id=%d, name=%s, wire=%s, state=%s]".formatted(id, name, wire, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Output other) {
			return this.id == other.id;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return ("Output:" + id).hashCode();
	}

}
