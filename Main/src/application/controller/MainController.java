package application.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Random;

import application.Main;
import application.model.Lottery;
import application.model.LotteryNumberModel;
import application.model.LottoCount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	private TableView<Lottery> lottryTableView;
	@FXML
	private TableColumn<Lottery, String> noColumn;
	@FXML
	private TableColumn<Lottery, String> dateColumn;
	@FXML
	private TableColumn<Lottery, String> joColumn;
	@FXML
	private TableColumn<Lottery, String> winColumn;
	@FXML
	private TableColumn<Lottery, String> bonusColumn;

	@FXML
	private TableColumn<LottoCount, String> no1Column, no2Column, no3Column, no4Column, no5Column, no6Column, numColumn;

	@FXML
	private Button lottoInsert, loadProbBtn;
	@FXML
	private DatePicker inputDate;
	@FXML
	private TextField inputJo;
	@FXML
	private TextField inputWin;
	@FXML
	private TextField inputBonus;
	@FXML
	private Button generationMaxLotto;
	@FXML
	private Button generationMinLotto;
	@FXML
	private Label generatedMaxLotto;
	@FXML
	private Label generatedMinLotto;
	@FXML
	private ListView<String> probMaxList;
	@FXML
	private ChoiceBox joSelector;
	@FXML
	private ListView<String> probMinList;

	private ObservableList<String> probListItem;
	ObservableList<Lottery> lotteryList = FXCollections.observableArrayList();

	@FXML
	private TableView<LottoCount> countNumberTable;
	private ObservableList<LottoCount> countNumberList = FXCollections.observableArrayList();

	// Number??? ?????? ???????????? ?????????, Integer, Long, Float, Double ??? ????????????, ?????????
	@FXML
	private BarChart<String, Number> probChart; // ???????????? X, Y???
	@FXML
	private CategoryAxis Xaxis; // X???

	// ??????????????? ???????????? ?????? ?????? ??????
	String[] cates = { "0???", "1???", "2???", "3???", "4???", "5???", "6???", "7???", "8???", "9???" };

	// ???????????? ?????? ?????? ??????
	XYChart.Series<String, Number> series = null; // ???????????? ?????? ?????? series ??????
	private ObservableList<String> xLables = FXCollections.observableArrayList(); // ??????????????? ?????? xLables ?????? ??????

	private Stage stage;
	private LotteryNumberModel lottoNumModel;

	WeightedValueCal wvc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lottoNumModel = new LotteryNumberModel();

		lotteryList = lottoNumModel.getLotteryTableList();

		wvc = new WeightedValueCal(lottoNumModel.getLotteryCsvList());

		noColumn.setCellValueFactory(cellData -> cellData.getValue().noProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		joColumn.setCellValueFactory(cellData -> cellData.getValue().joProperty());
		winColumn.setCellValueFactory(cellData -> cellData.getValue().lotteryProperty());
		bonusColumn.setCellValueFactory(cellData -> cellData.getValue().bonusProperty());

		no1Column.setCellValueFactory(cellData -> cellData.getValue().getNo1());
		no2Column.setCellValueFactory(cellData -> cellData.getValue().getNo2());
		no3Column.setCellValueFactory(cellData -> cellData.getValue().getNo3());
		no4Column.setCellValueFactory(cellData -> cellData.getValue().getNo4());
		no5Column.setCellValueFactory(cellData -> cellData.getValue().getNo5());
		no6Column.setCellValueFactory(cellData -> cellData.getValue().getNo6());
		numColumn.setCellValueFactory(cellData -> cellData.getValue().getNo0());

		for (int i = 1; i < 7; i++)
			joSelector.getItems().add(i + "???");

		lottryTableView.setItems(lotteryList);
	}

	public ObservableList<LottoCount> countNumber() {
		int[][] arr = wvc.countWinNumber();

		ObservableList<LottoCount> tmp = FXCollections.observableArrayList();

		for (int j = 0; j < arr[1].length; j++) {
			int[] ar1 = new int[6];
			for (int i = 0; i < arr.length; i++) {
				ar1[i] = arr[i][j];
			}

			tmp.add(new LottoCount(ar1, j + "???"));
		}

		return tmp;
	}

	public void setMainStage(Stage s) {
		stage = s;
	}

	@FXML
	protected void lottoInsertAction(ActionEvent e) {
		LocalDate date = inputDate.getValue();

		Lottery lotto = new Lottery();
		lotto.setAllInfo((lottoNumModel.getListCount() + 1) + "",
				date.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString(), inputJo.getText(), inputWin.getText(),
				inputBonus.getText());

		lottoNumModel.saveLottoInfo(lotto);

		lotteryList.add(lotto);
		lottryTableView.setItems(lotteryList);
	}

	@FXML
	protected void generateLottoMaxNumber(ActionEvent ev) {
		probListItem = FXCollections.observableArrayList();

		// false = ??????, true = ????????????
		wvc.calValueWeight(true);

		List<Pair<Integer, Float>> target = wvc.getNumber_weight();

		String rst = "";
		WeightedRandom random = null;
		for (int i = 0; i < 60; i += 10) {
			random = new WeightedRandom(target.subList(i, i + 10));
			rst += random.getRandom();
			rst += i >= 50 ? " " : ", ";
		}

		generatedMaxLotto.setText(rst);

		System.out.println("\n?????? ?????? 10000??? (6??? ????????? ??????)");

		int count = 10000;
		Map<Integer, Integer> wordCount = new HashMap<>();
		for (int i = 0; i < count; i++) {
			int word = random.getRandom();
			wordCount.put(word, 1 + wordCount.getOrDefault(word, 0));
		}

		DecimalFormat df = new DecimalFormat("#.##");

		for (Entry<Integer, Integer> c : wordCount.entrySet()) {
			System.out.printf("%s ?????? ?????? : %.2f\n", c.getKey(), (double) c.getValue() / (double) count);

			probListItem.add(c.getKey() + " ???????????? : " + df.format((double) c.getValue() / (double) count));
		}

		probMaxList.setItems(probListItem);
	}

	@FXML
	protected void generateLottoMinNumber(ActionEvent ev) {
		probListItem = FXCollections.observableArrayList();

		// false = ??????, true = ????????????
		wvc.calValueWeight(false);

		List<Pair<Integer, Float>> target = wvc.getNumber_weight();

		String rst = "";
		WeightedRandom random = null;
		for (int i = 0; i < 60; i += 10) {
			random = new WeightedRandom(target.subList(i, i + 10));
			rst += random.getRandom();
			rst += i >= 50 ? " " : ", ";
		}

		generatedMinLotto.setText(rst);

		System.out.println("\n?????? ?????? 10000??? (6??? ????????? ??????)");

		int count = 10000;
		Map<Integer, Integer> wordCount = new HashMap<>();
		for (int i = 0; i < count; i++) {
			int word = random.getRandom();
			wordCount.put(word, 1 + wordCount.getOrDefault(word, 0));
		}

		DecimalFormat df = new DecimalFormat("#.##");

		for (Entry<Integer, Integer> c : wordCount.entrySet()) {
			System.out.printf("%s ?????? ?????? : %.2f\n", c.getKey(), (double) c.getValue() / (double) count);

			probListItem.add(c.getKey() + " ???????????? : " + df.format((double) c.getValue() / (double) count));
		}

		probMinList.setItems(probListItem);
	}

	@FXML
	protected void loadProbInfo(ActionEvent ev) {
		countNumberList = countNumber();
		countNumberTable.setItems(countNumberList);

		int choiceBoxIdx = joSelector.getSelectionModel().getSelectedIndex();
		System.out.println(choiceBoxIdx);
		probChart.getData().clear(); // ?????? ?????????, ????????? ?????? ?????? ?????????

		xLables.clear();
		xLables.addAll(Arrays.asList(cates)); // ????????? ??? ??????, add??? ????????? ????????? ???
		Xaxis.setCategories(xLables);

		String name = "1???";
		series = new XYChart.Series<String, Number>();

		for (int j = 0, i=0; j < cates.length; j++, i++) {
			int count = 0;
			String label = "";

			count = Integer.parseInt(countNumberList.get(j).getNoProperty(choiceBoxIdx+1).getValue());
			label = xLables.get(j);
			
			if(choiceBoxIdx < 0)
				name = choiceBoxIdx+2 + "???";
			else
				name = choiceBoxIdx+1 + "???";
			
			XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(label, count);

			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
					if (newNode != null) {
						int color = data.getYValue().intValue() % 2;
						
						switch(color) {
							case 1:
								newNode.setStyle("-fx-bar-fill: salmon;");
								break;
							default:
								newNode.setStyle("-fx-bar-fill: skyblue;");
								break;
						}
						
					}
				}
			});
			
			series.getData().add(data);
		}

		series.setName(name);
		// ????????? ??????
		probChart.getData().add(series);
	}

}
