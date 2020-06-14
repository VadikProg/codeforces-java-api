import org.json.simple.JSONObject;

public class JudgeProtocol {
    private boolean manual;
    private String protocol;
    private String verdict;

    public JudgeProtocol(boolean manual, String protocol, String verdict) {
        this.manual = manual;
        this.protocol = protocol;
        this.verdict = verdict;
    }

    public boolean isManual() {
        return manual;
    }
    public String getProtocol() {
        return protocol;
    }
    public String getVerdict() {
        return verdict;
    }

    public static JudgeProtocol parseJSON(JSONObject json){
        boolean manual = Boolean.valueOf((String) json.get("manual"));
        String protocol = (String) json.get("protocol");
        String verdict = (String) json.get("verdict");

        return new JudgeProtocol(manual, protocol, verdict);
    }

}
