/*******************************************************************************
 * Copyright (c) 2000, 2021 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jdt.core.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * Instanceof expression AST node type.
 * <pre>
 * InstanceofExpression:
 *    Expression <b>instanceof</b> Type
 * </pre>
 *
 * @since 2.0
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@SuppressWarnings("rawtypes")
public class InstanceofExpression extends Expression {

	/**
	 * The "leftOperand" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 * @deprecated replaced by ELEMENTS_PROPERTY.
	 */
	public static final ChildPropertyDescriptor LEFT_OPERAND_PROPERTY =
		new ChildPropertyDescriptor(InstanceofExpression.class, "leftOperand", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(InstanceofExpression.class, "elements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "rightOperand" structural property of this node type (child type: {@link Type}).
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor RIGHT_OPERAND_PROPERTY =
		new ChildPropertyDescriptor(InstanceofExpression.class, "rightOperand", Type.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List properyList = new ArrayList(3);
		createPropertyList(InstanceofExpression.class, properyList);
		addProperty(ELEMENTS_PROPERTY, properyList);
		addProperty(RIGHT_OPERAND_PROPERTY, properyList);
		PROPERTY_DESCRIPTORS = reapPropertyList(properyList);
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 *
	 * @param apiLevel the API level; one of the
	 * <code>AST.JLS*</code> constants

	 * @return a list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor})
	 * @since 3.0
	 */
	public static List propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * The left operand; lazily initialized; defaults to an unspecified,
	 * but legal, simple name.
	 * @deprecated replaced by elements.
	 */
	private volatile Expression leftOperand;

	/**
	 * @since 3.39
	 */
	private ASTNode.NodeList elements = new ASTNode.NodeList(ELEMENTS_PROPERTY);

	/**
	 * The right operand; lazily initialized; defaults to an unspecified,
	 * but legal, simple type.
	 */
	private volatile Type rightOperand;

	/**
	 * Creates a new AST node for an instanceof expression owned by the given
	 * AST. By default, the node has unspecified (but legal) operator,
	 * left and right operands.
	 *
	 * @param ast the AST that is to own this node
	 */
	InstanceofExpression(AST ast) {
		super(ast);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == RIGHT_OPERAND_PROPERTY) {
			if (get) {
				return getRightOperand();
			} else {
				setRightOperand((Type) child);
				return null;
			}
		}
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
		return INSTANCEOF_EXPRESSION;
	}

	@Override
	ASTNode clone0(AST target) {
		InstanceofExpression result = new InstanceofExpression(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.elements().addAll(ASTNode.copySubtrees(target, elements()));
		result.setRightOperand((Type) getRightOperand().clone(target));
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
			acceptChild(visitor, getRightOperand());
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the left operand of this instanceof expression.
	 *
	 * @return the left operand node
	 * @deprecated replaced by elements.
	 */
	public Expression getLeftOperand() {
		if (this.leftOperand  == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.leftOperand == null) {
					preLazyInit();
					this.leftOperand = postLazyInit(new SimpleName(this.ast), LEFT_OPERAND_PROPERTY);
				}
			}
		}
		return this.leftOperand;
	}

	/**
	 * Sets the left operand of this instanceof expression.
	 *
	 * @param expression the left operand node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated replaced by elements.
	 */
	public void setLeftOperand(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.leftOperand;
		preReplaceChild(oldChild, expression, LEFT_OPERAND_PROPERTY);
		this.leftOperand = expression;
		postReplaceChild(oldChild, expression, LEFT_OPERAND_PROPERTY);
	}

	/**
	 * @since 3.39
	 */
	public List elements() {
		return this.elements;
	}

	/**
	 * Returns the right operand of this instanceof expression.
	 *
	 * @return the right operand node
	 */
	public Type getRightOperand() {
		if (this.rightOperand  == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.rightOperand == null) {
					preLazyInit();
					this.rightOperand = postLazyInit(new SimpleType(this.ast), RIGHT_OPERAND_PROPERTY);
				}
			}
		}
		return this.rightOperand;
	}

	/**
	 * Sets the right operand of this instanceof expression.
	 *
	 * @param referenceType the right operand node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setRightOperand(Type referenceType) {
		if (referenceType == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.rightOperand;
		preReplaceChild(oldChild, referenceType, RIGHT_OPERAND_PROPERTY);
		this.rightOperand = referenceType;
		postReplaceChild(oldChild, referenceType, RIGHT_OPERAND_PROPERTY);
	}

	@Override
	int memSize() {
		// treat Operator as free
		return BASE_NODE_SIZE + 2 * 4;
	}

	@Override
	int treeSize() {
		return
			memSize()
			+ (this.elements == null ? 0 : this.elements.listSize())
			+ (this.rightOperand == null ? 0 : getRightOperand().treeSize());
	}
}
