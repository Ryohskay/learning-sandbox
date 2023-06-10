public class Player {
    boolean has_moved;
    Room destination = null;
	Room in_room = null;

	public Player(Room loc, Room dn) {
		this.destination = dn;
		this.in_room = loc;
		this.has_moved = false;
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

	public Room getDestination(Room given_room) {
		return this.destination;
	}

	public Room getRoom() {
		return this.in_room;
	}
}

public class Room {
    boolean is_occupied = false;
    Player player = null;
	
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
		this.player = null;
		this.is_occupied = false;
	}
}

// Player centric approach
public static boolean canMove(Player plr) {
	if (plr.getDestination().getOccupancy() == false) {
		return true;
	} else if (plr.getDestination().getOccupancy() == true && plr.getDestination().getPlayer().hasMoved() == true){
		return false;
	} else {
		return canMove(plr.getDestination().getPlayer());
	}
}

public static boolean move(Player plr) {
	// commit move, return if move was successful
	if (canMove(plr) == true) {
		Room dest = plr.getDestination();
		dest.markOccupied();
		dest.setPlayer(plr);
		plr.markMoved();
		plr.finishMove();
		return boolean;
	} else {
		return false;
	}
}

// Room centric approach
public static boolean checkDeadLock(Room[] rooms) {
	for (room : rooms) {
		else if (room.player != null && room.player.getDestination().getOccupancy() == false) {
			Room destination = room.player.getDestination()
			destination.markOccupied();
			Player moving_player = room.getPlayer();
			destination.setPlayer(moving_player);
			destination.markMoved();
			room.clear();
		}
	}
}

public static void DeadLock() {
	public static void main(String[] args) {
		
	}	
}
