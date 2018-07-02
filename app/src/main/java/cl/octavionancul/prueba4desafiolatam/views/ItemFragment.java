package cl.octavionancul.prueba4desafiolatam.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import cl.octavionancul.prueba4desafiolatam.R;
import cl.octavionancul.prueba4desafiolatam.adapter.ItemAdapter;
import cl.octavionancul.prueba4desafiolatam.adapter.ItemCallback;
import cl.octavionancul.prueba4desafiolatam.background.GetDataItems;
import cl.octavionancul.prueba4desafiolatam.models.Item;
import cl.octavionancul.prueba4desafiolatam.models.Query;
import com.github.ybq.android.spinkit.SpinKitView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements ItemCallback {


    private ItemAdapter adapter;
    public static final String ITEM ="cl.octavionancul.prueba4desafiolatam.KEY.ITEM";
    private RecyclerView recyclerView;

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       recyclerView = view.findViewById(R.id.itemsRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    //@Override
    public void search(String name) {
        new GetResult().execute(name);
     //   Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clicked(Item item) {
        Intent intent = new Intent(getActivity(),ItemDetailActivity.class);
        intent.putExtra(ITEM,item);
        startActivity(intent);
    //    Toast.makeText(getContext(),item.getUpc(), Toast.LENGTH_SHORT).show();
    }

    private class GetResult extends GetDataItems {

        @Override
        protected void onPostExecute(Query query) {
            super.onPostExecute(query);
            SpinKitView loading = getActivity().findViewById(R.id.spin_kit);
            ImageButton button = getActivity().findViewById(R.id.searchBtn);
          //  button.setVisibility(View.VISIBLE);
            button.setClickable(true);
            loading.setVisibility(View.GONE);
            //controlar error 503 y sin conexion
            Log.d("postexecutequery", String.valueOf(query.getNumItems()+String.valueOf(query.getResult())));
            if(query.getResult()==200 && query.getNumItems()>0){
            adapter.update(query.getItems());
            recyclerView.scrollToPosition(0);
            }else if(query.getResult()==503){
                Toast.makeText(getContext(),"Servicio no disponible temporalmente.", Toast.LENGTH_SHORT).show();
            }else if( query.getResult()==200 && query.getNumItems()==0){
                Toast.makeText(getContext(),"No se encontraron productos", Toast.LENGTH_SHORT).show();
            }else if(query.getResult()==777){
                Toast.makeText(getContext(),"Sin conexion", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(),"Error desconocido", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
