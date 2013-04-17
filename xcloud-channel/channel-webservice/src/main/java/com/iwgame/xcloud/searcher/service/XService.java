package com.iwgame.xcloud.searcher.service;



import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.soap.SOAPBinding;

/**
 * @ClassName:    XService 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-7-27下午04:01:37
 * @Version:      1.0
 */
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public interface XService {

    /**
     * 能获得奖励的名单
     * @param array $params 传进来的参数
     * @example array('gameid' => 1, 'actid' => 2, 'maxid' => 123, 'limit' => 100)
     * actid可以不传
     * @example return array([AcceptId], [UserName], [RoleName], [type], [AreaId], [value], [CreateTime])
     * @return array
     */
	@WebMethod
	 public String winners(@WebParam(name="gameid",mode=Mode.IN) String gameid,
			 						@WebParam(name="actid",mode=Mode.IN) String actid,
			 						@WebParam(name="maxid",mode=Mode.IN) String maxid,
			 						@WebParam(name="limit",mode=Mode.IN) String limit,
			 						@WebParam(name="sign",mode=Mode.IN) String sign);   
}
