/*******************************************************************************
 * Copyright (c) 2014, 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.ui.utils;

import melnorme.util.swt.jface.ColorManager;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.MessageConsole;

public class ProcessMessageConsole extends MessageConsole {
	
	public final IOConsoleOutputStream stdOut;
	public final IOConsoleOutputStream stdErr;
	
	public ProcessMessageConsole(String name, ImageDescriptor imageDescriptor) {
		super(name, imageDescriptor);
		
		stdOut = newOutputStream();
		stdErr = newOutputStream();
		stdErr.setActivateOnWrite(true);
		
		// BM: it's not clear to me if a Color can be created outside UI thread, so do asyncExec
		// I would think one cant, but some Platform code (ProcessConsole) does freely create Color instances
		// on the UI thread, so maybe the asyncExec is not necessary.
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				initOuputStreamColors();
			}
		});
	}
	
	protected void initOuputStreamColors() {
	}
	
	protected ISharedTextColors getColorManager() {
		return ColorManager.getDefault();
	}
	
}