package com.main.entity;

public class Card {
	
	private int cardSetValue;
	private int cardfacevalue;
	public Card(int cardvalue, int cardfacevalue) {
		super();
		this.cardSetValue = cardvalue;
		this.cardfacevalue = cardfacevalue;
	}
	public int getCardSetvalue() {
		return cardSetValue;
	}
	public void setCardvalue(int cardvalue) {
		this.cardSetValue = cardvalue;
	}
	public int getCardfacevalue() {
		return cardfacevalue;
	}
	public void setCardfacevalue(int cardfacevalue) {
		this.cardfacevalue = cardfacevalue;
	}

}
