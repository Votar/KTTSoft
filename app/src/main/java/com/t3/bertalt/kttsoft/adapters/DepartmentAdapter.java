package com.t3.bertalt.kttsoft.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.t3.bertalt.kttsoft.R;
import com.t3.bertalt.kttsoft.model.Departments;

/**
 * Created by bertalt on 24.07.16.
 */
public class DepartmentAdapter extends ArrayAdapter {


    protected Context context;
    protected LayoutInflater inflater;
    protected Departments[] departments = Departments.values();


    public DepartmentAdapter(Context context, int resource, Object[] objects, LayoutInflater inflater) {

        super(context, resource, objects);
        this.context = context;
        this.inflater = inflater;
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        View layout = inflater.inflate(R.layout.item_department, parent, false);

// Declaring and Typecasting the textview in the inflated layout
        TextView tvDepart = (TextView) layout
                .findViewById(R.id.tv_depart_descr);

// Setting the text using the array
        switch (departments[position]){
            case DEVELOPMENT:
                tvDepart.setText(context.getString(R.string.title_dep_development));
                layout.setBackgroundColor(context.getResources().getColor(R.color.colorDepDev));
                break;
            case MANAGEMENT:
                tvDepart.setText(context.getString(R.string.title_dep_managment));
                layout.setBackgroundColor(context.getResources().getColor(R.color.colorDepManagement));
                break;
            case LOGISTIC:
                tvDepart.setText(context.getString(R.string.title_dep_logistic));
                layout.setBackgroundColor(context.getResources().getColor(R.color.colorDepLogistic));
                break;
        }


        return layout;
    }

    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // It gets a View that displays the data at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


}
