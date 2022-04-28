package org.xl.game.support.player;

import com.print.color.PrintColor;
import org.xl.framework.context.support.Bean;
import org.xl.game.support.available.Available;
import org.xl.game.support.available.material.Material;
import org.xl.game.support.available.weapons.Weapons;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.organism.Organism;

import java.util.List;

@Bean(attr = "player", text = "2")
public class Player extends Organism implements Search {
	private int maxXp;
	private int level = 1;
	private int xpGrowth = 1;

	private int index = 1;
	private final AvailableMap<Integer, Available> backpack = new AvailableMap<>();

	private Weapons equippedWeapons;

	public Player(String organismName) {
		super(organismName);
		setMaxXp();
	}

	public Player() {
		setMaxXp();
	}

	public void setLevel() {
		while((getXp() - maxXp) >= 0) {
			level++;

			setMaxHp(getMaxHp() * 2);
			setAggressivity(getAggressivity() + level);
			setDefense(getDefense() * level);

			setHp(getMaxHp());

			int miss = getMiss() + (level<<1);
			if(miss >= 75)
				miss = 75;
			setMiss(miss);
			setMaxXp();
			PrintColor.out.printHighlightGrayBoldItalic("玩家升阶至：");
			PrintColor.out.printHighlightGreenBoldItalic(String.valueOf(level));
			PrintColor.out.printlnHighlightGrayBoldItalic("阶");
		}
	}

	public void setXpGrowth(int xpGrowth) {
		this.xpGrowth = xpGrowth;
	}

	public void setMaxXp() {
		maxXp = 100 * level + factorial(level);
	}

