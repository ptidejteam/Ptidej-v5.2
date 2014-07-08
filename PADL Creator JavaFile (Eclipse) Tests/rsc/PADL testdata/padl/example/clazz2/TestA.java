/* (c) Copyright 2011 and following years, Aminata SABANÉ,
 * ÉCole Polytechnique de Montréal.
 * 
 * @author: Aminata SABANÉ
 *  
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.example.clazz2;

import java.util.Iterator;
import padl.example.clazz1.Test2A;

public class TestA extends Test2A {

	int a;

	TestB a2;

	Iterator iter;

	Iterator[] TabIter;

	public TestB getA2() {
		return a2;
	}

	
	public void TestA(){
		
	}
	
	
	public TestA(int a, TestB a2, Iterator iter, Iterator[] tabIter) {
		super();
		this.a = a;
		this.a2 = a2;
		this.iter = iter;
		this.TabIter = tabIter;
	}


	public void setA2(TestB _a2) {
		a2 = _a2;
	}

	void m1(final int k, final float a, String s) {

		final int locale;
		final Object oLocal = new Object();

		final Object oLocalTab = new Object[3];

		class ClassInMethod {

			int f1;
			Object j1;
		}
	}

	class MemberClass {

		String memberClassField;
		MemberClass(String aMemberClassField) {
			memberClassField = aMemberClassField;
		}
	}

}
