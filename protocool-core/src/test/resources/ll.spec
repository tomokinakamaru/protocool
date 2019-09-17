package ll;

class Begin {
    static Nest<End> begin() ;
}

class Nest<T> {
    Nest<Nest<T>> begin() ;
    T end() by Evaluator.end ;
}

class End {
}
