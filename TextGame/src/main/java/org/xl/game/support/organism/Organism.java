package org.xl.game.support.organism;

import com.print.color.PrintColor;
import com.print.color.ToColorString;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Monster;

import java.io.Serial;
import java.io.Serializable;

public abstract class Organism implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	protected static final ToColorString toColor = ToColorString.getInstance();

	private String organismName;
	private int maxHp;
	private int xp;
	private int hp;
	private int aggressivity;
	private int defense;
	//免疫
	private int miss;
	//暴击率
	private int critical;
	//命中
	private int hit;
	private boolean lifeState;

	private AbstractMap currentRoom;

	public Organism(String organismName) {
		this.organismName = organismName;
	}

	public Organism() {}

	public abstract void attack(Organism organism);
	public abstract void setOrganismCurrentRoom(AbstractMap currentRoom);

	public void death(Organism organism) {
		organism.lifeState = false;
		System.out.println(organism.organismName + descState());
		if(organism instanceof Monster) {
			Monster monster = (Monster) organism;
			PrintColor.out.printHighlightPurpleBoldItalic("获取");
			PrintColor.out.printHighlightGreenBoldItalic(String.valueOf(monster.getXp()));
			PrintColor.out.printlnHighlightRedBoldItalic("灵气" );
		}
	}

	@Override
	public String toString() {

		return toColor.valueOfHighlightCyanBoldItalic(organismName) +
				toColor.valueOfHighlightGrayBoldItalic(" 灵气：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(xp)) +
				toColor.valueOfHighlightGrayBoldItalic(" 生命值：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(hp)) +
				toColor.valueOfHighlightGrayBoldItalic("/") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(maxHp)) +
				toColor.valueOfHighlightGrayBoldItalic(" 攻击力：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(aggressivity)) +
				toColor.valueOfHighlightGrayBoldItalic(" 护甲：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(defense)) +
				toColor.valueOfHighlightGrayBoldItalic(" 闪避：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(miss)) +
				toColor.valueOfHighlightGrayBoldItalic("% 命中：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(hit)) +
				toColor.valueOfHighlightGrayBoldItalic("% 暴击：") +
				toColor.valueOfHighlightBlueBoldItalic(String.valueOf(critical)) +
				toColor.valueOfHighlightGrayBoldItalic("% 状态：") +
				descState();

//		return "\33[1;3;96m" + organismName + "\33[m" + "\33[1;3;97m" + " 灵气：" + "\33[m" + "\33[1;3;94m" + xp + "\33[m" +
//				"\33[1;3;97m" + " 生命值：" + "\33[m" + "\33[1;3;94m" + hp + "\33[m" +
//				"\33[1;3;97m" + "/" + "\33[m" + "\33[1;3;94m" + maxHp + "\33[m" +
//				"\33[1;3;97m" + " 攻击力：" + "\33[m" + "\33[1;3;94m" + aggressivity + "\33[m" +
//				"\33[1;3;97m" + " 护甲：" + "\33[m" + "\33[1;3;94m" + defense + "\33[m" +
//				"\33[1;3;97m" + " 闪避率：" + "\33[m" + "\33[1;3;94m" + miss + "%" + "\33[m" +
//				"\33[1;3;97m" + " 状态：" + "\33[m" + "\33[1;3;91m" + descState() + "\33[m";
	}

	public String descState() {
		return this.lifeState ? toColor.valueOfHighlightGreenBoldItalic("正常") : toColor.valueOfHighlightRedBoldItalic("已死亡");
	}

	public void setXp(int xp) {
		this.xp += xp;
	}

	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAggressivity(int aggressivity) {
		this.aggressivity = aggressivity;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setLifeState(boolean lifeState) {
		this.lifeState = lifeState;
	}

	public void setCurrentRoom(AbstractMap currentRoom) {
		this.currentRoom = currentRoom;
	}

	public String getOrganismName() {
		if(isLifeState())
			return organismName;
		return organismName + toColor.valueOfHighlightRedBoldItalic("(已死亡)");
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getHp() {
		return hp;
	}

	public int getAggressivity() {
		return aggressivity;
	}

	public int getDefense() {
		return defense;
	}

	public int getMiss() {
		return miss;
	}

	public int getCritical() {
		return critical;
	}

	public int getHit() {
		return hit;
	}

	public AbstractMap getCurrentMap() {
		return currentRoom;
	}

	public int getXp() {
		return xp;
	}

	public boolean isLifeState() {
		return lifeState;
	}

	public boolean isMiss(Organism organism) {
		return getMiss() >= OrganismMessage.getRandom() + organism.getHit();
	}

	public boolean isCritical() {
		return getCritical() >= OrganismMessage.getRandom();
	}
}
