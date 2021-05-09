package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lottery {
	private StringProperty noColumn;
	private StringProperty dateColumn;
	private StringProperty winColumn;
	private StringProperty bonusColumn;
	private StringProperty joColumn;
	
	public Lottery() {
		noColumn = new SimpleStringProperty("");
		dateColumn = new SimpleStringProperty("");
		winColumn = new SimpleStringProperty("");
		bonusColumn = new SimpleStringProperty("");
		joColumn = new SimpleStringProperty("");
	}
	
	public Lottery(StringProperty n, StringProperty d, StringProperty j, StringProperty l, StringProperty b) {
		noColumn = n;
		dateColumn = d;
		winColumn = l;
		bonusColumn = b;
		joColumn = j;
	}
	
	public void setAllInfo(String n, String d, String j, String l, String b) {
		noColumn = new SimpleStringProperty(n);
		dateColumn = new SimpleStringProperty(d);
		winColumn = new SimpleStringProperty(l);
		bonusColumn = new SimpleStringProperty(b);
		joColumn = new SimpleStringProperty(j);
	}
	
	public StringProperty noProperty() {
		return noColumn;
	}
	public void setNo(StringProperty no) {
		this.noColumn = no;
	}
	public StringProperty dateProperty() {
		return dateColumn;
	}
	public void setDate(StringProperty date) {
		this.dateColumn = date;
	}
	public StringProperty lotteryProperty() {
		return winColumn;
	}
	public void setLottery(StringProperty lottery) {
		this.winColumn = lottery;
	}
	public StringProperty bonusProperty() {
		return bonusColumn;
	}
	public void setBonus(StringProperty bonus) {
		this.bonusColumn = bonus;
	}

	public StringProperty joProperty() {
		return joColumn;
	}

	public void setJoColumn(StringProperty joColumn) {
		this.joColumn = joColumn;
	}
}
