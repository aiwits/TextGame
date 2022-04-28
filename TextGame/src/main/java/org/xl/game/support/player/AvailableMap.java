package org.xl.game.support.player;

import com.print.color.ToColorString;
import org.xl.game.support.available.material.Material;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AvailableMap<K, V> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	protected static final ToColorString toColor = ToColorString.getInstance();

	private final Map<K, V> map = new ConcurrentHashMap<>();

	private boolean flag;

	public V get(Object key) {
		return map.get(key);
	}

	public void put(K key, V value) {
		if(!(value instanceof Material)) {
			map.put(key, value);
			return;
		}

		flag = false;
		map.forEach((k, v) -> {
			if(v instanceof Material) {
				Material material = (Material) v;
				if((material.getOnlyNumber() == ((Material) value).getOnlyNumber())) {
					material.addMaterialNumber(1);
					flag = true;
				}
			}
		});

		if(!flag) {
			map.put(key, value);
			if(((Material) value).getMaterialNumber() == 0) {
				((Material) value).addMaterialNumber(1);
			}
		}
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public void remove(Object key) {
		map.remove(key);
	}

	public void forEach() {
		map.forEach((k, v) -> {
			System.out.println("\t" + toColor.valueOfHighlightBlueBoldItalic(String.valueOf(k)) + "\t" + v);
		});
	}
}
