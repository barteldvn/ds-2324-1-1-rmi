package hotel;

import shared.IBookingManager;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.*;

public class BookingManager implements IBookingManager {

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		for (Room room : rooms) {
			if (Objects.equals(room.getRoomNumber(), roomNumber)) {
				for (BookingDetail bookingDetail : room.getBookings()) {
					return bookingDetail.getDate() != date;
				}
			}
		}
		return false;
	}

	private Room getRoom(int roomNumber){
		for (Room room : rooms) {
			if (room.getRoomNumber() == roomNumber){
				return room;
			}
		}
		return null;
	}

	public void addBooking(BookingDetail bookingDetail) throws RemoteException {
		Room room = getRoom(bookingDetail.getRoomNumber());
		if (room == null) throw new RemoteException("Room does not exist");
		if (!isRoomAvailable(bookingDetail.getRoomNumber(), bookingDetail.getDate())) throw new RemoteException("Room not available");
		List<BookingDetail> bookings = room.getBookings();
		bookings.add(bookingDetail);
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set <Integer> availableRooms = new HashSet<>();
		for (Room room : rooms) {
			if (isRoomAvailable(room.getRoomNumber(), date)){
				availableRooms.add(room.getRoomNumber());
			}

		}
		return availableRooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
