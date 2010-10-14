package net.syntheum.exmlrpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;


/**
 * Code is inspired by Atlassian's XML-RPC extension (covered by the Apache license).
 *
 * @author Tomas Machalek <tomas.machalek@gmail.com>
 */
public class DefaultRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory
{

    /**
     * 
     */
    private final Map<Class<?>, InjectedRequestProcessorFactory> services;

    
    /**
     *
     * @param serviceList
     */
    public DefaultRequestProcessorFactoryFactory(List<?> serviceList)
    {
        services = new HashMap<Class<?>, InjectedRequestProcessorFactory>();

        for (Object service : serviceList) {
            this.services.put(service.getClass(), new InjectedRequestProcessorFactory(service.getClass(), service));
        }
    }


    /**
     *
     */
    @SuppressWarnings("unchecked")
	@Override
    public RequestProcessorFactory getRequestProcessorFactory(Class pClass)
        throws XmlRpcException
    {
    	InjectedRequestProcessorFactory processorFactory = services.get(pClass);
        if (processorFactory == null)
        {
            throw new XmlRpcException("Could not find service object instance for type " + pClass.getName());
        }
        return processorFactory;
    }


    /**
     *
     */
    public class InjectedRequestProcessorFactory implements RequestProcessorFactory {

        /**
         * Service class
         */
        @SuppressWarnings("unused")
		private final Class<?> pType;

        /**
         * Service object
         */
        private final Object serviceObject;

        /**
         *
         * @param pType
         * @param serviceObject
         */
        public InjectedRequestProcessorFactory(Class<?> pType, Object serviceObject)
        {
            this.pType = pType;
            this.serviceObject = serviceObject;
        }

        /**
         *
         * @param request
         * @return service object
         */
        @Override
        public Object getRequestProcessor(XmlRpcRequest request)
            throws XmlRpcException
        {
            return serviceObject;
        }

    }

}