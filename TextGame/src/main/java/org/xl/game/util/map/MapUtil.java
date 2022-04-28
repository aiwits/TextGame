package org.xl.game.util.map;

import org.xl.game.support.concreteMap.Smithy;
import org.xl.game.support.concreteMap.Start;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.player.Player;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;


public final class MapUtil {
	private static final String SAVE_GAME_PATH = "C:\\Program Exercise Files\\IdeaProjects\\Castle\\save";
	private static final List<AbstractMap> pointList;
	private static final List<AbstractMap> actionList;

	static {
		pointList = new ArrayList<>();
		AbstractMap begin = new Start();
		begin.setPoint(0, 0);
		begin.setRoomSelfAvailables();
		pointList.add(0, begin);

		actionList = new ArrayList<>();
		actionList.add(new Smithy());
	}

	private MapUtil() {}

	public static void autoMap(int maxRoom) {
		nodeFilter(maxRoom);
		linkNeighbor();
		setActionRoom();
	}

	public static AbstractMap getStartRoom() {
		return pointList.get(0);
	}

	private static void setActionRoom() {
		int rand;
		MapPool pool = MapPool.getPool();
		AbstractMap action;
		AbstractMap src;
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i = 0; i < actionList.size(); i++) {
			rand = getRandom(pointList.size() - 1) + 1;
			if(arr.contains(rand)) {
				i--;
				continue;
			}
			src = pointList.get(rand);
			action = pool.getCloneElement(actionList.get(i));
			action.setPoint(src.getPoint().getX(), src.getPoint().getY());
			swap(action, src);
			pointList.add(rand, action);
			arr.add(rand);
		}


