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
package org.fusesource.ide.camel.editor.properties;

import org.eclipse.ui.views.properties.tabbed.ITypeMapper;
import org.fusesource.ide.camel.editor.utils.NodeUtils;
import org.fusesource.ide.camel.model.service.core.model.CamelModelElement;

/**
 * @author lhein
 */
public class CamelTypeMapper implements ITypeMapper {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITypeMapper#mapType(java.lang.Object)
	 */
	@Override
	public Class mapType(Object object) {
		CamelModelElement node = NodeUtils.toCamelElement(object);
		if (node != null) {
			return CamelModelElement.class;
		}
		return object.getClass();
	}
}
