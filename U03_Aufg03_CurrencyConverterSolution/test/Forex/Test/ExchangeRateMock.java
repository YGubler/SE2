package Forex.Test;

import java.util.HashMap;

import Forex.Currency;
import Forex.ExchangeRates;

public class ExchangeRateMock implements ExchangeRates {
	private HashMap<Currency, Double> rates = new HashMap<Currency, Double>();
	
	public void register(Currency currency, double rate) {
		rates.put(currency, rate);
	}

	@Override
  public double getRateToUSD(Currency currency) {
		if (currency == Currency.USD) {
			return 1.0;
		} else {
			return rates.get(currency);
		}
  }
}
