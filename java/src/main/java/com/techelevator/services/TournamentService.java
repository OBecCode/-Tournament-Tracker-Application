package com.techelevator.services;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.techelevator.dao.MatchesDao;
import com.techelevator.dao.TeamsDao;
import com.techelevator.dao.TournamentDao;
import com.techelevator.exception.MatchNotFoundException;
import com.techelevator.model.Matches;
import com.techelevator.model.Teams;
import com.techelevator.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class TournamentService implements ServerTournamentService {
    private TournamentDao tournamentDao;
    private MatchesDao matchesDao;
    private TeamsDao teamsDao;

    private List<Matches> allMatches;
    private List<Matches> createdMatches; // matches returned from matchesDao.createMatch
    private int roundCounter;
    private int tournamentId;
    private int totalNumOfMatches;

    @Autowired
    public TournamentService(TournamentDao tournamentDao, MatchesDao matchesDao, TeamsDao teamsDao) {
        this.tournamentDao = tournamentDao;
        this.matchesDao = matchesDao;
        this.teamsDao = teamsDao;
    }


    @Override
    public List<Matches> generateBracket(List<Teams> teams, int tournamentId) {
        roundCounter = 1; // could set this differently in a generate matches call, then just set roundCounter in initial matches
        createdMatches = new ArrayList<>();
        allMatches = new ArrayList<>();
        this.tournamentId = tournamentId;
        for (Teams teams1 : teams) {
            System.out.println(teams1.getTeamName() + ", ");
        }
        System.out.println("Original Teams: " + teams);
        int pow2 = 1;
        while (pow2 < teams.size()) {
            pow2 = pow2 * 2;
        }
        int remainder = pow2 - teams.size();
        System.out.println("pow2:" + pow2);

        Collections.shuffle(teams);
//        teams = shuffle(teams);
        List<Teams[]> pairs = new ArrayList<>();

        while (teams.size() > remainder) {
            Matches match = new Matches();
            Teams[] pair = new Teams[2];
            pair[0] = teams.get(0);
            match.setHomeTeamId(pair[0].getTeamId());
            teams.remove(0);
            pair[1] = teams.get(0);
            match.setAwayTeamId(pair[1].getTeamId());
            teams.remove(0);
            pairs.add(pair);
            match.setTournamentId(tournamentId);
            match.setLocationId(1);
            match.setRoundNumber(1);
            match.setWinningTeamId(pair[1].getTeamId());
            match.setRoundNumber(1); // might want to refactor so we can reuse this for subsequent matches too
            allMatches.add(match);
            System.out.println("Away Team: " + match.getAwayTeamId() + "Home Team: " + match.getHomeTeamId());
            createdMatches.add(matchesDao.createMatch(match, tournamentId));
        }
//        for(Matches match : allMatches){
//            matchesDao.createMatch(match, tournamentId);
//        }

        // generate placeholder bye "matches" for remaining unpaired teams
        for (Teams team : teams) {
            Matches match = new Matches();
            match.setHomeTeamId(team.getTeamId());
            match.setAwayTeamId(13);
            match.setTournamentId(tournamentId);
            match.setRoundNumber(1);
            allMatches.add(match);
            createdMatches.add(matchesDao.createMatch(match, tournamentId));
        }

        System.out.println("All Matches: " + allMatches);

        for (Teams[] teams1 : pairs) {
            System.out.println(teams1[0].getTeamName() + " vs " + teams1[1].getTeamName());
        }
        System.out.println("Shuffled Teams: " + pairs);
        System.out.println("Number of Paired Teams: " + pairs.size());

        List<Teams> byes = teams;
        int round2NumberOfTeams = pow2 / 2;
        System.out.println("round1numofteams is " + round2NumberOfTeams);
        int numRounds = 1;
        int currentTeams = round2NumberOfTeams;

        System.out.println("Teams not paired: ");
        for (Teams teams1 : teams) {
            Matches byeMatches = new Matches();
            byeMatches.setHomeTeamId(teams1.getTeamId());
//            byeMatches.setAwayTeamId();
            byeMatches.setRoundNumber(1);
            System.out.println(teams1.getTeamName() + ", ");

        }


        while (currentTeams >= 2) {
            numRounds++;
            currentTeams = currentTeams / 2;
        }
        System.out.println("Number of Rounds: " + numRounds);
        int numMatches;
        int numMatchesInclByes;

        if (remainder == 0) {
            numMatches = pow2 - 1;
            numMatchesInclByes = numMatches;
        } else {
            numMatches = round2NumberOfTeams - 1 + pairs.size();
            numMatchesInclByes = numMatches + byes.size();
        }
        this.totalNumOfMatches = numMatchesInclByes;
        System.out.println("Num of Matches: " + numMatches);
        System.out.println("Num of Matches with byes: " + numMatchesInclByes);

        // assign round number for subsequent matches
        System.out.println("round 2 num of teams / 2 is " + round2NumberOfTeams / 2);
        assignRounds(round2NumberOfTeams / 2);
        return createdMatches;
    }

    private void assignRounds(int numMatchesInRound) {
        roundCounter++;
        for (int i = 1; i <= numMatchesInRound; i++) {
            Matches match = new Matches();
            match.setHomeTeamId(13);
            match.setAwayTeamId(13);
            match.setTournamentId(tournamentId);
            match.setRoundNumber(roundCounter);
            allMatches.add(match);
            createdMatches.add(matchesDao.createMatch(match, tournamentId));
        }
        if (allMatches.size() < totalNumOfMatches) {
            assignRounds(numMatchesInRound / 2);
        }
    }

    @Override
    public List<Matches> generateMatchesByRound(List<Teams> teams) {
        List<Matches> matchesByRound = new ArrayList<>();


        return null;
    }

    @Override
    public List<Matches> updateBracket(List<Teams> teams, int tournamentId, int roundNum) {


        List<Matches> listMatches = matchesDao.getMatchesByTournamentAndRound(tournamentId, roundNum);

        Collections.shuffle(teams);
        System.out.println(teams);

        for (Matches match : listMatches) {
            match.setHomeTeamId(teams.get(0).getTeamId());
            teams.remove(0);
            match.setAwayTeamId(teams.get(0).getTeamId());
            teams.remove(0);
            matchesDao.updateBracketMatches(match);
        }

        return listMatches;
    }

//    }
//    public void testRun(){
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
//        generateBracket(teamsList, 1);
//    }


    @Override
    public void advanceWinner() {

    }


    @Override
    public void endRound(List<Matches> matches) {


    }
}

