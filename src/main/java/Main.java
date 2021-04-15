
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

        //преобразование JSON в java объект
        ObjectMapper mapper = new ObjectMapper();
        List<Cat> cats = mapper.readValue(body, new TypeReference<List<Cat>>() {});

        //фильтрация фактов.
        System.out.println("\nФильтр фактов:");
        List<Cat>cats2 = cats.stream()
                .limit(5)
                .filter(x -> x.type.equals("cat"))
                .filter(x -> x.deleted.equals(false))
                .filter(x -> x.text.startsWith("Wikipedia"))
                .collect(Collectors.toList());
        System.out.println(cats2);
    }
}
