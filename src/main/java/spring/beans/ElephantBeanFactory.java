package spring.beans;


/**
 * bean工厂
 * @author Elephant
 *
 */
public interface ElephantBeanFactory {
	
	Object getBean(String name) throws Exception;

}
