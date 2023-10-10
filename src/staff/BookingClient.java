package staff;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;
import shared.IBookingManager;

public class BookingClient {

	private BookingManager bm = null;

	public static void main(String[] args) throws Exception {
		if (System.getSecurityManager() != null)
			System.setSecurityManager(null);
		if (args.length == 0) {
			args = new String[] { "BookingManager" };
		}
		BookingClient client = new BookingClient(args);
		client.run();
	}

	public BookingClient(String[] args) {
		bookingmanager = args[0];
	}

	private final String bookingmanager;

	public void run() {
		try {
			if (System.getSecurityManager() != null)
				System.setSecurityManager(null);
			Registry registry = LocateRegistry.getRegistry();

			IBookingManager rental = (IBookingManager) registry.lookup("BookingManager");

			System.out.println("All rooms " + rental.getAllRooms() + " found.");
		} catch (NotBoundException ex) {
			System.err.println("Could not find car rental company with given name.");
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
