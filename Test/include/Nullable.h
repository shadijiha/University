//
// Created by shadi on 2019-12-27.
//

#ifndef UNTITLED_NULLABLE_H
#define UNTITLED_NULLABLE_H

#include <string>
#include "Object.h"

template<typename T>
class Nullable : public Object {
private:
	T data = 0;
	bool corrupted = false;
public:
	explicit Nullable(T value);

	Nullable();

	void markNULL();

	bool isNULL();

	T value();

	std::string toString();
};


#endif //UNTITLED_NULLABLE_H
