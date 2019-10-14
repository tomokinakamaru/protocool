package itemize;

class Begin {
    S;
    static Nest<End, S> begin(S item) ;
}

class Nest<T, S> {
    Nest<Nest<T, S>, S> begin(S item) ;
    T end(S item) by Evaluator.end ;
}

class End {
}
