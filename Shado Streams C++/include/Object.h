//
// Created by shadi on 2019-12-28.
//

#ifndef UNTITLED_OBJECT_H
#define UNTITLED_OBJECT_H

#include <string>
#include <iostream>

class Object {
private:
	bool isnull = false;
public:
	Object() = default;

	virtual const Object* hashCode() const;

	std::string getClass() const;

	virtual std::string toString() const;

	bool equals(Object o) const;

	virtual bool isNULL() const final { return this->isnull; }

	virtual void toNULL() final;

	friend std::ostream& operator<<(std::ostream os, Object o);
};

#endif //UNTITLED_OBJECT_H
