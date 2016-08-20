package sdk.clipclap.clipclapcharge;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import droidbox.util.DialogUtils;
import sdk.clipclap.payandgo.R;

/**
 * Created by josedavidmantilla on 12/3/15.
 */
public class PayAndGo extends RelativeLayout{

    public static final int BLUE_THEME=0;
    public static final int BLACK_THEME=1;
    public static final int WHITE_THEME=2;

    public PayAndGo(Context context) {
        super(context);
        ctx=context;
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                click();
            }
        });
        init();
    }

    public PayAndGo(Context context, AttributeSet attrs) {
        super(context, attrs);

        ctx=context;
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                click();
            }
        });
        init();
    }

    public PayAndGo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx=context;
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
                click();
            }
        });
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.pay_and_go, this);
    }


    public void setTheme(int theme){
        switch (theme){
            case BLACK_THEME:{
                setTextColor(getResources().getColor(R.color.colorwhite));
                setBackGround(getResources().getColor(R.color.colorblack));
                setBackGroundImage(R.drawable.clipbutton);
                break;
            }
            case WHITE_THEME:{
                setTextColor(getResources().getColor(R.color.colorblue));
                setBackGround(getResources().getColor(R.color.colorwhite));
                setBackGroundImage(R.drawable.clipbuttonblue);
                break;
            }
            default:{
                setBackGround(getResources().getColor(R.color.colorblue));
                setTextColor(getResources().getColor(R.color.colorwhite));
                setBackGroundImage(R.drawable.clipbutton);
                break;
            }
        }
    }

    @Override
    public void setBackgroundColor(int color) {

    }

    public void setText(String text){

        ((TextView)findViewById(R.id.text)).setText(text);
    }

    private void setTextColor(int  color){

        ((TextView)findViewById(R.id.text)).setTextColor(color);
    }


    private void setBackGround(int  color){

        ((FrameLayout)findViewById(R.id.relative)).setBackgroundColor(color);
    }
    private void setBackGroundImage(int  image){

        ((ImageView)findViewById(R.id.image)).setImageResource(image);
    }

    public static String secretKey="pKFe1P2iYw6z73srBDBx";
    public static final int  DEVELOPMENT=1;
    public static  final int PRODUCTION=2;
    public static int img= R.mipmap.ic_launcher;
    public static JSONObject jsonObject;
    public static String failureMessage;
    public static String urlCallback;
    private String token;
    public static final String PLAYSTORE="https://play.google.com/store/apps/details?id=clipclap.app.clip&hl=es";

    public static int type;
    private  OnClickListener onClickListener;
    private   Context ctx;
    private SaveTokenListener mSaveTokenListener;

    public static ProgressDialog pd;



    public String getUrl(){
        return urlDeep +  token  + "&callbackurl="+urlCallback+"&code="+md5(getResources().getString(R.string.idt)+token+getResources().getString(R.string.idt));
    }

public void setSaveTokenListener(SaveTokenListener saveTokenListener){
    mSaveTokenListener=saveTokenListener;
}

    public static void resetDialog(){
        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry

        while (iter.hasNext())
        {
            Map.Entry pairs = (Map.Entry)iter.next();
            holder.put((String)pairs.getKey(), pairs.getValue());
        }

        //puts email and 'foo@bar.com'  together in map


        return holder;
    }

    String url;
    String urlDeep;
    private void click(){
        resetDialog();
        pd= DialogUtils.buildProgress(ctx, "Enviando datos");
        pd.show();
        Map<String, String> params= new HashMap<String, String>();

         url= (type==DEVELOPMENT)?"https://devpayment.clipclap.co/generate":"https://devpayment.clipclap.co/generate";
         urlDeep=(type==DEVELOPMENT)?"https://devwebpayment.clipclap.co?type=ClipClapWeb&token=":"https://devwebpayment.clipclap.co?type=ClipClapWeb&token=";
        try {
            HttpService.post3(ctx,url
                    ,
                   jsonObject, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            resetDialog();

                            try {

                                token= response.getString("token").toString();
                                mSaveTokenListener.saveToken(token);

                               // Uri uri = Uri.parse(urlDeep ++ "&callbackurl="+urlCallback);
                               // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                               // ctx.startActivity(intent);

                            }catch (Exception e){
                                e.printStackTrace();
                                if(failureMessage==null || failureMessage.equals("")){
                                    failureMessage=e.getMessage();
                                }
                                DialogUtils.showMessageDialog(ctx, "Error", failureMessage);
                   //             Log.e("ERROR", e.getMessage());
                            }

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                  System.err.println("RESPONSE" + responseString);
                            resetDialog();

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            try {
                  //              System.err.println("4 " + statusCode);
                                resetDialog();
                                if (failureMessage == null || failureMessage.equals("")) {
                                    failureMessage = response.toString();
                                }
                              //  DialogUtils.showMessageDialog(ctx, "Error", failureMessage);
                   //             Log.e("ERROR", response.toString());
                            }catch (Exception exception){
                                exception.printStackTrace();
                    //            Log.e("ERROR", exception.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            try {
                     //           System.err.println("4 " + statusCode);
                                resetDialog();
                                if (failureMessage == null || failureMessage.equals("")) {
                                    failureMessage = errorResponse.toString();
                                }
                                DialogUtils.showMessageDialog(ctx, "Error", failureMessage);
                        //        Log.e("ERROR", errorResponse.toString());
                            }catch (Exception exception){
                                exception.printStackTrace();
                        //        Log.e("ERROR", exception.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            try {
                     //           System.err.println("2 " + statusCode);
                            resetDialog();
                            if(failureMessage==null || failureMessage.equals("")){
                                failureMessage=responseString;
                            }
                            DialogUtils.showMessageDialog(ctx, "Error", failureMessage);
                       //     Log.e("ERROR", responseString);
                            }catch (Exception exception){
                                exception.printStackTrace();
                         //       Log.e("ERROR", exception.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                try {
                           //         System.err.println("3 " + statusCode);

                            //     System.err.println("4 " + errorResponse.toString());
                            resetDialog();
                            if(failureMessage==null || failureMessage.equals("")){
                                failureMessage=errorResponse.toString();
                            }
                            DialogUtils.showMessageDialog(ctx, "Error", failureMessage);
                        //    Log.e("ERROR", errorResponse.toString());
                        }catch (Exception exception){
                            exception.printStackTrace();
                         //   Log.e("ERROR", exception.getMessage());
                        }


                        }


                    });

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
            onClickListener=l;
    }



}
