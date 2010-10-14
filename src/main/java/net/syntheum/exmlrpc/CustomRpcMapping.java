package net.syntheum.exmlrpc;

import com.google.inject.Injector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;

/**
 * 
 * Code is inspired by Atlassian's XML-RPC extension (covered by the Apache license).
 *
 * @author Tomas Machalek <tomas.machalek@gmail.com> Machalek<tomas.machalek@gmail.com>
 */
public class CustomRpcMapping extends PropertyHandlerMapping
{

    /**
     * Injector creates instances of service objects
     */
    private Injector injector;


    /**
     *
     */
    public CustomRpcMapping(Injector injector)
    {
        this.injector = injector;
    }


    /**
     * Extension of Apaches original method ensures that only
     * annotated methods are taken into an account as a service ones.
     */
	@Override
    @SuppressWarnings("unchecked")
    protected void registerPublicMethods(String pKey,
            Class pType) throws XmlRpcException
    {
		super.registerPublicMethods(pKey, pType);

        for (final Method method : getMethods(pType)) {
            String name = pKey + "." + method.getName();
            final Object value = handlerMap.get(name);
            if (value != null) {
                handlerMap.remove(name);
                name = pKey + "." + method.getName();
                handlerMap.put(name, value);
            }
        }
    }


    /**
     * This method finishes mapper's configuration. It creates instance of
     * RequestProcessorFactoryFactory and pushes service instances to it.
     */
    public void initServiceClasses(Class<?>[] serviceClasses) throws XmlRpcException
    {
        LinkedList<Object> serviceObjects = new LinkedList<Object>();
        for (Class<?> clazz : serviceClasses) {
            serviceObjects.add(this.injector.getInstance(clazz));
        }
        setRequestProcessorFactoryFactory(
        		new DefaultRequestProcessorFactoryFactory(serviceObjects));

        for (Class<?> clazz : serviceClasses) {
            addHandler(clazz.getSimpleName(), clazz);
        }
    }
    

    /**
     * Returns list of valid (i.e. non-static, public, annotated with XmlRpcMethod)
     * methods of provided class.
     */
    private Method[] getMethods(Class<?> clazz) throws XmlRpcException
    {
        Annotation annotation;
        ArrayList<Method> rpcMethods = new ArrayList<Method>();

        for (Method method : clazz.getDeclaredMethods()) {
            annotation = (XmlRpcMethod) method.getAnnotation(XmlRpcMethod.class);
            if (annotation != null) {
                int mod = method.getModifiers();
                if (!Modifier.isPublic(mod) || Modifier.isStatic(mod)) {
                    throw new XmlRpcException(
                            String.format("Cannot expose method %s - invalid modifiers", method.getName()));
                }
                rpcMethods.add(method);
            }
        }
        return rpcMethods.toArray(new Method[rpcMethods.size()]);
    }


}