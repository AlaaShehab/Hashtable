package hashing;
/**
 *
 */

/**
 * @author Personal
 *
 */
public interface IHashTable {
	/**
	 * get maximum key in a set of keys.
	 * @return maxKey.
	 */
	public int getMaxKey();
	/**
	 * get binary representation of the key.
	 * @param maxkey of a set.
	 * @return binary rep of a key.
	 */
	public int[] getBinaryRep(int maxKey);
	int[] getKeyBinaryRep(int key, int maxBits);
	/**
	 * get b where m = 2^b.
	 * m is size of table.
	 * @param keysNumber of a set.
	 * @return b.
	 */
	public int bitCount(int keysNumber);
	/**
	 * generate the hash matrix at random.
	 * @param b where m = 2^b.
	 * @param keySize of binary key rep.
	 */
	public void generateMatrix(int keySize, int b);
	/**
	 * multiply matrix.
	 * @param hashMatrix which is random.
	 * @param keyMatrix of bits.
	 */
	public int multiplyMatrix(int[][] hashMatrix, int[] keyMatrix);
	/**
	 * @return hashing matrix.
	 */
	public int[][] getHashMatrix();
}
