import java.util.Comparator;

public class Champion implements Comparable{
	
	String defString;
	int id;
	
	public Champion (int id, String str) {
		this.id = id;
		this.defString = str;
	}

	@Override
	public int compareTo(Object o) {
		Champion c = (Champion) o;
		if (c instanceof Champion) {
			if (c.id > this.id) {
				return -1;
			} else if (c.id < this.id) {
				return 1;
			}
			return 0;
		}
		return 0;
	}
	
	public String toString() {
		return defString;
	}
}