package Forex.Test;

import org.junit.Assert;
import org.junit.Test;

import Forex.Currency;
import Forex.CurrencyConverter;

public class ExchangeRatesTest {
	@Test
	public void simpleConversion() {
		double CHFtoUSD = 1.1;
		double EURtoUSD = 1.5;
		double amount = 1000.0;
		double delta = 1e-6;
		ExchangeRateMock mock = new ExchangeRateMock();
		CurrencyConverter converter = new CurrencyConverter(mock);
		mock.register(Currency.CHF, CHFtoUSD);
		mock.register(Currency.EUR, EURtoUSD);
		Assert.assertEquals(amount * CHFtoUSD, converter.convert(amount, Currency.CHF, Currency.USD), delta);
		Assert.assertEquals(amount / CHFtoUSD, converter.convert(amount, Currency.USD, Currency.CHF), delta);
		Assert.assertEquals(amount * CHFtoUSD / EURtoUSD, converter.convert(amount, Currency.CHF, Currency.EUR), delta);
	}
}
