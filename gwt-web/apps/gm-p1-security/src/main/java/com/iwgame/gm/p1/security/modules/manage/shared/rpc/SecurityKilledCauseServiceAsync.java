package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

public interface SecurityKilledCauseServiceAsync {

	void loadKilledCauseData(String productId,
			BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void addKilledCause(String productId, KilledCauseConfig killedCause,
			AsyncCallback<Boolean> callback);

	void getKilledCauseById(String productId, Integer id,
			AsyncCallback<KilledCauseConfig> callback);

	void updateKilledCause(String productId, KilledCauseConfig killedCause,
			AsyncCallback<Boolean> callback);

	void deleteKilledCause(String productId, List<Integer> ids,
			AsyncCallback<Boolean> callback);

}
