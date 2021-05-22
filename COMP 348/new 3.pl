is_member(X,[X|_]).
is_member(X, [_|T]) :- member(X,T).

is_member(2, [1, 2, 3, 4]).
