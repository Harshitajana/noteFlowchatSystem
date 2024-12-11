package com.flow.baseQualification;


public class RelationalQualification extends Qualification{
	
	private String paramName;

	private Object value;

	private RelationalOperator operator;

	public RelationalQualification(String paramName, RelationalOperator operator, Object value) {
		this.paramName = paramName;
		this.value = value;
		this.operator = operator;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public RelationalOperator getOperator() {
		return operator;
	}

	public void setOperator(RelationalOperator operator) {
		this.operator = operator;
	}

}
