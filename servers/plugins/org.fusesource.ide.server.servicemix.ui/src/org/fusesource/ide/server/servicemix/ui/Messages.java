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

package org.fusesource.ide.server.servicemix.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.fusesource.ide.server.servicemix.ui.l10n.messages"; //$NON-NLS-1$
	
	public static String AbstractKarafRuntimeComposite_bin_karaf;
	public static String AbstractKarafRuntimeComposite_bin_karaf_bat;
	public static String AbstractKarafRuntimeComposite_invalid_dir;
	public static String AbstractKarafRuntimeComposite_no_dir;
	public static String AbstractKarafRuntimeComposite_not_a_dir;
	
	public static String ServiceMixServerPorpertiesComposite_wizard_desc;
	public static String ServiceMixServerPorpertiesComposite_wizard_title;
	public static String ServiceMixRuntimeComposite_wizard_desc;
	public static String ServiceMixRuntimeComposite_wizard_tite;
	public static String AbstractKarafRuntimeComposite_wizard_help_msg;
	public static String ServiceMix4xRuntimeComposite_bin_servicemix;
	public static String ServiceMix4xRuntimeComposite_bin_servicemix_bat;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}