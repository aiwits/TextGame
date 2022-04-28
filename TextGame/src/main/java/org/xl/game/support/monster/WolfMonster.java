package org.xl.game.support.monster;

import org.xl.game.support.available.Available;
import org.xl.game.support.available.material.MaterialFactory;
import org.xl.game.support.available.material.MaterialMessage;
import org.xl.game.support.organism.Organism;
import org.xl.game.support.organism.OrganismMessage;

import java.util.ArrayList;
import java.util.List;

public class WolfMonster extends Monster {
	public WolfMonster(String organismName) {
		super(organismName);
	}

	@Override
	public void attack(Organism organism) {
		attack(organism,
				"嘶吼着扑向",
				"进行撕咬，造成了",
				"你翻滚躲避了",
				"的撕咬!"
		);
	}

	@Override
	public void monsterInit() {
		int rand = Available.getRandom(2);
		List<Available> availables = new ArrayList<>();
		for(int i = 0; i < rand; i++) {
			availables.add(MaterialFactory.getMaterial(toColor.valueOfHighlightCyanBoldItalic("狼牙"),
					50, MaterialMessage.SPAKE_MATERIAL_ONLY_NUMBER));
		}

		this.setMaxHp(OrganismMessage.D_MONSTER_MAX_HP);
		this.setHp(this.getMaxHp());
		this.setXp(OrganismMessage.D_MONSTER_MAX_XP);
		this.setAggressivity(OrganismMessage.D_MONSTER_AGGRESSIVITY);
		this.setDefense(OrganismMessage.E_MONSTER_DEFENSE);
		this.setMiss(OrganismMessage.D_MONSTER_MISS);
		this.setLifeState(true);
		this.setAvailables(availables);
	}

	public static ArrayList<Monster> getMonsters(int number) {
		ArrayList<Monster> list = new ArrayList<>();
		for(int i = 0; i < number; i++) {
			list.add(new WolfMonster(toColor.valueOfHighlightCyanBoldItalic("狼")));
		}

		return list;
	}
}
