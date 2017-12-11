
public interface Listener {
	void onEvent(Event e) throws Throwable;
	Integer getID();
	void registerToEvent(String type);
}
