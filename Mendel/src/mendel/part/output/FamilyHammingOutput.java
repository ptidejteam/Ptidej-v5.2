/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package mendel.part.output;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import mendel.Util;
import mendel.family.Family;
import mendel.family.FamilyHomogeneity;
import mendel.family.Prototype;
import mendel.family.FamilyHomogeneity.FamilyResults;
import mendel.family.RoleTag.Tag;
import mendel.model.JClassEntity;
import mendel.model.SetOps;

/**
 * 
 * Input: Family
 * Output: String
 * 
 * @author deniersi
 *
 */
public class FamilyHammingOutput extends FamilyInterfaceOutput {
	
	private FamilyHomogeneity familyTool;

	/**
	 * 
	 */
	public FamilyHammingOutput() {
		this.familyTool = new FamilyHomogeneity();
	}
	
	@Override
	public String headers(Family family) {
		return super.headers(family)
				+ "Prototype, NoM, Hamming, med Ham, IQR Ham, SubBeh Tag, mean Jac, dev Jac, Category"
				+ ", eqP, incP, surP, interP, diffP, outliers\n\n";
	}

	@Override
	public StringBuffer printStats(Family family, StringBuffer buffer) {
		super.printStats(family, buffer);
		printPrototype(family, buffer, "prototype hamR");
		printPrototype(family, buffer, "prototype setR");
		return buffer.append("\n");
	}

	public StringBuffer printPrototype(Family family, StringBuffer buffer, String key) {
		Prototype prototype = family.getPrototype(key);
		FamilyResults stats = this.familyTool.computeFamilyStats(family, prototype);
		Set<JClassEntity>[] inclusions = this.familyTool.inclusions(family, prototype);
		List<Object> data = Arrays.asList(new Object[] {
				"\n" + prototype.getQualifier(), 
				prototype.getInterface().size(),
				prototype.hammingDistance(),
				stats.getHammingMedian(),
				stats.getHammingIQR(),
				subclassingBehavior(family.parent(), prototype),
				stats.getJaccardMean(),
				stats.getJaccardDev(),
				stats.getFamilyCategory(),
				inclusions[0].size(),
				inclusions[1].size(),
				inclusions[2].size(),
				inclusions[3].size(),
				inclusions[4].size(),
				stats.getNbOutliers(),
		});
		
		return new StringBuffer(Util.join(data, ", ", buffer));
	}

	/*
	 * @see RoleTag.tag(..)
	 */
	public Tag subclassingBehavior(JClassEntity parent, Prototype prototype) {
//		Set<String> parentIface = parent.getLocalMethods();
		Set<String> parentIface = parent.getAllMethods();
		Set<String> protoIface = prototype.getInterface();
		int local = protoIface.size();
		int overridden = SetOps.inter(parentIface, protoIface).size();
		int newm = SetOps.diff(protoIface, parentIface).size();
		if(	local == 0 ) {
			return Tag.Other;
		}
		if( overridden == 0 ) {
			return Tag.PureExtender;
		}
		if( newm == 0 ) { 
			return Tag.PureOverrider;
		}
		if( newm > overridden ) {
			return Tag.Extender;
		}
		if( overridden >= newm ) {
			return Tag.Overrider;
		}
		return Tag.Other;
	}

}
