package jjwu.xdeveloper.app.callback.chapter1;

public class ViewImpl {
	
	private Event callBack;
	
	public ViewImpl(){
		super();
	}
	
	
	public void confirm(){
		try {
			System.out.println("点击窗口确认事件!");
			EventButton eventButton = (EventButton)callBack;
			eventButton.confrimEvent();
		} catch (Exception e) {
			System.err.println("事件类型错误!");
		}
		
	}
	
	public void cancel(){
		try {
			System.out.println("点击窗口取消事件!");
			EventButton eventButton = (EventButton)callBack;
			eventButton.cancelEvent();
		} catch (Exception e) {
			System.err.println("事件类型错误!");
		}
		
	}
	
	public void registerEvent(Event callBack){
		this.callBack = callBack;
	}
}
