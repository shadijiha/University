/**
 *
 */
package com;

import java.util.ArrayList;
import java.util.List;

public final class Semaphore {

	public int value;
	public List<ShadoProcess> processes;

	public Semaphore()	{
		value = 1;
		processes = new ArrayList<>();
	}
}
