package net.syntheum.exmlrpc;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Sample and simple application demonstrating extension 
 * of Apache's XML-RPC libraries in a way allowing non-trivial
 * constructor based and persistent service classes
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public class App {

	/**
	 * 
	 */
	private Server server;
	
	/**
	 * 
	 */
	private Context servletContext;  
	
	/**
	 * 
	 */
	private Injector injector;
	
	
	/**
	 * 
	 */
	public App(Injector injector) {
		this.injector = injector;
	}
	
	
	/**
     * Initialization and start of the server
     * 
     */
    public void start()
            throws Exception
    {
        this.server = new Server(8080);
        this.servletContext = new Context(server, "/", Context.SESSIONS);
        
        Servlet servlet = new Servlet(injector);
        servlet.registerClass(net.syntheum.exmlrpc.services.Calculator.class);
		servlet.registerClass(net.syntheum.exmlrpc.services.Stack.class);
        
        this.servletContext.addServlet(new ServletHolder(servlet), "/*");
        this.server.start();
    }
    
    
    /**
     * @throws Exception 
     * 
     */
    public void stop() throws Exception
    {
    	this.server.stop();
    }
	
	
	
	public static void main(String[] argv) throws Exception {
		
		Injector injector = Guice.createInjector(new DefaultModule());
		App application = new App(injector);
		application.start();
	}
	
}
