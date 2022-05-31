package com.techelevator.dao;

import com.techelevator.model.Invitation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcInvitationDao implements InvitationDao{


    private final JdbcTemplate jdbcTemplate;

    public JdbcInvitationDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }


    @Override
    public void createInvitation(Invitation invitation) {

        String sql = "INSERT INTO invitations (tournament_id, team_id, organizer_id, inv_status) " +
                     "VALUES                 (?, ?, ?, ?);";

        int newInvId = jdbcTemplate.update(
                sql,
                invitation.getTournamentId(),
                invitation.getTeamId(),
                invitation.getOrganizerId(),
                invitation.getInviteStatus());



    }



    @Override
    public List<Invitation> getInvitationByStatus(String status) {

        List<Invitation> invitations = new ArrayList<>();

        String sql = "SELECT invitation_id, tournament_id, team_id, organizer_id, inv_status " +
                     "FROM invitations " +
                     "WHERE inv_status = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, status);

        while(results.next()){
            invitations.add(mapRowToInvites(results));
        }

        return invitations;
    }

    @Override
    public List<Invitation> getInvitationByTeamId(int id) {

        List<Invitation> invitations = new ArrayList<>();

        String sql = "SELECT invitations.inv_status, teams.team_name, players.player_name, tournaments.tournament_name, " +
                "     invitations.invitation_id, invitations.tournament_id, invitations.team_id, invitations.organizer_id  " +
                "     FROM invitations " +
                "     JOIN tournaments ON invitations.tournament_id = tournaments.tournament_id" +
                "     JOIN teams ON invitations.team_id = teams.team_id " +
                "     JOIN player_team ON teams.team_id = player_team.team_id " +
                "     JOIN players ON player_team.player_id = players.player_id " +
                "     WHERE teams.team_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            invitations.add(mapRowToInvites(results));
        }

        return invitations;
    }

    @Override
    public List<Invitation> getInvitationsByOrganizer(int id) {

        List<Invitation> invitations = new ArrayList<>();

        String sql = "SELECT invitation_id, tournament_id, team_id, organizer_id, inv_status " +
                     "FROM invitations " +
                     "WHERE organizer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            invitations.add(mapRowToInvites(results));
        }

        return invitations;
    }

    @Override
    public Invitation updateInvitationStatus(Invitation invitation) {

        String sql = "UPDATE invitations " +
                     "SET inv_status = ? " +
                     "WHERE invitation_id = ?;";


        jdbcTemplate.update(sql, invitation.getInviteStatus(), invitation.getInvitationId());

        return invitation;

    }



    @Override
    public List<Invitation> getInvitationsByTournamentId(int tournamentId) {

        List<Invitation> invitations = new ArrayList<>();

        String sql = "SELECT invitations.inv_status, teams.team_name, players.player_name, tournaments.tournament_name, " +
                " invitations.invitation_id, invitations.tournament_id, invitations.team_id, invitations.organizer_id " +
                "     FROM invitations " +
                "     JOIN tournaments ON invitations.tournament_id = tournaments.tournament_id" +
                "     JOIN teams ON invitations.team_id = teams.team_id " +
                "     JOIN player_team ON teams.team_id = player_team.team_id " +
                "     JOIN players ON player_team.player_id = players.player_id " +
                "     WHERE tournaments.tournament_id = ?;";

       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);

       while(results.next()){
           invitations.add(mapRowToInvites(results));
       }

       return invitations;
    }


    private Invitation mapRowToInvites(SqlRowSet row){

        Invitation invitation = new Invitation();

        invitation.setInvitationId(row.getInt("invitation_id"));
        invitation.setTournamentId(row.getInt("tournament_id"));
        invitation.setTeamId(row.getInt("team_id"));
        invitation.setOrganizerId(row.getInt("organizer_id"));
        invitation.setInviteStatus(row.getString("inv_status"));
        invitation.setTeamName(row.getString("team_name"));
        invitation.setTournamentName(row.getString("tournament_name"));
        invitation.setPlayerName(row.getString("player_name"));

        return invitation;



    }
}
