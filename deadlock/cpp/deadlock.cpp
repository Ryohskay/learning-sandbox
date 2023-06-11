#include <iostream>
#include <tuple>
#include <string>

class Player {
  public:
    std::string playerName;
    std::string getName() {
      if playerName != "" {
        return playerName;
      } else {
        return "None";
      }
    }
};

class Room {
  public:
    Player player_in_room;
    bool is_occupied;
}

bool trace_destination_player(Room start) {
  if start.is_occupied && start.player_in_room.
}

int main() {
}
