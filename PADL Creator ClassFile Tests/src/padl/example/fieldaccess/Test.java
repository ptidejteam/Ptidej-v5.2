package padl.example.fieldaccess;
 
public class Test {
	int a;
	void rien() {
		this.a = 3;
	}
	public static void main(String[] args) {
		Test t = new Test();
		t.a = 3;
		t.rien();
		System.out.println(t.a);
	}
}