	public Weapons getEquippedWeapons() {
		return equippedWeapons;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public int getXpGrowth() {
		return xpGrowth;
	}

	public int factorial(int num) {
		if(num == 1)
			return 1;
		return num * factorial(num - 1);
	}

	@Override
	public void attack(Organism organism) {
		if(organism != null) {
			if(organism instanceof Monster) {
				Monster monster = (Monster) organism;
				if(equippedWeapons != null) {
					int srcAgg = equippedWeapons.getAggressivity();
					int srcHit = equippedWeapons.getHit();
					int srcCri = equippedWeapons.getCritical();

					equippedWeapons.setAggressivity(srcAgg + getAggressivity());
					equippedWeapons.setHit(srcHit + getHit());
					equippedWeapons.setCritical(srcCri + getCritical());

					equippedWeapons.useWeapons(monster);

					equippedWeapons.setAggressivity(srcAgg);
					equippedWeapons.setHit(srcHit);
					equippedWeapons.setCritical(srcCri);
				} else {
					playerAttack(monster);
				}

				this.setXp(monster.isLife() * xpGrowth);
				setLevel();

				if(monster.isLifeState())
					monster.attack(this);
			}
		}
	}

	@Override
	public void setOrganismCurrentRoom(AbstractMap currentRoom) {
		setCurrentRoom(currentRoom);
	}

	@Override
	public String toString() {
		String str = super.toString() + "\r\n" +
				toColor.valueOfHighlightGrayBoldItalic("等阶：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(getLevel())) +
				toColor.valueOfHighlightGrayBoldItalic(" 进阶进度：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(getXp())) +
				toColor.valueOfHighlightGrayBoldItalic("/") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(getMaxXp()));

		if(equippedWeapons == null)
			return str + "\r\n\t" + toColor.valueOfHighlightGrayBoldItalic("武器：无");
		else
			return str + "\r\n\t" +
					toColor.valueOfHighlightGrayBoldItalic("武器：") +
					equippedWeapons.getAvailableName() +
					toColor.valueOfHighlightGrayBoldItalic(" 攻击：") +
					toColor.valueOfHighlightBlueBoldItalic(String.valueOf(equippedWeapons.getAggressivity())) +
					toColor.valueOfHighlightGrayBoldItalic(" 命中：") +
					toColor.valueOfHighlightBlueBoldItalic(String.valueOf(equippedWeapons.getHit())) +
					toColor.valueOfHighlightGrayBoldItalic("% 暴击：") +
					toColor.valueOfHighlightBlueBoldItalic(String.valueOf(equippedWeapons.getCritical()) +
					toColor.valueOfHighlightGrayBoldItalic("%"));
	}

	public void showBackpack() {
		if(backpack.isEmpty()) {
			PrintColor.out.printlnHighlightBlueBoldItalic("背包中没有道具...");
			return;
		}
		PrintColor.out.printlnHighlightBlueBoldItalic("背包：" + "\n\t" + "UID" + "\t" + "道具");
		backpack.forEach();
	}

	public void deleteAvailableToBackpack(int UID) {
		if(backpack.isEmpty()) {
			PrintColor.out.printlnHighlightPurpleBoldItalic("背包中没有物品...");
			return;
		}

		Available available = backpack.get(UID);
		if(available == null) {
			PrintColor.out.printlnHighlightPurpleBoldItalic("请选择已有的武器...");
			return;
		}
		if(available instanceof Weapons) {
			if(((Weapons) available).isUseState()) {
				unloadWeapons();
			}
			backpack.remove(UID);
			PrintColor.out.printlnHighlightGreenBoldItalic("分解成功...");
		} else {
			PrintColor.out.printlnHighlightPurpleBoldItalic("只能分解武器...");
		}
	}

	public void putAvailableToBackpack(Available available) {
		if(available != null) {
			backpack.put(index, available);
			index++;
		}
	}

	public Available getAvailableToBackpack(int availableUID) {
		return backpack.get(availableUID);
	}

	public void unloadWeapons() {
		if(this.equippedWeapons != null) {
			System.out.println(this.equippedWeapons.getAvailableName() + toColor.valueOfHighlightPurpleBoldItalic("已卸下..."));
			this.equippedWeapons.setPlayer(null);
			this.equippedWeapons.setUseState(false);
			this.equippedWeapons = null;
		} else {
			PrintColor.out.printlnHighlightPurpleBoldItalic("未装备武器...");
		}
	}

	public void setAvailableToBackpack(int availableUID) {
		if(backpack.isEmpty()) {
			PrintColor.out.printlnHighlightPurpleBoldItalic("背包中还没有道具...");
			return;
		}
		Available available = getAvailableToBackpack(availableUID);
		if(available instanceof Weapons) {
			if(this.equippedWeapons != null) {
				this.equippedWeapons.setUseState(false);
				this.equippedWeapons = null;
			}
			this.equippedWeapons = (Weapons) available;
			this.equippedWeapons.setPlayer(this);
			this.equippedWeapons.setUseState(true);
			PrintColor.out.printHighlightPurpleBoldItalic("已装备");
			System.out.println(available.getAvailableName());
		} else if(available instanceof Material) {
			PrintColor.out.printHighlightPurpleBoldItalic("暂不可使用...");
		} else {
			PrintColor.out.printHighlightPurpleBoldItalic("无此道具...");
		}
	}

	private void playerAttack(Monster monster) {
		int damage = getAggressivity();
		if(!monster.isMiss(this)) {
			if(isCritical()) {
				damage = getAggressivity() * 2;
				PrintColor.out.printlnHighlightRedBoldItalic("你触发了暴击!");
			}
			damage = damage - monster.getDefense();

			if(damage > 0) {
				monster.setHp(monster.getHp() - damage);
			} else {
				monster.setHp(monster.getHp() - 1);
				damage = 1;
			}
			String desc = toColor.valueOfHighlightGrayBoldItalic("你用拳头对") +
					monster.getOrganismName() +
					toColor.valueOfHighlightGrayBoldItalic("造成了") +
					toColor.valueOfHighlightGreenBoldItalic(String.valueOf(damage)) +
					toColor.valueOfHighlightGrayBoldItalic("点伤害...");
			System.out.println(desc);
		} else {
			PrintColor.out.printlnHighlightYellowBoldItalic("你的攻击被躲避了...");
		}
	}

	public void isLife() {
		if(this.getHp() <= 0) {
			death(this);
			PrintColor.out.printlnHighlightRedBoldItalic("游戏结束...");
			System.exit(0);
		}
	}

	@Override
	public void search() {
		AbstractMap room = this.getCurrentMap();
		PrintColor.out.printlnHighlightPurpleBoldItalic("正在翻找...");

		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		if(!room.getMonsters().isEmpty()) {
			List<Monster> monsters = room.getMonsters();
			for(Monster monster : monsters) {
				if(monster.isLifeState()) {
					PrintColor.out.printlnHighlightRedBoldItalic("你惊动了此地生物! 什么也没找到...");
					return;
				}
			}
		}

		List<Available> availables = room.getRoomSelfAvailables();

		if(availables.isEmpty()) {
			PrintColor.out.printlnHighlightRedBoldItalic("什么也没找到...");
			return;
		}

		for(Available available : availables) {
			if(available != null) {
				this.putAvailableToBackpack(available);
				PrintColor.out.printHighlightPurpleBoldItalic("\t获得：");
				System.out.println(available.getAvailableName());
			}
		}
		availables.clear();
	}

	@Override
	public void pickup() {
		List<Available> availables = getCurrentMap().getRoomToMonsterAvailables();

		if(availables.isEmpty()) {
			PrintColor.out.printlnHighlightRedBoldItalic("没有可拾取的物品...");
			return;
		}

		for(Available available : availables) {
			if(available != null) {
//				if(available instanceof Material) {
//					Material material = (Material) available;
//					material.addMaterialNumber(1);
//				}
				this.putAvailableToBackpack(available);
				PrintColor.out.printHighlightPurpleBoldItalic("\t拾得：");
				System.out.println(available.getAvailableName());
			}
		}
		availables.clear();
	}
}
