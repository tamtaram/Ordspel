package create;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class WordComparator implements Comparator<String>{
	Collator collator = Collator.getInstance(new Locale("sv", "SE"));

	@Override
	public int compare(String s1, String s2) {
		if(s1.length() < s2.length()) return -1;
		if(s1.length() > s2.length()) return 1;
		return collator.compare(s1, s2);
	}

}
