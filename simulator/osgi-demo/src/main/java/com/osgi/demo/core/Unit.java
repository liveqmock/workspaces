package com.osgi.demo.core;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iwgame.xnode.XNodeUnitSupport;

/**
 * 
 * @ClassName: Unit
 * @Description: XNode应用单元的启动器（Activator）
 * @author MAVEN
 *
 */
public class Unit extends XNodeUnitSupport {

	private final Logger logger=LoggerFactory.getLogger(Unit.class);

	@Override
	protected ClassLoader getUnitClassLoader() {
		return Unit.class.getClassLoader();
	}

	/* (non-Javadoc)
	 * @see com.iwgame.xnode.XNodeUnitSupport#onStart(org.osgi.framework.BundleContext)
	 */
	@Override
	protected void onStart(BundleContext context) {
		super.onStart(context);
		System.out.println("Start");
	}

	/* (non-Javadoc)
	 * @see com.iwgame.xnode.XNodeUnitSupport#onStop(org.osgi.framework.BundleContext)
	 */
	@Override
	protected void onStop(BundleContext context) {
		super.onStop(context);
	}




}
