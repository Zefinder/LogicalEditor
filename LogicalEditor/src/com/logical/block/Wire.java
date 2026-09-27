package com.logical.block;

import java.util.function.Supplier;

public class Wire {

	// For now does not have any idea on who it is linked to...
	// Just here as a "cache" and to make easier the "open" state

	private static int CURRENT_ID = 0;
	private static final Supplier<Integer> ID_SUPPLIER = () -> CURRENT_ID++;

	/**
	 * Wire that symbolises no wire connected. Setting state won't do anything. It
	 * always has id 0.
	 */
	public static final Wire NO_WIRE = new Wire() {
		@Override
		public void setState(State state) {
		}
	};

	private final int id;

	private State state;

	public Wire() {
		this.id = ID_SUPPLIER.get();
		this.state = State.OPEN;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State state() {
		return state;
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
