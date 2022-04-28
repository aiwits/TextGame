package org.xl.game.support.concreteMap;

import org.xl.game.support.available.weapons.SwordWeapons;
import org.xl.game.support.available.weapons.Weapons;
import org.xl.game.support.map.AbstractMap;

public class Start extends AbstractMap {
	private static final String MESSAGE = "树林外";

	public Start() {
		setRoomDescription(MESSAGE);
		setAttrCode(AbstractMap.NORMAL);
	}

	@Override
	public void setRoomSelfAvailables() {
		Weapons sword = new SwordWeapons(toColor.valueOfHighlightRedBoldItalic("破损的剑(灵器)"), 100, 25);
		sword.setHit(0);
		sword.setCritical(50);
		setAvailable(sword);
	}

	@Override
	public void setRoomSelfMonster() {

	}

	@Override
	public String toString() {
		return super.toString();
	}
}
