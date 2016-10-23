package com.example.chari.testscaninfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Chari on 10/23/2016.
 */

public class CoustomAdapter extends BaseAdapter
{
    Context context;
    String testItems [];
    String testItems1 [];
    LayoutInflater inflater;

    public CoustomAdapter(Context context, String [] testItems,String []testItems1)
    {
        this.context=context;
        this.testItems=testItems;
        this.testItems1=testItems1;
        inflater=(LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return testItems.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view= inflater.inflate(R.layout.row,null);
        TextView item1=(TextView)view.findViewById(R.id.textItem);
        TextView item2=(TextView)view.findViewById(R.id.textItem2);
        item1.setText(testItems[i]);
        item2.setText(testItems1[i]);

        return view;
    }

}
