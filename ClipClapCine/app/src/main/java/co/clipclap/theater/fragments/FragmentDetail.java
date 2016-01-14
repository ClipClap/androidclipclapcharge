package co.clipclap.theater.fragments;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.clipclap.theater.MovieDetail;
import co.clipclap.theater.R;

/**
 * Created by josedavidmantilla on 1/14/16.
 */
public class FragmentDetail extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);
                TextView textDescription = (TextView)rootView.findViewById(R.id.textDescription);
                textDescription.setText(MovieDetail.items.get(MovieDetail.position).getDescription());
                TextView textType = (TextView)rootView.findViewById(R.id.textType);
                textType.setText(MovieDetail.items.get(MovieDetail.position).getType());
        return rootView;
    }
}
