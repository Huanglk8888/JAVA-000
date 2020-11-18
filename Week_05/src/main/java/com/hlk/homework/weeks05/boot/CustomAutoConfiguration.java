package com.hlk.homework.weeks05.boot;

import com.hlk.homework.weeks05.spring.bean.Student;
import com.sun.xml.internal.bind.v2.schemagen.episode.Klass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Klass.class,School.class, Student.class})
public class CustomAutoConfiguration {
}
