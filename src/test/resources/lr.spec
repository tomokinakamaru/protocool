package lr;

class Begin {
    static BeginOrEnd<None> begin() ;
}

class BeginOrEnd<T> extends OK {
    BeginOrEnd<End<T>> begin() ;
    T end() by Evaluator.end ;
}

class End<T> extends OK {
    T end() by Evaluator.end ;
}

class None extends OK {
}

class OK {}
