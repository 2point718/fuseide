/*******************************************************************************
 * Copyright (c) 2014 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.fusesource.ide.server.karaf.core.server;

import org.eclipse.core.runtime.CoreException;
import org.fusesource.ide.server.karaf.core.server.subsystems.Karaf3xStartupLaunchConfigurator;
import org.jboss.ide.eclipse.as.core.server.ILaunchConfigConfigurator;

/**
 * @author lhein
 */
public class Karaf3xServerDelegate extends KarafServerDelegate implements
		IKarafServerDelegateWorkingCopy {

	/**
	 * returns the launch configurator for the server
	 * @return	the launch configurator
	 * @throws CoreException
	 */
	public ILaunchConfigConfigurator getLaunchConfigurator() throws CoreException {
		return new Karaf3xStartupLaunchConfigurator(getServer());
	}
}
