package com.example.springbootkafkademo.controller;

import com.example.springbootkafkademo.service.springboot.impl.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunkai
 * @title: KafkaController
 * @projectName project
 * @description: TODO
 * @date 2021/1/1116:55
 */

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @RequestMapping("/get")
    public void get(){
        producer.send("hello jim");
    }

}
