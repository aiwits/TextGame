package org.xl.game.util.map;

import org.xl.game.support.concreteMap.FootPath;
import org.xl.game.support.concreteMap.Forest;
import org.xl.game.support.map.AbstractMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class MapPool {
	private static final List<AbstractMap> mapPool;
	private static final MapPool pool;

	static {
		pool = new MapPool();

		mapPool = new ArrayList<>();
		mapPool.add(new FootPath());
		mapPool.add(new Forest());
	}

	private MapPool() {}

	public static MapPool getPool() {
		return pool;
	}

	public AbstractMap getCloneElement(int index) {
		if(index > mapPool.size())
			throw new ArrayIndexOutOfBoundsException("下标越界");
		return cloneObject(mapPool.get(index));
	}

	public AbstractMap getCloneElement(AbstractMap obj) {
		return cloneObject(obj);
	}

	public int getPoolSize() {
		return mapPool.size();
	}

	/**
	 * 通过序列化与反序列化来克隆对象
	 */
	private AbstractMap cloneObject(AbstractMap obj) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		AbstractMap cloneObj = null;

		try{
			baos = new ByteArrayOutputStream();
			oos  = new ObjectOutputStream(baos);
			oos.writeObject(obj);

			bais  = new ByteArrayInputStream(baos.toByteArray());
			ois  = new ObjectInputStream(bais);
			cloneObj = (AbstractMap) ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				bais.close();
				oos.close();
				baos.close();
			} catch(NullPointerException | IOException e) {
				e.printStackTrace();
			}
		}
		return cloneObj;
	}
}
