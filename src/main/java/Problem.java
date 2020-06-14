import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Problem {
    private long contestId;
    private String problemsetName;
    private String index;
    private String name;
    private Type type;
    private double points;
    private long rating;
    private ArrayList<String> tags;
    public Problem(long contestId, String problemsetName, String index, String name, Type type, ArrayList<String> tags){
        this.contestId = contestId;
        this.problemsetName = problemsetName;
        this.index = index;
        this.name = name;
        this.type = type;
        this.tags = tags;
    }
    public Problem(long contestId, String problemsetName, String index, String name, Type type,double points, long rating, ArrayList<String> tags){
        this(contestId, problemsetName, index, name, type, tags);
        this.points = points;
        this.rating = rating;
    }

    public long getContestId() {
        return contestId;
    }
    public String getProblemsetName() {
        return problemsetName;
    }
    public String getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public double getPoints() {
        return points;
    }
    public long getRating() {
        return rating;
    }
    public ArrayList<String> getTags() {
        return tags;
    }

    public static Problem parseJSON(JSONObject json){
        long contestId = json.get("contestId") == null ? -1 : (long) json.get("contestId");
        String  problemsetName = (String) json.get("problemsetName");
        String index = (String)json.get("index");
        String name = (String)json.get("name");
        Type type = Type.valueOf((String) json.get("type"));
        double points = json.get("points") == null ? -1 : (double)json.get("points");
        long rating = json.get("rating") == null ? -1 : (long) json.get("rating");
        ArrayList<String> tags = new ArrayList<>();
        JSONArray tds = (JSONArray) json.get("tags");
        JSONParser jfk = new JSONParser();
        for(int i = 0; i < tds.size(); i++){
            tags.add(tds.get(i).toString());
        }
        Problem ans = new Problem(contestId, problemsetName, index, name, type, points, rating, tags);
        return ans;
    }

}
