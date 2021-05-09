package application.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeightedValueCal {
	
	private List<Pair<Integer, Float>> number_weight = new ArrayList<>();
	private List<List<String>> lotteryNumberList = new ArrayList<List<String>>();
	
	public WeightedValueCal(List<List<String>> lottoList) {
		 lotteryNumberList = lottoList;
	}
	
	public int[] countJoNumber(int jo) {
		int[] count = new int[10];
		int tmp = 0;
		
		jo += 5;
		
		for(List<String> lotteryInfo : lotteryNumberList) {
			tmp = Integer.parseInt(lotteryInfo.get(jo));
			count[tmp]++;
		}
		
		return count;
		
	}
	
	// 조별 숫자 카운팅 배열 리턴
	public int[][] countWinNumber(){
		int countNumber[][] = new int[6][10];
		
		for(int row=0; row<6; row++) {
			countNumber[row] = countJoNumber(row);
		}
		
		return countNumber;
	}
	
	// 최대 빈도
	public void calValueWeight(boolean  max) {
	
		DecimalFormat f = new DecimalFormat("#.#");
		number_weight.clear();
		
	    float weight;
	    int a = 1;
	    
		int [][]countNumber = countWinNumber();
		
	    for(int i=0; i<6; i++){
	    	weight = Arrays.stream(countNumber[i])
	                .max()
	                .getAsInt();
	    	
	    	float final_weight = 0;
	        for(int j = 0; j<10; j++) {
	        	if(max)
	        		final_weight = countNumber[i][j]/weight;
	        	else
	        		final_weight = weight/countNumber[i][j];
	        	
	        	final_weight = Float.parseFloat(f.format(final_weight));
	        	
	        	//System.out.println(countNumber[i][j] + " " + final_weight);
	        	
	        	number_weight.add(new Pair<>(j, final_weight));
	        }
	        
	    }
	    
	   // printWeightedValue(number_weight);
	}

	public List<Pair<Integer, Float>> getNumber_weight() {
		return number_weight;
	}
	
	public void printWeightedValue(List<Pair<Integer, Float>> nw) {
		for(Pair<Integer, Float> pair : nw) {
			System.out.println("Num : "+pair.word+", Weight : "+pair.weight);
		}
	}
	
}
