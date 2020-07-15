import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlogEntry {
    private long id;
    private String originalLocale;
    private long creationTimeSecond;
    private String authorHandle;
    private String title;
    private String content;
    private String locale;
    private long modificationTimeSeconds;
    private boolean allowViewHistory;
    private ArrayList<String> tags;
    private long rating;

    public BlogEntry(long id, String originalLocale, long creationTimeSecond, String authorHandle, String title, String content, String locale, long modificationTimeSeconds, boolean allowViewHistory, ArrayList<String> tags, long rating) {
        this.id = id;
        this.originalLocale = originalLocale;
        this.creationTimeSecond = creationTimeSecond;
        this.authorHandle = authorHandle;
        this.title = title;
        this.content = content;
        this.locale = locale;
        this.modificationTimeSeconds = modificationTimeSeconds;
        this.allowViewHistory = allowViewHistory;
        this.tags = tags;
        this.rating = rating;
    }

    public BlogEntry(JSONObject json){
        this.id = (long) json.get("id");
        this.originalLocale  = (String)json.get("originalLocale");
        this.creationTimeSecond = (long)json.get("creationTimeSeconds");
        this.authorHandle = (String) json.get("authorHandle");
        this.title =  (String) json.get("title");
        this.content = (String) json.get("content");
        this.modificationTimeSeconds = (long)json.get("modificationTimeSeconds");
        this.allowViewHistory = (boolean)json.get("allowViewHistory");
        this.rating  = (long)json.get("rating");
        JSONArray JsonArr = (JSONArray) json.get("tags");
        String[] Arr = (String[]) JsonArr.toArray();
        this.tags = new ArrayList<>(Arrays.asList(Arr));
    }

    public long getId() {
        return id;
    }
    public String getOriginalLocale() {
        return originalLocale;
    }
    public long getCreationTimeSecond() {
        return creationTimeSecond;
    }
    public String getAuthorHandle() {
        return authorHandle;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getLocale() {
        return locale;
    }
    public long getModificationTimeSeconds() {
        return modificationTimeSeconds;
    }
    public boolean isAllowViewHistory() {
        return allowViewHistory;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    public long getRating() {
        return rating;
    }
    @Override
    public String toString() {
        return "BlogEntry{" +
                "id=" + id +
                ", originalLocale='" + originalLocale + '\'' +
                ", creationTimeSecond=" + creationTimeSecond +
                ", authorHandle='" + authorHandle + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", locale='" + locale + '\'' +
                ", modificationTimeSeconds=" + modificationTimeSeconds +
                ", allowViewHistory=" + allowViewHistory +
                ", tags=" + tags.toString() +
                ", rating=" + rating +
                '}';
    }
    public BlogEntry parseJSON(JSONObject json){
        return new BlogEntry(json);
    }

}

