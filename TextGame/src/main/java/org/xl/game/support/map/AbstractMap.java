package org.xl.game.support.map;

import com.print.color.ToColorString;
import org.xl.game.support.available.Available;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.organism.Organism;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractMap implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	public static final int NORMAL = 0x00;
	public static final int ACTION = 0x01;

	protected static final ToColorString toColor = ToColorString.getInstance();

	private final Boundary eastBoundary;  //东
	private final Boundary southBoundary; //南
	private final Boundary westBoundary;  //西
	private final Boundary northBoundary; //北
	private final Boundary floor;
	private final Boundary ceiling;

	private final List<Monster> monsters = new ArrayList<>();

	private final List<Available> roomSelfAvailables = new ArrayList<>();
	private final List<Available> roomToMonsterAvailables = new ArrayList<>();

	private Point point;

	// 地图属性（普通、功能...）
	private int attrCode;

//	public static AbstractRoom room;
	private String roomDescription;

	public AbstractMap() {
		this.eastBoundary = new Boundary("东");
		this.southBoundary = new Boundary("南");
		this.westBoundary = new Boundary("西");
		this.northBoundary = new Boundary("北");
		this.floor = new Boundary("上");
		this.ceiling = new Boundary("下");
	}

	public abstract void setRoomSelfAvailables();

	public abstract void setRoomSelfMonster();

	protected void function(Organism organism) {}

	public void setMonsters(List<Monster> monsters) {
		for(Monster monster : monsters) {
			monster.setOrganismCurrentRoom(this);
		}
		this.monsters.addAll(monsters);
	}

	public void setMonster(Monster monster) {
		monster.setOrganismCurrentRoom(this);
		this.monsters.add(monster);
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public Point getPoint() {
		return point;
	}

	public Boundary getEastBoundary() {
//		AbstractRoom.room = this;
		return eastBoundary;
	}

	public Boundary getSouthBoundary() {
//		AbstractRoom.room = this;
		return southBoundary;
	}

	public Boundary getWestBoundary() {
//		AbstractRoom.room = this;
		return westBoundary;
	}

	public Boundary getNorthBoundary() {
//		AbstractRoom.room = this;
		return northBoundary;
	}

	public Boundary getFloor() {
//		AbstractRoom.room = this;
		return floor;
	}

	public Boundary getCeiling() {
//		AbstractRoom.room = this;
		return ceiling;
	}

	public List<Available> getRoomSelfAvailables() {
		return roomSelfAvailables;
	}

	public List<Monster> getMonsters() {
//		if(monsters == null)
//			return null;
//		for(Monster monster : monsters)
//			monster.setLifeState(true);
//		return new ArrayList<>(monsters);
		return monsters;
	}

	public Boundary getBoundary(String direction) {
		Boundary boundary = null;
		switch(direction) {
			case "d":
				boundary = this.eastBoundary;
				break;
			case "s":
				boundary = this.southBoundary;
				break;
			case "a":
				boundary = this.westBoundary;
				break;
			case "w":
				boundary = this.northBoundary;
				break;
			case "up":
				boundary = this.floor;
				break;
			case "down":
				boundary = this.ceiling;
				break;
			default:
				break;
		}

		return boundary;
	}

	public String getExitDesc() {
		StringBuilder builder = new StringBuilder();
		if(this.northBoundary.getRoom() != null)
			builder.append("前 ");
		if(this.southBoundary.getRoom() != null)
			builder.append("后 ");
		if(this.westBoundary.getRoom() != null)
			builder.append("左 ");
		if(this.eastBoundary.getRoom() != null)
			builder.append("右 ");
		if(this.floor.getRoom() != null)
			builder.append("楼上 ");
		if(this.ceiling.getRoom() != null)
			builder.append("楼下 ");

		return builder.toString();
	}

	@Override
	public String toString() {
		return "\33[1;3;97m" + "你在: " + "\33[m" + "\33[1;3;96m" + this.roomDescription + "\33[m" +
				"\33[1;3;97m" + " 坐标:" + "\33[m" + "\33[1;3;96m" + this.point + "\33[m";
	}

	public String toMonsterString() {
		StringBuilder builder = new StringBuilder();
		for(Monster monster : monsters) {
			builder.append(monster.getOrganismName()).append(" ");
		}

		return builder.toString();
	}

	public List<Available> getRoomToMonsterAvailables() {
		return roomToMonsterAvailables;
	}

	public void setAvailableToMonster(List<Available> availables) {
		this.roomToMonsterAvailables.addAll(availables);
		for(Available available : availables) {
			System.out.println("\33[1;3;95m" + "\t掉落：" + "\33[m" + available.getAvailableName());
		}
	}

	/**
	 * 向房间中添加多件物品
	 * @param availables
	 */
	public void setAvailables(List<Available> availables) {
		for(Available available : availables) {
			if(available.getAvailableProbability() >= Available.getRandom(100)) {
				this.roomSelfAvailables.add(available);
			}
		}
	}

	/**
	 * 向房间中添加一件物品
	 * @param available
	 */
	public void setAvailable(Available available) {
		this.roomSelfAvailables.add(available);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		AbstractMap room = (AbstractMap) o;
		return this.point.equals(room.point);
	}

	public void setPoint(int x, int y) {
		this.point = new Point(x, y);
	}

	public int getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(int attrCode) {
		this.attrCode = attrCode;
	}

	public static class Point implements Serializable {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Point calculatePoint(int x, int y) {
			return new Point(this.x + x, this.y + y);
		}

		public boolean isXOY() {
			return this.equals(new Point(0, 0));
		}

		@Override
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if(o == null || getClass() != o.getClass())
				return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public String toString() {
			return this.x + "," + this.y;
		}
	}
}
