package org.xl.game.support.concreteMap;

import org.xl.game.support.available.Available;
import org.xl.game.support.available.material.MaterialFactory;
import org.xl.game.support.available.material.MaterialMessage;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Chicken;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.monster.WolfMonster;
import org.xl.game.support.organism.OrganismMessage;

import java.util.ArrayList;
import java.util.List;

public class Forest extends AbstractMap {
	private static final String MESSAGE = "森林";

	public Forest() {
		setRoomDescription(MESSAGE);
		setAttrCode(AbstractMap.NORMAL);
	}

	@Override
	public void setRoomSelfAvailables() {

	}

	@Override
	public void setRoomSelfMonster() {
		int rand = Available.getRandom(100);
		if(rand < 5) {
			Monster monster = new WolfMonster(toColor.valueOfHighlightRedBoldItalic("狼王"));
			monster.setMaxHp(OrganismMessage.C_MONSTER_MAX_HP);
			monster.setHp(monster.getMaxHp());
			monster.setXp(OrganismMessage.C_MONSTER_MAX_XP);
			monster.setDefense(OrganismMessage.C_MONSTER_DEFENSE);
			monster.setAggressivity(OrganismMessage.C_MONSTER_AGGRESSIVITY);
			monster.setMiss(OrganismMessage.C_MONSTER_MISS);
			monster.setLifeState(true);
			List<Available> availables = new ArrayList<>();
			availables.add(MaterialFactory.getMaterial(toColor.valueOfHighlightRedBoldItalic("huge_spake"), 100, MaterialMessage.HUGE_SPAKE_MATERIAL_ONLY_NUMBER));
			monster.setAvailables(availables);
			setMonster(monster);
		}
		if(rand < 15) {
			setMonsters(Chicken.getMonsters(Available.getRandom(2)));
		}
		if(rand < 70) {
			setMonsters(WolfMonster.getMonsters(Available.getRandom(5)));
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
