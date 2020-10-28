package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作业2 基于httpClient的demo的测试
 * @author huanglk
 */
@RestController
public class HttpClientDemoTest {

    @GetMapping()
    public String getMethodTest(){
        return "success";
    }
}
