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

package org.fusesource.ide.server.servicemix.ui.runtime.smx4x;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.server.ui.wizard.IWizardHandle;
import org.fusesource.ide.server.karaf.ui.runtime.AbstractKarafRuntimeComposite;
import org.fusesource.ide.server.karaf.ui.runtime.KarafWizardDataModel;
import org.fusesource.ide.server.karaf.ui.runtime.v2x.KarafRuntimeWizardFragment2x;

/**
 * @author lhein
 */
public class ServiceMixRuntimeWizardFragment4x extends
		KarafRuntimeWizardFragment2x {
	
	/* (non-Javadoc)
	 * @see org.fusesource.ide.server.servicemix.ui.runtime.AbstractKarafRuntimeWizardFragment#getRuntimeComposite(org.eclipse.swt.widgets.Composite, org.eclipse.wst.server.ui.wizard.IWizardHandle, org.fusesource.ide.server.servicemix.ui.runtime.KarafWizardDataModel)
	 */
	@Override
	protected AbstractKarafRuntimeComposite getRuntimeComposite(
			Composite parent, IWizardHandle handle, KarafWizardDataModel model) {
		return new ServiceMixRuntimeComposite4x(parent, handle, model);
	}
}