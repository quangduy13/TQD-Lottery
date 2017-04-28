package vn.com.quangduy.mylottery.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by quangduy on 28/04/2017.
 */

public class RestClient {
    public static final String URL = "http://thanhhungqb.tk:8080";
    private static RestClient instance = new RestClient();

    private Retrofit retrofit;
    private OkHttpClient httpClient;
    private String authToken, portalId;

    private LotteryService lotteryService;
    private RestClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(logging);
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                if (authToken != null) {
                    requestBuilder.header("Authorization", authToken);
                }
                requestBuilder.header("Content-Type", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        httpBuilder.addInterceptor(requestInterceptor);

        httpClient = httpBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(JacksonConverterFactory.create(createObjectMapper()))
                .client(httpClient)
                .build();
    }

    public static RestClient getInstance() {
        return instance;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public String getPortalId() {
        return portalId;
    }

    public void setPortalId(String portalId) {
        this.portalId = portalId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static String getApiBaseUrl() {
        return URL;
    }


    public LotteryService getLotteryService(){
        if(lotteryService == null){
            lotteryService = createService(LotteryService.class);
        }
        return lotteryService;
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }
}
