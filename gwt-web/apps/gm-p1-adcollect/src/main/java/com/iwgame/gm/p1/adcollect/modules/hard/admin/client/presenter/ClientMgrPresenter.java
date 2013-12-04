/**      
 * ClientMgrpresenter.java Create on 2012-11-12 上午9:43:07    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * @ClassName: ClientMgrpresenter
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-12 上午9:43:07
 * @Version 1.0
 * 
 */
public interface ClientMgrPresenter extends SchemaGridViewPresenter {

    public void getMedia(int type,
	    AsyncCallback<List<DropDownListData>> callback);

    public void addClent(String mediaId, String version, String code,
	    AsyncCallback<Integer> callback);

    public void updateClent(String mediaId, String version, String code,
	    int id, AsyncCallback<Integer> callback);

}
