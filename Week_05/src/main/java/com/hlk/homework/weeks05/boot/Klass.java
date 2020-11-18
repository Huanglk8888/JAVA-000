package com.hlk.homework.weeks05.boot;

import com.hlk.homework.weeks05.spring.bean.Student;
import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Data
@Configuration
@AutoConfigureBefore(School.class)
public class Klass {
    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }
}
