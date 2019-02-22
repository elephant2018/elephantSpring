package spring.beans;

/**
 * bean定义接口
 * @author Elephant
 *
 */
public interface ElephantBeanDefinition {
	
	final static String SINGLETION = "singleton";

	final static String PROTOTYPE = "prototype";
	
	Class<?> getBeanClass();
	
	String getScope();
	
	boolean isSingleton();
	
	boolean isPrototype();
	
	String getInitMethodName();
	
}
