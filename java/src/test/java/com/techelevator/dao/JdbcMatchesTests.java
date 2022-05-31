package com.techelevator.dao;

import com.techelevator.model.Matches;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.User;
import com.techelevator.model.Authority;
import com.techelevator.FunctionalTests.*;
import com.techelevator.dao.JdbcUserDaoTests;
import com.techelevator.controller.MatchesController;
import com.techelevator.dao.JdbcMatchesDao;

import java.time.LocalTime;
import java.util.List;
import java.time.LocalDate;


public class JdbcMatchesTests extends BaseDaoTests {

    private static final Matches MATCHES_1 = new Matches(1,1, LocalDate.parse("2022-01-01"), LocalTime.parse("12:00"), 1, 5, 1, 1, 2);
    private static final Matches MATCHES_2 = new Matches(2,2, LocalDate.parse("2022-01-02"), LocalTime.parse("12:00"), 2, 6, 2, 2, 2);
    private static final Matches MATCHES_3 = new Matches(3,3, LocalDate.parse("2022-01-03"), LocalTime.parse("12:00"), 3, 7, 7, 3, 3);
    private static final Matches MATCHES_4 = new Matches(4,4, LocalDate.parse("2022-01-04"), LocalTime.parse("12:00"), 4, 8, 8, 4, 4);


    private Matches testMatches;

    private JdbcMatchesDao sut;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcMatchesDao(jdbcTemplate);
        testMatches = new Matches(96, 97, LocalDate.now(), LocalTime.now(), 98, 99, 100, 101, 102);
    }

    @Test
    public void getMatch_returns_correct_matches_for_id() {
        Matches match = sut.getMatch(1);
        Assert.assertNotNull("getMatches returned null", match);
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_1, match);

        match = sut.getMatch(2);
        Assert.assertNotNull("getMatches returned null", match);
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_2, match);
    }

    @Test
    public void getMatches_return_null_when_id_not_found() {
        Matches match= sut.getMatch(96);
        Assert.assertNotNull("getMatches failed to return null for id not in database", match);
    }


    @Test
    public void getMatchesByTournamentId_returns_list_of_all_matches_by_date() {
        List<Matches> matches = sut.getMatchesByTournament(3);
        Assert.assertNotNull("getMatchesByTournamentId returns wrong number matches", matches);
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_1, matches.get(2));
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_2, matches.get(1));

        matches = sut.getMatchesByTournament(2);
        Assert.assertNotNull("getMatchesByDateTournamentId returns wrong number matches", matches);
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_3, matches.get(4));
        assertMatchesMatch("getMatches return wrong or partial data", MATCHES_4, matches.get(3));


    }


    @Test
    public void createMatch_return_matches_with_id_and_expected_values() {
        Matches createMatch = sut.createMatch(testMatches, 97);

        Assert.assertNotNull("create Match return null", createMatch);

        int newId = createMatch.getMatchId();
        Matches retrievedMatch = sut.getMatch(newId);

        assertMatchesMatch("createMatch did not save correct data in database", createMatch, retrievedMatch);
    }


    @Test
    public void updated_match_has_expected_values_when_retrieved() {
        Matches match = sut.getMatch(1);
        match.setMatchId(2);
        match.setTournamentId(2);
        match.setStartDate(LocalDate.now());
        match.setStartTime(LocalTime.now());
        match.getHomeTeamId();
        match.getAwayTeamId();
    }

    @Test
    public void deleted_match_cant_be_retrieved() {
        sut.deleteMatch(1);

        Matches match  = sut.getMatch(1);
        Assert.assertNotNull("deleteMatch failed to remove match from database", match);

        List<Matches> matches = sut.getMatchesByDate(LocalDate.parse("2022-1-1"));
        Assert.assertEquals("deleteMatches removed wrong number of matches", 1, matches.size());
        assertMatchesMatch("deleteMatch deleted wrong match", MATCHES_2, matches.get(0));
    }



    private void assertMatchesMatch(String message, Matches expected, Matches actual) {
        Assert.assertEquals(message, expected.getMatchId(), actual.getMatchId());
        Assert.assertEquals(message, expected.getStartDate().toString(), actual.getStartDate());
        Assert.assertEquals(message, expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(message, expected.getTournamentId(), actual.getTournamentId());
        Assert.assertEquals(message, expected.getAwayTeamId(), actual.getAwayTeamId());
        Assert.assertEquals(message, expected.getHomeTeamId(), actual.getHomeTeamId());

    }

}
