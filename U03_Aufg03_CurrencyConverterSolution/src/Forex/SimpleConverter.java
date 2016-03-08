package Forex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SimpleConverter {
	public static void main(String[] args) throws IOException {
		CurrencyConverter converter = new CurrencyConverter(new ExchangeRateStore());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintStream writer = System.out;
		while (true) {
			writer.println("Enter amount: ");
			String line = reader.readLine();
			double amount = Double.parseDouble(line);
			writer.println("Enter original currency: ");
			line = reader.readLine();
			Currency from = Currency.valueOf(line);
			writer.println("Enter target currency: ");
			line = reader.readLine();
			Currency to = Currency.valueOf(line);
			double result = converter.convert(amount, from, to);
			writer.println("Result: " + result + " " + to);
		}
	}
}
