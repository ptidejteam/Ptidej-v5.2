package padl.cpp.visitor;

import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.ICPPGhost;
import padl.cpp.kernel.ICPPMemberClass;
import padl.cpp.kernel.ICPPMemberGhost;
import padl.cpp.kernel.IDestructor;
import padl.cpp.kernel.IEnum;
import padl.cpp.kernel.IEnumValue;
import padl.cpp.kernel.IGlobalField;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.kernel.IMemberStructure;
import padl.cpp.kernel.IStructure;
import padl.cpp.kernel.IUnion;
import padl.visitor.IGenerator;

public interface ICPPGenerator extends IGenerator {
	void close(final ICPPClass aCPPClass);
	void close(final ICPPGhost aCPPGhost);
	void close(final ICPPMemberClass aCPPMemberClass);
	void close(final ICPPMemberGhost aCPPMemberGhost);
	void close(final IDestructor aDestructor);
	void close(final IEnum anEnum);
	void close(final IGlobalField aGlobalField);
	void close(final IGlobalFunction aGlobalFunction);
	void close(final IMemberStructure aMemberStructure);
	void close(final IStructure aStruct);
	void close(final IUnion anUnion);
	void open(final ICPPClass aCPPClass);
	void open(final ICPPGhost aCPPGhost);
	void open(final ICPPMemberClass aCPPMemberClass);
	void open(final ICPPMemberGhost aCPPMemberGhost);
	void open(final IDestructor aDestructor);
	void open(final IEnum anEnum);
	void open(final IGlobalField aGlobalField);
	void open(final IGlobalFunction aGlobalFunction);
	void open(final IMemberStructure aMemberStructure);
	void open(final IStructure aStruct);
	void open(final IUnion anUnion);
	void visit(final IEnumValue anEnumValue);
}
