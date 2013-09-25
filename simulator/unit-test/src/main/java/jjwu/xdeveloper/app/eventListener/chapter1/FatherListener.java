package jjwu.xdeveloper.app.eventListener.chapter1;

import java.util.EventListener;


/**
 * 父亲是一个监听者,监听观察小孩是否醒了
 * @author jjwu
 *
 */
public interface FatherListener extends EventListener {
	
	public void doFeeding(FeedingChildrenEvent feeding);

}
