package spring.beans;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的bean工厂实现类，需实现bean定义注册
 * @author Elephant
 *
 */
public class DefaultBeanFactory implements ElephantBeanFactory, ElephantBeanDefinitionRegistry {

	private Map<String, Object> beanMap = new ConcurrentHashMap<>();
	private Map<String, ElephantBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	
	/* 
	 * 注册bean定义，需要给定唯一bean的名称和bean的定义,放到bean定义集合中
	 */
	@Override
	public void registerBeanDefinition(String beanName, ElephantBeanDefinition ElephantBeanDefinition) throws Exception {
		Objects.requireNonNull(beanName, "beanName不能为空");
		Objects.requireNonNull(ElephantBeanDefinition, "beanDefinition不能为空");
		if (beanDefinitionMap.containsKey(beanName)){
			throw new Exception("已存在【"+beanName+ "】的bean定义"+getBeanDefinition(beanName));
		}
		beanDefinitionMap.put(beanName, ElephantBeanDefinition);

	}

	/* 
	 * 获得bean定义
	 */
	@Override
	public ElephantBeanDefinition getBeanDefinition(String beanName) {
		return beanDefinitionMap.get(beanName);
	}

	/* 
	 * 是否存在bean定义
	 */
	@Override
	public boolean containsBeanDefinition(String beanName) {
		return beanDefinitionMap.containsKey(beanName);
	}

	/* 
	 * 获得bean的门面方法
	 */
	@Override
	public Object getBean(String name) throws Exception {
		return doGetBean(name);
	}
	
	/**
	 * getBean的具体逻辑
	 * 
	 * 事实上，在spring的bean定义中，还可以静态工厂方法和成员工厂方法来创建实例，但这2种用的较少，所以此处只使用构造器来创建bean
	 */
	private Object doGetBean(String beanName) throws Exception{
		Objects.requireNonNull(beanName, "beanName不能为空");
		Object instance = beanMap.get(beanName);
		//如果bean已存在，则直接返回
		if(instance != null){
			return instance;
		}
		ElephantBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		Objects.requireNonNull(beanDefinition, "beanDefinition不能为空");
		Class<?> class1 = beanDefinition.getBeanClass();
		Objects.requireNonNull(class1, "bean定义中class类型不能为空");
		instance = class1.newInstance();
		
		//实例已创建好，通过反射执行bean的init方法
		String initMethodName = beanDefinition.getInitMethodName();
		if(null!=initMethodName){
			Method method = class1.getMethod(initMethodName, null);
			method.invoke(instance, null);
		}
		
		//将单例bean放到map中，下次可直接拿到
		if(beanDefinition.isSingleton()){
			beanMap.put(beanName, instance);
		}
		return instance;
	}

}
