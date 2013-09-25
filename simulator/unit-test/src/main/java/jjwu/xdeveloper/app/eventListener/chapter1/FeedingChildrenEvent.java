package jjwu.xdeveloper.app.eventListener.chapter1;

import java.util.EventObject;

public class FeedingChildrenEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2989820777934164744L;
	
	private	String sName;
	
	private Object sourceEvent;

	public FeedingChildrenEvent(Object sourceEvent,String sName) {
		super(sourceEvent);
		this.sName = sName;
		this.sourceEvent = sourceEvent;
		
	}

	public Object getSourceEvent() {
		return sourceEvent;
	}

	public String getsName() {
		return sName;
	}
	
}
