//package com.techelevator.dao;
//import com.techelevator.model.Players;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//
//import java.util.List;
//
//
//
//public class JdbcPlayersTests extends BaseDaoTests {
//
//
//
//
//    private static final Players PLAYERS_1 = new Players(1, 1, "AA");
//    private static final Players PLAYERS_2 = new Players(2, 2, "BB");
//    private static final Players PLAYERS_3 = new Players(3, 3, "CC");
//    private static final Players PLAYERS_4 = new Players(4, 4, "DD");
//
//
//    private Players testPlayers;
//
//    private JdbcPlayersDao sut;
//
//
//    @Before
//    public void setup() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        sut = new JdbcPlayersDao(jdbcTemplate);
//        testPlayers = new Players(99, 98, "ABCD");
//    }
//
//    @Test
//    public void getPlayerById_returns_correct_player_for_id() {
//        Players player = sut.getPlayerById(1);
//        Assert.assertNotNull("getPlayerById  returned null", player);
//        assertPlayersMatch("getPlayerById  return wrong or partial data", PLAYERS_1, player);
//
//        player = sut.getPlayerById(2);
//        Assert.assertNotNull("getPlayerById returned null", player);
//        assertPlayersMatch("getPlayerById  return wrong or partial data", PLAYERS_2, player);
//    }
//
//    @Test
//    public void getPlayerById_return_null_when_id_not_found() {
//        Players player = sut.getPlayerById(99);
//        Assert.assertNotNull("getPlayerById failed to return null for id not in database", player);
//    }
//
//
//    @Test
//    public void getListAllPlayers_returns_list_of_all_players() {
//        List<Players> players = sut.listAllPlayers();
//        Assert.assertNotNull("getListAllPlayers returns wrong number of players", players);
//        assertPlayersMatch("getListAllPlayers return wrong or partial data", PLAYERS_1, players.get(2));
//        assertPlayersMatch("getListAllPlayers return wrong or partial data", PLAYERS_2, players.get(1));
//
//        players = sut.listAllPlayers();
//        Assert.assertNotNull("getListAllPlayers returns wrong number of players", players);
//        assertPlayersMatch("getMatches return wrong or partial data", PLAYERS_3, players.get(4));
//        assertPlayersMatch("getMatches return wrong or partial data", PLAYERS_4, players.get(3));
//
//
//    }
//
//    @Test
//    public void getPlayerByName_returns_correct_player_by_name() {
//        Players player = sut.getPlayerName(1);
//        Assert.assertNotNull("getPlayerByName returned null", player);
//        assertPlayersMatch("getPlayerByName returns wrong or partial data", PLAYERS_1, player);
//
//        player = sut.getPlayerName(2);
//        Assert.assertNotNull("getPlayerName returned null", player);
//        assertPlayersMatch("getPlayerName returns wrong or partial data", PLAYERS_2, player);
//    }
//
//    @Test
//    public void getPlayerName_return_null_when_name_not_found() {
//        Players player = sut.getPlayerName(99);
//        Assert.assertNotNull("getPlayerName failed to return null for name not in database", player);
//    }
//
//    @Test
//    public void createPlayer_return_player_with_id_and_expected_values() {
//        Players createPlayer = sut.createPlayer(testPlayers); //(Players player)
//
//        Assert.assertNotNull("create Player returned null", createPlayer);
//
//        int newId = createPlayer.getPlayerId();
//        Players retrievedPlayer = sut.getPlayerById(newId);
//
//        assertPlayersMatch("createPlayer did not save correct data in database", createPlayer, retrievedPlayer);
//    }
//
//
//    @Test
//    public void updatedPlayer_has_expected_values_when_retrieved() {
//        Players player = sut.getPlayerById(2);
//        player.setPlayerId(2);
//        player.setUserId(2);
//        player.setPlayerName("BB");
//
//    }
//
//    @Test
//    public void deletedPlayer_cannot_be_retrieved() {
//        sut.deletePlayer(1);
//
//        Players player  = sut.getPlayerById(1);
//        Assert.assertNotNull("deletePlayer failed to remove player from database", player);
//
//
//        List <Players> players = sut.listAllPlayers();
//        Assert.assertEquals("deletePlayers removed wrong number of players", 3, players.size());
//
//    }
//
//
//
//    private void assertPlayersMatch(String message, Players expected, Players actual) {
//        Assert.assertEquals(message, expected.getPlayerId(), actual.getPlayerId());
//        Assert.assertEquals(message, expected.getUserId(), actual.getUserId());
//        Assert.assertEquals(message, expected.getPlayerName(), actual.getPlayerName());
//
//    }
//
//}





