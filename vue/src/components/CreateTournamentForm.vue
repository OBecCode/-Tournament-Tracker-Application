<template>
  <div id="form">
      <form v-on:submit.prevent="submitForm()">
          <label for="tournamentName" id="name">Tournament Name: </label>
          <input type="text" name="tournamentName" v-model="newTournament.tournamentName">
          <br>
          <label for="sportType">Sport or Game: </label>
          <select id="sportType" name="sportType" v-model="newTournament.sportId">
              <option value='' disabled></option>
              <option v-for="sport in this.$store.state.sportList" v-bind:key="sport.sportId" v-bind:value="sport.sportId">{{sport.sportName}}</option>
              <option value="newSport">Add New Sport </option>
          </select>
          <p v-if="newTournament.sportId == 'newSport'">
              <label for="newSportName">New Sport Name: </label>
              <input type="text" name="newSportName" id="newSportName" v-model="newSport.sportName">
          </p>
          <br>
          <label for="startDate">Start Date: </label>
          <input type="date" name="startDate" v-model="newTournament.startDate">
          <br>
          <label for="endDate">End Date: </label>
          <input type="date" name="endDate" v-model="newTournament.endDate">
          <br>
          <label for="tournamentDescription"> Description: </label>
          <input type="text" placeholder="Enter a brief description pertaining to the tournament you are entering in!" v-model="newTournament.description" >
          <br>
          <br>
          <button id="btn" type="submit"> Create Tournament </button>
      </form>
  </div>
</template>

<script>
import TournamentService from '@/services/TournamentService.js';

export default {
    name: "create-tournament-form",
    data() {
        return {
            newTournament: {
                tournamentName: '',
                startDate: '',
                endDate: '',
                sportId: '',
                numOfTeams: 0,
                description: '',
                organizerId: this.$store.state.user.id
                
            },
            newSport: {
                sportName: ''
            }
        }
    },
    created(){
        if (this.$store.state.sportList.length == 0) {
            TournamentService.getSportsList().then((response) => {
                if (response.status == 200) {
                    this.$store.commit("SET_SPORT_LIST", response.data);
                }
            });
        }
    },
    methods: {
        submitForm() {
            if (this.newSport.sportName != '') {
                TournamentService.createSport(this.newSport).then((response) => {
                if (response.status == 200) {
                    this.$store.commit("ADD_SPORT_TO_LIST", response.data);
                    console.log(response.data.sportId + ' is sportid')
                    this.newTournament.sportId = Number(response.data.sportId);
                    this.createTournament();
                }
            })
        
            } else {
                this.createTournament();
            }
        },
        createTournament() {
            this.$store.commit("ADD_TOURN_TO_LIST", this.newTournament);
            TournamentService.createTournament(this.newTournament).then((response) => {
                if (response.status == 200) {
                    const newID = Number(response.data.tournamentId);
                    this.$router.push({name: 'view-tournament', params: {id: newID}});
                } else {
                    console.log(response);
                }
            })
        }
    }
}
</script>

<style>
#tournament-form{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#form{
    
    position: relative;
  background: black #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  display: flex;
  flex-direction: column;
  color: goldenrod;
}

</style>