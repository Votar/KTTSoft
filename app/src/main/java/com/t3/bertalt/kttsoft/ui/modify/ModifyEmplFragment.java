package com.t3.bertalt.kttsoft.ui.modify;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.t3.bertalt.kttsoft.R;
import com.t3.bertalt.kttsoft.adapters.DepartmentAdapter;
import com.t3.bertalt.kttsoft.model.Departments;
import com.t3.bertalt.kttsoft.model.Empl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by bertalt on 4/1/16.
 */
public class ModifyEmplFragment extends DialogFragment {

    public static String TAG = "ModifyEmplFragment";
    public static String KEY_ID = "modify_key_id";
    private static ModifyEmplFragment sInstance;
    private Realm realm;
    private Empl currentEmpl;

    @BindView(R.id.fragment_et_btn_save)
    protected Button btnSave;

    @OnClick(R.id.fragment_et_btn_save)
    public void onSave() {


        if (realm != null) {
            if (!realm.isClosed()) {

                if(!commonValidatorIsOk()){

                    Toast.makeText(getContext(), getString(R.string.toast_enter_all_fields), Toast.LENGTH_SHORT).show();
                    return;
                }

                    realm.beginTransaction();
                    currentEmpl.setFullName(etName.getText().toString());
                    currentEmpl.setAge(Integer.valueOf(etAge.getText().toString()));
                    currentEmpl.setDepartment((int) spDepartment.getSelectedItemId());
                    currentEmpl.setSalary(Double.valueOf(etSalary.getText().toString()));
                    currentEmpl.setPosition(etPosition.getText().toString());


                    realm.copyToRealmOrUpdate(currentEmpl);
                    realm.commitTransaction();

                    dismiss();

            }
        }

    }

    private boolean commonValidatorIsOk() {

        if(etAge.getText().toString().isEmpty()) return false;
        else if(etPosition.getText().toString().isEmpty())return false;
        else if(etSalary.getText().toString().isEmpty()) return false;
        else if(etName.getText().toString().isEmpty()) return false;

        return true;
    }

    @BindView(R.id.fragment_et_fullname)
    protected EditText etName;

    @BindView(R.id.fragment_et_age)
    protected EditText etAge;

    @BindView(R.id.fragment_et_position)
    protected EditText etPosition;

    @BindView(R.id.fragment_et_salary)
    protected EditText etSalary;

    @BindView(R.id.fragment_et_logo)
    protected TextView tvLogo;

    @BindView(R.id.fragment_et_spinner_dep)
    protected Spinner spDepartment;


    public ModifyEmplFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflaterm
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_modify_empl, null);

        realm = Realm.getDefaultInstance();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        builder.setView(view);

        ButterKnife.bind(this, view);

        Bundle args = getArguments();

        spDepartment.setAdapter(new DepartmentAdapter(getContext(),
                R.layout.item_department, Departments.values(),
                getActivity().getLayoutInflater()));

        if (args != null) {
            tvLogo.setText(getString(R.string.text_edit_profile));
            int id = getArguments().getInt(ModifyEmplFragment.KEY_ID, -1);


            currentEmpl = realm.where(Empl.class).equalTo("id",id).findFirst();

            bindData(currentEmpl);


        } else {

            currentEmpl = new Empl();
            tvLogo.setText(getString(R.string.add_profile));
        }

        return builder.create();
    }


    private void bindData(Empl empl) {

        if (empl == null) return;

        etName.setText(empl.getFullName());
        etAge.setText(String.valueOf(empl.getAge()));


        spDepartment.setSelection(empl.getDepartment());

        etPosition.setText(empl.getPosition());
        etSalary.setText(String.valueOf(empl.getSalary()));

    }




}
