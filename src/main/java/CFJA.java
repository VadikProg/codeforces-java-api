import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.io.*;


public class CFJA {
    private String key = "";
    private String secret = "";
    private final String adress = "https://codeforces.com/api/";
    private static String lang = "en";


    public CFJA(String TokenKey, String TokenSecret){
        key = TokenKey;
        secret = TokenSecret;
    }
    public CFJA(String TokenKey, String TokenSecret, String language){
        this(TokenKey, TokenSecret);
        if(language.equals("ru")){
            this.lang = language;
        }
    }
    public CFJA(){}
    public static String getLanguage(){
        return lang;
    }


    private static JSONObject request(String link){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JSONObject json = new JSONObject();
        //Creating a HttpGet object
        HttpGet httpget = new HttpGet(link);
        try {
            HttpResponse httpresponse = httpclient.execute(httpget);
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));
            //System.out.println(httpresponse.getStatusLine());
            String ans = reader.readLine();
            JSONParser parser = new JSONParser();
            json = (JSONObject)parser.parse(ans);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return json;
    }

    public ArrayList<Comment> blogEntryComments(int id) throws CodeforcesApiException{
        JSONObject json = request("https://codeforces.com/api/blogEntry.comments?blogEntryId=" + id + "&lang=" + lang);

        //System.out.println(json.toJSONString());
        String status = (String)json.get("status");

        ArrayList<Comment> ans = new ArrayList<>();
        try {
            if(status.equals("OK")){
                JSONArray result = (JSONArray) json.get("result");
                JSONParser ps = new JSONParser();

                for(int i = 0; i < result.size(); i++){
                    JSONObject now = (JSONObject)ps.parse(result.get(i).toString());
                    ans.add(Comment.parseJSON(now));
                }
            }else{
                String comment = (String) json.get("comment");
                throw new CodeforcesApiException(comment);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return ans;
    }
    public BlogEntry blogEntryView(int id) throws CodeforcesApiException{
        JSONObject json = request("https://codeforces.com/api/blogEntry.view?blogEntryId=" + id + "&lang=" + lang);
        String status = (String)json.get("status");
        BlogEntry ans = null;
        if(status.equals("OK")){
            JSONObject now = (JSONObject) json.get("result");
            ans = new BlogEntry(now);
        }else{
            String comment = (String) json.get("comment");
            throw new CodeforcesApiException(comment);
        }
        return ans;
    }
    public ArrayList<Hack> contestHacks(long contestId) throws CodeforcesApiException {
        ArrayList<Hack> ans = new  ArrayList<>();
        JSONObject json = request("https://codeforces.com/api/contest.hacks?contestId=" + contestId);
        //System.out.println(json.toJSONString());
        if(json.get("status").equals("OK")){
            JSONArray jsonres = (JSONArray) json.get("result");
            JSONParser parser = new JSONParser();
            try {
                for(int i = 0; i < jsonres.size(); i++){
                    JSONObject now = (JSONObject) parser.parse(jsonres.get(i).toString());
                    ans.add(Hack.parseJSON(now));
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }else{
            String comment = (String) json.get("comment");
            throw new CodeforcesApiException(comment);
        }


        return ans;
    }
    public ArrayList<Contest> contestList(boolean gym) throws CodeforcesApiException{
        ArrayList<Contest> ans = new ArrayList<>();
        JSONObject json = request("https://codeforces.com/api/contest.list?gym=" + String.valueOf(gym));
        if(json.get("status").equals("OK")){
            JSONArray jsonres = (JSONArray) json.get("result");
            JSONParser parser = new JSONParser();
            try {
                for(int i = 0; i < jsonres.size(); i++){
                    JSONObject now = (JSONObject) parser.parse(jsonres.get(i).toString());
                    ans.add(Contest.parseJSON(now));
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }else{
            String comment = (String) json.get("comment");
            throw new CodeforcesApiException(comment);
        }
        return ans;
    }

}
