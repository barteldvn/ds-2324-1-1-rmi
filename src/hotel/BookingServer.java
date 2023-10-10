package hotel;

import shared.IBookingManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingServer {

    private static final Logger logger = Logger.getLogger(BookingServer.class.getName());

    public static void main(String[] args) throws Exception{
        if(System.getSecurityManager() != null)
            System.setSecurityManager(null);
        IBookingManager bookingManager = new BookingManager();
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry();

        } catch(RemoteException e) {
            logger.log(Level.SEVERE, "Could not locate RMI registry.");
            System.exit(-1);
        }
        try {
            IBookingManager stub = (IBookingManager) UnicastRemoteObject.exportObject(bookingManager, 0);

            registry.rebind("BookingManager", stub);
            logger.log(Level.INFO, "<{0}> BookingManager {0} is registered.", "BookingManager");
        }
        catch (RemoteException e){
            logger.log(Level.SEVERE, "<{0}> Could not get stub bound of Booking Manager {0}.", "BookingManager");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
