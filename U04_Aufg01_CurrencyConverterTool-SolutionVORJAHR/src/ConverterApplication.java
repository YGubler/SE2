import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConverterApplication {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread,
					final Throwable throwable) {
				System.err.println("Error in thread " + thread + ": "
						+ throwable.getMessage());
				throwable.printStackTrace();
				// Thread safe handling for exceptions that are not provoked by UI thread
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(null,
								"An error occurred: " + throwable.getMessage());
					}
				});
			}
		});

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
