<template>
  <div>
      <div class="container">
        <div class="prologue">
                    <h3>Bracket</h3>
            <router-link v-bind:to="{name: 'manage-bracket', params: {id: this.tournamentID, matches: this.matches, tournamentID: this.tournamentID}}"> <button v-if="isHost && matches.length > 0">Edit Bracket</button></router-link>
            <button v-if="isHost && matches.length == 0" v-on:click.prevent="generateBracket()">Generate Matches</button>
            <button v-if="isHost && canGenerateNextRound" v-on:click.prevent="generateNextRound()">Generate Matches for Next Round</button>
        </div>
        <p v-if="matches.length == 0">Schedule TBD</p>
        <table id="schedule" v-if="matches.length > 0">
            <tr>
                <th>Round</th>
                <th>Home Team</th>
                <th>Away Team</th>
                <th>Location</th>
                <th>Start Date</th>
                <th>Start Time</th>
                <th>Winner</th>
            </tr>
            <tr v-for="match in sortedByStartDate" v-bind:key="match.matchId">
                <td>{{match.roundNumber}}</td>
                <td>{{getTeamNameFromTeamList(match.homeTeamId)}}</td>
                <td>{{getTeamNameFromTeamList(match.awayTeamId)}}</td>
                <td>{{getLocationNameFromLocationList(match.locationId)}}</td>
                <td>{{match.startDate}}</td>
                <td>{{match.startTime}}</td>
                <td>{{getTeamNameFromTeamList(match.winningTeamId) || "TBD"}}</td>
                <td><button v-on:click="addWinner(match)" v-if="match.winningTeamId == 0">Add Winner</button></td>
            </tr>
        </table>
      </div>
  </div>
</template>

<script>
import TournamentService from "@/services/TournamentService.js";
export default {
    name: 'bracket',
    props: {
        tournamentID: Number
    },
    methods: {
        generateBracket() {
            TournamentService.createBracketForTournament(this.tournamentID).then((response) => {
                if (response.status == 200) {
                        this.$router.push({name: "manage-bracket", params: {id: this.tournamentID, matches: response.data, tournamentID: this.tournamentID, teams: this.tournamentTeams}});
                    }
            });
        },
        generateNextRound() {
            TournamentService.createMatchesForNextRound(this.tournamentID, this.nextRound).then((response) => {
                if (response.status == 200) {
                        this.$router.push({name: "manage-bracket", params: {id: this.tournamentID, matches: response.data, tournamentID: this.tournamentID, teams: this.tournamentTeams}});
                    }
            });
        },
        getTeamNameFromTeamList(teamId) {
            const activeTeam = this.tournamentTeams.find((team) => {
                if (team.teamId == teamId) {
                    return true;
                }
            });
            if (activeTeam) {
                return activeTeam.teamName;
            }
            return '';
        },
        getLocationNameFromLocationList(locationId) {
            const activeLocation = this.locations.find((location) => {
                if (location.locationId == locationId) {
                    return true;
                }
            })
            if (activeLocation && activeLocation.cityName) {
                return `${activeLocation.cityName}, ${activeLocation.stateName}`
            }
            return '';
        },
        compareDate(match1, match2) {
            // checks regular dates first, then date + time if dates are equal
            if (match1.startDate == null) {
                return +1;
            }
            if (match2.startDate == null) {
                return 0;
            }
            if (match1.startDate.toString() > match2.startDate.toString()) {
                return 1;
            } else if (match1.startDate.toString() < match2.startDate.toString()) {
                return -1;
            }
            if (match1.startTime == null) {
                return +1;
            } else if (match2.startTime == null) {
                return 0;
            } else if ((match1.startDate.toString() + match1.startTime.toString()) > (match2.startDate.toString() + match2.startTime.toString())) {
                return 1;
            } else if ((match1.startDate.toString() + match1.startTime.toString()) < (match2.startDate.toString() + match2.startTime.toString())) {
                return -1;
            }
            return 0;
        },
        addWinner(currentMatch) {
            this.$router.push({name: 'winner-form', params: {id: currentMatch.matchId}})
        },
        // check for the most recent round where num of matches = num of winners declared, and set to 0 if no rounds completed
        // then check if a match in the round after that has a bye, and if so, next round must be generated
        checkCompletedRounds() {
            let recordMatches = {}; // number of matches per round
            let recordWins = {}; // number of wins per round
            let validRounds = []; // number of rounds in this set of matches
            for (let match of this.matches) {
                if (recordMatches[match.roundNumber] >= 1) {
                    recordMatches[match.roundNumber]++;
                } else {
                    recordMatches[match.roundNumber] = 1;
                    validRounds.push(match.roundNumber)
                }
                if (recordWins[match.roundNumber] >= 1 && match.winningTeamId != 0) {
                    recordWins[match.roundNumber]++;
                } else if (match.winningTeamId != 0) {
                    recordWins[match.roundNumber] = 1;
                } else if (match.winningTeamId == 0) {
                    recordWins[match.roundNumber] = 0;
                }
            }
            let mostRecentlyCompleted = 0;
            for (let round in validRounds) {
                if (recordMatches[round] == recordWins[round]) {
                    mostRecentlyCompleted = Number(round);
                } 
            }
            let readyForNextRound = false;

            if (mostRecentlyCompleted == 0) {
                return readyForNextRound;
            }

            // if any match of round after 1 has bye, that round hasn't had matches generated yet
            for (let match of this.matches) {
                if (match.roundNumber == mostRecentlyCompleted+1) {
                    if (match.homeTeamId == 13 || match.awayTeamId == 13) {
                        readyForNextRound = true;
                        break;
                    }
                }
            }
            if (readyForNextRound) {
                this.nextRound = mostRecentlyCompleted+1;
            }
            return readyForNextRound;
        }
    },
    computed: {
        sortedByStartDate() {
            return this.matches.slice().sort(this.compareDate);
        },
        canGenerateNextRound() {
            return this.checkCompletedRounds();
        }
    },
    created () {
        TournamentService.getMatchesByTournamentId(this.tournamentID).then((response) => {
                if (response.status == 200) {
                    this.matches = response.data;
                            console.log(this.checkCompletedRounds())
                    TournamentService.getParticipantsInTournament(this.$route.params.id).then(response => {
                        if (response.status == 200) {
                            this.tournamentTeams = response.data;
                        }
                    })            
                }
            });
        TournamentService.getAllLocations().then((response) => {
                if (response.status == 200) {
                    this.locations = response.data;            
                }
            })
    },
    data () {
        return {
            isHost: this.$store.state.user.role == 'ROLE_HOST',
            winnerForm: false,
            tournamentTeams: [],
            matches: [],
            locations: [],
            nextRound: 0
        }
    }
}
</script>

<style scoped>
    img {
        width: 500px;
    }
    th {
        text-transform: uppercase;
        background-color: goldenrod;
        color: white;
        font-size: 90%;
        padding: 6px;
    }
    div {
        color:white;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    h3 {
        color:#DC8400;
        margin-right: 1rem;
    }
    td {
        font-size: 90%;
    }
    .prologue {
        margin-bottom: 0.5rem;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    button {
        text-transform: uppercase;
        outline: 0;
        background-color: goldenrod;
        /* background-image: linear-gradient(45deg,#328f8a,#08ac4b); */
        /* width: 100%; */
        border: 0;
        padding: 5px;
        color: #FFFFFF;
        font-size: 14px;
        -webkit-transition: all 0.3 ease;
        transition: all 0.3 ease;
        cursor: pointer;
        margin-left: 0.5rem;
}
</style>