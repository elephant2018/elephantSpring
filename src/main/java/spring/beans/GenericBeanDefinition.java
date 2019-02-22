package spring.beans;

import java.util.Objects;

/**
 * 通用bean定义实现
 * @author Elephant
 *
 */
public class GenericBeanDefinition implements ElephantBeanDefinition {
	
	private Class<?> beanClass;
	
	private String scope = ElephantBeanDefinition.SINGLETION;
	
	private String initMethodName;

	
	

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}


	@Override
	public Class<?> getBeanClass() {
		return beanClass;
	}

	@Override
	public String getScope() {
		return scope;
	}

	@Override
	public boolean isSingleton() {
		return Objects.equals(scope, ElephantBeanDefinition.SINGLETION);
	}

	@Override
	public boolean isPrototype() {
		return Objects.equals(scope, ElephantBeanDefinition.PROTOTYPE);
	}

	@Override
	public String getInitMethodName() {
		return initMethodName;
	}


}
