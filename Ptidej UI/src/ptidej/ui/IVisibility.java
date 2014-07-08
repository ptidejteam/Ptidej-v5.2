/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.ui;

public interface IVisibility {
	public static int AGGREGATION_DISPLAY_ELEMENTS = 2;
	public static int AGGREGATION_NAMES = 1;
	public static int ASSOCIATION_DISPLAY_ELEMENTS = 8;
	public static int ASSOCIATION_NAMES = 4;
	public static int COMPOSITION_DISPLAY_ELEMENTS = 32;
	public static int COMPOSITION_NAMES = 16;
	public static int CONTAINER_AGGREGATION_DISPLAY_ELEMENTS = 128;
	public static int CONTAINER_AGGREGATION_NAMES = 64;
	public static int CONTAINER_COMPOSITION_DISPLAY_ELEMENTS = 1024;
	public static int CONTAINER_COMPOSITION_NAMES = 256;
	public static int CREATION_DISPLAY_ELEMENTS = 4096;
	public static int CREATION_NAMES = 2048;
	public static int DELEGATION_DISPLAY_ELEMENTS = 16384;
	public static int DELEGATION_NAMES = 8192;
	public static int FIELD_NAMES = 32768;
	public static int FULLY_QUALIFIED_NAMES = 65536;
	public static int GHOST_ENTITIES_DISPLAY = 131072;
	public static int HIERARCHY_DISPLAY_ELEMENTS = 524288;
	public static int HIERARCHY_NAMES = 262144;
	public static int METHOD_NAMES = 1048576;
	public static int USE_DISPLAY_ELEMENTS = 4194304;
	public static int USE_NAMES = 2097152;

	int getVisibleElements();
	void setVisibleElements(final int visibility);
}
