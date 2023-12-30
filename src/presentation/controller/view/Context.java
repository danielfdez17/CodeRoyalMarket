package presentation.controller.view;

import presentation.controller.Events;

public class Context {
	private Events event;
	private Object data;

	public Context(Events event, Object data) {
		this.event = event;
		this.data = data;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}