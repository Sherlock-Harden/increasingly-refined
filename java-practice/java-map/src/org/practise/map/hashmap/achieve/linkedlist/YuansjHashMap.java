package org.practise.map.hashmap.achieve.linkedlist;

import java.util.LinkedList;
import org.practise.map.YuansjMap;

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
 * @since 2020/9/26 14:27
 **/
public class YuansjHashMap<K, V> implements YuansjMap<K, V> {

  private final LinkedList[] objects = new LinkedList[100];


  @Override
  public int size() {
    return 0;
  }

  @Override
  public V put(K k, V v) {
    int index = index(k);
    LinkedList<YuansjNode<K, V>> linkedList = objects[index];
    if (linkedList == null) {
      linkedList = new LinkedList<>();
    }
    for (YuansjNode<K, V> node : linkedList) {
      if (k.equals(node.getKey())) {
        node.setValue(v);
        return v;
      }
    }
    YuansjNode<K, V> yuansjNode = new YuansjNode<>(k, v);
    linkedList.add(yuansjNode);
    objects[index] = linkedList;
    return v;
  }

  @Override
  public V get(K k) {
    int index = index(k);
    LinkedList<YuansjNode<K, V>> linkedList = objects[index];
    for (YuansjNode<K, V> node : linkedList) {
      if (k.equals(node.getKey())) {
        return node.getValue();
      }
    }
    return null;
  }

  private int index(K k) {
    return Math.abs(k.hashCode() % objects.length);
  }

  static class YuansjNode<K, V> implements Entry<K, V> {

    /**
     * key
     */
    private K k;

    /**
     * value
     */
    private V v;

    public YuansjNode(K k, V v) {
      this.k = k;
      this.v = v;
    }

    @Override
    public K getKey() {
      return k;
    }

    @Override
    public V getValue() {
      return v;
    }

    @Override
    public V setValue(V v) {
      this.v = v;
      return v;
    }
  }

}
