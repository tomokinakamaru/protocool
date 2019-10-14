package neuralnetwork;

class API {
  IN;
  OUT;
  static Network<IN, OUT> network() add(Layer<IN, OUT> layer);
  static Layer<IN, OUT> layer() in(IN dim) out(OUT dim);
  static Layer<IN, OUT> layer() out(OUT dim) in(IN dim);
}

class Network<IN, OUT> {
  NEW_OUT;
  Network<IN, NEW_OUT> add(Layer<OUT, NEW_OUT> l);
}

class Layer<IN, OUT> {
  String toString() by Evaluator.layerToString;
}

foreign String;
