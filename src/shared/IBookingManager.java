package shared;

import hotel.BookingDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

public interface IBookingManager extends Remote {
    /**
     * Return true if there is no booking for the given room on the date,
     * otherwise false
     */
    boolean isRoomAvailable(Integer room, LocalDate date) throws RemoteException;

    /**
     * Add a booking for the given guest in the given room on the given
     * date. If the room is not available, throw a suitable Exception.
     */
    void addBooking(BookingDetail bookingDetail) throws RemoteException;

    /**
     * Return a list of all the available room numbers for the given date
     */
    Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException;

    /**
     * Return a list of all the room numbers
     */
    Set<Integer> getAllRooms() throws RemoteException;
}
