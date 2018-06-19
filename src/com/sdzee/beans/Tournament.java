package com.sdzee.beans;

import java.util.Map;

public class Tournament {
	
	private int id;
	private String title;
	private int numberOfTeams;
	private String[] teams;
	private Map<Integer, String> spots;
	private Map<Integer, Integer[]> scores;
	private String dateTournament;
	
	public void setId(int n) {
		this.id = n;
	}
	
	public void setTitle (String t) {
		this.title = t;
	}
	
	public void setNumberOfTeams (int n) {
		this.numberOfTeams = n;
	}
	
	public void setTeams (String[] str) {
		int n = str.length;
		if (n == numberOfTeams) {
			this.teams = new String[numberOfTeams];
			for (int i = 0; i < n; i++) {
				this.teams[i] = str[i];
			}
		}
	}
		
	public void setSpots (Map<Integer, String> str) {
		this.spots = str;
	}
	
	public void setSpot (int spotNumber, String team) {
		this.spots.put(spotNumber,team);
	}
	
	public void setScores (Map<Integer, Integer[]> scores) {
		this.scores = scores;
	}
	
	public void setScore (int matchNumber, int topOrBottom, int score) {
		Integer[] ts = this.scores.get(matchNumber);
		if (ts == null) {
			ts = new Integer[2];
			ts[0] = -1;
			ts[1] = -1;
		}
		ts[topOrBottom] = score;
		this.scores.put(matchNumber, ts);
	}
	
	public void setDateTournament (String date) {
		this.dateTournament = date;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getNumberOfTeams() {
		return this.numberOfTeams;
	}
	
	public String[] getTeams() {
		return this.teams;
	}
	
	public Map<Integer, String> getSpots() {
		return this.spots;
	}
	
	public Map<Integer, Integer[]> getScores() {
		return this.scores;
	}
	
	public String getDateTournament() {
		return this.dateTournament;
	}

}
