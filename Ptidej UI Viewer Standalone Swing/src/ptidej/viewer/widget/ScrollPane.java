package ptidej.viewer.widget;

import java.awt.Component;

import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;

	public static final int BOTH = 0;
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;

	protected int defaultConstraint = BOTH;
	protected int defaultIncrementUnit = 10;

	public ScrollPane() {
		this.setDefaultIncrementUnit();
	}

	public ScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		this.setDefaultIncrementUnit();
	}

	public ScrollPane(
		int increment,
		int constraint,
		Component view,
		int vsbPolicy,
		int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		this.setIncrementUnit(increment, constraint);
	}

	public ScrollPane(Component view) {
		super(view);
		this.setDefaultIncrementUnit();
	}

	public ScrollPane(int increment, int constraint, Component view) {
		super(view);
		this.setIncrementUnit(increment, constraint);
	}

	public ScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		this.setDefaultIncrementUnit();
	}

	public ScrollPane(
		int increment,
		int constraint,
		int vsbPolicy,
		int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		this.setIncrementUnit(increment, constraint);
	}

	public void setIncrementUnit(int increment, int constraint) {
		switch (constraint) {
			case HORIZONTAL :
				this.getHorizontalScrollBar().setUnitIncrement(increment);
				break;
			case VERTICAL :
				this.getVerticalScrollBar().setUnitIncrement(increment);
				break;
			case BOTH :
				this.getHorizontalScrollBar().setUnitIncrement(increment);
				this.getVerticalScrollBar().setUnitIncrement(increment);
				break;
			default :
				throw new IllegalArgumentException();
		}
	}

	public void setIncrementUnit(int increment) {
		this.setIncrementUnit(increment, this.defaultConstraint);
	}

	public void setDefaultIncrementUnit(int constraint) {
		switch (constraint) {
			case HORIZONTAL :
				this.getHorizontalScrollBar().setUnitIncrement(
					this.defaultIncrementUnit);
				break;
			case VERTICAL :
				this.getVerticalScrollBar().setUnitIncrement(
					this.defaultIncrementUnit);
				break;
			case BOTH :
				this.getHorizontalScrollBar().setUnitIncrement(
					this.defaultIncrementUnit);
				this.getVerticalScrollBar().setUnitIncrement(
					this.defaultIncrementUnit);
				break;
			default :
				throw new IllegalArgumentException();
		}
	}

	public void setDefaultIncrementUnit() {
		this.setIncrementUnit(
			this.defaultIncrementUnit,
			this.defaultConstraint);
	}
}