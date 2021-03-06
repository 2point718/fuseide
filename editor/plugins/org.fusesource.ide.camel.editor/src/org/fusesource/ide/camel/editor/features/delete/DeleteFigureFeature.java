/******************************************************************************* 
 * Copyright (c) 2015 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.fusesource.ide.camel.editor.features.delete;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.fusesource.ide.camel.editor.internal.CamelEditorUIActivator;
import org.fusesource.ide.camel.model.service.core.model.CamelContextElement;
import org.fusesource.ide.camel.model.service.core.model.CamelElementConnection;
import org.fusesource.ide.camel.model.service.core.model.CamelModelElement;

/**
 * @author lhein
 */
public class DeleteFigureFeature extends DefaultDeleteFeature {

	/**
	 * @param fp
	 */
	public DeleteFigureFeature(IFeatureProvider fp) {
		super(fp);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.features.DefaultDeleteFeature#preDelete(org.eclipse.graphiti.features.context.IDeleteContext)
	 */
	@Override
	public void preDelete(IDeleteContext context) {
		super.preDelete(context);

		// now delete the BO from our model
		PictogramElement pe = context.getPictogramElement();
		Object[] businessObjectsForPictogramElement = getAllBusinessObjectsForPictogramElement(pe);
		if (businessObjectsForPictogramElement != null && businessObjectsForPictogramElement.length > 0) {
			Object bo = businessObjectsForPictogramElement[0];
			if (bo instanceof CamelElementConnection) {
				deleteFlowFromModel((CamelElementConnection) bo);
			} else if (bo instanceof CamelModelElement) {
				deleteBOFromModel((CamelModelElement)bo);
			} else {
				CamelEditorUIActivator.pluginLog().logWarning("Cannot figure out Node or Flow from BO: " + bo);
			}
		}
	}

	private void deleteBOFromModel(CamelModelElement nodeToRemove) {
		// we can't remove null objects or the root of the routes
		if (nodeToRemove == null || nodeToRemove instanceof CamelContextElement) return;

		// lets remove all connections
		if (nodeToRemove.getParent() != null) nodeToRemove.getParent().removeChildElement(nodeToRemove);
		if (nodeToRemove.getInputElement() != null) nodeToRemove.getInputElement().setOutputElement(null);
		if (nodeToRemove.getOutputElement() != null) nodeToRemove.getOutputElement().setInputElement(null);
	}

	private void deleteFlowFromModel(CamelElementConnection bo) {
		bo.disconnect();
	}
}
