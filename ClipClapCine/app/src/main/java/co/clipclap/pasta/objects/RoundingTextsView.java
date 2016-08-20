package co.clipclap.pasta.objects;

/**
 * Created by josedavidmantilla on 1/14/16.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import co.clipclap.pasta.R;

public class RoundingTextsView  extends RelativeLayout {

    private TextView up;
    private TextView middle;
    private TextView down;
    private int id;


        public RoundingTextsView(Context context) {
            super(context);
            init();
        }

        public RoundingTextsView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public RoundingTextsView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            inflate(getContext(), R.layout.roundingtextsview, this);
            this.up = (TextView)findViewById(R.id.upt);
            this.middle = (TextView)findViewById(R.id.middlet);
            this.down = (TextView)findViewById(R.id.downt);
        }

    public int getIds(){return id;}
    public void setIds(int id){this.id=id;}
    public void setUpText(String text){
        up.setText(text);
    }
    public void setDownText(String text){
        down.setText(text);
    }
    public void setMiddleText(String text){
        middle.setText(text);
    }

    public void setUpTextColor(int text){
        up.setTextColor(text);
    }
    public void setDownTextColor(int text){
        down.setTextColor(text);
    }
    public void setMiddleTextColor(int text){
        middle.setTextColor(text);
    }

    public void setCount(int count){
         if(count==1){
             down.setVisibility(View.GONE);
             middle.setVisibility(View.GONE);
         }
    }

}
