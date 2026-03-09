package br.ies.aps.eightpuzzle.model;

public class Player {
	private String name;
	private Boolean winner = false;
	private Integer id;
	private Integer moves = 0;

	public Player(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getMoves() {
		return moves;
	}
	
	public void setMoves(Integer moves) {
		this.moves = moves;
	}
	
	public Boolean isWinner() {
		return winner;
	}
	
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
