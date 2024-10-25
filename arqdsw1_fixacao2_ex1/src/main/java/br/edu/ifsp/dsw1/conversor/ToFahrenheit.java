package br.edu.ifsp.dsw1.conversor;

public class ToFahrenheit implements ConversionStrategy {

	@Override
	public double converter(double temperature) {
		return temperature*1.8+32;
	}

}
