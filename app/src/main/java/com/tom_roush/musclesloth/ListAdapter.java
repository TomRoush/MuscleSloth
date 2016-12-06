package com.tom_roush.musclesloth;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by brent on 12/6/2016.
 */

class ListAdapter<T> extends ArrayAdapter<T> {
    public View v;
    public ListAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context,resource,textViewResourceId,objects);
    }

    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        this.v = convertView;

        ImageButton ib = (ImageButton) v.findViewById(R.id.imageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("banana", "just bloody press at this point");
            }
        });

        //TextView txt = ( TextView ) v.findViewById(R.id.listview_elem);
        //txt.setText(recieved_Array[pos]) ;
        return v;
    }
}
