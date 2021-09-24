package org.practice.map;

/**
 * @param <K> key 键
 * @param <V> value 值
 *
 * @author yuansj[yuansj@neusoft.com]
 * @since 2020/9/26 10:10
 */
public interface YuansjMap<K, V> {

  /**
   * 集合大小
   *
   * @return 集合大小
   */
  int size();

  /**
   * 添加
   *
   * @param k 键
   * @param v 值
   *
   * @return value
   */
  V put(K k, V v);

  /**
   * 获取
   *
   * @param k 键
   *
   * @return 值
   */
  V get(K k);

  /**
   * HashMap 存放一条数据 包含那鞋内容 key value Entry 表示存放在 HashMap中一条键值对
   */
  interface Entry<K, V> {

    /**
     * 获取 key
     *
     * @return key
     */
    K getKey();

    /**
     * 获取value
     *
     * @return value
     */
    V getValue();

    /**
     * 设置value
     *
     * @param v 值
     *
     * @return value
     */
    V setValue(V v);

  }

}
