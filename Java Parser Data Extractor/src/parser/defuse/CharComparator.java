package parser.defuse;

import java.util.Comparator;

public class CharComparator implements Comparator<char[]> {

	public int compare(final char[] arg0, final char[] arg1) {
		/*		if((new String(arg0)).equalsIgnoreCase(new String(arg1))){
					return 0;
				}
				else 
				return -1;*/
		//		if(ArrayUtils.isEquals(arg0, arg1)){
		//			return 0;
		//		}
		//		else 
		//		return -1;
		return new String(arg0).compareTo(new String(arg1));
	}

}
