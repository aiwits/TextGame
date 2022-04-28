package org.xl.game.support.monster;

import com.print.color.PrintColor;
import org.xl.game.support.available.Available;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.organism.Organism;
import org.xl.game.support.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster extends Organism {
	private final List<Available> availables = new ArrayList<>();

	public Monster(String organismName) {
		super(organismName);
		monsterInit();
	}

	@Override
	public abstract void attack(Organism organism);

	public abstract void monsterInit();

	public void attack(Organism organism, String actionStr, String behaviorStr, String dodgeStr, String endStr) {
		int damage = getAggressivity();
		if(organism != null) {
			if(organism instanceof Player) {
				Player player = (Player) organism;
				if(!player.isMiss(this)) {
					if(isCritical()) {
						damage = damage * 2;
						System.out.print(getOrganismName());
						PrintColor.out.printlnHighlightRedBoldItalic("触发了暴击...");
					}
					damage = damage - player.getDefense();
					if(damage > 0) {
						player.setHp(player.getHp() - damage);
					} else {
						player.setHp(player.getHp() - 1);
						damage = 1;
					}
					String desc = getOrganismName() +
							toColor.valueOfHighlightGrayBoldItalic(actionStr) +
							toColor.valueOfHighlightCyanBoldItalic(player.getOrganismName()) +
							toColor.valueOfHighlightGrayBoldItalic(behaviorStr) +
							toColor.valueOfHighlightGreenBoldItalic(String.valueOf(damage)) +
							toColor.valueOfHighlightGrayBoldItalic("点伤害...");
					System.out.println(desc);
					player.isLife();
				} else {
					PrintColor.out.printHighlightGreenBoldItalic(dodgeStr);
					System.out.print(getOrganismName());
					PrintColor.out.printlnHighlightGreenBoldItalic(endStr);
				}
			}
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public void setOrganismCurrentRoom(AbstractMap currentRoom) {
		setCurrentRoom(currentRoom);
	}

	public List<Available> getAvailables() {
		return availables;
	}

	public void setAvailables(List<Available> availables) {
		isAvailable(availables);
	}

	public boolean isCriticalHit() {
		return false;
	}

	public int isLife() {
		if(this.getHp() <= 0) {
			death(this);
			PrintColor.out.printlnHighlightBlueBoldItalic("战斗结束...");
			getCurrentMap().setAvailableToMonster(getAvailables());
			return this.getXp();
		}
		return 0;
	}

	private void isAvailable(List<Available> availables) {
		for(Available available : availables) {
			if(available.getAvailableProbability() >= Available.getRandom(100)) {
				this.availables.add(available);
			}
		}
	}
}