/*
//FIRST calculate byes and assign pairs for matches

const originalTeamList = ['a1', 'a2', 'a3', 'a4', 'a5', 'a6', 'a7', 'a8', 'a9', 'a10', 'a11', 'a12', 'a13', 'a14', 'a15'];

// calculate powers of 2 until equal or greater to number of teams
let pow2 = 1;
while (pow2 < originalTeamList.length) {
	pow2 = pow2 * 2;
}

// subtract teams from the found power of 2 to find number of byes
let remainder = pow2 - originalTeamList.length;

console.log(pow2 + ' and remainder of ' + remainder)


//Fisher-Yates algorithm for randomly shuffling a sequence
function shuffle (list) {
  for (let i = list.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    const temp = list[i];
    list[i] = list[j];
    list[j] = temp;
  }
  return list;
}

const shuffledTeams = shuffle(originalTeamList);

console.log(shuffledTeams)

const pairs = [];

// pop is destructive, so each team popped from the shuffle will be added to a new pair and removed from the original. when the length == number of byes, stop pairing
while (shuffledTeams.length > remainder) {
	let pair = [];
  pair.push(shuffledTeams.pop());
  pair.push(shuffledTeams.pop());
  pairs.push(pair);
}

for (let i = 0; i < pairs.length; i++) {
	console.log('pair ' + i + ': ' + pairs[i])
}

console.log(' and remainder: ' + shuffledTeams);
const byes = shuffledTeams;

// NEXT calculate how many rounds will be needed
// round 2: num of teams will be nearest power of 2 below starting number (so pow2/2)
// once num is a power of 2, each round will have half the previous num of teams
// then add 1 to account for uneven first round

const round2NumTeams = pow2/2; // will be true even if byes exist

let numRounds = 1;
let currentTeams = round2NumTeams;

while (currentTeams >= 2) {
	numRounds++;
  currentTeams = currentTeams / 2;
}

console.log('rounds: ' + numRounds)

// NEXT num of matches
// formula for num of matches when num of teams is a power of 2: numTeams - 1
// add the number of pairs to account for round 1 matches when original lineup wasn't a power of 2
// optionally? add every bye as a ghost match
	// possibly useful when determining second round

let numMatches;
let numMatchesInclByes;

if (remainder == 0) {
	numMatches = pow2 - 1;
  numMatchesInclByes = numMatches;
} else {
	numMatches = round2NumTeams - 1 + pairs.length;
  numMatchesInclByes = numMatches + byes.length;
}

console.log(numMatches, numMatchesInclByes)
 */
