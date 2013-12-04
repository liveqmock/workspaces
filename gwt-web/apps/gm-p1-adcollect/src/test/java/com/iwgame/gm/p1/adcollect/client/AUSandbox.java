package com.iwgame.gm.p1.adcollect.client;

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

		// final ModulePlace defaultPlace = new ModulePlace("adcollect",
		// "reports", "daylist");
		// final ModulePlace defaultPlace = new ModulePlace("adcollect",
		// "reports", "list");
		// ModulePlace defaultPlace = new ModulePlace("adcollect", "keys",
		// "input");
		// final ModulePlace defaultPlace = new ModulePlace("adcollect", "keys",
		// "batch");
		final ModulePlace defaultPlace = new ModulePlace("adcollect", "report", "report");
		ApplicationContext.init(new AsyncCallbackEx<Context>() {

			@Override
			public void onSuccess(final Context context) {

				final Product product = new Product();
				product.setName("P-P1.5");
				product.setTitle("醉逍遥");
				ApplicationContext.addProduct(product);
				ApplicationContext.setCurrentProduct("P-P1.5");

				injector.getXMVPApp().run(defaultPlace);

			}
		});
	}
}
