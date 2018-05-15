package hashing;

import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
	      String regex1 = "X\'([a-zA-Z0-9])+\'";
	      String trial = "X'05F'";
	      boolean pattern1Match =
	                Pattern.compile(regex1).matcher(trial).matches();
	      System.out.println(pattern1Match);

	}
}
