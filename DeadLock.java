import java.util.Scanner;
import java.util.Arrays;

class Player {
    boolean has_moved;
    Room destination;
	Room in_room;

	public Player(Room loc, Room dn) {
		this.destination = dn;
		loc.setPlayer(this);
		this.in_room = loc;
		this.has_moved = false;
	} 

	public void markMoved() {
		this.has_moved = true;
	}

	public boolean hasMoved() {
		return this.has_moved;
	}

	public void finishMove() {
		this.markMoved();
		this.in_room.clear();
		this.in_room = this.destination;
	}

	public void setDestination(Room given_room) {
		this.destination = given_room;
	}

	public Room getDestination() {
		return this.destination;
	}

	public Room getRoom() {
		return this.in_room;
	}

	public static Player dummyPlayer() {
		Room r1 = new Room();
		return new Player(r1, r1);
	}

	public boolean isDummy() {
		return this.in_room == this.destination;
	}
}

class Room {
    boolean is_occupied, is_dummy;
    Player player;
	
	public Room() {
		is_occupied = false;
		this.player = Player.dummyPlayer();
		is_dummy = false;
	}

	public void markOccupied() {
		this.is_occupied = true;
	}

	public boolean getOccupancy() {
		return this.is_occupied;
	}

	public void setPlayer(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void clear() {
		this.player = Player.dummyPlayer();
		this.is_occupied = false;
	}

	public boolean isDummy() {
		return this.is_dummy;
	}

	public static Room dummyRoom() {
		Room rm = new Room();
		rm.is_dummy = true;
		return rm;
	}
}

public class DeadLock {
	// Player centric approach
	public static boolean canMove(Player plr) {
		if (! plr.getDestination().getOccupancy()) {
			return true;
		} else if (plr.getDestination().getOccupancy() && plr.getDestination().getPlayer().hasMoved()){
			return false;
		} else {
			return canMove(plr.getDestination().getPlayer());
		}
	}

	public static boolean move(Player plr) {
		// commit move, return if move was successful
		if (canMove(plr)) {
			Room dest = plr.getDestination();
			dest.markOccupied();
			dest.setPlayer(plr);
			plr.markMoved();
			plr.finishMove();
			return true;
		} else {
			return false;
		}
	}

	public static void init(Player[] ps, int p_idx, Room[] rs, int c_id, int d_id) {
		// set rs properly
		if (rs[c_id].isDummy() && rs[d_id].isDummy()) {
			rs[c_id] = new Room();
			rs[d_id] = new Room();
		} else if (rs[c_id].isDummy() && !(rs[d_id].isDummy())) {
			rs[c_id] = new Room();
		} else if (!(rs[c_id].isDummy()) && rs[d_id].isDummy()) {
			rs[d_id] = new Room();
		} // Do nothing to rs otherwise

		// initialise a Player instance
		ps[p_idx] = new Player(rs[c_id], rs[d_id]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String first_line = sc.nextLine();
		String[] first_inputs = first_line.split(" ");
	    int num_rooms = Integer.parseInt(first_inputs[0]);
		int num_players = Integer.parseInt(first_inputs[1]);

		Player[] players = new Player[num_players];
		Arrays.fill(players, Player.dummyPlayer()); 
		Room[] rooms = new Room[num_rooms];
		Arrays.fill(players, Room.dummyRoom()); 

		// initialise various things
		for (int i = 0; i < num_players; i++) {
			// handle input
			String scanned = sc.nextLine();
			String[] items = scanned.split(" ");
			int current_id = Integer.parseInt(items[0]);
			int dest_id = Integer.parseInt(items[1]);

			// setup
			init(players, i, rooms, current_id, dest_id);
		}
	}	
}
