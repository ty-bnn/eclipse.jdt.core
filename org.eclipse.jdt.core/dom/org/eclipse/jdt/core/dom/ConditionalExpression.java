/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
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
 * Conditional expression AST node type.
 *
 * <pre>
 * ConditionalExpression:
 *    Expression <b>?</b> Expression <b>:</b> Expression
 * </pre>
 *
 * @since 2.0
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@SuppressWarnings("rawtypes")
public class ConditionalExpression extends Expression {

	/**
	 * The "expression" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 * @deprecated
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY =
		new ChildPropertyDescriptor(ConditionalExpression.class, "expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor EXPRESSION_ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(ConditionalExpression.class, "expressionElements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "thenExpression" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 * @deprecated
	 */
	public static final ChildPropertyDescriptor THEN_EXPRESSION_PROPERTY =
		new ChildPropertyDescriptor(ConditionalExpression.class, "thenExpression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor THEN_ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(ConditionalExpression.class, "thenElements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "elseExpression" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 * @deprecated
	 */
	public static final ChildPropertyDescriptor ELSE_EXPRESSION_PROPERTY =
		new ChildPropertyDescriptor(ConditionalExpression.class, "elseExpression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor ELSE_ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(ConditionalExpression.class, "elseElements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List properyList = new ArrayList(4);
		createPropertyList(ConditionalExpression.class, properyList);
		addProperty(EXPRESSION_ELEMENTS_PROPERTY, properyList);
		addProperty(THEN_ELEMENTS_PROPERTY, properyList);
		addProperty(ELSE_ELEMENTS_PROPERTY, properyList);
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
	 * The condition expression; lazily initialized; defaults to an unspecified,
	 * but legal, expression.
	 * @deprecated
	 */
	private volatile Expression conditionExpression;

	/**
	 * @since 3.39
	 */
	private ASTNode.NodeList expressionElements = new ASTNode.NodeList(EXPRESSION_ELEMENTS_PROPERTY);

	/**
	 * The "then" expression; lazily initialized; defaults to an unspecified,
	 * but legal, expression.
	 * @deprecated
	 */
	private volatile Expression thenExpression;

	/**
	 * @since 3.39
	 */
	private ASTNode.NodeList thenElements = new ASTNode.NodeList(EXPRESSION_ELEMENTS_PROPERTY);

	/**
	 * The "else" expression; lazily initialized; defaults to an unspecified,
	 * but legal, expression.
	 * @deprecated
	 */
	private volatile Expression elseExpression;

	/**
	 * @since 3.39
	 */
	private ASTNode.NodeList elseElements = new ASTNode.NodeList(EXPRESSION_ELEMENTS_PROPERTY);

	/**
	 * Creates a new unparented conditional expression node owned by the given
	 * AST. By default, the condition, "then", and "else" expresssions are
	 * unspecified, but legal.
	 * <p>
	 * N.B. This constructor is package-private.
	 * </p>
	 *
	 * @param ast the AST that is to own this node
	 */
	ConditionalExpression(AST ast) {
		super(ast);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == EXPRESSION_PROPERTY) {
			if (get) {
				return getExpression();
			} else {
				setExpression((Expression) child);
				return null;
			}
		}
		if (property == THEN_EXPRESSION_PROPERTY) {
			if (get) {
				return getThenExpression();
			} else {
				setThenExpression((Expression) child);
				return null;
			}
		}
		if (property == ELSE_EXPRESSION_PROPERTY) {
			if (get) {
				return getElseExpression();
			} else {
				setElseExpression((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == EXPRESSION_ELEMENTS_PROPERTY) {
			return expressionElements();
		}
		if (property == THEN_ELEMENTS_PROPERTY) {
			return thenElements();
		}
		if (property == ELSE_ELEMENTS_PROPERTY) {
			return elseElements();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final int getNodeType0() {
		return CONDITIONAL_EXPRESSION;
	}

	@Override
	ASTNode clone0(AST target) {
		ConditionalExpression result = new ConditionalExpression(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.expressionElements().addAll(ASTNode.copySubtrees(target, expressionElements()));
		result.thenElements().addAll(ASTNode.copySubtrees(target, thenElements()));
		result.elseElements().addAll(ASTNode.copySubtrees(target, elseElements()));
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
			acceptChildren(visitor, this.expressionElements);
			acceptChildren(visitor, this.thenElements);
			acceptChildren(visitor, this.elseElements);
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the condition of this conditional expression.
	 *
	 * @return the condition node
	 * @deprecated
	 */
	public Expression getExpression() {
		if (this.conditionExpression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.conditionExpression == null) {
					preLazyInit();
					this.conditionExpression = postLazyInit(new SimpleName(this.ast), EXPRESSION_PROPERTY);
				}
			}
		}
		return this.conditionExpression;
	}

	/**
	 * Sets the condition of this conditional expression.
	 *
	 * @param expression the condition node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated
	 */
	public void setExpression(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.conditionExpression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.conditionExpression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	/**
	 * @since 3.39
	 */
	public List expressionElements() {
		return this.expressionElements;
	}

	/**
	 * Returns the "then" part of this conditional expression.
	 *
	 * @return the "then" expression node
	 * @deprecated
	 */
	public Expression getThenExpression() {
		if (this.thenExpression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.thenExpression == null) {
					preLazyInit();
					this.thenExpression = postLazyInit(new SimpleName(this.ast), THEN_EXPRESSION_PROPERTY);
				}
			}
		}
		return this.thenExpression;
	}

	/**
	 * Sets the "then" part of this conditional expression.
	 *
	 * @param expression the "then" expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated
	 */
	public void setThenExpression(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.thenExpression;
		preReplaceChild(oldChild, expression, THEN_EXPRESSION_PROPERTY);
		this.thenExpression = expression;
		postReplaceChild(oldChild, expression, THEN_EXPRESSION_PROPERTY);
	}

	/**
	 * @since 3.39
	 */
	public List thenElements() {
		return this.thenElements;
	}

	/**
	 * Returns the "else" part of this conditional expression.
	 *
	 * @return the "else" expression node
	 * @deprecated
	 */
	public Expression getElseExpression() {
		if (this.elseExpression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.elseExpression == null) {
					preLazyInit();
					this.elseExpression = postLazyInit(new SimpleName(this.ast), ELSE_EXPRESSION_PROPERTY);
				}
			}
		}
		return this.elseExpression;
	}

	/**
	 * Sets the "else" part of this conditional expression.
	 *
	 * @param expression the "else" expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated
	 */
	public void setElseExpression(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.elseExpression;
		preReplaceChild(oldChild, expression, ELSE_EXPRESSION_PROPERTY);
		this.elseExpression = expression;
		postReplaceChild(oldChild, expression, ELSE_EXPRESSION_PROPERTY);
	}

	/**
	 * @since 3.39
	 */
	public List elseElements() {
		return this.elseElements;
	}

	@Override
	int memSize() {
		// treat Code as free
		return BASE_NODE_SIZE + 3 * 4;
	}

	@Override
	int treeSize() {
		return
			memSize()
			+ (this.expressionElements == null ? 0 : this.expressionElements.listSize())
			+ (this.thenElements == null ? 0 : this.thenElements.listSize())
			+ (this.elseElements == null ? 0 : this.elseElements.listSize());
	}
}
