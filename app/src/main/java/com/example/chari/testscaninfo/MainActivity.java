package com.example.chari.testscaninfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{

    EditText EditAgencyName,EditPassword;
    TextView TextIMEI,textView;
    Button BtnSubmit;
    private String ImEINo;
    private ProgressDialog pDialog;
    private String agencyName;
    PostDetails postDetails;
    public static final String URL_AGENCYNAME = "http://friskcon.com/AgencyRestService/AgencyRestService.svc/GetAgencyName/?";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRef();
        TextIMEI.setText("1234567890");
        postDetails= new PostDetails();

    }


    public void NextOn(View view)
    {
        if (haveNetworkConnection()==true)
        {
            new HttpAsyncTaskFromDetails().execute(URL_AGENCYNAME);

        }

    }


    public void getRef()
    {
        EditAgencyName=(EditText)findViewById(R.id.EditAgencyName);
        EditPassword=(EditText)findViewById(R.id.EditPassword);
        TextIMEI=(TextView) findViewById(R.id.TextIMEI);
        BtnSubmit=(Button) findViewById(R.id.BtnSubmit);

    }

    private boolean haveNetworkConnection()
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private String IMEI()
    {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ImEINo = tm.getDeviceId();

        return ImEINo;
    }



    private class HttpAsyncTaskFromDetails extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls)
        {
            String s=getformDetailsJson();
            postDetails.POSTFormdetalis(urls[0],s);
            String result = postDetails.POSTFormdetalis(urls[0],s);
            Log.e("result",result);

            if (result != null)
            {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    agencyName = jsonObj.getString("AgencyName");
                    Log.e("Agency Name",agencyName);
                    if((agencyName.equals("No such Agency Found.")))
                    {return agencyName;}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else
            {
                Toast.makeText(getApplicationContext(),"No such Agency Found.",Toast.LENGTH_SHORT).show();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(String.valueOf(result));

            pDialog.dismiss();

            if (!((result==null)&&(result.equals(""))))
            {
                if (result.equals("No such Agency Found."))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Agency",Toast.LENGTH_LONG).show();
                }else
                {
                    Intent intent = new Intent(MainActivity.this,ListViewShow.class);
                    startActivity(intent);
                    finish();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Please Check The Network",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private String getformDetailsJson()
    {
        String AgCode =EditAgencyName.getText().toString() ;
        String Password1 =EditPassword.getText().toString();
        String IMEINo = TextIMEI.getText().toString();


        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("AgencyCode", AgCode);
            jsonObject.put("IMEINo", IMEINo);
            jsonObject.put("Password", Password1);
            jsonArray.put(jsonObject);
            jsonObject1.put("jsonObject_Details", jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Jdon Login Str",jsonObject1.toString());

        return jsonObject1.toString();
    }
}
