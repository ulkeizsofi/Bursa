import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Dispatcher{
	
	private HashMap<String, ArrayList<Actor>> clients = new HashMap<>();
	private BlockingQueue<Event> eventQueue = new ArrayBlockingQueue<>(100);
	
	public Dispatcher() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						Event event = eventQueue.take();
						processEvent(event);
					} catch (InterruptedException e) {
						Thread.interrupted();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void registerListener(String type, Filter filter, Listener listener) {
		synchronized (clients) {
			
			ArrayList<Actor> subscribedObjects = clients.get(type);
			if (subscribedObjects == null) {
				clients.put(type, new ArrayList<>());
			}
			clients.get(type).add(
					new Actor(listener, filter)
			);
		}
	}
	
	public void sendEvent(Event e) throws InterruptedException {
		eventQueue.put(e);
	}
	
	private void processEvent(Event e) throws Throwable {
		String type = e.getType();
		synchronized (clients) {
			
			ArrayList<Actor> subscribedObjects = clients.get(type);
			if(subscribedObjects != null) {
				for (Actor a : subscribedObjects) {
					if (a.getfilter().accept(e)) {
						a.getlistener().onEvent(e);
						if (e instanceof OfferEvent) {
							OfferEvent offerEvent = (OfferEvent)e;
							Offer offer = offerEvent.getNewOffer();
							if (offer != null) { //If the offer was not deleted
								sendEvent(new OfferReadEvent(EventStrings.offerRead, a.getlistener().getID(), offerEvent.getSourceID(),offer));
							}
						}
					}
				}
			}
		}
	}
}
