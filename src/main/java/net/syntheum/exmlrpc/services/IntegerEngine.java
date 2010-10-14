package net.syntheum.exmlrpc.services;

/**
 * Calculator engine based on integer arithmetics 
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public class IntegerEngine implements CalculatorEngine {

	/**
	 * 
	 */
	@Override
	public Integer add(Number a, Number b) {
		return a.intValue() + b.intValue();
	}

	/**
	 * 
	 */
	@Override
	public Integer div(Number a, Number b) {
		return a.intValue() / b.intValue();
	}

	/**
	 * 
	 */
	@Override
	public Integer sub(Number a, Number b) {
		return a.intValue() - b.intValue();
	}

	/**
	 * 
	 */
	@Override
	public Integer tim(Number a, Number b) {
		return a.intValue() * b.intValue();
	}

}
