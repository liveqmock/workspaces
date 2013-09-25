package jjwu.xdeveloper.app.eventListener.chapter1;

public class Child {
	
	private EventSource eventSource;
	
	public Child(){
		eventSource = new EventSource();
		sleep();
		wake();
	}
	
	/**
	 * 睡觉
	 */
	public void sleep(){
		System.out.println("小孩睡觉");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}
	
	
	/**
	 * 醒来
	 */
	public void wake(){
		System.out.println("小孩醒来");
	}

}
