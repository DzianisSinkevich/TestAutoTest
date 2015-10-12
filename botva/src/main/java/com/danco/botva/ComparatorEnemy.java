package com.danco.botva;

import java.util.Comparator;

public class ComparatorEnemy implements Comparator<Enemy> {
	@Override
	public int compare(Enemy enemy1, Enemy enemy2) {
		Float value1 = enemy1.getValue();
		Float value2 = enemy2.getValue();
		return value1.compareTo(value2);
	}

}
