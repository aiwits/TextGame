package org.xl.game.support.concreteMap;

import org.xl.game.support.available.Available;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Chicken;
import org.xl.game.support.monster.WolfMonster;

public class FootPath extends AbstractMap {
	private static final String MESSAGE = "小路";

	public FootPath() {
		setRoomDescription(MESSAGE);
		setAttrCode(AbstractMap.NORMAL);
	}

	@Override
	public void setRoomSelfAvailables() {

	}

	@Override
	public void setRoomSelfMonster() {
		int rand = Available.getRandom(100);
		if(rand < 25) {
			setMonster(new Chicken(toColor.valueOfHighlightCyanBoldItalic("大公鸡")));
		}
		if(rand < 10) {
			setMonsters(WolfMonster.getMonsters(Available.getRandom(2)));
		}
		if(rand < 70) {
			setMonsters(Chicken.getMonsters(Available.getRandom(4)));
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
