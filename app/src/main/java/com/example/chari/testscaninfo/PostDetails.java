package com.example.chari.testscaninfo;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Chari on 10/22/2016.
 */

public class PostDetails
{

    public String POSTFormdetalis(String url, String json)
    {

        InputStream inputStream = null;
        String result = "";
        try
        {


            Log.e("itd coming 1","Step1");
            //1. create HTTPCLIENT
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            // httpPost.setEntity(se);
            // 7. Set some headers to inform server about the type of the content   

            httpPost.setHeader("Input",json);
            // httpPost.setHeader("Input",json);
            //8.Set Header to Json String


            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // 10. convert inputstream to string

            /*int Output=inputStream.read();
            textView.setText(Output);*/


            Log.e("InputStream Responce",inputStream.toString());
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        }catch (Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }


        Log.e("Retrun",result);
        // 11. return result
        return result;




    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {

        String line="";
        String data="";
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            while((line=br.readLine())!=null){

                data+=line;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  data;

    }

}
