import org.json.simple.JSONObject;

import java.util.Objects;

public class Contest {
    private long id;
    private String name;
    private ContestType type;
    private ContestPhase phase;
    private boolean frozen;
    private long durationSeconds;
    private long startTimeSeconds;
    private long relativeTimeSeconds;
    private String preparedBy; //can be null
    private String websiteUrl; //can be null
    private String description; //can be null
    private int difficulty; // 1 - 5
    private String kind; //can be null
    private String icpcRegion; //can be null
    private String country; //can be null
    private String city; //can be null
    private String season; //can be null

    public Contest(long id, String name, ContestType type, ContestPhase phase, boolean frozen, long durationSeconds, long startTimeSeconds, long relativeTimeSeconds, String preparedBy, String websiteUrl, String description, int difficulty, String kind, String icpcRegion, String country, String city, String season) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phase = phase;
        this.frozen = frozen;
        this.durationSeconds = durationSeconds;
        this.startTimeSeconds = startTimeSeconds;
        this.relativeTimeSeconds = relativeTimeSeconds;
        this.preparedBy = preparedBy;
        this.websiteUrl = websiteUrl;
        this.description = description;
        this.difficulty = difficulty;
        this.kind = kind;
        this.icpcRegion = icpcRegion;
        this.country = country;
        this.city = city;
        this.season = season;
    }
    //Delete prefere commit
    public Contest() {
    }


    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ContestType getType() {
        return type;
    }
    public ContestPhase getPhase() {
        return phase;
    }
    public boolean isFrozen() {
        return frozen;
    }
    public long getDurationSeconds() {
        return durationSeconds;
    }
    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }
    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }
    public String getPreparedBy() {
        return preparedBy;
    }
    public String getWebsiteUrl() {
        return websiteUrl;
    }
    public String getDescription() {
        return description;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public String getKind() {
        return kind;
    }
    public String getIcpcRegion() {
        return icpcRegion;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getSeason() {
        return season;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return id == contest.id &&
                frozen == contest.frozen &&
                durationSeconds == contest.durationSeconds &&
                startTimeSeconds == contest.startTimeSeconds &&
                relativeTimeSeconds == contest.relativeTimeSeconds &&
                difficulty == contest.difficulty &&
                Objects.equals(name, contest.name) &&
                type == contest.type &&
                phase == contest.phase &&
                Objects.equals(preparedBy, contest.preparedBy) &&
                Objects.equals(websiteUrl, contest.websiteUrl) &&
                Objects.equals(description, contest.description) &&
                Objects.equals(kind, contest.kind) &&
                Objects.equals(icpcRegion, contest.icpcRegion) &&
                Objects.equals(country, contest.country) &&
                Objects.equals(city, contest.city) &&
                Objects.equals(season, contest.season);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, phase, frozen, durationSeconds, startTimeSeconds, relativeTimeSeconds, preparedBy, websiteUrl, description, difficulty, kind, icpcRegion, country, city, season);
    }
    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", phase=" + phase +
                ", frozen=" + frozen +
                ", durationSeconds=" + durationSeconds +
                ", startTimeSeconds=" + startTimeSeconds +
                ", relativeTimeSeconds=" + relativeTimeSeconds +
                ", preparedBy='" + preparedBy + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty +
                ", kind='" + kind + '\'' +
                ", icpcRegion='" + icpcRegion + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
    public static Contest parseJSON(JSONObject json){
        long id = (long) json.get("id");
        String name = (String)json.get("name");
        ContestType type = ContestType.valueOf((String) json.get("type"));
        ContestPhase phase = ContestPhase.valueOf((String)json.get("phase"));
        boolean frozen = (boolean) json.get("frozen");
        long durationSeconds = (long) json.get("durationSeconds");
        long startTimeSeconds = (long) json.get("startTimeSeconds") ;
        long relativeTimeSeconds = (long) json.get("relativeTimeSeconds");
        String preparedBy = (String)json.get("preparedBy");
        String websiteUrl = (String)json.get("websiteUrl");
        String description = (String)json.get("description");
        int difficult = (int)json.get("difficult");
        String kind = (String)json.get("kind");
        String icpcRegion = (String)json.get("icpcRegion");
        String country = (String)json.get("country");
        String city = (String)json.get("city");
        String season = (String)json.get("season");
        return new Contest(id ,name, type, phase, frozen, durationSeconds, startTimeSeconds, relativeTimeSeconds, preparedBy, websiteUrl, description, difficult, kind, icpcRegion, country, city, season);
    }

}
