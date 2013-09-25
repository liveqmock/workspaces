package jjwu.xdeveloper.app.callback.chapter1;

public interface EventButton extends Event{
	
	/**
	 * 确认事件
	 */
	public void confrimEvent();
	
	/**
	 * 取消事件
	 */
	public void cancelEvent();
	
	/**
	 * 缩小事件
	 */
	public void minimizeEvent();
	
	/**
	 * 放大事件
	 */
	public void zoomlEvent();

}
