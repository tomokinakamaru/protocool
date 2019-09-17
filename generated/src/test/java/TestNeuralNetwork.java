import static neuralnetwork.API.layer;
import static neuralnetwork.API.network;

import neuralnetwork.Layer;
import neuralnetwork.Network;
import org.junit.jupiter.api.Test;

final class TestNeuralNetwork {

  private static final class Dim128 {}

  private static final class Dim256 {}

  private static final Dim128 dim128 = new Dim128();

  private static final Dim256 dim256 = new Dim256();

  @Test
  void main() {
    Layer<Dim128, Dim256> in = layer().in(dim128).out(dim256);
    Layer<Dim256, Dim256> hid = layer().in(dim256).out(dim256);
    Layer<Dim256, Dim128> out = layer().out(dim128).in(dim256);
    Network<Dim128, Dim128> nn = network().add(in).add(hid).add(out);
  }
}
