package net.syntheum.exmlrpc.services;

/**
 * General calculation engine
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public interface CalculatorEngine {

	/**
	 * Adds two numbers
	 * 
	 * @param a first summand
	 * @param b second summand
	 */
	public Number add(Number a, Number b);
	
	/**
	 * Subtracts number b from a
	 * 
	 * @param a minuend
	 * @param b subtrahend
	 */
	public Number sub(Number a, Number b);
	
	/**
	 * Creates number a times number b
	 * 
	 * @param a first multiplicand
	 * @param b second multiplicand 
	 */
	public Number tim(Number a, Number b);
	
	/**
	 * Creates number a divided by number b
	 * 
	 * @param a dividend
	 * @param b divisor
	 */
	public Number div(Number a, Number b); 
	
}
