package co.clipclap.theater;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import co.clipclap.theater.objects.DateParser;
import co.clipclap.theater.objects.Movie;
import sdk.clipclap.clipclapcharge.CCBilleteraPayment;
import sdk.clipclap.clipclapcharge.PayAndGo;
import sdk.clipclap.clipclapcharge.SaveTokenListener;

public class MoviePriceDetail extends AppCompatActivity {
    public static int position;
    public static ArrayList<Movie> items;
    public static final String DATE="date";
    public static final String TIME="time";
    public static final String CURRENT_DATA="current";
    int  count;
    MoviePriceDetail self;
   static Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_price_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        String data;
        self=this;
        Intent intent = getIntent();
        Log.e("LLEGO", "LLEGP");
        if (intent != null && intent.getData() != null) {
            data= intent.getDataString();
            String cadena = "no entro";
            if (data.contains("ok")) {
                cadena = "pagada";
            } else {
                if (data.contains("cancel")) {
                    cadena = "rechazada";
                } else {
                    if (data.contains("&message"))
                        cadena = data.split("&message")[1];
                }
            }
            Log.e("LLEGO", "cadena es "+cadena);
            Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();


        }

        if (this.getIntent().getExtras() != null){
            b=this.getIntent().getExtras();
            updateData();
        }else{
            if(savedInstanceState!=null){
                b=savedInstanceState.getBundle(CURRENT_DATA);
                updateData();
            }
        }
        count=0;
        final  PayAndGo payAndGo = (PayAndGo)findViewById(R.id.payButton);
        payAndGo.setEnabled(false);
        ImageView addButton= (ImageView)findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count < 20){
                    count++;
                    ((TextView)findViewById(R.id.counttext)).setText(""+count);
                }
                if(count>0){
                    payAndGo.setEnabled(true);

                }

            }
        });
        ImageView removeButton= (ImageView)findViewById(R.id.removebutton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0){
                    count--;
                    ((TextView)findViewById(R.id.counttext)).setText(""+count);
                }
                if(count==0){
                    payAndGo.setEnabled(false);
                }

            }
        });


        payAndGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try {
                        CCBilleteraPayment ccBilleteraPayment = new CCBilleteraPayment("pKFe1P2iYw6z73srBDBx");
                        ccBilleteraPayment.addItem("Cine: " + items.get(position).getName(), count, items.get(position).getPrice(), CCBilleteraPayment.IVA_REGULAR_16_);
                        PayAndGo.urlCallback = "cineclipclap://cine";
                        PayAndGo.jsonObject = ccBilleteraPayment.getJSON();
                        PayAndGo.failureMessage = "Error intente más tarde";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });

        payAndGo.setSaveTokenListener(new SaveTokenListener() {
            @Override
            public void saveToken(String token) {
                try {
                    Uri uri = Uri.parse(payAndGo.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri = Uri.parse(PayAndGo.PLAYSTORE);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

        });
    }

    public void updateData(){
        items = b.getParcelableArrayList(MovieDetail.DATA);
        ImageView img=(ImageView)findViewById(R.id.image);
        position=b.getInt(MovieDetail.CURRENT_POSITION,-1);
        img.setImageResource(items.get(position).getImage());
        TextView nombre = (TextView) findViewById(R.id.name);
        TextView type = (TextView) findViewById(R.id.type);
        TextView price = (TextView) findViewById(R.id.price);
        TextView dateT = (TextView) findViewById(R.id.date);
        nombre.setText(items.get(position).getName());
        type.setText(items.get(position).getType());
        price.setText("$"+(items.get(position).getPrice()));

        int id= b.getInt(DATE,-1);
        Date date = new Date();
        date= DateParser.addDays(date,id);
        dateT.setText(DateParser.getDate(date));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(CURRENT_DATA,b);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onResume() {
        updateData();
        super.onResume();
    }
}
