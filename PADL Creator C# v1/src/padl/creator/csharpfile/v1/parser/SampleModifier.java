
package padl.creator.csharpfile.v1.parser;
import java.lang.reflect.Modifier;

public class SampleModifier {

	  public static void main(String[] args) {
	    new String();
	  }

	  public static void printModifiers(Object o) {
	    Class c = o.getClass();
	    int m = c.getModifiers();
	    if (Modifier.isPublic(m))
	     System.out.println("public");
	   if (Modifier.isAbstract(m))
	     System.out.println("abstract");
	 //   if (Modifier.isFinal(m))
	//      System.out.println("final");
	  }
	}
       