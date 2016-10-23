package com.example.chari.testscaninfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Chari on 10/22/2016.
 */

public class ListViewShow extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView ListViewItems;
    private Button BtnSubmit;
    String[] testItems = {"Surya", "Narayan","Singh","Chundawat"};

    String[] testItems1 = {"Surya1","Narayan1","Singh1", "Chundawat1"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewshow);
        getRef();

        CoustomAdapter coustomAdapter = new CoustomAdapter(getApplicationContext(),testItems,testItems1);
        ListViewItems.setAdapter(coustomAdapter);
        ListViewItems.setOnItemClickListener(this);

            }


    public void getRef()
    {
        ListViewItems=(ListView)findViewById(R.id.list);
        //BtnSubmit=(Button)findViewById(R.id.BtnSubmit);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Toast.makeText(getApplicationContext(), "You clicked on position : " + position , Toast.LENGTH_LONG).show();

    }
}
