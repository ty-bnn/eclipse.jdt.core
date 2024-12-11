package org.eclipse.jdt.core.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.39
 */
@SuppressWarnings({ "rawtypes" })
public class Pattern2 extends Pattern {
	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(Pattern2.class, "elements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$

	private static final List PROPERTY_DESCRIPTORS;

	static {
		List properyList = new ArrayList(5);
		createPropertyList(Pattern2.class, properyList);
		addProperty(ELEMENTS_PROPERTY, properyList);
		PROPERTY_DESCRIPTORS = reapPropertyList(properyList);
	}

	public static List propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	private ASTNode.NodeList elements = new ASTNode.NodeList(ELEMENTS_PROPERTY);

	Pattern2(AST ast) {
		super(ast);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		// allow default implementation to flag the error
		return super.internalGetSetObjectProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == ELEMENTS_PROPERTY) {
			return elements();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final int getNodeType0() {
		return PATTERN2;
	}

	@Override
	ASTNode clone0(AST target) {
		Pattern2 result = new Pattern2(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.elements().addAll(ASTNode.copySubtrees(target, elements()));
		return result;
	}

	@Override
	final boolean subtreeMatch0(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	@Override
	void accept0(ASTVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChildren(visitor, this.elements);
		}
		visitor.endVisit(this);
	}

	/**
	 * @since 3.39
	 */
	public List elements() {
		return this.elements;
	}


	@Override
	int memSize() {
		// treat Operator as free
		return BASE_NODE_SIZE + 4 * 4;
	}

	@Override
	int treeSize() {
		return
			memSize()
			+ (this.elements == null ? 0 : this.elements.listSize());
	}
}
