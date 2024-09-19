package ru.inno.course.player;

public class Player {
    private String firstName;
    private String lastName;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

// Интерфейс DataProvider
public interface DataProvider {
    void addPlayer(Player player);
    void removePlayer(Player player);
    List<Player> getAllPlayers();
}

// Реализация PlayerServiceImpl
public class PlayerServiceImpl {
    private final DataProvider dataProvider;

    public PlayerServiceImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void addPlayer(Player player) {
        dataProvider.addPlayer(player);
    }

    public void removePlayer(Player player) {
        dataProvider.removePlayer(player);
    }

    public List<Player> getAllPlayers() {
        return dataProvider.getAllPlayers();
    }
}