package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LottoCount {
	private StringProperty no0;
	private StringProperty no1;
	private StringProperty no2;
	private StringProperty no3;
	private StringProperty no4;
	private StringProperty no5;
	private StringProperty no6;
	
	
	public LottoCount(String n0, String n1, String n2, String n3, String n4, String n5, String n6) {
		no1 = new SimpleStringProperty(n1+"");
		no2 = new SimpleStringProperty(n2);
		no3 = new SimpleStringProperty(n3);
		no4 = new SimpleStringProperty(n4);
		no5 = new SimpleStringProperty(n5);
		no6 = new SimpleStringProperty(n6);
		no0 = new SimpleStringProperty(n0);
	}

	public LottoCount(int []n, String num) {
		no1 = new SimpleStringProperty(n[0]+"");
		no2 = new SimpleStringProperty(n[1]+"");
		no3 = new SimpleStringProperty(n[2]+"");
		no4 = new SimpleStringProperty(n[3]+"");
		no5 = new SimpleStringProperty(n[4]+"");
		no6 = new SimpleStringProperty(n[5]+"");
		no0 = new SimpleStringProperty(num);
	}

	public StringProperty getNoProperty(int a) {
		switch(a) {
			case 1:
				return no1;
			case 2:
				return no2;
			case 3:
				return no3;
			case 4:
				return no4;
			case 5:
				return no5;
			case 6:
				return no6;
			default:
				return no1;
		}
		
	}
	
	public StringProperty getNo0() {
		return no0;
	}


	public void setNo0(StringProperty no0) {
		this.no0 = no0;
	}


	public StringProperty getNo1() {
		return no1;
	}


	public void setNo1(StringProperty no1) {
		this.no1 = no1;
	}


	public StringProperty getNo2() {
		return no2;
	}


	public void setNo2(StringProperty no2) {
		this.no2 = no2;
	}


	public StringProperty getNo3() {
		return no3;
	}


	public void setNo3(StringProperty no3) {
		this.no3 = no3;
	}


	public StringProperty getNo4() {
		return no4;
	}


	public void setNo4(StringProperty no4) {
		this.no4 = no4;
	}


	public StringProperty getNo5() {
		return no5;
	}


	public void setNo5(StringProperty no5) {
		this.no5 = no5;
	}


	public StringProperty getNo6() {
		return no6;
	}


	public void setNo6(StringProperty no6) {
		this.no6 = no6;
	}
	

}
