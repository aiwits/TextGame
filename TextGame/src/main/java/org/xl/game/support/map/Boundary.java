package org.xl.game.support.map;

import java.io.Serial;
import java.io.Serializable;

public class Boundary implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private AbstractMap room;
	private String desc;

	public Boundary(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}

	public AbstractMap getRoom() {
		return room;
	}

	public void setRoom(AbstractMap room) {
		this.room = room;
//		AbstractRoom r = AbstractRoom.room;
//		if(this.getDesc().equals("东"))
//
//		if(this.getDesc().equals("西"))
//			room.getEastBoundary().setRoom(r);
//		if(this.getDesc().equals("北"))
//			room.getSouthBoundary().setRoom(r);
//		if(this.getDesc().equals("南"))
//			room.getNorthBoundary().setRoom(r);
//		if(this.getDesc().equals("上"))
//			room.getCeiling().setRoom(r);
//		if(this.getDesc().equals("下"))
//			room.getFloor().setRoom(r);
	}

}
