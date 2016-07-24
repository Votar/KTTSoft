package com.t3.bertalt.kttsoft.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t3.bertalt.kttsoft.R;
import com.t3.bertalt.kttsoft.model.Departments;
import com.t3.bertalt.kttsoft.model.Empl;
import com.t3.bertalt.kttsoft.ui.empl_list.EmplListActivity;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MyRecyclerViewAdapter extends RealmRecyclerViewAdapter<Empl, MyRecyclerViewAdapter.MyViewHolder> {

    private final EmplListActivity activity;

    public MyRecyclerViewAdapter(EmplListActivity activity, OrderedRealmCollection<Empl> data) {
        super(activity ,data, true);
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_empl, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Empl obj = getData().get(position);
        holder.data = obj;
        holder.fullName.setText(obj.getFullName());
        holder.position.setText(obj.getPosition());

        switch (Departments.getDevelopmentById(obj.getDepartment())){
            case DEVELOPMENT:
                holder.department.setText(activity.getString(R.string.title_dep_development));
                holder.itemEmpl.setBackgroundColor(context.getResources().getColor(R.color.colorDepDev));
                break;
            case MANAGEMENT:
                holder.department.setText(activity.getString(R.string.title_dep_managment));
                holder.itemEmpl.setBackgroundColor(context.getResources().getColor(R.color.colorDepManagement));
                break;
            case LOGISTIC:
                holder.department.setText(activity.getString(R.string.title_dep_logistic));
                holder.itemEmpl.setBackgroundColor(context.getResources().getColor(R.color.colorDepLogistic));
                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private static final String TAG = "MyViewHolder";

        public RelativeLayout itemEmpl;

        public TextView fullName, position, department;
        public Empl data;

        public MyViewHolder(View view) {
            super(view);
            fullName = (TextView) view.findViewById(R.id.recycler_full_name);
            position = (TextView) view.findViewById(R.id.recycler_position);
            department = (TextView) view.findViewById(R.id.recycler_depart);

            itemEmpl = (RelativeLayout)view.findViewById(R.id.item_empl);




            view.setOnLongClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.openToEditProfile(data.getId());
                }
            });
        }

        @Override
        public boolean onLongClick(View v) {

            if(activity !=null){
                activity.getListByDepartment(data.getDepartment());
            }


            return true;
        }


    }
}