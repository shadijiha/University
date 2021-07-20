package com.engin.interfaces;

import com.engin.math.ImmutableVec2f;

public interface IClickEvent {

	public void call(ImmutableVec2f position, float dt);
}
