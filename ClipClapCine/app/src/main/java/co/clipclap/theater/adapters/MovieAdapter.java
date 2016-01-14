package co.clipclap.theater.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.clipclap.theater.R;
import co.clipclap.theater.objects.Movie;

/**
 * Created by josedavidmantilla on 1/13/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

        private List<Movie> items;

        public static class MovieViewHolder extends RecyclerView.ViewHolder {
            // Campos respectivos de un item
            public ImageView imagen;
            public TextView nombre;
            public TextView type;

            public MovieViewHolder(View v) {
                super(v);
                imagen = (ImageView) v.findViewById(R.id.image);
                nombre = (TextView) v.findViewById(R.id.name);
                type = (TextView) v.findViewById(R.id.type);
            }
        }

        public MovieAdapter(List<Movie> items) {
            this.items = items;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_movie, viewGroup, false);
            return new MovieViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder viewHolder, int i) {
            viewHolder.imagen.setImageResource(items.get(i).getImage());
            viewHolder.nombre.setText(items.get(i).getName());
            viewHolder.type.setText("Género:"+items.get(i).getType());
        }

}
