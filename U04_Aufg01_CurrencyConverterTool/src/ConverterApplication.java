import javax.swing.SwingUtilities;

public class ConverterApplication {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ExchangeRates exchangeRates = new ExchangeRates();
				ConverterLogic converterLogic = new ConverterLogic(exchangeRates);
				ConverterFrame frame = new ConverterFrame(converterLogic);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
