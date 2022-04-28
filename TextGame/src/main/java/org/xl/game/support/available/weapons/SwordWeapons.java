package org.xl.game.support.available.weapons;

import com.print.color.PrintColor;
import org.xl.game.support.monster.Monster;

public class SwordWeapons extends Weapons {
	public SwordWeapons(String weaponsName, int availableProbability, int aggressivity) {
		super(weaponsName, availableProbability, aggressivity);
	}

	@Override
	public void useWeapons(Monster monster) {
		int damage = getAggressivity();
		if(!isMiss(monster)) {
			if(isCritical()) {
				damage = getAggressivity() * 2;
				PrintColor.out.printHighlightRedBoldItalic("你使用");
				System.out.print(getWeaponsName());
				PrintColor.out.printlnHighlightRedBoldItalic("触发了暴击...");
			}
			damage = damage - monster.getDefense();
			if(damage > 0) {
				monster.setHp(monster.getHp() - damage);
			} else {
				monster.setHp(monster.getHp() - 1);
				damage = 1;
			}
			String desc = toColor.valueOfHighlightGrayBoldItalic("你使用") +
					getWeaponsName() +
					toColor.valueOfHighlightGrayBoldItalic("对") +
					monster.getOrganismName() +
					toColor.valueOfHighlightGrayBoldItalic("造成了") +
					toColor.valueOfHighlightGreenBoldItalic(String.valueOf(damage)) +
					toColor.valueOfHighlightGrayBoldItalic("点伤害...");
			System.out.println(desc);
		} else {
			PrintColor.out.printlnHighlightYellowBoldItalic("你的攻击被躲避了...");
		}
	}
}
