package co.clipclap.theater;



import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import co.clipclap.theater.fragments.FragmentDetail;
import co.clipclap.theater.fragments.FragmentFunction;
import co.clipclap.theater.objects.Movie;

public class MovieDetail extends AppCompatActivity {
public static final String DATA="Data";
    public static final String CURRENT_POSITION="position";
    public static final String CURRENT_DATA="current";
    public static int position;
    public static ArrayList<Movie> items;
    private FragmentTabHost mTabHost;
     CollapsingToolbarLayout mCollapsingToolbarLayout;
   static Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        final  android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AppBarLayout appbar= (AppBarLayout)findViewById(R.id.appbar);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        this.getSupportActionBar().setIcon(
                new ColorDrawable(this.getResources().getColor(android.R.color.transparent)));


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);


        mTabHost.addTab(mTabHost.newTabSpec("FUNCIONES").setIndicator("FUNCIONES"),
                FragmentFunction.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("DETALLES").setIndicator("DETALLES"),
                FragmentDetail.class, null);

        if(this.getIntent().getExtras()!=null){
            b=this.getIntent().getExtras();
            updateData();
        }else{
            if(savedInstanceState!=null){
                b=savedInstanceState.getBundle(CURRENT_DATA);
                updateData();
            }
        }


    }

    public void updateData(){
        if (b != null) {
            items = b.getParcelableArrayList(DATA);
            position = b.getInt(CURRENT_POSITION, -1);
            ImageView img = (ImageView) findViewById(R.id.profile_img_photo);

            mCollapsingToolbarLayout.setTitle(items.get(position).getName());
            mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
            mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
            Log.e("RESUME", "" + items.get(position).getImageLarge());
            img.setImageResource(items.get(position).getImageLarge());


        }
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
