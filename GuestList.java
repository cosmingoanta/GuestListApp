package registrationApp;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {
	//necessary fields
	private final int maxNumberOfSeats;
	private ArrayList<Guest> guestList;
	private ArrayList<Guest> waitList;
	Scanner sc = new Scanner(System.in);

	//minimum constructor. we always need the max number of seats
	GuestList(int maxNumberOfSeats){
		this.maxNumberOfSeats = maxNumberOfSeats;
		guestList = new ArrayList<Guest>();
		waitList = new ArrayList<Guest>();
	}

	//aux function that reads a guest info and returns the guest object, so that it can be further used (added, removed etc.)
	public Guest readGuestInfo() {
		System.out.println("Insert first name:");
		String firstName = sc.next();
		System.out.println("Insert last name:");
		String lastName = sc.next();
		System.out.println("Insert email: ");
		String email = sc.next();
		System.out.println("Insert phone number: ");
		String phone = sc.next();
		Guest g = new Guest(firstName, lastName, email, phone);
		return g;
		
	}
	
	//public main search method
	public boolean searchPerson() {
		System.out.println("What criteria do you want to search by? [1/2/3]: \n1. Nume + Prenume \n2. Email \n3. Phone Number");
		int selection = sc.nextInt();
		Guest auxGuest;
		switch(selection) {
		case 1:
			auxGuest = searchByName(returnGuestNameCase());
			return generalSearchPerson(auxGuest);
		case 2:
			auxGuest = searchByEmail(returnGuestEmailCase());
			return generalSearchPerson(auxGuest);
		case 3:
			auxGuest = searchByPhoneNumber(returnGuestPhoneNumberCase());
			return generalSearchPerson(auxGuest);
		default:
			System.out.println("Wrong input.");
			break;
		}
		return false;
	}

	//next 3 methods search both arrays comparing only guest first name + last name OR email OR phone number
	//they return the already added Guest, from either the guest list or wait list, if he exists. if not, return null
	private Guest searchByName(Guest g) {
		for(Guest aux : this.guestList) {
			if(aux.getFirstName().equals(g.getFirstName()) && aux.getLastName().equals(g.getLastName())) {
				return aux;
			}
		}
		for(Guest aux : this.waitList) {
			if(aux.getFirstName().equals(g.getFirstName()) && aux.getLastName().equals(g.getLastName())) {
				return aux;
			}
		}
		return null;
	}

	private Guest searchByEmail(Guest g) {
		for(Guest aux : this.guestList) {
			if(aux.getEmail().equals(g.getEmail())) {
				return aux;
			}
		}
		for(Guest aux : this.waitList) {
			if(aux.getEmail().equals(g.getEmail())) {
				return aux;
			}
		}
		return null;
	}

	private Guest searchByPhoneNumber(Guest g) {
		for(Guest aux : this.guestList) {
			if(aux.getPhoneNumber().equals(g.getPhoneNumber())) {
				return aux;
			}
		}
		for(Guest aux : this.waitList) {
			if(aux.getPhoneNumber().equals(g.getPhoneNumber())) {
				return aux;
			}
		}
		return null;
	}


	//next 3 methods are aux methods that take input from the keyboard and return an aux Guest object with only the relevant 
	//fields filled out, while the other fields are null. 
	private Guest returnGuestNameCase() {
		Guest auxGuest = new Guest();
		System.out.println("Insert first name:");
		String firstName = sc.next();
		System.out.println("Insert last name:");
		String lastName = sc.next();
		auxGuest.setFirstName(firstName);
		auxGuest.setLastName(lastName);
		return auxGuest;
	}

	private Guest returnGuestEmailCase() {
		Guest auxGuest = new Guest();
		System.out.println("Insert email:");
		String email = sc.next();
		auxGuest.setEmail(email);
		return auxGuest;
	}

	private Guest returnGuestPhoneNumberCase() {
		Guest auxGuest = new Guest();
		System.out.println("Insert phone number:");
		String phoneNumber = sc.next();
		auxGuest.setPhoneNumber(phoneNumber);
		return auxGuest;
	}

	//general search method, used to output a relevant text and return the result[true/false]
	private boolean generalSearchPerson(Guest auxGuest) {
		if(auxGuest != null) {
			System.out.println("Person is registered.");
			return true;
		}
		System.out.println("Person was not found.");
		return false;
	}

	//main add guest method
	public int addGuest(Guest g) {
		if(searchByName(g) != null || searchByEmail(g) != null || searchByPhoneNumber(g) != null) {
			System.out.println("The person is already added at the event");
			return -1;
		}
		if(this.guestList.size() < maxNumberOfSeats) {
			this.guestList.add(g);
			System.out.println("Cograts! Your seat is confirmed");
			return 0;
		} else {
			this.waitList.add(g);
			System.out.println("You've been added to the waitlist. Order number: " + this.waitList.size());
			return this.waitList.size();
		}
	}

	//main remove guest method. uses some of the already implemented aux methods, and some other, new ones.
	public boolean removeGuest() {
		System.out.println("What criteria do you want to search by? [1/2/3]: \n1. Nume + Prenume \n2. Email \n3. Phone Number");
		int selection = sc.nextInt();
		Guest auxGuest;
		switch(selection) {
		case 1:
			auxGuest = searchByName(returnGuestNameCase());
			return searchAndRemoveGuest(auxGuest);
		case 2:
			auxGuest = searchByEmail(returnGuestEmailCase());
			return searchAndRemoveGuest(auxGuest);
		case 3:
			auxGuest = searchByPhoneNumber(returnGuestPhoneNumberCase());
			return searchAndRemoveGuest(auxGuest);
		default:
			System.out.println("Wrong input.");
			break;
		}
		System.out.println("Person was not in any list.");
		return false;
	}

	//aux remove methods. they have different results based on the list from which we remove
	private boolean searchAndRemoveGuest(Guest auxGuest) {
		if(auxGuest == null) {
			System.out.println("Guest was not found.");
			return false;
		}else {
			if(this.guestList.contains(auxGuest)) {
				removeFromGuestList(auxGuest);
				System.out.println("The guest was succesfully deleted from the guest list.");
				return true;
			} else {
				removeFromWaitList(auxGuest);
				System.out.println("The guest was succesfully deleted from the wait list.");
				return true;
			}
		} 
	}

	private void removeFromGuestList(Guest g) {
		this.guestList.remove(g);
		if(this.waitList.size() != 0) {
			System.out.println("Felicitari! " + this.waitList.get(0).toString() + ". Ai fost adaugata la event. ");
			this.guestList.add(this.waitList.get(0));
			this.waitList.remove(0);
		}
	}

	private void removeFromWaitList(Guest g) {
		this.waitList.remove(g);
	}

	//main info update function. reuses some of the existing aux functions, and uses some new ones
	public boolean updateGuest() {
		System.out.println("What criteria do you want to search by? [1/2/3]: \n1. Nume + Prenume \n2. Email \n3. Phone Number");
		int selection = sc.nextInt();
		Guest auxGuest;
		switch(selection) {
		case 1:
			auxGuest = searchByName(returnGuestNameCase());
			return generalUpdateGuest(auxGuest);
		case 2:
			auxGuest = searchByEmail(returnGuestEmailCase());
			return generalUpdateGuest(auxGuest);
		case 3:
			auxGuest = searchByPhoneNumber(returnGuestPhoneNumberCase());
			return generalUpdateGuest(auxGuest);
		default:
			System.out.println("Wrong input.");
			break;
		}
		return false;
	}

	//aux update functions. after we confirm that a searched Guest exists, we give the user the option to update whatever he wants.
	private boolean generalUpdateGuest(Guest guest) {
		if(guest == null) {
			System.out.println("Guest was not found.");
			return false;
		} else {
			System.out.println("What criteria do you want to update? [1/2/3] \n1. Nume + Prenume \n2. Email \n3. Phone Number");
			int selection = sc.nextInt();
			switch(selection) {
			case 1:
				System.out.println("Enter the new first name: ");
				String firstName = sc.next();
				System.out.println("Enter the new last name: ");
				String lastName = sc.next();
				guest.setFirstName(firstName);
				guest.setLastName(lastName);
				return true;
			case 2:
				System.out.println("Enter new email: ");
				String email = sc.next();
				guest.setEmail(email);
				return true;
			case 3:
				System.out.println("Enter new phone number");
				String phoneNumber = sc.next();
				guest.setPhoneNumber(phoneNumber);
				return true;
			default:
				System.out.println("Wrong input.");
				return false;
			}
		}
	}

	//lower level of complexity functions required by the program.
	public void showGuestList() {
		if (this.guestList.size() == 0) {
			System.out.println("The list is empty.");
		}
		for(Guest g : this.guestList) {
			System.out.println(g);
		}
	}

	public void showWaitList() {
		if (this.waitList.size() == 0) {
			System.out.println("The list is empty.");
		}
		for(Guest g : this.waitList) {
			System.out.println(g);
		}
	}
	
	public void numberOfParticipantsInGuestList() {
		System.out.println("The event has: " + this.guestList.size() + " participants in guest list.");
	}
	
	public void numberOfParticipantsInWaitList() {
		System.out.println("The event has: " + this.waitList.size() + " participants in the wait list.");
	}
	
	public void numberOfAvailableSeats() {
		int res = this.maxNumberOfSeats - this.guestList.size();
		System.out.println("Number of available seats in guest list is: " + res);
	}
	
	public void totalNumberOfParticipants() {
		int res = this.waitList.size() + this.guestList.size();
		System.out.println("The event has a total of: " + res + " participants.");
	}
	
	public ArrayList<Guest> partialSearch(){
		ArrayList<Guest> result = new ArrayList<Guest>();
		System.out.println("Please input what you want to search: ");
		String search = sc.next();
		search = search.toLowerCase();
		for (Guest g : guestList) {
			String aux = g.getFirstName() + " " + g.getLastName() + " " + g.getEmail() + " " + g.getPhoneNumber();
			aux = aux.toLowerCase();
			if (aux.contains(search)) {
				result.add(g);
			}
		}
		return result;
	}
}
