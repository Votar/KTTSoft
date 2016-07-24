
package com.t3.bertalt.kttsoft.ui.recyclerview;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.t3.bertalt.kttsoft.R;
import com.t3.bertalt.kttsoft.model.Empl;
import com.t3.bertalt.kttsoft.ui.DividerItemDecoration;
import com.t3.bertalt.kttsoft.ui.modify.ModifyEmplFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class EmplListActivity extends AppCompatActivity {

    private static final String TAG = "EmplListActivity";
    private Realm realm;

    private RealmResults<Empl> empls;

    private FragmentManager fragmentManager;

    @BindView(R.id.recycler)
    public RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.tv_list_is_empty)
    public TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        realm = Realm.getDefaultInstance();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        setUpRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {

            if (fragmentManager != null) {
                if (fragmentManager.findFragmentByTag(ModifyEmplFragment.TAG) != null)
                    return false; //fragment already showing

                DialogFragment modifyEmplFragment =new ModifyEmplFragment();
                modifyEmplFragment.show(fragmentManager, ModifyEmplFragment.TAG);
            }

            return true;

        }

        if(id == R.id.action_reset){
            empls = realm.where(Empl.class).findAll();
            recyclerView.setAdapter(new MyRecyclerViewAdapter(this, empls));
            toolbar.getMenu().findItem(R.id.action_add).setVisible(true);
            toolbar.getMenu().findItem(R.id.action_reset).setVisible(false);
            toolbar.getMenu().findItem(R.id.action_reset).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        empls = realm.where(Empl.class).findAll();
        empls.removeChangeListeners();
        empls.addChangeListener(new RealmChangeListener<RealmResults<Empl>>() {
            @Override
            public void onChange(RealmResults<Empl> element) {
                invalidateView();
            }
        });
        recyclerView.setAdapter(new MyRecyclerViewAdapter(this, empls));
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        invalidateView();
    }

    public void invalidateView() {

        if (recyclerView != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            adapter.notifyDataSetChanged();

            if (adapter.getItemCount() == 0) {
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        }
    }

    public void openToEditProfile(int id){

        if (fragmentManager != null) {
            if (fragmentManager.findFragmentByTag(ModifyEmplFragment.TAG) != null)
                return; //fragment already showing

            DialogFragment modifyEmplFragment = new ModifyEmplFragment();

            Bundle args = new Bundle();
            args.putInt(ModifyEmplFragment.KEY_ID, id);
            modifyEmplFragment.setArguments(args);
            modifyEmplFragment.show(fragmentManager, ModifyEmplFragment.TAG);
        }
    }

    public void getListByDepartment(int departmentId){

        empls = realm.where(Empl.class).equalTo("department", departmentId).findAll();
        recyclerView.setAdapter(new MyRecyclerViewAdapter(this, empls));
        toolbar.getMenu().findItem(R.id.action_add).setVisible(false);
        toolbar.getMenu().findItem(R.id.action_reset).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        toolbar.getMenu().findItem(R.id.action_reset).setVisible(true);

    }



    /*
    public void deleteItem(Empl item) {
        final String id = item.getTimeStamp();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Empl.class).equalTo(Empl.TIMESTAMP, id)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }
    */
}