		rand = getRandom(10);
		if(rand == 5) {
			rand = getRandom(pointList.size() - 1) + 1;
			if(!arr.contains(rand)) {
				src = pointList.get(rand);
				action = pool.getCloneElement(actionList.get(0));
				action.setPoint(src.getPoint().getX(), src.getPoint().getY());
				swap(action, src);
				pointList.add(rand, action);
				arr.add(rand);
			}
		}
		/*rand = getRandom(10);
		if(rand == 5) {
			rand = getRandom(pointList.size() - 1) + 1;
			if(!arr.contains(rand)) {
				src = pointList.get(rand);
				action = pool.getCloneElement(actionList.get(1));
				action.setPoint(src.getPoint().getX(), src.getPoint().getY());
				swap(action, src);
				pointList.add(rand, action);
				arr.add(rand);
			}
		}*/
	}

	private static void swap(AbstractMap action, AbstractMap src) {
		AbstractMap temp;
		temp = src.getNorthBoundary().getRoom();
		if(temp != null) {
			temp.getSouthBoundary().setRoom(action);
			action.getNorthBoundary().setRoom(temp);
			src.getNorthBoundary().setRoom(null);
		}
		temp = src.getSouthBoundary().getRoom();
		if(temp != null) {
			temp.getNorthBoundary().setRoom(action);
			action.getSouthBoundary().setRoom(temp);
			src.getSouthBoundary().setRoom(null);
		}
		temp = src.getWestBoundary().getRoom();
		if(temp != null) {
			temp.getEastBoundary().setRoom(action);
			action.getWestBoundary().setRoom(temp);
			src.getWestBoundary().setRoom(null);
		}
		temp = src.getEastBoundary().getRoom();
		if(temp != null) {
			temp.getWestBoundary().setRoom(action);
			action.getEastBoundary().setRoom(temp);
			src.getEastBoundary().setRoom(null);
		}
	}

	private static void linkNeighbor() {
		AbstractMap currentNode;
		AbstractMap.Point currentPoint;
		AbstractMap.Point nextPoint;
		int length = pointList.size();
		for(int i = 1; i < length; i++) {
			currentNode = pointList.get(i);
			currentPoint = currentNode.getPoint();

			nextPoint = currentPoint.calculatePoint(-1, 0);
			if(!nextPoint.isXOY()) {
				for(AbstractMap room : pointList) {
					if(room.getPoint().equals(nextPoint)) {
						if(currentNode.getWestBoundary().getRoom() == null) {
							if(getRandom(100) < 33) {
								currentNode.getWestBoundary().setRoom(room);
								room.getEastBoundary().setRoom(currentNode);
							}
						}
					}
				}
			}
			nextPoint = currentPoint.calculatePoint(1, 0);
			if(!nextPoint.isXOY()) {
				for(AbstractMap room : pointList) {
					if(room.getPoint().equals(nextPoint)) {
						if(currentNode.getEastBoundary().getRoom() == null) {
							if(getRandom(100) < 33) {
								currentNode.getEastBoundary().setRoom(room);
								room.getWestBoundary().setRoom(currentNode);
							}
						}
					}
				}
			}
			nextPoint = currentPoint.calculatePoint(0, 1);
			if(!nextPoint.isXOY()) {
				for(AbstractMap room : pointList) {
					if(room.getPoint().equals(nextPoint)) {
						if(currentNode.getNorthBoundary().getRoom() == null) {
							if(getRandom(100) < 33) {
								currentNode.getNorthBoundary().setRoom(room);
								room.getSouthBoundary().setRoom(currentNode);
							}
						}
					}
				}
			}
			nextPoint = currentPoint.calculatePoint(0, -1);
			if(!nextPoint.isXOY()) {
				for(AbstractMap room : pointList) {
					if(room.getPoint().equals(nextPoint)) {
						if(currentNode.getSouthBoundary().getRoom() == null) {
							if(getRandom(100) < 33) {
								currentNode.getSouthBoundary().setRoom(room);
								room.getNorthBoundary().setRoom(currentNode);
							}
						}
					}
				}
			}
		}
	}

	private static void linkNode(AbstractMap currentNode, AbstractMap nextNode) {
		AbstractMap.Point currentPoint = currentNode.getPoint();
		AbstractMap.Point nextPoint = nextNode.getPoint().calculatePoint(-1, 0);
		if(currentNode.getEastBoundary().getRoom() == null) {
			if(currentPoint.equals(nextPoint)) {
				currentNode.getEastBoundary().setRoom(nextNode);
				nextNode.getWestBoundary().setRoom(currentNode);
			}
		}
		nextPoint = nextNode.getPoint().calculatePoint(1, 0);
		if(currentNode.getWestBoundary().getRoom() == null) {
			if(currentPoint.equals(nextPoint)) {
				currentNode.getWestBoundary().setRoom(nextNode);
				nextNode.getEastBoundary().setRoom(currentNode);
			}
		}
		nextPoint = nextNode.getPoint().calculatePoint(0, 1);
		if(currentNode.getSouthBoundary().getRoom() == null) {
			if(currentPoint.equals(nextPoint)) {
				currentNode.getSouthBoundary().setRoom(nextNode);
				nextNode.getNorthBoundary().setRoom(currentNode);
			}
		}
		nextPoint = nextNode.getPoint().calculatePoint(0, -1);
		if(currentNode.getNorthBoundary().getRoom() == null) {
			if(currentPoint.equals(nextPoint)) {
				currentNode.getNorthBoundary().setRoom(nextNode);
				nextNode.getSouthBoundary().setRoom(currentNode);
			}
		}
	}

	/**
	 * 生成指定数量的节点
	 */
	private static void nodeFilter(int maxNode) {
		if(maxNode < 2 )
			throw new IllegalArgumentException("节点数不能小于二");

		AbstractMap nextNode;
		AbstractMap currentNode;
		List<AbstractMap> p;

		currentNode = pointList.get(0);
		p = createNode(currentNode);
		int rand = getRandom(4);
		nextNode = p.get(rand);
		linkNode(currentNode, nextNode);
		pointList.add(nextNode);
		p.remove(rand);
		rand = getRandom(3);
		nextNode = p.get(rand);
		linkNode(currentNode, nextNode);
		pointList.add(nextNode);

		maxNode -= 2;
		int nodeNum = 0;
		int node;
		for(int i = 1; pointList.size() <= maxNode; i++) {
			currentNode = pointList.get(i);
			p = createNode(currentNode);
			nodeNum = getRandom(p.size());
			if(nodeNum > (maxNode - i)) {
				nodeNum = maxNode - i - 1;
			}
			int count = nodeNum;
			for(int j = 0; j <= nodeNum; j++) {
				node = getRandom(count);
				nextNode = p.get(node);
				linkNode(currentNode, nextNode);
				nextNode.setRoomSelfMonster();
				nextNode.setRoomSelfAvailables();
				pointList.add(nextNode);
				p.remove(node);
				count--;
			}
		}
	}

	/**
	 * 按给定的节点生成周围可用节点
	 */
	private static List<AbstractMap> createNode(AbstractMap room) {
		List<AbstractMap> points = new ArrayList<>();
		AbstractMap.Point point = room.getPoint();
		AbstractMap p;
		p = getNodeByXY(point.getX() + 1, point.getY());
		if(!pointList.contains(p)) {
			points.add(p);
		}
		p = getNodeByXY(point.getX() - 1, point.getY());
		if(!pointList.contains(p)) {
			points.add(p);
		}
		p = getNodeByXY(point.getX(), point.getY() + 1);
		if(!pointList.contains(p)) {
			points.add(p);
		}
		p = getNodeByXY(point.getX(), point.getY() - 1);
		if(!pointList.contains(p)) {
			points.add(p);
		}

		return points;
	}

	private static AbstractMap getNodeByXY(int x, int y) {
		MapPool pool = MapPool.getPool();
		int index = getRandom(pool.getPoolSize());
		AbstractMap node = pool.getCloneElement(index);
		node.setPoint(x, y);
		return node;
	}

	private static int getRandom(int maxNode) {
		if(maxNode == 0)
			maxNode++;
		Random random = new Random();
		return random.nextInt(maxNode);
	}

	public static void saveMap(Player player, String saveName) {
		if(saveName.isEmpty()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd$HH:mm:ss");
			saveName = simpleDateFormat.format(new Date());
		}

		File file = new File(SAVE_GAME_PATH);
		if(!file.exists()) {
			file.mkdirs();
		}

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_GAME_PATH + "\\" + saveName + ".save"))) {
//			oos.writeObject(rooms);
			oos.writeObject(player);
			System.out.print("\33[1;3;92m");
			System.out.println("保存成功...");
			System.out.print("\33[m");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static Player loadSave(String saveName) {
		Player player = null;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_GAME_PATH + "\\" + saveName + ".save"))) {
			player = (Player) ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			System.out.print("\33[1;3;91m");
			System.out.println("Error...");
			System.out.print("\33[m");
		}

		return player;
	}

	public static String getSaveList() {
		File saveFile = new File(SAVE_GAME_PATH);
		File[] saveList = saveFile.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return Pattern.compile("^.+(.save)$").matcher(pathname.getName()).matches();
			}
		});

		if(saveList == null)
			return null;

		if(saveList.length == 0)
			return null;

		StringBuilder builder = new StringBuilder();
		for(File file : saveList) {
			String[] save;
			save = file.getName().split("\\.");
			builder.append("\t");
			builder.append(save[0]).append("\r\n");
		}
		return builder.toString();
	}

	public static void delete(String fileName) {
		File file = new File(SAVE_GAME_PATH + "//" +fileName + ".save");
		if(file.exists()) {
			file.delete();
			System.out.print("\33[1;3;92m");
			System.out.println("删除成功...");
			System.out.print("\33[m");
		} else {
			System.out.print("\33[1;3;91m");
			System.out.println("Error...");
			System.out.print("\33[m");
		}
	}

	public static void deleteAll() {
		File file = new File(SAVE_GAME_PATH);
		File[] files = file.listFiles();

		if(files == null)
			return;

		if(files.length == 0) {
			System.out.print("\33[1;3;91m");
			System.out.println("Error...");
			System.out.print("\33[m");

		} else {
			for(File f : files) {
				f.delete();
			}
			System.out.print("\33[1;3;92m");
			System.out.println("删除成功...");
			System.out.print("\33[m");
		}
	}

	public static void updateMonster() {
		if(!pointList.isEmpty()) {
			for(AbstractMap room : pointList) {
				List<Monster> monsters = room.getMonsters();
				if(monsters != null) {
					for(Monster monster : monsters) {
						monster.setLifeState(true);
						monster.setHp(monster.getMaxHp());
					}
				}
			}
			System.out.print("\33[1;3;91m");
			System.out.println("怪物已刷新...");
			System.out.print("\33[m");
		}
	}
}
