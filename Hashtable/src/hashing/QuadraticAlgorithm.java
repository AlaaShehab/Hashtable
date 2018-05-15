/**
 *
 */
package hashing;

/**
 * @author Personal
 *
 */
public class QuadraticAlgorithm {

	private int[][] hashMatrix;
	private IHashTable hashFuntion;
	private int maxKeySize;
	private Integer[] table;
	private int collisions;

	public QuadraticAlgorithm () {
		collisions = 0;
	}
	/* (non-Javadoc)
	 * @see hashing.IHash#hashSet()
	 */
	public Integer[] hashSet(Integer[] set) {

		hashFuntion = new HashTable(set);
		int b = hashFuntion.bitCount((int)Math.pow(set.length, 2));
		int maxKey = hashFuntion.getMaxKey();
		maxKeySize = (hashFuntion.getBinaryRep(maxKey)).length;
		hashFuntion.generateMatrix(maxKeySize, b);
		hashMatrix = hashFuntion.getHashMatrix();
		table = new Integer[(int) Math.pow(set.length, 2)];
		initialize();
		for (int i = 0; i < set.length; i++) {
			int[] keyMatrix = hashFuntion.getKeyBinaryRep(set[i], maxKeySize);
			int hashCode = hashFuntion.multiplyMatrix(hashMatrix, keyMatrix);
			if (hashCode >= set.length) {
				hashCode %= ((int)Math.pow(set.length, 2));
			}
			if (table[hashCode] == -1 || table[hashCode] == set[i]) {
				table[hashCode] = set[i];
				continue;
			}
			hashFuntion.generateMatrix(maxKeySize, b);
			hashMatrix = hashFuntion.getHashMatrix();
			initialize();
			i = -1;
			collisions++;
		}
		return table;
	}
	/**
	 * @param table
	 */
	private void initialize() {
		for (int i = 0; i < table.length; i++) {
			table[i] = -1;
		}
	}
	
	public int getCollisionNumber() {
		return collisions;
	}
	
	public boolean find(Integer key) {
		int[] keyMatrix = hashFuntion.getKeyBinaryRep(key, maxKeySize);
		int hashCode = hashFuntion.multiplyMatrix(hashMatrix, keyMatrix);
		if (hashCode >= table.length) {
			hashCode %= table.length;
		}
		if (table[hashCode] != -1 && table[hashCode] == key) {
			return true;
		}
		return false;
	}
	
	public int[][] getHashMatrix() {
		return hashMatrix;
	}

	public Integer[] getTable() {
		return table;
	}
}
