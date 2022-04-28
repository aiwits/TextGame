package org.xl.game.support.play;

import com.print.color.Color;
import com.print.color.PrintColor;
import org.xl.framework.context.support.GameStartClass;
import org.xl.framework.context.support.InstanceInjection;
import org.xl.framework.context.support.MainMethod;
import org.xl.game.support.map.AbstractMap;
import org.xl.game.support.monster.Monster;
import org.xl.game.support.player.Player;
import org.xl.game.util.map.MapUtil;

import java.util.List;
import java.util.Scanner;

@GameStartClass
public class GameLogic {
	//transient
	private static final Scanner in = new Scanner(System.in);

	@InstanceInjection
	private Player player;

	@MainMethod
	public void mainMethod() {
		// write your code here
		System.out.println();
		PrintColor.out.printlnHighlightGreenBoldItalic("\r\n输入\"help\"获取帮助");
		boolean flag = true;
		while(flag) {
			PrintColor.out.printlnHighlightBlueBoldItalic("1. NEW GAME...");
			PrintColor.out.printlnHighlightBlueBoldItalic("2. LOAD GAME...");
			System.out.println();
			PrintColor.out.print("> ", Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_UNDERLINE);
			String choose = in.nextLine();
			switch(choose) {
				case "1" -> {
//                    player = newGame();
					playGame(newGame(this.player));
				}
				case "2" -> {
//                    player = CreateOrSaveMap.loadSave("game_save_1");
					String str = MapUtil.getSaveList();

					if(str != null) {
						flag = save(str);
					} else {
						PrintColor.out.printlnHighlightGrayBoldItalic("无存档...");
					}
				}
				case "help" -> {
					showHelp();
				}
				default -> {
					PrintColor.out.printlnHighlightRedBoldItalic("Error...");
				}
			}
		}
		in.close();
	}

	public static void goMap(Player player, String str) {
		AbstractMap nextRoom = null;
		try {
			str = str.toLowerCase();
			nextRoom = player.getCurrentMap().getBoundary(str).getRoom();
		} catch(NullPointerException e) {
//            System.out.println("没有门...");
		}
		if(nextRoom != null) {
//            if(nextRoom instanceof BedRoom) {
//                BedRoom bedRoom = (BedRoom) nextRoom;
//                if(bedRoom.isLock()) {
//
//                }
//            }
			player.setCurrentRoom(nextRoom);
			return;
		}
		PrintColor.out.printHighlightGrayBoldItalic("你往");
		PrintColor.out.printHighlightCyanBoldItalic(str);
		PrintColor.out.printlnHighlightGrayBoldItalic("走，发现没有路...");
	}

	public static void attack(List<Monster> monsters, Player player, String[] index) {
		int lifeNumber = 0;
		int point = -1;
		try {
			if(index.length != 1)
				point = Integer.parseInt(index[1]);
		} catch(NumberFormatException e) {
			PrintColor.out.printlnHighlightRedBoldItalic("Error...");
			return;
		}
		if(point > 0 && point < monsters.size() + 1) {
			Monster monster = monsters.get(point - 1);
			if(monster.isLifeState()) {
				do {
					player.attack(monster);
					delay(500);
				} while(monster.isLifeState() && player.isLifeState());
			} else {
				PrintColor.out.printlnHighlightPurpleBoldItalic("已死亡，无法攻击...");
			}
			showPlayerMessage(player);
			return;
		}
		for(Monster monster : monsters) {
			if(monster.isLifeState()) {
				do {
					player.attack(monster);
					delay(500);
				} while(monster.isLifeState() && player.isLifeState());
				break;
			} else {
				lifeNumber++;
			}
		}

		if(lifeNumber == monsters.size()) {
			PrintColor.out.printlnHighlightPurpleBoldItalic("没有可攻击的生物...");
			return;
		}
		showPlayerMessage(player);
	}

	public static void showMap(Player player, List<Monster> monsters) {
		System.out.println(player.getCurrentMap());
		PrintColor.out.printHighlightGrayBoldItalic("去：");
		PrintColor.out.printlnHighlightCyanBoldItalic(player.getCurrentMap().getExitDesc());

		if(!monsters.isEmpty()) {
			PrintColor.out.printHighlightGrayBoldItalic("在此地发现生物：");
			System.out.print(player.getCurrentMap().toMonsterString() + "\r\n");
		}
	}

	public static void showPlayerMessage(Player player) {
		System.out.println();
		PrintColor.out.printHighlightBlueBoldItalic("灵气掉落倍数：");
		PrintColor.out.printlnHighlightRedBoldItalic(String.valueOf(player.getXpGrowth()));
		System.out.println(player);
		System.out.println();
	}

	public static void search(Player player) {
		player.search();
	}

	public static void update() {
		MapUtil.updateMonster();
	}

	public static void showBackpack(Player player) {
		player.showBackpack();
	}

	public static void unloadWeapons(Player player) {
		player.unloadWeapons();
	}

	public static void setAvailableToBackpack(Player player, String UID) {
		try {
			player.setAvailableToBackpack(Integer.parseInt(UID));
		} catch(NumberFormatException e) {
			PrintColor.out.printlnHighlightPurpleBoldItalic("请输入道具的UID号...");
		}
	}

