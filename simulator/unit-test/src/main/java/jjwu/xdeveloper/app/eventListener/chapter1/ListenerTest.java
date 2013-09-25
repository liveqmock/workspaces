package jjwu.xdeveloper.app.eventListener.chapter1;

public class ListenerTest implements FatherListener {

	private EventSource eventSource;

	public ListenerTest() {
		super();
		eventSource = new EventSource();
		eventSource.addListener(this);
		System.out.println("添加监听器完毕");
		try {
			Thread.sleep(3000);
			// 改变属性,触发事件
			eventSource.setName("改变属性,触发事件");
		} catch (InterruptedException ex) {
		}
	}

	@Override
	public void doFeeding(FeedingChildrenEvent feeding) {
		System.out.println("爸爸给小孩换尿布");
	}

	public static void main(String[] args) {
		new ListenerTest();
	}

}
