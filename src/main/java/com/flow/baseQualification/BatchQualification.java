package com.flow.baseQualification;

import java.util.Set;



public class BatchQualification extends Qualification{
	
	private Set<Qualification> qualSet;

	private BinaryOperator binaryOperator;

	public Set<Qualification> getQualSet() {
		return qualSet;
	}

	public void setQualSet(Set<Qualification> qualSet) {
		this.qualSet = qualSet;
	}

	public BinaryOperator getBinaryOperator() {
		return binaryOperator;
	}

	public void setBinaryOperator(BinaryOperator binaryOperator) {
		this.binaryOperator = binaryOperator;
	}

}
