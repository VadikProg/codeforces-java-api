import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.*;
import java.lang.*;
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

    public static JSONObject request(String link) {
        JSONObject json = new JSONObject();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(link);
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
        try{
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                //System.out.println(result);

                JSONParser ps = new JSONParser();
                json = (JSONObject) ps.parse(result);

            }
            response.close();
            httpClient.close();
        }catch (IOException e){

        }catch (ParseException e){

        }

//        System.out.println(response.getProtocolVersion());              // HTTP/1.1
//        System.out.println(response.getStatusLine().getStatusCode());   // 200
//        System.out.println(response.getStatusLine().getReasonPhrase()); // OK
//        System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

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

                    long a = (long) now.get("id");
                    long b = (long) now.get("creationTimeSeconds");
                    String c = (String) now.get("commentatorHandle");
                    String d = (String) now.get("locale");
                    String e = (String) now.get("text");
                    long f = -1;
                    if(now.get("parentCommentId") != null){
                        f = (long)now.get("parentCommentId");
                    }
                    long g = (long)now.get("rating");
                    ans.add(new Comment(a, b, c, d, e, f, g));
                }
            }else{
                String comment = (String) json.get("comment");
                throw new CodeforcesApiException(comment);
            }
        }catch (ParseException e){

        }
        return ans;
    }

    public BlogEntry blogEntryView(int id) throws CodeforcesApiException{
        JSONObject json = request("https://codeforces.com/api/blogEntry.view?blogEntryId=" + id + "&lang=" + lang);
        String status = (String)json.get("status");
        BlogEntry ans = null;
        if(status.equals("OK")){
            JSONObject res = (JSONObject) json.get("result");
            long a = (long) res.get("id");
            String g = (String)res.get("originalLocale");
            long b = (long)res.get("creationTimeSeconds");
            String c = (String) res.get("authorHandle");
            String d = (String) res.get("title");
            long e = (long)res.get("modificationTimeSeconds");
            boolean f = (boolean)res.get("allowViewHistory");
            JSONArray arr = (JSONArray) res.get("tags");
            long nd = (long)res.get("rating");
            String[] afr = Arrays.copyOf(arr.toArray(), arr.toArray().length, String[].class);
            ans = new BlogEntry(a, g, b, c, d, e, f, afr, nd);
        }else{
            System.out.println("FAILED");
            String comment = (String) json.get("comment");
            throw new CodeforcesApiException(comment);
        }
        return ans;
    }

}
