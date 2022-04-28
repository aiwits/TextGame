package org.xl.game.support.available.material;

public final class MaterialFactory {

	public static Material getMaterial(String name, int availableProbability, long onlyNumber) {
			return new ConcreteMaterial(name, availableProbability, onlyNumber);
	}
}
