#pragma once
#include <iostream>
#include <string>

template <class T>
class SharedArrayStack {
public:

	SharedArrayStack() {
		last = nullptr;
	}

	void push(T element) {

		// First element in stack
		if (last == nullptr) {

			int index_to_add = 0;
			while (s_array[index_to_add] != nullptr)
				index_to_add++;

			s_array[index_to_add] = new SharedArrayStackNode(&element, index_to_add, SharedArrayStackNode::null, SharedArrayStackNode::null);
			last = s_array[index_to_add];
			
		} else
		{
			int index_to_add = 0;
			while (s_array[index_to_add] != nullptr)
				index_to_add++;

			s_array[last->index()]->setNext(index_to_add);
			s_array[index_to_add] = new SharedArrayStackNode(&element, index_to_add, last->index(), SharedArrayStackNode::null);

			last = s_array[index_to_add];
		}
		
	}

	T pop() {

		// if stack is empty
		if (last == nullptr)
			__debugbreak();

		auto* temp_last = new SharedArrayStackNode(*last);
		auto* temp_pointer = last;
		s_array[last->index()] = nullptr;

		if (temp_last->previous() == SharedArrayStackNode::null)
			last = nullptr;
		else
			last = s_array[temp_last->previous()];

		// Return the poped value and free memory
		T return_value = *(temp_last->value());

		delete temp_last;
		delete temp_pointer;	

		return return_value;
	}

	int size() const {

		int count = 0;
		int index = last->index();

		while (index != SharedArrayStackNode::null) {
			count++;
			index = s_array[index]->previous();
		}

		return count;
	}

	const T& top() const {

		if (last == nullptr)
			__debugbreak();

		return *(s_array[last->index()]->value());		
	}

	std::string toString() {

		std::string builder = "[";

		int index = last->index();

		while(index != SharedArrayStackNode::null) {

			auto* element = s_array[index];

			builder += element->toString();

			// Don't add ", " if it is the last element
			if (element->previous() != SharedArrayStackNode::null)
				builder += ", ";

			index = element->previous();			
		}

		return builder + "]";
	}

	static void print_shared_array() {

		std::cout << "[";

		for (int i = 0; i < MAX_SIZE; i++) {
			std::cout << s_array[i]->toString() << std::endl;

			if (i < MAX_SIZE - 1)
				std::cout << ", ";
		}

		std::cout << "]";		
	}
	
private:

	class SharedArrayStackNode {

	public:
		SharedArrayStackNode(T* value, int index, int previous_index, int next_index)
			: m_value(value), m_index(index), m_next(next_index), m_previous(previous_index)
		{}

		SharedArrayStackNode(const SharedArrayStackNode& o)
			: SharedArrayStackNode(o.m_value, o.m_index, o.m_previous, o.m_next)	{}

		SharedArrayStackNode()
			: SharedArrayStackNode(nullptr, null, null, null)
		{}
		
		bool hasNext() const { return m_next != null; }
		bool hasPrevious() const { return m_previous != null; }
		bool hasValue() const { m_value != nullptr; }

		int next() const { return m_next; }
		int previous() const { return m_previous; }
		int index() const { return m_index; }
		T& value() const { return *m_value; }

		void setNext(int o) { m_next = o; }
		void setPrevious(int o) { m_previous = o; }
		void setIndex(int i) { m_index = i; }
		void setValue(T* value) { m_value = value; }

		std::string toString() { return std::to_string(*m_value); }

	private:
		T* m_value;
		int m_index;
		int m_next;
		int m_previous;

	public:
		static const int null = -1;
	};

	static const int MAX_SIZE = 10;
	static SharedArrayStackNode* s_array[MAX_SIZE];

	SharedArrayStackNode* last;
};
