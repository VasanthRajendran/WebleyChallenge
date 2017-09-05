import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PriceCombination {

	public ArrayList<ArrayList<Double>> combinationSum(ArrayList<Double> listArray, double target) {
		ArrayList<Double> current = new ArrayList<Double>();
		double[] list = new double[listArray.size()];
		for(int i=0;i<listArray.size();i++)
		{
			list[i] = listArray.get(i);
		}
		Arrays.sort(list);
		ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
		combinationSum(list, target, 0, current, result);
		return result;
	}

	public void combinationSum(double[] prices, double target, int j, ArrayList<Double> current, ArrayList<ArrayList<Double>> result) {
		if (target == 0) {
			System.out.println("The combination = ");
			for (int i = 0; i < current.size(); i++) {
				System.out.print( current.get(i) + " ");
				result.add(new ArrayList<Double>(current));
			}
			System.out.println();
			return;
		}
		for (int i = j; i < prices.length; i++) {
			if (target < prices[i]) {
				return;
			}
			if(current.contains(prices[i]))
			{
				continue;
			}
			current.add(prices[i]);
			Double newTarget = target - prices[i];
			double roundOffTarget = Math.round(newTarget * 100.0) / 100.0;
			combinationSum(prices, roundOffTarget,i+1, current, result);
			current.remove(current.size() - 1);
		}
	}

	public static void main(String args[]) {
		PriceCombination c = new PriceCombination();
		File file = new File("Price.csv");
		Double totalPrice;
		ArrayList<Double> list = new ArrayList<Double>();
		try {
			Scanner inputStream = new Scanner(file);
			String targetValue = inputStream.nextLine();
			String[] targetValuePrice = targetValue.split(",");
			String test = targetValuePrice[1].substring(1);
			totalPrice = Double.parseDouble(test);
			while (inputStream.hasNext()) {
				String dish = inputStream.nextLine();
				String[] dishPrice = dish.split(",");
				String Price = dishPrice[1].substring(1);
				list.add(Double.parseDouble(Price));
			}
			System.out.println("Target Price = " + totalPrice);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("The price of the dishes = " + list.get(i));
			}
			ArrayList<ArrayList<Double>> result = c.combinationSum(list, totalPrice);
			if(result.size() == 0)
			{
				System.out.println("There is no combination of dishes that is equal to the target price.");
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
