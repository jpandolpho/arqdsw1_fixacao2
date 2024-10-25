package br.edu.ifsp.dsw1.conversor;

public class Converter {
	private double temperatura;
	private ConversionStrategy strategy;
	
	public Converter() {
		temperatura = 0;
		strategy = new ToFahrenheit();
	}
	
	public Converter(double temperatura, ConversionStrategy strategy) {
		this();
		this.temperatura=temperatura;
		if(strategy!=null) {
			this.strategy=strategy;
		}
	}
	
	public double converter() {
		return strategy.converter(temperatura);
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public ConversionStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ConversionStrategy strategy) {
		if(strategy!=null) {
			this.strategy = strategy;
		}
	}
}
