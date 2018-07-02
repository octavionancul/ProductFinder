package cl.octavionancul.prueba4desafiolatam.networks;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemInterceptor {

    public static final String BASE_URL = "http://api.walmartlabs.com/v1/";

    public  GetItems get() {

        /*This is very common in gets cause increase the response time wait and add headers and does retrys*/
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                        /*Common headers*/
                        //  .header("Authorization", "Client-ID 564b0ed5f643989db3757e5a6231b13c9557736e01e2a389d015fbad4ef9ca3b")
                        // .header("Accept", "application/json")
                        /*Custom header*/
                        //  .header("Source", "mobile")
                        .build();
                Log.d("items", "reitentos: " + request.toString());
                Response response = chain.proceed(request);

                Log.d("respuesta", "response: " + response.toString());

                /*If the request fail then you get 3 retrys*/
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);

                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GetItems request = interceptor.create(GetItems.class);
        Log.d("items", request.toString());
        return request;
    }
    }
