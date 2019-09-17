package matrix;

class API {
  R extends Size;
  C extends Size;
  static IntMat<R,C> randInt() row(R r) col(C c);
  static FltMat<R,C> randFlt() row(R r) col(C c);
}

class IntMat<R extends Size, C extends Size> {
  NEW_C extends Size;
  IntMat<R, C> plus(IntMat<R, C> m);
  FltMat<R, C> plus(FltMat<R, C> m);
  IntMat<R, NEW_C> mult(IntMat<C, NEW_C> m);
  FltMat<R, NEW_C> mult(FltMat<C, NEW_C> m);
  int[][] toArray() by Evaluator.toIntArray;
}

class FltMat<R extends Size, C extends Size> {
  NEW_C extends Size;
  FltMat<R, C> plus(IntMat<R, C> m);
  FltMat<R, C> plus(FltMat<R, C> m);
  FltMat<R, NEW_C> mult(IntMat<C, NEW_C> m);
  FltMat<R, NEW_C> mult(FltMat<C, NEW_C> m);
  float[][] toArray() by Evaluator.toFloatArray;
}
