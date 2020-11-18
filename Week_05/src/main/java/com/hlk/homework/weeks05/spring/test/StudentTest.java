package com.hlk.homework.weeks05.spring.test;

import com.hlk.homework.weeks05.spring.bean.Student;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StudentTest {
    /**
     *  注解式装配
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.hlk.homework.weeks05.spring.bean");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
    }

    /**
     *  xml式装配
     */
    @Test
    public void test02() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
    }

    /**
     *  全路径装配
     */
    @Test
    public void test03() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("\\src\\main\\resources\\spring.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
    }

    /**
     *  springboot装配
     */
    @Test
    public void test04() {
        //这个springboot在启动的时候就会用到这个上下文来，启动spring容器，new Tomcat 嵌入式tomcat
//        EmbeddedWebApplicationContext context = new EmbeddedWebApplicationContext();
//        Student student = (Student) context.getBean("student");
//        System.out.println(student.getName());
    }
}
