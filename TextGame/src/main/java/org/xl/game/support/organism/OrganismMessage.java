package org.xl.game.support.organism;

import java.util.Random;

public final class OrganismMessage {
	private static final int DEFAULT_HP_GROWTH = 5;

	public static final int E_MONSTER_MAX_HP = DEFAULT_HP_GROWTH * 2;
	public static final int D_MONSTER_MAX_HP = E_MONSTER_MAX_HP * DEFAULT_HP_GROWTH;
	public static final int C_MONSTER_MAX_HP = D_MONSTER_MAX_HP * DEFAULT_HP_GROWTH;
	public static final int B_MONSTER_MAX_HP = C_MONSTER_MAX_HP * DEFAULT_HP_GROWTH;
	public static final int A_MONSTER_MAX_HP = B_MONSTER_MAX_HP * DEFAULT_HP_GROWTH;

	private static final int DEFAULT_DEFENSE_GROWTH = 5;

	public static final int E_MONSTER_DEFENSE = E_MONSTER_MAX_HP / DEFAULT_DEFENSE_GROWTH;
	public static final int D_MONSTER_DEFENSE = D_MONSTER_MAX_HP / DEFAULT_DEFENSE_GROWTH;
	public static final int C_MONSTER_DEFENSE = C_MONSTER_MAX_HP / DEFAULT_DEFENSE_GROWTH;
	public static final int B_MONSTER_DEFENSE = B_MONSTER_MAX_HP / DEFAULT_DEFENSE_GROWTH;
	public static final int A_MONSTER_DEFENSE = A_MONSTER_MAX_HP / DEFAULT_DEFENSE_GROWTH;

	private static final int DEFAULT_XP_GROWTH = 4;

	public static final int E_MONSTER_MAX_XP = E_MONSTER_MAX_HP / DEFAULT_XP_GROWTH;
	public static final int D_MONSTER_MAX_XP = D_MONSTER_MAX_HP / DEFAULT_XP_GROWTH;
	public static final int C_MONSTER_MAX_XP = C_MONSTER_MAX_HP / DEFAULT_XP_GROWTH;
	public static final int B_MONSTER_MAX_XP = B_MONSTER_MAX_HP / DEFAULT_XP_GROWTH;
	public static final int A_MONSTER_MAX_XP = A_MONSTER_MAX_HP / DEFAULT_XP_GROWTH;

	private static final int DEFAULT_AGGRESSIVITY_GROWTH = 2;

	public static final int E_MONSTER_AGGRESSIVITY = 5;
	public static final int D_MONSTER_AGGRESSIVITY = E_MONSTER_AGGRESSIVITY * DEFAULT_AGGRESSIVITY_GROWTH;
	public static final int C_MONSTER_AGGRESSIVITY = D_MONSTER_AGGRESSIVITY * DEFAULT_AGGRESSIVITY_GROWTH;
	public static final int B_MONSTER_AGGRESSIVITY = C_MONSTER_AGGRESSIVITY * DEFAULT_AGGRESSIVITY_GROWTH;
	public static final int A_MONSTER_AGGRESSIVITY = B_MONSTER_AGGRESSIVITY * DEFAULT_AGGRESSIVITY_GROWTH;

	public static final int E_MONSTER_MISS = 10;
	public static final int D_MONSTER_MISS = 20;
	public static final int C_MONSTER_MISS = 30;
	public static final int B_MONSTER_MISS = 40;
	public static final int A_MONSTER_MISS = 50;



	private OrganismMessage() {}

	public static int getRandom() {
		return getRandom(100) + 1;
	}

	private static int getRandom(int max) {
		Random random = new Random();
		return random.nextInt(max) + 1;
	}

	private static int getRandom(int min, int max) {
		Random random = new Random();
		return (random.nextInt(max - min + 1) + min);
	}
}
