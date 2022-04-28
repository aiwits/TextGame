package org.xl.game.support.monster;

import org.xl.game.support.available.Available;
import org.xl.game.support.available.material.MaterialFactory;
import org.xl.game.support.available.material.MaterialMessage;
import org.xl.game.support.organism.Organism;
import org.xl.game.support.organism.OrganismMessage;

import java.util.ArrayList;
import java.util.List;

public class Chicken extends Monster {
	public Chicken(String organismName) {
		super(organismName);

	}

	@Override
	public void attack(Organism organism) {
		attack(organism,
				"冲过来对",
				"进行冲撞，造成了",
				"你躲避了",
				"的冲撞!"
		);
	}

	@Override
	public void monsterInit() {
		int rand = Available.getRandom(3);
		List<Available> availables = new ArrayList<>();
		for(int i = 0; i < rand; i++) {
			availables.add(MaterialFactory.getMaterial(toColor.valueOfHighlightGrayBoldItalic("羽毛"),
					100, MaterialMessage.FEATHER_MATERIAL_ONLY_NUMBER));
		}

		this.setMaxHp(OrganismMessage.E_MONSTER_MAX_HP + 20);
		this.setHp(this.getMaxHp());
		this.setXp(OrganismMessage.E_MONSTER_MAX_XP);
		this.setAggressivity(OrganismMessage.E_MONSTER_AGGRESSIVITY - 3);
		this.setDefense(0);
		this.setMiss(OrganismMessage.C_MONSTER_MISS + 5);
		this.setLifeState(true);
		this.setAvailables(availables);
	}

	public static ArrayList<Monster> getMonsters(int number) {
		ArrayList<Monster> list = new ArrayList<>();
		for(int i = 0; i < number; i++) {
			list.add(new Chicken(toColor.valueOfHighlightGrayBoldItalic("鸡")));
		}

		return list;
	}
}
