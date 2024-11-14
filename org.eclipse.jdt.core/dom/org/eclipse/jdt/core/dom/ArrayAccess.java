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
 * Array access expression AST node type.
 *
 * <pre>
 * ArrayAccess:
 *    Expression <b>[</b> Expression <b>]</b>
 * </pre>
 *
 * @since 3.40
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@SuppressWarnings("rawtypes")
public class ArrayAccess extends Expression {

	/**
	 * The "array" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 */
	public static final ChildPropertyDescriptor ARRAY_PROPERTY =
		new ChildPropertyDescriptor(ArrayAccess.class, "array", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "index" structural property of this node type (child type: {@link Expression}).
	 * @since 3.0
	 * @deprecated replaced by INDEXES_PROPERTY.
	 */
	public static final ChildPropertyDescriptor INDEX_PROPERTY =
		new ChildPropertyDescriptor(ArrayAccess.class, "index", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "index" structural property of this node type (element type: {@link Expression}).
	 * @since 3.40
	 */
	public static final ChildListPropertyDescriptor INDEXES_PROPERTY =
		new ChildListPropertyDescriptor(ArrayAccess.class, "indexes", Expression.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List properyList = new ArrayList(3);
		createPropertyList(ArrayAccess.class, properyList);
		addProperty(ARRAY_PROPERTY, properyList);
		addProperty(INDEXES_PROPERTY, properyList);
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
	 * The array expression; lazily initialized; defaults to an unspecified,
	 * but legal, expression.
	 */
	private volatile Expression arrayExpression;

	/**
	 * The index expression; lazily initialized; defaults to an unspecified,
	 * but legal, expression.
	 * @deprecated replaces by indexeExpressions.
	 */
	private volatile Expression indexExpression;


	private ASTNode.NodeList indexExpressions = new ASTNode.NodeList(INDEXES_PROPERTY);

	/**
	 * Creates a new unparented array access expression node owned by the given
	 * AST. By default, the array and index expresssions are unspecified,
	 * but legal.
	 * <p>
	 * N.B. This constructor is package-private.
	 * </p>
	 *
	 * @param ast the AST that is to own this node
	 */
	ArrayAccess(AST ast) {
		super(ast);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == ARRAY_PROPERTY) {
			if (get) {
				return getArray();
			} else {
				setArray((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == INDEXES_PROPERTY) {
			return indexExpressions();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final int getNodeType0() {
		return ARRAY_ACCESS;
	}

	@Override
	ASTNode clone0(AST target) {
		ArrayAccess result = new ArrayAccess(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.setArray((Expression) getArray().clone(target));
		result.indexExpressions().addAll(ASTNode.copySubtrees(target, indexExpressions()));
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
			acceptChild(visitor, getArray());
			acceptChildren(visitor, this.indexExpressions);
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the array expression of this array access expression.
	 *
	 * @return the array expression node
	 */
	public Expression getArray() {
		if (this.arrayExpression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.arrayExpression == null) {
					preLazyInit();
					this.arrayExpression = postLazyInit(new SimpleName(this.ast), ARRAY_PROPERTY);
				}
			}
		}
		return this.arrayExpression;
	}

	/**
	 * Sets the array expression of this array access expression.
	 *
	 * @param expression the array expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 */
	public void setArray(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		// an ArrayAccess may occur inside an Expression
		// must check cycles
		ASTNode oldChild = this.arrayExpression;
		preReplaceChild(oldChild, expression, ARRAY_PROPERTY);
		this.arrayExpression = expression;
		postReplaceChild(oldChild, expression, ARRAY_PROPERTY);
	}

	/**
	 * Returns the index expression of this array access expression.
	 *
	 * @return the index expression node
	 * @deprecated
	 */
	public Expression getIndex() {
		if (this.indexExpression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.indexExpression == null) {
					preLazyInit();
					this.indexExpression = postLazyInit(new SimpleName(this.ast), INDEX_PROPERTY);
				}
			}
		}
		return this.indexExpression;
	}

	/**
	 * Sets the index expression of this array access expression.
	 *
	 * @param expression the index expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated
	 */
	public void setIndex(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		// an ArrayAccess may occur inside an Expression
		// must check cycles
		ASTNode oldChild = this.indexExpression;
		preReplaceChild(oldChild, expression, INDEX_PROPERTY);
		this.indexExpression = expression;
		postReplaceChild(oldChild, expression, INDEX_PROPERTY);
	}

	/**
	 * Returns the live ordered list of index expressions.
	 *
	 * @return the live list of index expressions
	 *    (element type: {@link Expression})
	 * @since 3.40
	 */
	public List indexExpressions() {
		return this.indexExpressions;
	}

	@Override
	int memSize() {
		return BASE_NODE_SIZE + 2 * 4;
	}

	@Override
	int treeSize() {
		return
			memSize()
			+ (this.arrayExpression == null ? 0 : getArray().treeSize())
			+ (this.indexExpressions == null ? 0 : this.indexExpressions.listSize());
	}
}

