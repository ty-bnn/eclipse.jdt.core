/*******************************************************************************
 * Copyright (c) 2013, 2014 IBM Corporation and others.
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
 * Expression method reference AST node type (added in JLS8 API).
 * <pre>
 * ExpressionMethodReference:
 *     Expression <b>::</b>
 *         [ <b>&lt;</b> Type { <b>,</b> Type } <b>&gt;</b> ]
 *         Identifier
 * </pre>
 *
 * @since 3.10
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ExpressionMethodReference extends MethodReference {

	/**
	 * The "expression" structural property of this node type (child type: {@link Expression}).
	 * @deprecated replaced by ELEMENTS_PROPERTY.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY =
		new ChildPropertyDescriptor(ExpressionMethodReference.class, "expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * @since 3.39
	 */
	public static final ChildListPropertyDescriptor ELEMENTS_PROPERTY =
			new ChildListPropertyDescriptor(ExpressionMethodReference.class, "elements", ASTNode.class, NO_CYCLE_RISK); //$NON-NLS-1$


	/**
	 * The "typeArguments" structural property of this node type (element type: {@link Type})
	 */
	public static final ChildListPropertyDescriptor TYPE_ARGUMENTS_PROPERTY =
		internalTypeArgumentsFactory(ExpressionMethodReference.class);

	/**
	 * The "name" structural property of this node type (child type: {@link SimpleName}).
	 */
	public static final ChildPropertyDescriptor NAME_PROPERTY =
		new ChildPropertyDescriptor(ExpressionMethodReference.class, "name", SimpleName.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS_8_0;

	static {
		List propertyList = new ArrayList(4);
		createPropertyList(ExpressionMethodReference.class, propertyList);
		addProperty(ELEMENTS_PROPERTY, propertyList);
		addProperty(TYPE_ARGUMENTS_PROPERTY, propertyList);
		addProperty(NAME_PROPERTY, propertyList);
		PROPERTY_DESCRIPTORS_8_0 = reapPropertyList(propertyList);
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 *
	 * @param apiLevel the API level; one of the AST.JLS* constants
	 * @return a list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor})
	 */
	public static List propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS_8_0;
	}

	/**
	 * The expression; lazily initialized; defaults to an unspecified,
	 * legal expression.
	 * @deprecated replaced by elements.
	 */
	private volatile Expression expression;

	/**
	 * @since 3.39
	 */
	private ASTNode.NodeList elements = new ASTNode.NodeList(ELEMENTS_PROPERTY);

	/**
	 * The method name; lazily initialized; defaults to an unspecified,
	 * legal Java method name.
	 */
	private volatile SimpleName methodName;

	/**
	 * Creates a new AST node for an ExpressionMethodReference declaration owned
	 * by the given AST.
	 * <p>
	 * N.B. This constructor is package-private; all subclasses must be
	 * declared in the same package; clients are unable to declare
	 * additional subclasses.
	 * </p>
	 *
	 * @param ast the AST that is to own this node
	 */
	ExpressionMethodReference(AST ast) {
		super(ast);
		unsupportedIn2_3_4();
	}

	@Override
	final ChildListPropertyDescriptor internalTypeArgumentsProperty() {
		return TYPE_ARGUMENTS_PROPERTY;
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == NAME_PROPERTY) {
			if (get) {
				return getName();
			} else {
				setName((SimpleName) child);
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
		if (property == TYPE_ARGUMENTS_PROPERTY) {
			return typeArguments();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final int getNodeType0() {
		return EXPRESSION_METHOD_REFERENCE;
	}

	@Override
	ASTNode clone0(AST target) {
		ExpressionMethodReference result = new ExpressionMethodReference(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.elements().addAll(ASTNode.copySubtrees(target, elements()));
		result.typeArguments().addAll(ASTNode.copySubtrees(target, typeArguments()));
		result.setName((SimpleName) getName().clone(target));
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
			acceptChild(visitor, getExpression());
			acceptChildren(visitor, this.elements);
			acceptChild(visitor, getName());
		}
		visitor.endVisit(this);
	}

	/**
	 * Returns the expression of this expression method reference expression
	 *
	 * @return the expression node
	 * @deprecated replaced by elements.
	 */
	public Expression getExpression() {
		if (this.expression == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.expression == null) {
					preLazyInit();
					this.expression = postLazyInit(new SimpleName(this.ast), EXPRESSION_PROPERTY);
				}
			}
		}
		return this.expression;
	}

	/**
	 * Sets the expression of this expression method reference.
	 *
	 * @param expression the expression node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @deprecated replaced by elements.
	 */
	public void setExpression(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.expression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.expression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	/**
	 * @since 3.39
	 */
	public List elements() {
		return this.elements;
	}

	/**
	 * Returns the live ordered list of type arguments of this expression method reference
	 *
	 * @return the live list of type arguments
	 *    (element type: {@link Type})
	 */
	@Override
	public List typeArguments() {
		return this.typeArguments;
	}

	/**
	 * Returns the name of the method referenced in this expression.
	 *
	 * @return the method name node
	 */
	public SimpleName getName() {
		if (this.methodName == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.methodName == null) {
					preLazyInit();
					this.methodName = postLazyInit(new SimpleName(this.ast), NAME_PROPERTY);
				}
			}
		}
		return this.methodName;
	}

	/**
	 * Sets the name of the method referenced in this expression to the
	 * given name.
	 *
	 * @param name the new method name
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * </ul>
	 */
	public void setName(SimpleName name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.methodName;
		preReplaceChild(oldChild, name, NAME_PROPERTY);
		this.methodName = name;
		postReplaceChild(oldChild, name, NAME_PROPERTY);
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
			+ (this.elements == null ? 0 : this.elements.listSize())
			+ (this.typeArguments == null ? 0 : this.typeArguments.listSize())
			+ (this.methodName == null ? 0 : getName().treeSize());
	}
}