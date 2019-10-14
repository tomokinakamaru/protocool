package sample;

class Sample extends SampleBase implements Runnable {
  void run() by Evaluator.run;
}

class SampleSet<T> {
  T any() by Evaluator.any;
}

class ShallowX {
  ShallowY methodX() by Evaluator.methodX;
}

class ShallowY {
  ShallowX methodY() by Evaluator.methodY;
}

class Empty {}

class VarLen {
  static VarLen method(String... strings);
}

class MultipleOccurrence {
  MultipleOccurrence mo1() mo2() mo1();
  MultipleOccurrence mo2() (mo3() mo4())+ mo5() (mo6() | mo7())* end();
  MultipleOccurrence mo8() | mo9() mo8();
}

class Wildcard {
  java.lang.Object method(java.util.Collection<? extends Wildcard> x) by Evaluator.wildcard;
}
