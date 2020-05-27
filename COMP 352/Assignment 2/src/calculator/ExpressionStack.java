package calculator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionStack {

	private List<Expression> list;

	public ExpressionStack() {
		list = new ArrayList<>();
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public Expression top() {
		return list.get(list.size() - 1);
	}

	public void push(Expression e) {
		list.add(e);
	}

	public Expression pop() {
		Expression item_to_remove = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return item_to_remove;
	}
}
