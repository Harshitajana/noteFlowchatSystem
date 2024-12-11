package com.flow.baseQualification;

public enum BinaryOperator {
	
	And(0),

	Or(1);

	private int id;

	private BinaryOperator(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
