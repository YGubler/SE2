package Forex;

public class CurrencyConverter {
	private ExchangeRates exchangeRates;
	
	public CurrencyConverter(ExchangeRates exchangeRates) {
		this.exchangeRates = exchangeRates;
	}
	
	public double convert(double amount, Currency from, Currency to) {
		return amount * exchangeRates.getRateToUSD(from) / exchangeRates.getRateToUSD(to);
	}
}
