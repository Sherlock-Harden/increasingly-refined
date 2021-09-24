package org.practice.map.hashmap;

import org.practice.map.YuansjMap;

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
 * @since 2020/9/28 14:21
 **/
public class YuansjHashMap<K, V> implements YuansjMap<K, V> {

  /**
   * 初始化容量 16
   */
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

  /**
   * 最大容量 10亿多
   */
  static final int MAXIMUM_CAPACITY = 1 << 30;

  /**
   * 默认 加载因子
   */
  static final float DEFAULT_LOAD_FACTOR = 0.75f;
  static final YuansjEntry<?, ?>[] EMPTY_TABLE = {};
  /**
   * 加载因子
   */
  final float loadFactor;
  transient YuansjEntry<K, V>[] table = (YuansjEntry<K, V>[]) EMPTY_TABLE;

  /**
   * 阈值 需要扩容的时候设置值 实际HashMap存放的大小
   *
   * 容量*加载因子
   *
   * 当达到阈值的时候开始扩容
   */
  int threshold;

  transient int hashSeed = 0;

  /**
   * 集合大小
   */
  transient int size;

  public YuansjHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public YuansjHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }

  /**
   * @param initialCapacity 初始容量
   * @param loadFactor 加载因子
   */
  public YuansjHashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY) {
      initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
    }
    // 设置加载因子
    this.loadFactor = loadFactor;
    // 设置阈值
    threshold = initialCapacity;
    // 学习使用 暂时不需要他
    //init();
  }

  /**
   * 模板方法 实现自定义拓展
   */
  //void init() {
  //}
  @Override
  public int size() {
    return 0;
  }

  @Override
  public V put(K k, V v) {
    if (table == EMPTY_TABLE) {
      //初始化数组
      inflateTable(threshold);
    }

    int hash = hash(k);
    int index = indexFor(hash, table.length);

    for (YuansjEntry<K, V> e = table[index]; e != null; e = e.next) {
      Object key;
      //hashcode 相同且 对象相同或者对象值相同 修改value
      if (e.hash == hash && ((key = e.k) == k || k.equals(key))) {
        V oldValue = e.v;
        e.v = v;
        //e.recordAccess(this);
        return oldValue;
      }
    }
    //直接添加元素
    addEntry(hash, k, v, index);

    return null;
  }

  @Override
  public V get(K k) {
    return null;
  }


  /**
   * 默认数组初始化
   */
  private void inflateTable(int toSize) {
    //计算初始容量
    int capacity = roundUpToPowerOf2(toSize);
    //计算阈值
    threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
    table = new YuansjEntry[capacity];
    //initHashSeedAsNeeded(capacity);
  }

  private static int roundUpToPowerOf2(int number) {
    return number >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
  }

  /**
   * 计算hash
   */
  final int hash(Object k) {
    int hash = hashSeed;
    if (0 != hash && k instanceof String) {
      // JDK 1.7
      //return sun.misc.Hashing.stringHash32((String) k);
    }
    hash ^= k.hashCode();
    hash ^= (hash >>> 20) ^ (hash >>> 12);
    return hash ^ (hash >>> 7) ^ (hash >>> 4);
  }

  /**
   * 计算数组下标存放位置
   */
  static int indexFor(int hash, int length) {
    return hash & (length - 1);
  }

  void addEntry(int hash, K key, V value, int bucketIndex) {
    //if ((size >= threshold) && (null != table[bucketIndex])) {
    //  resize(2 * table.length);
    //  hash = (null != key) ? hash(key) : 0;
    //  bucketIndex = indexFor(hash, table.length);
    //}

    createEntry(hash, key, value, bucketIndex);
  }

  void createEntry(int hash, K key, V value, int bucketIndex) {
    YuansjEntry<K, V> e = table[bucketIndex];
    table[bucketIndex] = new YuansjEntry<>(hash, key, value, e);
    size++;
  }

  static class YuansjEntry<K, V> implements Entry<K, V> {

    int hash;
    final K k;
    V v;
    YuansjEntry<K, V> next;

    public YuansjEntry(int hash, K k, V v, YuansjEntry<K, V> next) {
      this.hash = hash;
      this.k = k;
      this.v = v;
      this.next = next;
    }

    @Override
    public K getKey() {
      return this.k;
    }

    @Override
    public V getValue() {
      return this.v;
    }

    @Override
    public V setValue(V v) {
      V oldValue = this.v;
      this.v = v;
      return oldValue;
    }

    /**
     * 模板方法 实现自定义拓展
     */
    void recordAccess(YuansjHashMap<K, V> m) {
    }
  }

}
