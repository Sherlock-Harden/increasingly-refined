package org.practice.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import org.practice.map.hashmap.achieve.arraylist.YuansjHashMap;


/**
 * 应用模块名称：
 *
 * 代码描述：
 *
 * Copyright: Copyright (C) 2020 XXX, Inc. All rights reserved.
 *
 * Company: neusoft
 *
 * @author yuansj[yuansj@neusoft.com]
 * @since 2020/9/25 15:49
 **/
public class YuansjTest {

  public static void main(String[] args) {

    // HashMap put的时候 没有加 synchronized 线程不安全 效率相对 hashtable 高
    HashMap<String, String> hashMap = new HashMap<>(8);
    hashMap.put("a", "a");

    //Hashtable put的时候 有加 synchronized 线程安全 效率相对 HashMap 低
    Hashtable<String, String> hashtable = new Hashtable<>(8);
    hashtable.put("a", "a");

    //通过 ArrayList 手写一个简易的HashMap
    YuansjMap<Object, String> yuansjMap = new YuansjHashMap<>();

    yuansjMap.put("伟哥月薪", "15k");

    System.out.println(yuansjMap.get("伟哥月薪"));

    //hashcode 和 equals 区别
    //两个对象的hashcode相同，对象不一定相同
    //两个对象equals相同，对象一定相同
    String a = "a";
    Integer b = 97;
    System.out.println(a.hashCode());
    System.out.println(b.hashCode());

    //通过数组 形式 手写一个简易的HashMap
    yuansjMap = new org.practice.map.hashmap.achieve.linkedlist.YuansjHashMap<>();

    yuansjMap.put("a", "伟哥年薪240k");
    yuansjMap.put(97, "伟哥年薪241k");

    System.out.println(yuansjMap.get("a"));
    System.out.println(yuansjMap.get(97));

    //String html = """ <html> <body> <p>Hello, world</p> </body> </html> """;

    /**
     * 看源码步骤
     * 1、构造方法
     * 2、看具体方法
     *
     * JDK7 HashMap 底层如何实现 数组+链表
     * JDK8 HashMap 底层如何实现 数组+链表+红黑树
     *
     * 为什么使用数组+链表结构
     *
     * JDK7 使用 Entry对象封装键值对 包含 key、value、next
     * 链表分为几种 单向、双向
     * HashMap使用单向链表
     *
     * JDK8 使用 Node对象
     *
     */

    yuansjMap = new org.practice.map.hashmap.YuansjHashMap<>();
    yuansjMap.put("a", "测试A");
    yuansjMap.put(97, "测试B");

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
    System.out.println(sum.get());

  }

}
