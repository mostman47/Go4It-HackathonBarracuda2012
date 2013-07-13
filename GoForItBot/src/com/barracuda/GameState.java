package com.barracuda;

import java.util.ArrayList;
import java.util.Map;

public class GameState {
	public Integer idx;
	public Integer opponent_id;
	public ArrayList<ArrayList<Integer>> owned_squares;
	public Integer turn;
	public ArrayList<ArrayList<Integer>> board;
	public Integer id;
	public Integer credits;
	public Double remaining_time;

	public GameState(Map<String, Object> state) {
		idx = (Integer)state.get("idx");
		opponent_id = (Integer)state.get("opponent_id");
		owned_squares = unpackListofListofInts(state.get("owned_squares"));
		turn = (Integer)state.get("turn");
		board = unpackListofListofInts(state.get("board"));
		id = (Integer)state.get("id");
		credits = (Integer)state.get("credits");
		remaining_time = (Double)state.get("remaining_time");
	}

	private ArrayList<ArrayList<Integer>> unpackListofListofInts(Object obj) {
		ArrayList<ArrayList<Integer>> targetList = new ArrayList<ArrayList<Integer>>();
		Object[] srcList = (Object[])obj;
		for (int i=0; i < srcList.length; i++) {
			Object[] oList = (Object [])srcList[i];
			ArrayList<Integer> listofIntegers = new ArrayList<Integer>();
			for (int j=0; j < oList.length; j++)
				listofIntegers.add((Integer)oList[j]);
			targetList.add(listofIntegers);
		}
		return targetList;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("idx: " + idx.toString() + "\n");
		s.append("opponent_id: " + opponent_id.toString() + "\n");
		s.append("owned_squares: " + owned_squares.toString() + "\n");
		s.append("turn: " + turn.toString() + "\n");
		s.append("board: " + board.toString() + "\n");
		s.append("id:" + id.toString() + "\n");
		s.append("credits: " + credits.toString() + "\n");
		s.append("remaining_time: " + remaining_time.toString());
		return s.toString();
	}
}
