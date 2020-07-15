import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Objects;

public class Party {
    private long contestId;
    private ArrayList<Member> members;
    private ParticipantType participantType;
    private long teamId;
    private String teamName;
    private boolean ghost;
    private long room;
    private long startTimeSeconds;


    public Party(long contestId, ArrayList<Member> members, ParticipantType participantType, long teamId, String teamName, boolean ghost, long room, long startTimeSeconds) {
        this.contestId = contestId;
        this.members = members;
        this.participantType = participantType;
        this.teamId = teamId;
        this.teamName = teamName;
        this.ghost = ghost;
        this.room = room;
        this.startTimeSeconds = startTimeSeconds;
    }
    public Party(JSONObject json){
        long contestId = json.get("contestId") == null ? -1 :(long)json.get("contestId");
        JSONArray arr = (JSONArray) json.get("members");
        JSONParser parser = new JSONParser();
        ArrayList<Member> mems = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            JSONObject handle = (JSONObject) arr.get(i);
            mems.add(Member.parseJSON(handle));
        }
        ParticipantType type = ParticipantType.valueOf((String) json.get("participantType"));
        long teamId = json.get("teamId") == null ? -1 :(long) json.get("teamId");
        String teamName = (String) json.get("teamName");
        boolean ghost = json.get("ghost") != null && (boolean) json.get("ghost");
        //System.out.println(json.get("room"));
        long room = json.get("room") == null ? -1 :(long) json.get("room");
        long startTimeSeconds = json.get("startTimeSeconds") == null ? -1 :(long) json.get("startTimeSeconds");
        Party ans = new Party(contestId, mems, type, teamId, teamName, ghost, room, startTimeSeconds);
    }
    public long getContestId() {
        return contestId;
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
    public ParticipantType getParticipantType() {
        return participantType;
    }
    public long getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public boolean isGhost() {
        return ghost;
    }
    public long getRoom() {
        return room;
    }
    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return contestId == party.contestId &&
                teamId == party.teamId &&
                ghost == party.ghost &&
                room == party.room &&
                startTimeSeconds == party.startTimeSeconds &&
                Objects.equals(members, party.members) &&
                participantType == party.participantType &&
                Objects.equals(teamName, party.teamName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(contestId, members, participantType, teamId, teamName, ghost, room, startTimeSeconds);
    }
    @Override
    public String toString() {
        return "Party{" +
                "contestId=" + contestId +
                ", members=" + members +
                ", participantType=" + participantType +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", ghost=" + ghost +
                ", room=" + room +
                ", startTimeSeconds=" + startTimeSeconds +
                '}';
    }

    public static Party parseJSON(JSONObject json){
        return new Party(json);
    }


}
