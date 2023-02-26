package com.cli.ecart.entity;

public enum STATUS {

	ACTIVE(1), INACTIVE(0);

	private int value;

	STATUS(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
