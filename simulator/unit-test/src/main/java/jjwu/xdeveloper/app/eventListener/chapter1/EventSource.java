package jjwu.xdeveloper.app.eventListener.chapter1;

import java.util.Enumeration;
import java.util.Vector;

public class EventSource {

	private final Vector<FatherListener> repository = new Vector<FatherListener>();

	private String sName = "";

	private FatherListener listener;

	public void addListener(FatherListener listener) {
		repository.addElement(listener);// 这步要注意同步问题
	}

	public void notifyEvent(FeedingChildrenEvent event) {
		Enumeration<FatherListener> senum = repository.elements();// 这步要注意同步问题
		while (senum.hasMoreElements()) {
			listener = (FatherListener) senum.nextElement();
			listener.doFeeding(event);
		}
	}

	// 删除监听器，如果这里没有使用Vector而是使用ArrayList那么要注意同步问题
	public void removeDemoListener(FatherListener listener) {
		repository.remove(listener);// 这步要注意同步问题
	}

	/**
	 * 设置属性
	 * 
	 * @param str1
	 *            String
	 */
	public void setName(String str1) {
		boolean bool = false;
		if (str1 == null && sName != null) {
			bool = true;
		} else if (str1 != null && sName == null) {
			bool = true;
		} else if (!sName.equals(str1)) {
			bool = true;
		}

		this.sName = str1;

		// 如果改变则执行事件
		if (bool) {
			notifyEvent(new FeedingChildrenEvent(this, sName));
		}

	}
}
