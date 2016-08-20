package co.clipclap.pasta.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Date;

import co.clipclap.pasta.PastaDetail;
import co.clipclap.pasta.PastaPriceDetail;
import co.clipclap.pasta.R;
import co.clipclap.pasta.objects.DateParser;
import co.clipclap.pasta.objects.RoundingTextsView;

/**
 * Created by josedavidmantilla on 1/14/16.
 */
public class FragmentFunction extends Fragment {

    LinearLayout horizontalContent;
    LinearLayout horizontalContent2;
    public int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_functions, container, false);

        horizontalContent = (LinearLayout)rootView.findViewById(R.id.horizontalcontent);

        for (int i=0;i<7;i++){
            Date date= new Date();
            date= DateParser.addDays(date,i);

            RoundingTextsView roundingTextsView = new RoundingTextsView(getActivity());

            roundingTextsView.setCount(3);
            roundingTextsView.setIds(i);
            roundingTextsView.setUpText(DateParser.getDayS(date).substring(0, 3) + ".");
            roundingTextsView.setMiddleText(DateParser.getDayI(date));
            roundingTextsView.setDownText(DateParser.getMonthS(date).substring(0, 3) + ".");

            horizontalContent.addView(roundingTextsView);
            if(i==0){
                roundingTextsView.setAlpha(1f);
                id=0;
            }else{
                roundingTextsView.setAlpha(0.5f);
            }
            roundingTextsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  for(int i=0; i<  horizontalContent.getChildCount();i++){
                      horizontalContent.getChildAt(i).setAlpha(0.5f);

                  }
                    id=((RoundingTextsView)v).getIds();
                    v.setAlpha(1f);
                }
            });
        }

        horizontalContent2 = (LinearLayout)rootView.findViewById(R.id.horizontalcontent2);

        for (int i=12;i<=22;i=i+2){


            RoundingTextsView roundingTextsView = new RoundingTextsView(getActivity());

            roundingTextsView.setCount(1);
            roundingTextsView.setIds(i);
            roundingTextsView.setUpText(i + "h00");
            roundingTextsView.setUpTextColor(Color.YELLOW);
            horizontalContent2.addView(roundingTextsView);
            roundingTextsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),PastaPriceDetail.class);
                    Bundle b = new Bundle();
                    b.putParcelableArrayList(PastaDetail.DATA, PastaDetail.items);
                    b.putInt(PastaDetail.CURRENT_POSITION, PastaDetail.position);
                    b.putInt(PastaPriceDetail.DATE, id);
                    b.putInt(PastaPriceDetail.TIME, ((RoundingTextsView) v).getIds());

                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
        }


        return rootView;
    }


}