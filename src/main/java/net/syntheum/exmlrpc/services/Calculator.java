package net.syntheum.exmlrpc.services;

import net.syntheum.exmlrpc.XmlRpcMethod;

import com.google.inject.Inject;


/**
 * XML-RPC service class offering simple numeric operations +,-,*,/
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public class Calculator {

	/**
	 * Logs called operations
	 */
	private final OperationLog log;
	
	/**
	 * Realizes calculations
	 */
	private final CalculatorEngine engine; 
	
	
	/**
	 * @param engine plugged calculator engine (integer based, floating point,...)
	 * @param log plugged logging service 
	 */
	@Inject
	public Calculator(CalculatorEngine engine, OperationLog log)
	{
		this.engine = engine;
		this.log = log;
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a + b
	 */
	@XmlRpcMethod
	public String add(String a, String b) {
		Number ans = this.engine.add(Float.valueOf(a), Float.valueOf(b));
		this.log.log(String.format("%s + %s = %s", a, b, ans));
		return ans.toString();
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a - b
	 */
	@XmlRpcMethod
	public String sub(String a, String b) {
		Number ans = this.engine.sub(Float.valueOf(a), Float.valueOf(b));
		this.log.log(String.format("%s - %s = %s", a, b, ans));
		return ans.toString();
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a * b
	 */
	@XmlRpcMethod
	public String tim(String a, String b) {
		Number ans = this.engine.tim(Float.valueOf(a), Float.valueOf(b));
		this.log.log(String.format("%s * %s = %s", a, b, ans));
		return ans.toString();
	}
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a / b
	 */
	@XmlRpcMethod
	public String div(String a, String b) {
		Number ans = this.engine.div(Float.valueOf(a), Float.valueOf(b));
		this.log.log(String.format("%s / %s = %s", a, b, ans));
		return ans.toString();
	}
}
