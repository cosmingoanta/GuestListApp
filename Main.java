package registrationApp;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the max. number of people for your event:");
		int max = sc.nextInt();
		GuestList guestlist = new GuestList(max);
		System.out.println("" + "help - Shows the command list\r\n" + "add - Add a new Guest\r\n"
				+ "check - Check if a Guest is already added\r\n" + "remove - Delete an existing Guest\r\n"
				+ "update - Update Guest info\r\n" + "guests - The current Guest list\r\n"
				+ "waitlist - The current waiting list\r\n" + "available - Number of available spots\r\n"
				+ "guests_no - Number of people participating at the event\r\n"
				+ "waitlist_no - Number of people in the wating list\r\n"
				+ "subscribe_no - Total number of guests invited\r\n"
				+ "search - Search the guestlist based on partial info\r\n" + "quit - Close the app");
		System.out.println("Please enter a command: ");
		String command = sc.next();
		while (!command.equals("quit")) {
			switch (command) {
			case "help":
				System.out.println("" + "help - Shows the command list\r\n" + "add - Add a new Guest\r\n"
						+ "check - Check if a Guest is already added\r\n" + "remove - Delete an existing Guest\r\n"
						+ "update - Update Guest info\r\n" + "guests - The current Guest list\r\n"
						+ "waitlist - The current waiting list\r\n" + "available - Number of available spots\r\n"
						+ "guests_no - Number of people participating at the event\r\n"
						+ "waitlist_no - Number of people in the wating list\r\n"
						+ "subscribe_no - Total number of guests invited\r\n"
						+ "search - Search the guestlist based on partial info\r\n" + "quit - Close the app");
				break;
			case "add":
				guestlist.addGuest(guestlist.readGuestInfo());
				break;
			case "check":
				guestlist.searchPerson();
				break;
			case "remove":
				guestlist.removeGuest();
				break;
			case "update":
				guestlist.updateGuest();
				break;
			case "guests":
				guestlist.showGuestList();
				break;
			case "waitlist":
				guestlist.showWaitList();
				break;
			case "available":
				guestlist.numberOfAvailableSeats();
				break;
			case "guests_no":
				guestlist.numberOfParticipantsInGuestList();
				break;
			case "waitlist_no":
				guestlist.numberOfParticipantsInWaitList();
				break;
			case "subscribe_no":
				guestlist.totalNumberOfParticipants();
				break;
			case "search":
				System.out.println(guestlist.partialSearch());
				break;
			case "quit":
				break;
			default:
				System.out.println("You have entered an invalid request. Please retry.");
				break;
			}
			System.out.println("Please enter a command: ");
			command = sc.next();
		}
		sc.close();
	}

}
