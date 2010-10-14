package net.syntheum.exmlrpc.services;

public class StdoutLog implements OperationLog {

	/**
	 * 
	 */
	public void log(String msg)
	{
		System.out.println("log: " + msg);
	}
	
}
