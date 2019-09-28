package itemize;

import ll.Nest;

public final class Evaluator {

  @SuppressWarnings("unchecked")
  public static <T> T end() {
    return (T) new Nest<Object>();
  }
}
