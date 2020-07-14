import org.json.simple.JSONObject;

import java.util.Objects;

public class Comment {


    private long id;
    private long creationTimeSeconds;
    private String commentatorHandle;
    private String locale;
    private String text;
    private long parentCommentID;
    private long rating;

    public Comment(long id, long creationTimeSeconds, String commentatorHandle, String locale, String text, long parentCommentID, long rating) {
        this.id = id;
        this.creationTimeSeconds = creationTimeSeconds;
        this.commentatorHandle = commentatorHandle;
        this.locale = locale;
        this.text = text;
        this.parentCommentID = parentCommentID;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                creationTimeSeconds == comment.creationTimeSeconds &&
                parentCommentID == comment.parentCommentID &&
                rating == comment.rating &&
                Objects.equals(commentatorHandle, comment.commentatorHandle) &&
                Objects.equals(locale, comment.locale) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTimeSeconds, commentatorHandle, locale, text, parentCommentID, rating);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", creationTimeSeconds=" + creationTimeSeconds +
                ", commentatorHandle='" + commentatorHandle + '\'' +
                ", locale='" + locale + '\'' +
                ", text='" + text + '\'' +
                ", parentCommentID=" + parentCommentID +
                ", rating=" + rating +
                '}';
    }

    public long getId() {
        return id;
    }

    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public String getLocale() {
        return locale;
    }

    public String getText() {
        return text;
    }

    public long getParentCommentID() {
        return parentCommentID;
    }

    public long getRating() {
        return rating;
    }

    public String getCommentatorHandle() {
        return commentatorHandle;
    }

    public static Comment parseJSON(JSONObject json) {
        long id = (long)json.get("id");
        long creationTimeSeconds = (long)json.get("creationTimeSeconds");
        String commentatorHandle = (String) json.get("comment");
        String locale = (String) json.get("locale");
        String text = (String) json.get("text");
        long parentCommentID = json.get("comment") != null ? (long) json.get("comment") : -1;
        long rating = (long) json.get("rating");
        return new Comment(id, creationTimeSeconds, commentatorHandle, locale, text, parentCommentID, rating);
    }

}
