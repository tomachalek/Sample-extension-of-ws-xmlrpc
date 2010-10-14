package net.syntheum.exmlrpc;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.metadata.XmlRpcSystemImpl;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import com.google.inject.Injector;

/**
 * XML-RPC serlvet extension
 * 
 * @author Tomas Machalek <tomas.machalek@gmail.com> Machalek <tomas.machalek@gmail.com>
 */
public class Servlet extends XmlRpcServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8985212257427759845L;

    /**
     * 
     */
    Injector injector;
    
    /**
     * 
     */
    public Servlet(Injector injector)
    {
    	this.injector = injector;
    }


    /**
     * 
     */
    @Override
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
            throws XmlRpcException
    {
        CustomRpcMapping mapping = new CustomRpcMapping(this.injector);

        Class<?>[] serviceClasses = {
        		net.syntheum.exmlrpc.services.Calculator.class,
        		net.syntheum.exmlrpc.services.Stack.class
        };

        mapping.setTypeConverterFactory(getXmlRpcServletServer().getTypeConverterFactory());
        mapping.initServiceClasses(serviceClasses);

        XmlRpcSystemImpl.addSystemHandler(mapping);
        return mapping;
    }

}