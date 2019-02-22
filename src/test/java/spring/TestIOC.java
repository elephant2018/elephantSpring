/**
 * 
 */
package spring;

import org.junit.AfterClass;
import org.junit.Test;

import spring.beans.DefaultBeanFactory;
import spring.beans.ElephantBeanDefinition;
import spring.beans.GenericBeanDefinition;

/**
 * @author Elephant
 *
 */
public class TestIOC {
    static DefaultBeanFactory factory = new DefaultBeanFactory();
    
    @Test
    public void testRegist() throws Exception {
        GenericBeanDefinition bd = new GenericBeanDefinition(); 
        bd.setBeanClass(TeacherBean.class);
//        bd.setScope(ElephantBeanDefinition.PROTOTYPE);
        bd.setInitMethodName("init");
        factory.registerBeanDefinition("teacher", bd);
        
    }
    
    @AfterClass
    public static void testGetBean() throws Exception{
        TeacherBean t =(TeacherBean) factory.getBean("teacher");
        TeacherBean t1 =(TeacherBean) factory.getBean("teacher");
        t.teach();
        t1.teach();
        System.out.println(t==t1);
    }

}
