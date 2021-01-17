package com.example.springbootkafkademo.service.simple.impl;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author sunkai
 * @title: Producer
 * @projectName project
 * @description: TODO
 * @date 2021/1/317:02
 */
public class Producer extends Thread {

    private final KafkaProducer<Integer,String> prod;
    private final String topic;
    private final Boolean isAsync;

    public static void main(String[] args){
        Producer producer =new Producer("test",true);
        producer.start();
    }

    public Producer( String topic, Boolean isAsync) {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.211.146:9092");
        props.put("client.id","DemoProducer");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("client.id","DemoProducer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        this.prod = new KafkaProducer<Integer, String>(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    @SneakyThrows
    @Override
    public void run() {
        int num =1;
        while(true){
            String msg = "message_"+num;
            if(isAsync && num <10) {
                System.out.println("发送信息:"+msg);
                prod.send(new ProducerRecord<Integer,String>(topic,num,msg),
                new Callback(){

                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.println("ddd");
                    }
                });
            }else {
                //prod.send(new ProducerRecord<Integer, String>(topic,num,msg)).get();
            }
            ++num;
        }
    }
}
