package net.syntheum.exmlrpc.services;

import net.syntheum.exmlrpc.XmlRpcMethod;


public class Stack {

	/**
	 * 
	 */
	private java.util.Stack<String> data;
	
	/**
	 * 
	 */
	public Stack()
	{
		this.data = new java.util.Stack<String>();
	}
	
	/**
	 * java.util.Stack is synchronized itself 
	 */
	@XmlRpcMethod
	public String push(String item)
	{
		return this.data.push(item);
	}
	
	/**
	 * java.util.Stack is synchronized itself 
	 */
	@XmlRpcMethod
	public String pop()
	{
		return this.data.pop();
	}
}
