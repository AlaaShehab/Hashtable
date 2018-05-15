package hashing;

import java.util.ArrayList;

public class LinearAlgorithm {

	private int[][] hashMatrix;
	private IHashTable hashFuntion;
	private int maxKeySize;
	private SubTable[] table;
	private int allSizes = 0;
	private int collisions = 0;

	public SubTable[] hashSet(Integer[] set) {
		hashFuntion = new HashTable(set);
		int b = hashFuntion.bitCount(set.length);
		int maxKey = hashFuntion.getMaxKey();
		maxKeySize = (hashFuntion.getBinaryRep(maxKey)).length;
		hashFuntion.generateMatrix(maxKeySize, b);
		hashMatrix = hashFuntion.getHashMatrix();
		table = new SubTable[set.length];
		initialize();

		for (int i = 0; i < set.length; i++) {
			int[] keyMatrix = hashFuntion.getKeyBinaryRep(set[i], maxKeySize);
			int hashCode = hashFuntion.multiplyMatrix(hashMatrix, keyMatrix);
			if (hashCode >= set.length) {
				hashCode %= set.length;
			}
			table[hashCode].addElement(set[i]);
		}
		
		for (int i = 0; i < set.length; i++) {
			QuadraticAlgorithm hashSubTable = new QuadraticAlgorithm();
			if (table[i].getElements().size() > 1){
			table[i].setElements(arrayListToArray(hashSubTable.hashSet(arrayToArrayList(table[i].getElements()))));
			table[i].setHashMatrix(hashSubTable.getHashMatrix());
			allSizes += table[i].getElements().size();
			collisions += hashSubTable.getCollisionNumber();
			}
		}

		return table;
	}

	private void initialize() {
		for (int i = 0; i < table.length; i++) {
			table[i] = new SubTable();
		}
	}

	private ArrayList<Integer> arrayListToArray(Integer[] array) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		return arrayList;
	}

	private Integer[] arrayToArrayList(ArrayList<Integer> arrayList) {
		Integer[] array = new Integer[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}

	public boolean find(Integer key) {
		int[] keyMatrix = hashFuntion.getKeyBinaryRep(key, maxKeySize);
		int hashCode = hashFuntion.multiplyMatrix(hashMatrix, keyMatrix);
		hashCode %= table.length;
		int subHashCode = 0;
		if (table[hashCode].getHashMatrix() != null){
			subHashCode = hashFuntion.multiplyMatrix(table[hashCode].getHashMatrix(), keyMatrix);
		}
		if(table[hashCode].getElements().size() > 0){
			subHashCode %= table[hashCode].getElements().size();
		}
		
		if (table[hashCode].getElements().size() >= 1 && table[hashCode].getElements().get(subHashCode) == key) {
			return true;
		}
		
		return false;
	}

	public int getSize() {
		return allSizes;
	}
	public int getCollisionNumber() {
		return collisions;
	}
}
