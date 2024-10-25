package br.edu.ifsp.dsw1.conversor;

public class ToCelsius implements ConversionStrategy {

	@Override
	public double converter(double temperature) {
		return (temperature-32)*5/9;
	}

}
