package co.clipclap.pasta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.clipclap.pasta.PastaDetail;
import co.clipclap.pasta.R;

/**
 * Created by josedavidmantilla on 1/14/16.
 */
public class FragmentDetail extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);
                TextView textDescription = (TextView)rootView.findViewById(R.id.textDescription);
                textDescription.setText(PastaDetail.items.get(PastaDetail.position).getDescription());
                TextView textType = (TextView)rootView.findViewById(R.id.textType);
                textType.setText(PastaDetail.items.get(PastaDetail.position).getType());
        return rootView;
    }
}
