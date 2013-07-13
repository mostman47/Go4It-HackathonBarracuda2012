package com.barracuda;

import java.util.Map;

public class MoveResult {
	public Integer id = null;
	public String result = null;
	public Integer choice = null;

	public MoveResult(Map<String, Object> state) {
		id = (Integer)state.get("id");
		result = (String)state.get("result");
		if (state.containsKey("choice"))
			choice = (Integer)state.get("choice");
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("id:" + id.toString() + "\n");
		s.append("result: " + result + "\n");
		if (choice != null)
			s.append("choice: " + choice.toString() + "\n");

		return s.toString();
	}
}
