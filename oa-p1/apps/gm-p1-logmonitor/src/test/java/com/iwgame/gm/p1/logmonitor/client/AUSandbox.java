package com.iwgame.gm.p1.logmonitor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xmvp.client.autest.AUTestInjector;
import com.iwgame.xmvp.client.place.ModulePlace;
import com.iwgame.xportal.common.client.ApplicationContext;
import com.iwgame.xportal.common.shared.model.Context;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AUSandbox implements EntryPoint {

	final private AUTestInjector injector = GWT.create(AUTestInjector.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		//
		final ModulePlace defaultPlace = new ModulePlace("LogMonitor", "game", "action");

		ApplicationContext.init(new AsyncCallbackEx<Context>() {

			@Override
			public void onSuccess(final Context result) {

				Product product = new Product();
				product.setName("P-P1");
				product.setTitle("蜀门");
				ApplicationContext.addProduct(product);
				ApplicationContext.setCurrentProduct("P-P1");

				injector.getXMVPApp().run(defaultPlace);

			}
		});
	}
}
