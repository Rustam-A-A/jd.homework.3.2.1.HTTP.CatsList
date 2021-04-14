
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;


public class Main {
    private static final String REMOTE_SERVICE_URI ="https://cat-fact.herokuapp.com/facts";

    public static void main(String[] args) throws IOException, ClassCastException, ParseException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("  ")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        //создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON.getMimeType());

        //отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);

        //вывод полученных заголовков
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        //чтение тела ответа
        String body = new String(response.getEntity().getContent().readAllBytes(), UTF_8);
        //System.out.println(body);

        //сопоставление JSON объекта с классом Cat
        List<Cat> cats = jsonToCat(body);

        //фильтрация фактов.
        System.out.println("\nФильтр фактов:");
        List<Cat>cats2 = cats.stream()
                .limit(5)
                .filter(x -> x.type.equals("cat"))
                .filter(x -> x.deleted.equals(false))
                //.filter(x -> x.text.startsWith("Wikipedia"))
                .collect(Collectors.toList());
        System.out.println(cats2);
    }

    //сопоставление JSON объекта с классом Cat
    public static List<Cat> jsonToCat(String body) throws ParseException {
        List<Cat> cats = new ArrayList<>();
        Object obj = new JSONParser().parse(body);
        JSONArray jsonArray = (JSONArray) obj;
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            //Status status = (Status) jsonObject.get("status");
            String type = (String) jsonObject.get("type");
            Boolean deleted = (Boolean) jsonObject.get("deleted");
            String id = (String) jsonObject.get("_id");
            String user = (String) jsonObject.get("user");
            String text= (String) jsonObject.get("text");
            long v = (Long) jsonObject.get("__v");;
            String source = (String) jsonObject.get("source");
            String updatedAt = (String) jsonObject.get("updatedAt");
            String createdAt = (String) jsonObject.get("createdAt");
            Boolean used = (Boolean) jsonObject.get("used");

            Cat cat = new Cat(type, deleted, id, user, text, v, source, updatedAt, createdAt, used);
            cats.add(cat);
            //System.out.println(cat.toString());
        }
        return cats;
    }
}
