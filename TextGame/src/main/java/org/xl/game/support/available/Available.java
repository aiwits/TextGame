package org.xl.game.support.available;

import java.util.Random;

public interface Available {

	String getAvailableName();

	int getAvailableProbability();

	static int getRandom(int max) {
		Random random = new Random();
		return random.nextInt(max) + 1;
	}
}
