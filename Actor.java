
public class Actor {
	

		  private final Listener listener;
		  private final Filter filter;

		  public Actor(Listener listener, Filter filter) {
		    this.listener = listener;
		    this.filter = filter;
		  }

		  public Listener getlistener() { return listener; }
		  public Filter getfilter() { return filter; }

		  @Override
		  public int hashCode() { return listener.hashCode() ^ filter.hashCode(); }

		  @Override
		  public boolean equals(Object o) {
		    if (!(o instanceof Actor)) return false;
		    Actor actor = (Actor) o;
		    return this.listener.equals(actor.getlistener()) &&
		           this.filter.equals(actor.getfilter());
		  }

		
}
