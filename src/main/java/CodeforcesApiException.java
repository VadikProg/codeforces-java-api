public class CodeforcesApiException extends Exception {
    private String comment;
    public CodeforcesApiException(String comment){
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
