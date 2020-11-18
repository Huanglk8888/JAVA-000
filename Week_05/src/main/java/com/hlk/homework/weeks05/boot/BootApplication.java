package com.hlk.homework.weeks05.boot;

import com.hlk.homework.weeks05.spring.bean.Student;
import com.sun.xml.internal.bind.v2.schemagen.episode.Klass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName ==>" + beanDefinitionName);
        }
        Klass klassBean = context.getBean(Klass.class);
        School schoolBean = context.getBean(School.class);
        Student studentBean = context.getBean(Student.class);
        System.out.println("klassBean ==>" + klassBean);
        System.out.println("schoolBean ==>" + schoolBean);
        System.out.println("studentBean ==>" + studentBean.getName());
    }
}