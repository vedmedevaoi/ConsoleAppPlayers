import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.course.player.data.DataProvider;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerServiceImplTest {

    private PlayerServiceImpl playerService;
    private TestDataProvider testDataProvider;

    @BeforeEach
    public void setUp() {
        testDataProvider = new TestDataProvider();
        playerService = new PlayerServiceImpl(testDataProvider);
    }

    private static class TestDataProvider implements DataProvider {
        private final List<Player> players = new ArrayList<>();

        @Override
        public void addPlayer(Player player) {
            players.add(player);
        }

        @Override
        public void removePlayer(Player player) {
            players.remove(player);
        }

        @Override
        public List<Player> getAllPlayers() {
            return new ArrayList<>(players);
        }
    }

    @Test
    public void testAddPlayer_CheckInList() {
        Player player = new Player("John", "Doe");
        playerService.addPlayer(player);

        List<Player> players = playerService.getAllPlayers();
        assertEquals(1, players.size());
        assertSame(player, players.get(0));
    }

    @Test
    public void testAddAndRemovePlayer_CheckAbsence() {
        Player player = new Player("John", "Doe");
        playerService.addPlayer(player);
        playerService.removePlayer(player);

        List<Player> players = playerService.getAllPlayers();
        assertTrue(players.isEmpty());
    }

    @Test
    public void testAddMultiplePlayers_CheckAllInList() {
        Player player1 = new Player("John", "Doe");
        Player player2 = new Player("Jane", "Smith");
        playerService.addPlayer(player1);
        playerService.addPlayer(player2);

        List<Player> players = playerService.getAllPlayers();
        assertEquals(2, players.size());
        assertTrue(players.contains(player1));
        assertTrue(players.contains(player2));
    }

    @Test
    public void testRemovePlayer_NotInList() {
        Player player1 = new Player("John", "Doe");
        Player player2 = new Player("Jane", "Smith");
        playerService.addPlayer(player1);
        playerService.removePlayer(player2); // удаляем несуществующего игрока

        List<Player> players = playerService.getAllPlayers();
        assertEquals(1, players.size());
        assertSame(player1, players.get(0));
    }

    @Test
    public void testGetAllPlayers_EmptyList() {
        List<Player> players = playerService.getAllPlayers();
        assertTrue(players.isEmpty());
    }
}