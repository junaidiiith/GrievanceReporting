package com.example.gra.gra;

/**
 * Created by sachin on 31/3/17.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<JSONObject>{

    int vg;
    ArrayList<JSONObject> list;
    Context context;

    public ListAdapter(Context context, int vg, int id, ArrayList<JSONObject> list){

        super(context,vg, id,list);

        this.context=context;
        this.vg=vg;
        this.list=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);

        TextView txtId=(TextView)itemView.findViewById(R.id.txtgid);
        TextView txtCategory=(TextView)itemView.findViewById(R.id.txtcategory);
        TextView txtDescription=(TextView)itemView.findViewById(R.id.txtdescription);
        TextView txtStatus=(TextView)itemView.findViewById(R.id.txtstatus);

        try {

            txtId.setText(list.get(position).getString("g_id"));
            txtCategory.setText(list.get(position).getString("category"));
            txtDescription.setText(list.get(position).getString("description"));
            txtStatus.setText(list.get(position).getString("status"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemView;

    }

}
