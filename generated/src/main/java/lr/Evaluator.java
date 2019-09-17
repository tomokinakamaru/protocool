package lr;

public final class Evaluator {

  @SuppressWarnings("unchecked")
  public static <T> T end() {
    return (T) new End<T>();
  }
}
