import org.json.simple.JSONObject;

import java.util.Objects;

public class Member {
    private String handle;

    public Member(String handle) {
        this.handle = handle;
    }

    public String getHandle() {
        return handle;
    }

    @Override
    public String toString() {
        return "Member{" +
                "handle='" + handle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(handle, member.handle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handle);
    }

    public static Member parseJSON(JSONObject json){
        return new Member((String) json.get("handle"));
    }
}
