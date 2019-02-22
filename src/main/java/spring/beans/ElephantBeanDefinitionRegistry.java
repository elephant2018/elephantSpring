package spring.beans;

/**
 * bean定义注册接口
 * @author Elephant
 *
 */
public interface ElephantBeanDefinitionRegistry {
	
	void registerBeanDefinition (String beanName, ElephantBeanDefinition ElephantBeanDefinition) throws Exception;
	
	ElephantBeanDefinition getBeanDefinition(String beanName);
	
	boolean containsBeanDefinition(String beanName);

}
