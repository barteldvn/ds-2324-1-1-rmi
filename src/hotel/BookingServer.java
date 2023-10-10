package hotel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BookingServer {

    public static void main(String[] args) throws Exception{
        System.setSecurityManager(null);
        BookingManager bookingManager = new BookingManager();
        BookingManager stub = (BookingManager) UnicastRemoteObject.exportObject(bookingManager, 0);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("BookingManager", stub);
    }
}
