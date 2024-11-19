package org.eclipse.jdt.core.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.40
 */
@SuppressWarnings("rawtypes")
public class Operator extends ASTNode {
	public static final SimplePropertyDescriptor OPERATOR_PROPERTY =
			new SimplePropertyDescriptor(Operator.class, "operator", String.class, MANDATORY); //$NON-NLS-1$

	private static final List PROPERTY_DESCRIPTORS;

	static {
		List propertyList = new ArrayList(2);
		createPropertyList(Operator.class,propertyList);
		addProperty(OPERATOR_PROPERTY, propertyList);
		PROPERTY_DESCRIPTORS = reapPropertyList(propertyList);
	}

	public static List propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	private String operator = "";


	Operator(AST ast) {
		super(ast);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		if (property == OPERATOR_PROPERTY) {
			if (get) {
				return getOperator();
			} else {
				setOperator((String) value);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetObjectProperty(property, get, value);
	}

	@Override
	final int getNodeType0() {
		return OPERATOR;
	}

	@Override
	ASTNode clone0(AST target) {
		Operator result = new Operator(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.setOperator(getOperator());
		return result;
	}

	@Override
	boolean subtreeMatch0(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		if (operator == null) {
			throw new IllegalArgumentException();
		}
		preValueChange(OPERATOR_PROPERTY);
		this.operator = operator;
		postValueChange(OPERATOR_PROPERTY);
	}

	@Override
	void accept0(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	@Override
	int memSize() {
		return BASE_NODE_SIZE + 1 * 4 + stringSize(this.operator);
	}

	@Override
	int treeSize() {
		return memSize();
	}
}
