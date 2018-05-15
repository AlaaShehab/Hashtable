package hashing;

import java.util.ArrayList;

public class SubTable {

	private int[][] hashMatrix;
	private ArrayList<Integer> elements = new ArrayList<Integer>();

	public void setHashMatrix(int[][] hashMatrix) {
		this.hashMatrix = hashMatrix;
	}

	public int[][] getHashMatrix() {
		return hashMatrix;
	}

	public ArrayList<Integer> getElements() {
		return elements;
	}

	public void addElement(Integer key) {
		elements.add(key);
	}

	public void setElements(ArrayList<Integer> elements) {
		this.elements = elements;
	}

}
