package net.syntheum.exmlrpc.services;

/**
 * Calculator engine supporting floating point arithmetics
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public class FloatingPointEngine implements CalculatorEngine {

	/**
	 * 
	 */
	@Override
	public Float add(Number a, Number b) {
		return a.floatValue() + b.floatValue();
	}
	
	/**
	 * 
	 */
	@Override
	public Float div(Number a, Number b) {
		return a.floatValue() / b.floatValue();
	}

	/**
	 * 
	 */
	@Override
	public Float sub(Number a, Number b) {
		return a.floatValue() / b.floatValue();
	}

	/**
	 * 
	 */
	@Override
	public Float tim(Number a, Number b) {
		return a.floatValue() * b.floatValue();
	}

	
	
}
