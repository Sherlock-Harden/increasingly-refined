package org.practice.map.hashmap.achieve.arraylist;

import java.util.ArrayList;
import java.util.List;
import org.practice.map.YuansjMap;

/**
 * 通过ArrayList 集合实现 HashMap
 *
 * 缺点明显 效率极低 属于数组方式实现
 *
 * @author yuansj[yuansj@neusoft.com]
 * @since 2020/9/26 10:12
 **/
public class YuansjHashMap<K, V> implements YuansjMap<K, V> {

  private final List<YuansjNode<K, V>> nodeList = new ArrayList<>();


  @Override
  public int size() {
    return nodeList.size();
  }

  @Override
  public V put(K k, V v) {
    YuansjNode<K, V> yuansjNode = new YuansjNode<>(k, v);
    nodeList.add(yuansjNode);
    return v;
  }

  @Override
  public V get(K k) {
    for (YuansjNode<K, V> node : nodeList) {
      if (node.getKey().equals(k)) {
        return node.getValue();
      }
    }
    return null;
  }

  static class YuansjNode<K, V> implements Entry<K, V> {

    /**
     * key
     */
    private final K k;

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
    public V setValue(V value) {
      this.v = value;
      return v;
    }
  }
}
