package driver;

import java.util.*;

public interface IExperimentalMap<K, V> extends Map<K, V> {

	public int getCollisions();
}
