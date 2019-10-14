package constmap;

class ConstMapBuilder {
  K;
  V;
  static ConstMap<K, V> add(K key, V value)* build();
}

class ConstMap<K, V> {
  V get(K key) by Evaluator.get;
}
