/****************************************************************
 * 文件名 : d.java
 * 日期 : 2013-6-7
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-7 上午11:45:30
 * @版本: v1.0.0
 */
public enum LogAction {

	INSERT(0,"增加"),
	DELETE(1,"删除"),
	UPDATE(2,"更新"),
	QUERY(3,"查询"),
	EXPORT(4,"导出数据"),
	IMPORT(5,"导入数据"),
	HANDLE(6,"处理数据"),
	KILL(7,"帐号封杀"),
	UNKILL(8,"帐号解封"),
	ADDSAFE(9,"添加安全模式"),
	UNSAFE(10,"接触安全模式");


	private int index;
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param name
	 * @param index
	 */
	private LogAction(int index,String name) {
		this.name = name;
		this.index = index;
	}

}
