package com.guisalmeida.eightpuzzle.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "moves")
	private Integer moves = 0;

	@Column(name = "winner")
	private Boolean winner = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	protected Player() {
		// Required by JPA
	}

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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
