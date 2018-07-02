package cl.octavionancul.prueba4desafiolatam.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cl.octavionancul.prueba4desafiolatam.R;
import cl.octavionancul.prueba4desafiolatam.models.Item;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Item item = (Item) getIntent().getSerializableExtra(ItemFragment.ITEM);

        getSupportActionBar().setTitle(item.getName());
        ImageView imageView = findViewById(R.id.imageIv);
        TextView nameTv = findViewById(R.id.namedTv);
        TextView descriptionTv = findViewById(R.id.shortDescriptionTv);
        TextView priceTv = findViewById(R.id.salePricedTv);
        TextView ratingTv = findViewById(R.id.customerRatingTv);

        Picasso.get().load(item.getLargeImage()).centerCrop().fit().into(imageView);
        nameTv.setText(item.getName());
        String description ="";
        if(item.getShortDescription()!=null){
          description = String.valueOf(Html.fromHtml(Html.fromHtml(item.getShortDescription().toString()).toString()));
        }

        descriptionTv.setText(description);
        priceTv.setText(String.valueOf(item.getSalePrice()));
        ratingTv.setText(item.getCustomerRating());

      //  Toast.makeText(ItemDetailActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
    }
}
