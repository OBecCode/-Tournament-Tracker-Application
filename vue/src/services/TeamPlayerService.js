import axios from 'axios';
import TournamentService from '@/services/TournamentService.js';

export default {

    //repeat of method in tournament service, will eventually move methods for better organization
    getUserPlayerID(userID) {
        return axios.get(`/players/user/${userID}`);
    },

    getTeamByUserId(userId) {
        let playerId;
        let team;
        TournamentService.getUserPlayerID(userId).then((response) => {
            console.log(userId)
            console.log('response from get userplayerid: ' + response.data)
            if (response.status == 200) {
                if (response.data.playerId != 0) {
                    playerId = response.data.playerId;
                    TournamentService.getTeamOfPlayer(playerId).then((response) => {
                        console.log('got team of player')
                        if (response.status == 200) {
                            team = response.data;
                            return Promise.resolve(team);
                        }
                    })
                }
            }
        })

    }

}