package hashing;
import java.util.Random;

/**
*
*/

/**
* @author Personal
*
*/
public class HashTable implements IHashTable{

	private int[][] matrix;
	private Integer[] set;
	public HashTable (Integer[] set2) {
		this.set = set2;
	}
	/* (non-Javadoc)
	 * @see IHashTable#getMaxKey()
	 */
	public int getMaxKey() {
		int maxKey = 0;
		if (set.length != 0) {
			maxKey = set[0];
		}
		for (int i = 1; i < set.length; i++) {
			if (set[i] > maxKey) {
				maxKey = set[i];
			}
		}
		return maxKey;
	}

	/* (non-Javadoc)
	 * @see IHashTable#getBinaryRep(int)
	 */
	public int[] getBinaryRep(int key) {
		int numOfBits = bitCount(key);
		int [] binRepOfKey = new int [numOfBits];
		int i = 0, zero = 0, two = 2;
		while (key != zero) {
			binRepOfKey[i] = key %two;
			key = key /two;
			i++;
		}
		return binRepOfKey;
	}
	
	public int[] getKeyBinaryRep(int key, int maxBits) {
		int [] binRepOfKey = new int [maxBits];
		int i = maxBits - 1, zero = 0, two = 2;
		while (key != zero && i >= 0) {
			binRepOfKey[i--] = key %two;
			key = key /two;
		}
		return binRepOfKey;
	}


	/* (non-Javadoc)
	 * @see IHashTable#generateMatrixSize(int)
	 */
	public int bitCount(int number) {
		double two = 2;
		int b = (int) Math.ceil(Math.log10(number) / Math.log10(two));
		if (number == (int)Math.pow(2, b)) {
			return b + 1;
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see IHashTable#generateMatrix(int, int)
	 */
	public void generateMatrix(int keySize, int b) {
		int [][] hashMatrix = new int [b][keySize];
		Random rand = new Random();
		int two = 2;
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < keySize; j++) {
				hashMatrix[i][j] = rand.nextInt(two);
			}
		}

		setHashMatrix(hashMatrix);
	}

	/**
	 *
	 */
	private void setHashMatrix(int [][] hashMatrix) {
		matrix = hashMatrix;
	}
	public int[][] getHashMatrix() {
		return matrix;
	}
	/* (non-Javadoc)
	 * @see IHashTable#multiplyMatrix(int[][], int[][])
	 */
	public int multiplyMatrix(int[][] hashMatrix, int[] keyMatrix) {
		int rows = hashMatrix.length;
		int cols = hashMatrix[0].length;
		int hashValue = 0;
		int pow = 1;
		for(int i = 0; i < rows; i++) {
			int val = 0;
			for(int k = 0; k < cols; k++) {
				val += hashMatrix[i][k] * keyMatrix[k];
			}
			val %= 2;
			hashValue += val * (pow);
			pow *= 2;
		}
		return hashValue;
	}

}