	public static void deleteAvailableToBackpack(Player player, String UID) {
		try {
			player.deleteAvailableToBackpack(Integer.parseInt(UID));
		} catch(NumberFormatException e) {
			PrintColor.out.printlnHighlightGrayBoldItalic("请输入武器的UID号...");
		}
	}

	public static void pickup(Player player) {
		player.pickup();
	}

	public static void showMonsters(List<Monster> monsters) {
		for(Monster monster : monsters) {
			System.out.println(monster);
		}
	}

	public static Player newGame(Player player) {
		PrintColor.out.printHighlightPurpleBoldItalic("请设置世界大小：");
		String str = in.nextLine();
		int maxMap;
		if(str.isEmpty())
			str = "15";
		try {
			maxMap = Integer.parseInt(str);
		} catch(NumberFormatException e) {
			PrintColor.out.printlnHighlightRedBoldItalic("Error...");
			maxMap = 15;
		}
		PrintColor.out.printlnHighlightPurpleBoldItalic("正在创建世界...");
		delay(1000);
		MapUtil.autoMap(maxMap);
		PrintColor.out.printlnHighlightPurpleBoldItalic("世界创建完成...");
		delay(500);

		player.setXpGrowth(10);
		player.setCurrentRoom(MapUtil.getStartRoom());

		return player;
	}

	public static void showHelp() {
		System.out.print("\33[1;3;92m");
		System.out.print("""
				可选行动：\r
				\thelp：                 帮助\r
				\tgo W/A/S/D/up/down：   移动\r
				\tpickup：               拾取\r
				\tattack：               攻击\r
				\tB：                    背包\r
				\tE：                    搜索\r
				\tuse UID：              使用道具\r
				\tunload：               卸载武器\r
				\tdel UID：              分解武器\r
				\tupdate：               刷新怪物\r
				\tshow map：             显示当前地点信息\r
				\tshow player：          显示玩家信息\r
				\tshow monster：         显示当前地点生物信息\r
				\tshow all：             显示全部信息\r
				\tsave name：            保存游戏\r
				\tshow save：            显示存档\r
				\tend/END：              结束游戏\r
				""");
		System.out.print("\33[m");
	}

	public static void playGame(Player player) {
		List<Monster> monsters = null;

		label:
		while(true) {
			monsters = player.getCurrentMap().getMonsters();

			showMap(player, monsters);

			loop:
			while(true) {
				PrintColor.out.print("> ", Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_UNDERLINE);
				String line = in.nextLine();
				String[] words = line.split(" ");
				String cmd = words[0].toLowerCase();
				try {
					switch(cmd) {
						case "show":
							cmd = words[1].toLowerCase();
							switch(cmd) {
								case "player" -> showPlayerMessage(player);
								case "monster" -> showMonsters(monsters);
								case "map" -> showMap(player, monsters);
								case "all" -> {
									showPlayerMessage(player);
									showMonsters(monsters);
									showMap(player, monsters);
								}
								case "save" -> System.out.println(MapUtil.getSaveList());
								default -> PrintColor.out.printlnHighlightRedBoldItalic("Error...");
							}
							break;
						case "help":
							showHelp();
							break;
						case "e":
							search(player);
							break;
						case "pickup":
							pickup(player);
							break;
						case "update":
							update();
							break;
						case "b":
							showBackpack(player);
							break;
						case "unload":
							unloadWeapons(player);
							break;
						case "use":
							setAvailableToBackpack(player, words[1]);
							break;
						case "del":
							deleteAvailableToBackpack(player, words[1]);
							break;
						case "attack":
							attack(monsters, player, words);
							break;
						case "save":
							MapUtil.saveMap(player, words[1]);
							break;
						case "go":
							goMap(player, words[1].toLowerCase());
							break loop;
						case "end":
							break label;
						default:
							PrintColor.out.printlnHighlightRedBoldItalic("Error...");
							break;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					PrintColor.out.printlnHighlightRedBoldItalic("Error...");
				}
			}
		}
		in.close();
		System.exit(0);
	}

	private static void mapSwitch(Player player) {
		int code = player.getCurrentMap().getAttrCode();
		if(AbstractMap.NORMAL == code)
			return;
		while(true) {

		}
	}

	public static boolean save(String str) {
		boolean flag = true;
		PrintColor.out.printlnHighlightCyanBoldItalic("load name   加载存档");
		PrintColor.out.printlnHighlightCyanBoldItalic("rm name     删除存档");
		PrintColor.out.printlnHighlightCyanBoldItalic("rm -a       删除所有存档");
		PrintColor.out.printlnHighlightCyanBoldItalic("back        返回");
		PrintColor.out.printlnHighlightGrayBoldItalic("存档列表:");
		PrintColor.out.printlnHighlightCyanBoldItalic(str);
		PrintColor.out.print("> ", Color.STYLE_GREEN_HIGHLIGHT, Color.FONT_BOLD_UNDERLINE);
		String i = in.nextLine();
		String[] input = i.split(" ");
		switch(input[0]) {
			case "load":
				Player player = MapUtil.loadSave(input[1]);
				if(player != null) {
					flag = false;
					playGame(player);
				}
				break;
			case "rm":
				if(input[1].equals("-a")) {
					MapUtil.deleteAll();
				} else {
					MapUtil.delete(input[1]);
				}
				break;
			case "back":
				return true;
			default:
				PrintColor.out.printlnHighlightRedBoldItalic("Error...");
		}
		return flag;
	}

	public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
