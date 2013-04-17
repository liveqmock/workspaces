package com.iwgame.account.kill.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xmvp.client.autest.AUTestInjector;
import com.iwgame.xmvp.client.place.ModulePlace;
import com.iwgame.xportal.common.client.ApplicationContext;
import com.iwgame.xportal.common.shared.model.Context;
import com.iwgame.xportal.common.shared.model.Product;
import com.iwgame.xportal.common.shared.model.User;

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
//    	final ModulePlace defaultPlace = new ModulePlace("kill","policy", "list");
    	final ModulePlace defaultPlace = new ModulePlace("kill","logs", "auto");

		ApplicationContext.init(new AsyncCallbackEx<Context>() {

			@Override
			public void onSuccess(final Context context) {

				Product product = new Product();
				product.setName("P-P1");
				product.setTitle("蜀门");
				ApplicationContext.addProduct(product);
				ApplicationContext.setCurrentProduct("P-P1");
				
				User user = new User();
				user.setUsername("maiqi");
				user.setFullname("麦琪");
				user.setId(1L);
				// user.setReference("MEDIA");
				// user.setReferenceId(1);
				context.setCurrentUser(user);

				injector.getXMVPApp().run(defaultPlace);

			}
		});
    }
}
