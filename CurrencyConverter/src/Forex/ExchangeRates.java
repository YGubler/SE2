package Forex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ExchangeRates {
	private final String inputFilePath = "data/rates.txt";
	private HashMap<Currency, Double> rates = new HashMap<Currency, Double>();
	
	public ExchangeRates()  {
		rates.put(Currency.USD, 1.0);
		readRatesFromFile();
	}
	
	public double getRateToUSD(Currency currency) {
		return rates.get(currency);
	}
	
	private void readRatesFromFile() {
		try {
			InputStream stream = new FileInputStream(inputFilePath);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
				String line = reader.readLine();
				while (line != null) {
					StringTokenizer tokenizer = new StringTokenizer(line);
					String label = tokenizer.nextToken();
					Currency currency = Currency.valueOf(label);
					String value = tokenizer.nextToken();
					double amount = Double.parseDouble(value);
					rates.put(currency, amount);
					line = reader.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
