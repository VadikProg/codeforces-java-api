import org.json.simple.JSONObject;

public class Hack {
    private long id;
    private long creationTimeSeconds;
    private Party hacker;
    private Party defender;
    private Verdict verdict;
    private Problem problem;
    private String test;
    private JudgeProtocol judgeProtocol;

    public Hack(long id, long creationTimeSeconds, Party hacker, Party defender, Verdict verdict, Problem problem, String test, JudgeProtocol judgeProtocol) {
        this.id = id;
        this.creationTimeSeconds = creationTimeSeconds;
        this.hacker = hacker;
        this.defender = defender;
        this.verdict = verdict;
        this.problem = problem;
        this.test = test;
        this.judgeProtocol = judgeProtocol;
    }
    public long getId() {
        return id;
    }
    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }
    public Party getHacker() {
        return hacker;
    }
    public Party getDefender() {
        return defender;
    }
    public Verdict getVerdict() {
        return verdict;
    }
    public Problem getProblem() {
        return problem;
    }
    public JudgeProtocol getJudgeProtocol() {
        return judgeProtocol;
    }

    @Override
    public String toString() {
        return "Hack{" +
                "id=" + id +
                ", creationTimeSeconds=" + creationTimeSeconds +
                ", hacker=" + hacker.toString() +
                ", defender=" + defender.toString() +
                ", verdict=" + verdict +
                ", problem=" + problem.toString() +
                ", test='" + test + '\'' +
                ", judgeProtocol=" + judgeProtocol.toString() +
                '}';
    }

    public static Hack parseJSON(JSONObject json){
        long id = (long) json.get("id");
        long creationTimeSeconds = (long) json.get("creationTimeSeconds");
        Party hacker = Party.parseJSON((JSONObject) json.get("hacker"));
        Party defender = Party.parseJSON((JSONObject) json.get("defender"));
        Verdict verdict = Verdict.valueOf((String) json.get("verdict"));
        Problem problem = Problem.parseJSON((JSONObject) json.get("problem"));
        String test = (String)json.get("test");
        JudgeProtocol judgeProtocol = JudgeProtocol.parseJSON((JSONObject) json.get("judgeProtocol"));
        return new Hack(id, creationTimeSeconds, hacker, defender, verdict, problem, test, judgeProtocol);
    }


}

