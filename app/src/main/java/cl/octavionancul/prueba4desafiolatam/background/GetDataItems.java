package cl.octavionancul.prueba4desafiolatam.background;

import android.os.AsyncTask;
import android.util.Log;

import cl.octavionancul.prueba4desafiolatam.models.Query;
import cl.octavionancul.prueba4desafiolatam.networks.GetItems;
import cl.octavionancul.prueba4desafiolatam.networks.ItemInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Response;


public class GetDataItems extends AsyncTask<String,Integer,Query> {
    private Map<String,String> queryMap;
    private final GetItems request = new ItemInterceptor().get();

    @Override
    protected Query doInBackground(String... maps) {

         Query query = new Query();
        int code = 555;
        query.setResult(code);
        queryMap = new HashMap<String,String>();

        queryMap.put("apiKey","py2xe4krqb6en5px35y75ab4");
        queryMap.put("numItems","20");
        queryMap.put("query",maps[0]);
        Call<Query> call = request.get(queryMap);
       // call.toString();
        try {
            Response<Query> response = call.execute();
            code = response.code();
            Log.d("query", String.valueOf(code));
            if(code==200) {
                query = response.body();
                query.setResult(code);
            }else{
                query.setResult(code);
            }

        }catch (IOException e){

            code=777;
            query.setResult(code);
            Log.d("items","error"+e.toString());
        }

        Log.d("items", String.valueOf(code));
        return query;

    }


}
