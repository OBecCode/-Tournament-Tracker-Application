//package com.techelevator.service;
//
//import com.techelevator.dao.JdbcTournamentDao;
//import com.techelevator.dao.MatchesDao;
//import com.techelevator.dao.TeamsDao;
//import com.techelevator.dao.TournamentDao;
//import com.techelevator.exception.TournamentNotFoundException;
//import com.techelevator.model.Teams;
//import com.techelevator.services.TournamentService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TournamentServiceTests {
//    public TournamentDao tournamentDao;
//    public MatchesDao matchesDao;
//    public TeamsDao teamsDao;
//
//    public TournamentServiceTests(TournamentDao tournamentDao, MatchesDao matchesDao, TeamsDao teamsDao) {
//        this.tournamentDao = tournamentDao;
//        this.matchesDao = matchesDao;
//        this.teamsDao = teamsDao;
//    }
//
//    public static void main(String[] args) throws TournamentNotFoundException {
//        TournamentService tournamentService = new TournamentService();
//        List<Teams> teamsList = new ArrayList<>();
//        Teams one = new Teams(1, "one", 4);
//        Teams two = new Teams(2, "two", 4);
//        Teams three = new Teams(3, "three", 4);
//        Teams four = new Teams(4, "four", 4);
//        Teams five = new Teams(5, "five", 4);
//        Teams six = new Teams(6, "six", 4);
//        Teams seven = new Teams(7, "seven", 4);
//        Teams eight = new Teams(8, "eight", 4);
//        Teams nine = new Teams(9, "nine", 4);
//        Teams ten = new Teams(10, "ten", 4);
//        Teams eleven = new Teams(11, "eleven", 4);
//        Teams twelve = new Teams(12, "twelve", 4);
//
//        teamsList.add(one);
//        teamsList.add(two);
//        teamsList.add(three);
//        teamsList.add(four);
//        teamsList.add(five);
//        teamsList.add(six);
//        teamsList.add(seven);
//        teamsList.add(eight);
////        teamsList.add(nine);
////        teamsList.add(ten);
////        teamsList.add(eleven);
////        teamsList.add(twelve);
//
//
//        tournamentService.generateBracket(teamsList, 1);
//    }
//}
