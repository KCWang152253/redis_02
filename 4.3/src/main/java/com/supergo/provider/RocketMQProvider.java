package com.supergo.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByRandom;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * Created by on 2019/9/5.
 * 需求：
 * 测试消息发送结构
 *
 *
 */
public class RocketMQProvider {


    public static void main(String[] args) throws Exception {
        //System.out.println(System.getProperty("user.home"));
        //System.setProperty("rocketmq.client.log.loadconfig", "false");


        //创建一个消息的生产者，且指定一个组
        DefaultMQProducer producer = new DefaultMQProducer("group-D");
        //设置namesrv地址
        producer.setNamesrvAddr("192.168.66.66:9876");

        //设置
        //producer.setDefaultTopicQueueNums(5);

        //开启
        producer.start();

        //创建多条消息
        for (int i = 0; i < 10; i++) {
            //创建消息对象
            Message message = new Message("topic-A",
                    "tagA", ("helloA" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 衣服
            // 鞋子 TAG

            //设置消息延时级别
            message.setDelayTimeLevel(6);

            //创建消息队列
            //MessageQueue queue = new MessageQueue("jackhu-01","jackhu",0);

            producer.send(message,new SelectMessageQueueByHash(),"");

            //发送消息
            SendResult result = producer.send(message);

            //打印
            System.out.println("发送消息返回结果:" + result);
        }
        //关闭
        producer.shutdown();
    }

}