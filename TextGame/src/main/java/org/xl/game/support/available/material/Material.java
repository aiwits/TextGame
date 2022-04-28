package org.xl.game.support.available.material;

import com.print.color.ToColorString;
import org.xl.game.support.available.Available;

import java.io.Serial;
import java.io.Serializable;

public abstract class Material implements Available, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	protected static final ToColorString toColor = ToColorString.getInstance();

	private String materialName;
	private int materialNumber;
	private final long onlyNumber;
	private final int availableProbability;

	public Material(String materialName, int availableProbability, long onlyNumber) {
		this.materialName = materialName;
		this.availableProbability = availableProbability;
		this.onlyNumber = onlyNumber;
	}

	public void addMaterialNumber(int materialNumber) {
		this.materialNumber += materialNumber;
	}

	public boolean deleteMaterialNumber(int materialNumber) {
		int number = this.materialNumber - materialNumber;
		if(number < 0) {
			return false;
		}
		this.materialNumber = number;
		return true;
	}

	public int getMaterialNumber() {
		return materialNumber;
	}

	@Override
	public String getAvailableName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Override
	public int getAvailableProbability() {
		return availableProbability;
	}

	@Override
	public String toString() {
		return String.format("%-11s%-1s%-1s",
				materialName,
				toColor.valueOfHighlightCyanBoldItalic("x"),
				toColor.valueOfHighlightCyanBoldItalic(String.valueOf(this.materialNumber))
		);
	}

	public long getOnlyNumber() {
		return onlyNumber;
	}
}
