package com.barracuda;

import java.util.Map;

public class GameResult {
	public String winner;
	public Integer id;
	public String full_game;

	public GameResult(Map<String, Object> state) {
		id = (Integer)state.get("id");
		winner = (String)state.get("winner");
		full_game = (String)state.get("full_game");
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("winner: " + winner+ "\n");
		s.append("id: " + id.toString() + "\n");
		s.append("full_game: " + full_game + "\n");
		return s.toString();
	}
}
