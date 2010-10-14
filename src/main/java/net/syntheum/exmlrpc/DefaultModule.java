package net.syntheum.exmlrpc;

import net.syntheum.exmlrpc.services.CalculatorEngine;
import net.syntheum.exmlrpc.services.FloatingPointEngine;
import net.syntheum.exmlrpc.services.OperationLog;
import net.syntheum.exmlrpc.services.StdoutLog;

import com.google.inject.AbstractModule;


/**
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 *
 */
public class DefaultModule extends AbstractModule {

	@Override
	protected void configure() 
	{
		bind(OperationLog.class).to(StdoutLog.class);
		bind(CalculatorEngine.class).to(FloatingPointEngine.class);
		
		
	}

}
