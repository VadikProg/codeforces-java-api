public class Main {
    public static void main(String[] args) {
        CFJA cf = new CFJA();
        try {
            System.out.println(cf.blogEntryComments(0));
        }catch (CodeforcesApiException e){
            System.out.println(e.getComment());
        }

    }
}
