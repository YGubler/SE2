package Forex;

public class CurrencyConverter {
	private ExchangeRates exchangeRates = new ExchangeRates();
	
	public double convert(double amount, Currency from, Currency to) {
		return amount * exchangeRates.getRateToUSD(from) / exchangeRates.getRateToUSD(to);
	}
}
