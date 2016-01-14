package co.clipclap.theater;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import java.util.ArrayList;

import co.clipclap.theater.adapters.MovieAdapter;
import co.clipclap.theater.objects.Movie;
import co.clipclap.theater.objects.RecyclerItemClickListener;

public class TheaterList extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    Activity self;
    ArrayList items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_list);

        // Inicializar Animes
         items= new ArrayList();
        self=this;
        items.add(new Movie(R.drawable.movie1, R.drawable.movielarge1 ,"Batman v Superman: Dawn of Justice. 3D", 15000, "Acción , Aventura","Temiendo las consecuencias que pueden tener las acciones descontroladas de un superhéroe con unos poderes casi divinos, el defensor de Gotham decide viajar a Metrópolis para enfrentarse al respetado salvador de la ciudad. Pero mientras Batman y Superman se enfrentan entre sí y el mundo lucha por resolver qué tipo de héroe es el que necesitan realmente, una nueva amenaza que pone en peligro a toda la humanidad empieza a cobrar fuerza."));
        items.add(new Movie(R.drawable.movie2, R.drawable.movielarge2,"Suicide Squad", 8000,"Acción , Aventura","El Escuadrón Suicida son un grupo de siete villanos que, encarcelados por el Gobierno de los Estados Unidos, colaboran a su vez con este en misiones secretas para limpiar su expediente."));
        items.add(new Movie(R.drawable.movie3, R.drawable.movielarge3,"Kung Fu Panda 3. 3D", 15000, "Animación", "cuando el durante mucho tiempo perdido padre de Po aparece repentinamente, el dúo ya reunido viaja a un paraíso secreto de pandas para conocer a montones de nuevos e hilarantes osos. Pero cuando el villano sobrenatural Kai comienza a recorrer toda China venciendo a todos los maestros de kung fu, Po deberá hacer lo imposible: aprender a entrenar a una comunidad repleta de divertidos y torpes hermanos para formar el equipo definitivo de Kung Fu Pandas."));
        items.add(new Movie(R.drawable.movie5, R.drawable.movielarge4,"Dead Pool 3D", 15000, "Acción , Aventura","Deadpool es un mercenario que se unió al proyecto Arma X para curar el cáncer de pulmón que lo estaba consumiendo. El proyecto logró darle un factor curativo artificial que salvó su vida pero lo dejó horriblemente desfigurado. Fue así que Deadpool decidió vestir un uniforme que cubriese todo su cuerpo y comenzar a vender sus servicios al mejor postor, logrando sobrevivir a cada misión con una mezcla de locura, ironía y explosiones."));
        items.add(new Movie(R.drawable.movie4,R.drawable.movielarge5 ,"Mascotas", 8000, "Animación", "Todo cambia cuando el dueño de un perro llamado Max decide adoptar a un mestizo, Luke, que amenaza su liderazgo. A causa de una pelea los dos acaban fuera de su casa, donde encuentran a un conejo adorable que se dedica a crear un ejército de vengativos animales abandonados. "));
// Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(self,MovieDetail.class);
                        Bundle b = new Bundle();
                        b.putParcelableArrayList(MovieDetail.DATA, items);
                        b.putInt(MovieDetail.CURRENT_POSITION, position);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                })
        );

// Crear un nuevo adaptador
        adapter = new MovieAdapter(items);
        recycler.setAdapter(adapter);
        configureBarWithoutBack("ClipClapCine",this);
    }

    public static void configureBarWithoutBack(String title, AppCompatActivity act) {
        act.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
      //  act.getSupportActionBar().setIcon(R.drawable.);
        act.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(act.getResources().getColor(R.color.app_color)));
        updateTitle(title, act);
    }

    public static void configureBarPlainWithoutBack(String title, AppCompatActivity act) {
        act.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
      //  act.getSupportActionBar().setIcon();
        act.getSupportActionBar().setTitle(title);
    }

    public static void updateTitle(String title, AppCompatActivity act) {
        Spannable actionBarTitle = new SpannableString(" " + title);
        actionBarTitle.setSpan(
                new ForegroundColorSpan(Color.WHITE),
                0,
                actionBarTitle.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        act.getSupportActionBar().setTitle(actionBarTitle);
    }
}
