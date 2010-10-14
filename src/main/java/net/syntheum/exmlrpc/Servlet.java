package net.syntheum.exmlrpc;

import java.util.LinkedList;
import java.util.List;

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
    private final Injector injector;
    
    /**
     * 
     */
    private List<Class<?>> serviceClasses;
    
    /**
     * 
     */
    public Servlet(Injector injector)
    {
    	this.injector = injector;
    	this.serviceClasses = new LinkedList<Class<?>>();
    }
    
    
    /**
     * 
     */
    public void registerClass(Class<?> clazz)
    {
    	this.serviceClasses.add(clazz);
    }
    
    
    /**
     * 
     */
    public Class<?>[] getRegisteredClasses()
    {
    	return this.serviceClasses.toArray(new Class<?>[this.serviceClasses.size()]);
    }


    /**
     * 
     */
    @Override
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
            throws XmlRpcException
    {
        CustomRpcMapping mapping = new CustomRpcMapping(this.injector);

        mapping.setTypeConverterFactory(getXmlRpcServletServer().getTypeConverterFactory());
        mapping.initServiceClasses(getRegisteredClasses());

        XmlRpcSystemImpl.addSystemHandler(mapping);
        return mapping;
    }

}