package org.xl.game.support.concreteMap;

import com.print.color.PrintColor;
import org.xl.game.support.available.weapons.Weapons;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.organism.Organism;
import org.xl.game.support.player.Player;

public class Smithy extends AbstractMap {
	private static final String MESSAGE = "铁匠铺(强化)";

	public Smithy() {
		setRoomDescription(MESSAGE);
		setAttrCode(AbstractMap.ACTION);
	}

	@Override
	public void setRoomSelfAvailables() {
	}

	@Override
	public void setRoomSelfMonster() {

	}

	@Override
	protected void function(Organism organism) {
		Player player = null;
		if(!(organism instanceof Player)) {
			return;
		}
		player = (Player) organism;
		Weapons weapons = player.getEquippedWeapons();
		boolean levelAuto = weapons.levelAuto();
		if(levelAuto) {
			int aggressivity = weapons.getAggressivity();
			int critical = weapons.getCritical();
			int hit = weapons.getHit();
			aggressivity += aggressivity * weapons.getLevel();
			critical += critical * 0.05 * weapons.getLevel();
			hit += hit * 0.05 * weapons.getLevel();
			weapons.setAggressivity(aggressivity);
			weapons.setCritical(critical);
			weapons.setHit(hit);
		} else {
			PrintColor.out.printlnHighlightRedBoldItalic("无法强化");
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
