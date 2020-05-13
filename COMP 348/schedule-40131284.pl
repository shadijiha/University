team(['40131284').
student_info('40131284', 'Shadi', 'Jiha').

takes_course('40131284', 'comp', '348', 'aa').
takes_course('40131284', 'comp', '348', 'aaaf').
takes_course('40131284', 'comp', '352', 'aa').
takes_course('40131284', 'comp', '352', 'aaae').


course_schedule('comp', '348', 'aa', 'mon', '1445', '1715').
course_schedule('comp', '348', 'aa', 'wed', '1445', '1715').
course_schedule('comp', '348', 'aaaf', 'tue', '1215', '1315').
course_schedule('comp', '348', 'aaaf', 'thu', '1215', '1315').
course_schedule('comp', '352', 'aa', 'tue', '1830', '2100').
course_schedule('comp', '352', 'aaae', 'wed', '1315', '1415').

team(X), member(S, X),
findall(S, takes_course(S, _, _, _), LL),
length(LL, NN),
write(S), write(' has only taken '), write(NN),
write(' courses and tutorials in summer 2020.'), nl, fail.