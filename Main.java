import java.util.Date;

public class Main {
	public static void main(String argv[]) throws Throwable {
		
			Dispatcher dispatcher = new Dispatcher();
			
			new Thread() {
				public void run() {
					Customer customer1 = new Customer(1, dispatcher);
					try {
						customer1.subscribeToCompany(4);
						customer1.subscribeToCompany(5);
						customer1.setFilter(200.0, 300.0);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					customer1.registerToOfferEvents();
				}
			}.start();
			
			new Thread() {
				public void run() {
					Customer customer2 = new Customer(2, dispatcher);
					try {
						customer2.subscribeToCompany(6);
						
						customer2.registerToOfferEvents();
						while (true) {
							customer2.setFilter(100.0, 200.0);
							sleep(2000);
							customer2.setFilter(200.0, 300.0);
							sleep(2000);
						}
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			}.start();
			
			
			

			new Thread() {
				public void run() {
					Customer customer3 = new Customer(3, dispatcher);
					try {
						customer3.subscribeToCompany(5);
						customer3.registerToOfferEvents();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			

			new Thread() {
				public void run() {
					Company company1 = new Company(4, dispatcher);
					company1.registerToEvent(EventStrings.offerRead);
					try {
						company1.createOffer(1, 100.3, new Date());
						company1.modifyOffer(1, 500, new Date());
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			

			new Thread() {
				public void run() {
					Company company2 = new Company(5, dispatcher);
					company2.registerToEvent(EventStrings.offerRead);
					company2.setFilter(200.0, 500.0);
					
					try {
						company2.createOffer(2, 100.3, new Date());
						company2.removeOffer(2);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			

			new Thread() {
				public void run() {
					int createCount  = 10;
					Company company3 = new Company(6, dispatcher);
					company3.registerToEvent(EventStrings.offerRead);
					try {
						while(true) {
							company3.createOffer(createCount, 150, new Date());
							sleep(500);
							company3.removeOffer(createCount++);
						}
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
	}
}
