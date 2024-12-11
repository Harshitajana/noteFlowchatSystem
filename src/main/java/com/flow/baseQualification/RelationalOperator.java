package com.flow.baseQualification;

public enum RelationalOperator {
	
	LessThan(0),

	LessThanOrEqual(1),

	GreaterThanOrEqual(2),

	GreaterThan(3),

	Equal(4),

	Equal_Case_Insensitive(5),

	Like(6),

	In(7),

	Is_Member(8),

	All_Members_Exist(9),

	Any_Member_Or_All_Members_Exist(10),

	No_Members_Exist(11),

	Any_Member_But_Not_All_Members_Exist(12),

	Match_Tree(15),

	Not_Equal(16),

	Not_In(17),

	Start_With(18),

	End_With(19),

	Contains(20),

	Between(21),

	Not_Equal_Case_Insensitive(22),

	In_Case_Insensitive(23),

	Not_In_Case_Insensitive(24);

	private int id;

	private RelationalOperator(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
