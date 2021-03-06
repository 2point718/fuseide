	/*******************************************************************************
 * Copyright (c) 2013 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.fusesource.ide.jmx.commons.views.diagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.fusesource.ide.camel.model.service.core.model.CamelModelElement;
import org.fusesource.ide.camel.model.service.core.model.CamelRouteElement;
import org.fusesource.ide.jmx.commons.tree.ConnectedNode;
import org.fusesource.ide.jmx.commons.tree.GraphableNode;
import org.fusesource.ide.jmx.commons.tree.GraphableNodeConnected;
import org.jboss.tools.jmx.core.tree.Node;

public class NodeGraphContentProvider implements  IStructuredContentProvider, IGraphEntityContentProvider {

	private static final Object[] EMPTY = {};

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IGraphContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object input) {
		if (input instanceof CamelModelElement) {
			CamelModelElement node = (CamelModelElement) input;

			Set<CamelModelElement> set = new HashSet<CamelModelElement>();
			CamelRouteElement parent;
			if (node instanceof CamelRouteElement) {
				parent = (CamelRouteElement) node;
			} else {
				parent = getRoute(node);
				set.add(node);
			}
			if (parent == null) {
				set.add(node.getOutputElement());
			} else {
				getAllChildren(parent.getChildElements(), set);
			}
			return set.toArray();
		} else {
			List<Object> answer = new ArrayList<Object>();
			if (input instanceof GraphableNode) {
				GraphableNode node = (GraphableNode) input;
				answer.addAll(node.getChildrenGraph());
			} else if (input != null) {
				answer.add(input);
				if (input instanceof Node) {
					Node aNode = (Node) input;
					answer.addAll(aNode.getChildrenList());
				}
			} else {
				// will prevent the grayish box in the diagram view
				return null;
			}
			return answer.toArray();
		}
	}
	
	private CamelRouteElement getRoute(CamelModelElement e) {
		CamelModelElement cme = e;
		while (cme != null && cme instanceof CamelRouteElement == false) {
			cme = cme.getParent();
		}
		if (cme != null && cme instanceof CamelRouteElement) {
			return (CamelRouteElement)cme;
		}
		return null;
	}

	private void getAllOutputs(CamelModelElement elem, Set<CamelModelElement> set) {
		if (elem.getOutputElement() != null) set.add(elem.getOutputElement());
	}
	
	private void getAllChildren(List<CamelModelElement> elems, Set<CamelModelElement> set) {
		for (CamelModelElement e : elems) {
			set.add(e);
			getAllOutputs(e, set);
			if (e.getChildElements().isEmpty() == false) {
				getAllChildren(e.getChildElements(), set);
			}
		}
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if (entity instanceof GraphableNodeConnected) {
			GraphableNodeConnected gn = (GraphableNodeConnected) entity;
			return gn.getGraphConnectedTo().toArray();
		} else if (entity instanceof CamelRouteElement) {
			CamelRouteElement route = (CamelRouteElement) entity;
			return route.getInputs().toArray();
		} else if (entity instanceof CamelModelElement) {
			CamelModelElement node = (CamelModelElement) entity;
			return new Object[] {node.getOutputElement()};
		} else if (entity instanceof ConnectedNode) {
			ConnectedNode node = (ConnectedNode) entity;
			return node.getConnectedTo().toArray();
		} else if (entity instanceof Node) {
			Node aNode = (Node) entity;
			return aNode.getChildren();
		}
		return EMPTY;
	}
}
