package cl.octavionancul.prueba4desafiolatam.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import cl.octavionancul.prueba4desafiolatam.R;

import com.github.ybq.android.spinkit.SpinKitView;

public class MainActivity extends AppCompatActivity   {

    private ItemFragment itemFragment;
   private ImageButton button;
    private SpinKitView loading;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemFragment= (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.itemsFreagment);
        button = findViewById(R.id.searchBtn);
       final EditText textEt = findViewById(R.id.searchEt);
        loading = findViewById(R.id.spin_kit);
        logo = findViewById(R.id.logoIv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//validar vacio.........................................................>
              //  button.setVisibility(View.INVISIBLE);

                    String text = textEt.getText().toString();
                if(text.trim().length()>0){
                    button.setClickable(false);
                    logo.setVisibility(View.GONE);
                    loading.setVisibility(View.VISIBLE);
                    itemFragment.search(text);
                }else {
                    int ecolor = Color.WHITE; // whatever color you want
                    String estring = "Ingresa un texto";
                    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
                    ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
                    textEt.setError(ssbuilder);
               // textEt.setError(Html.fromHtml("<font color='white'>Ingrese un texto</font>"));

                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }



}
