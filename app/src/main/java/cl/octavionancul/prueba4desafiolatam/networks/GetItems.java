package cl.octavionancul.prueba4desafiolatam.networks;

import cl.octavionancul.prueba4desafiolatam.models.Query;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetItems {
    @GET("search")
    Call<Query> get(@QueryMap Map<String,String> queryMap);

    @GET("search")
    Call<Query> getTrend(@QueryMap Map<String,String> queryMap);
}
