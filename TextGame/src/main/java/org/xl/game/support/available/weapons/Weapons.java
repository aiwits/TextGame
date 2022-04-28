package org.xl.game.support.available.weapons;

import com.print.color.ToColorString;
import org.xl.game.support.available.Available;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.organism.Organism;
import org.xl.game.support.organism.OrganismMessage;
import org.xl.game.support.player.Player;

import java.io.Serial;
import java.io.Serializable;

public abstract class Weapons implements Available, WeaponsBehavior, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final int MAX_LEVEL = 5;

	protected static final ToColorString toColor = ToColorString.getInstance();

	private String weaponsName;
	private int aggressivity;
	// 命中
	private int hit;
	// 暴击
	private int critical;
	private Player player;

	private int level;

	private final int availableProbability;

	private boolean useState;

	public Weapons(String weaponsName, int availableProbability, int aggressivity) {
		this.weaponsName = weaponsName;
		this.availableProbability = availableProbability;
		this.aggressivity = aggressivity;
	}

	@Override
	public String getAvailableName() {
		return getWeaponsName();
	}

	@Override
	public int getAvailableProbability() {
		return availableProbability;
	}

	@Override
	public abstract void useWeapons(Monster monster);

	public void setUseState(boolean useState) {
		this.useState = useState;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setAggressivity(int aggressivity) {
		this.aggressivity = aggressivity;
	}

	public void setWeaponsName(String weaponsName) {
		this.weaponsName = weaponsName;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public Player getPlayer() {
		return player;
	}

	public int getCritical() {
		return critical;
	}

	public int getHit() {
		return hit;
	}

	public boolean isUseState() {
		return useState;
	}

	public int getAggressivity() {
		return aggressivity;
	}

	public String getWeaponsName() {
		return weaponsName;
	}

	public boolean isCritical() {
		return getCritical() + player.getCritical() >= Available.getRandom(100);
	}

	public boolean isMiss(Organism organism) {
		return organism.getMiss() >= OrganismMessage.getRandom() + getHit();
	}

	@Override
	public String toString() {
		if(isUseState())
			return weaponsName + toColor.valueOfHighlightCyanBoldItalic("(已装备)");
		else
			return weaponsName;
	}

	public int getLevel() {
		return level;
	}

	public boolean levelAuto() {
		if(this.level >= MAX_LEVEL) {
			return false;
		}
		this.level++;
		return true;
	}
}
