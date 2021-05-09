package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LotteryNumberModel {
	private ObservableList<Lottery> lotteryTableList = FXCollections.observableArrayList();
	private List<List<String>> ret = new ArrayList<List<String>>();
	private ObjectProperty<Lottery> currentLottery = new SimpleObjectProperty<>();
	
	private int listCount = 0;

	public LotteryNumberModel() {
		loadLottoInfo();
	}

	public List<List<String>> getLotteryCsvList(){
		return ret;
	}
	
	public int getListCount() {
		return listCount;
	}
	
	public ObjectProperty<Lottery> currentLotteryProperty() {
		return currentLottery;
	}

	public Lottery getCurrentLottery() {
		return currentLottery.get();
	}

	public void setCurrentLottery(Lottery lottery) {
		currentLottery.set(lottery);
	}

	public ObservableList<Lottery> getLotteryTableList() {
		return lotteryTableList;
	}

	public void setLotteryTableList(List<List<String>> lottoList) {
		for (List<String> lotto : lottoList) {
			Lottery tmp = new Lottery();
			tmp.setAllInfo(lotto.get(0),
					lotto.get(1),
					lotto.get(2),
					lotto.get(3),
					lotto.get(4));

			lotteryTableList.add(tmp);
		}
	}

	public void loadLottoInfo() {
		

		BufferedReader br = null;

		try {
			br = Files
					.newBufferedReader(Paths.get("C:\\Users\\Park\\eclipse-workspace\\Main\\src\\LottoWinNumber.csv"));
			// Charset.forName("UTF-8");
			String line = "";

			List<String> tmpList = new ArrayList<String>();

			line = br.readLine();

			while ((line = br.readLine()) != null) {
				// CSV 1행을 저장하는 리스트

				tmpList = new ArrayList<String>();
				String array[] = line.split(",");

				// 배열에서 리스트 반환
				tmpList = Arrays.asList(array);
				System.out.println(tmpList);
				ret.add(tmpList);
				
				listCount++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		setLotteryTableList(ret);
	}

	public void saveLottoInfo(Lottery lotto) {
		String filePath = "C:/Users/Park/eclipse-workspace/Main/src/LottoWinNumber.csv";

		File file = null;
		BufferedWriter bw = null;
		String num = lotto.noProperty().getValue();
		String date = lotto.dateProperty().getValue();
		String jo = lotto.joProperty().getValue();
		String lottery = lotto.lotteryProperty().getValue();
		String bonus = lotto.bonusProperty().getValue();
		
		try {
			file = new File(filePath);
			bw = new BufferedWriter(new FileWriter(file, true));

			bw.append(num);
			bw.append(",");

			bw.append(date);
			bw.append(",");

			bw.append(jo);
			bw.append(",");

			bw.append(lottery);
			bw.append(",");

			bw.append(bonus);
			bw.append(",");

			for (int i = 0; i < lottery.length(); i++) {
				bw.append(lottery.charAt(i));
				bw.append(i < lottery.length() - 1 ? "," : "");
			}

			bw.append("\n");

			bw.flush();
			bw.close();
			
			System.out.println("종료");
			
			listCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
