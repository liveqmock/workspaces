package com.iwgame.xcloud.searcher.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.iwgame.xcloud.searcher.model.ParamBean;

/**
 * @ClassName: AccountSearchService
 * @Description: TODO(...)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-7-27下午04:01:37
 * @Version: 1.0
 */
@Path("/")
@Produces("appication/json")
public interface OASecuritySearchService {

	@GET
	@Path("/{pid}/getOAsecurityAccount")
	@Produces("application/json")
	public String getOAsecurityAccount(
			@PathParam("pid") String pid, 
			@QueryParam("username") String username, 
			@QueryParam("") ParamBean queryParam);

}
