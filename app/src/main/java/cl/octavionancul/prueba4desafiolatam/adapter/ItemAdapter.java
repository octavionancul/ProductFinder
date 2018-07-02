package cl.octavionancul.prueba4desafiolatam.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cl.octavionancul.prueba4desafiolatam.R;
import cl.octavionancul.prueba4desafiolatam.models.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    private List<Item> itemList = new ArrayList<>();
    private ItemCallback callback;

    public ItemAdapter(ItemCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final Item item  = itemList.get(position);
        Picasso.get().load(item.getLargeImage()).centerCrop().fit().into(holder.itemPhoto);
        holder.nameTv.setText(item.getName());
        holder.salePriceTv.setText(String.valueOf(item.getSalePrice()));
        holder.customerRatingTv.setText(item.getCustomerRating());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(, item.getName(), Toast.LENGTH_SHORT).show();
             //   Log.d("click",item.getName());
               callback.clicked(item);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void update(List<Item> items) {
        itemList.clear();
        itemList.addAll(items);
      notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemPhoto;
        private TextView nameTv,salePriceTv,customerRatingTv;

        public ViewHolder(View itemView) {
            super(itemView);

            itemPhoto= itemView.findViewById(R.id.itemPhotoIv);
            nameTv=itemView.findViewById(R.id.nameTv);
            salePriceTv=itemView.findViewById(R.id.salePriceTv);
            customerRatingTv=itemView.findViewById(R.id.RatingTv);
        }
    }
}
