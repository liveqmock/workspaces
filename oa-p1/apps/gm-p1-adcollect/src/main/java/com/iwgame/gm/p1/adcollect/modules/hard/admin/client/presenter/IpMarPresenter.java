/**      
 * IpMarPresenter.java Create on 2012-11-8 下午12:04:31    
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
 * @ClassName: IpMarPresenter
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午12:04:31
 * @Version 1.0
 * 
 */
public interface IpMarPresenter  extends SchemaGridViewPresenter {

    public void getMedia(int type, AsyncCallback<List<DropDownListData>> callback);
    
    public void addNetbarIp(String mediaId, String ip, AsyncCallback<Integer> callback);
    
    public void delNetbarIps(String ids, AsyncCallback<Integer> callback);
    
    public void chenkbatchAddNetbarIpAuthority(AsyncCallback<Void> callback);
    
}
