package il.co.kdror.dror;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FirstTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView idErr = (TextView) findViewById(R.id.id_err);
        final TextView nameErr = (TextView) findViewById(R.id.name_err);

        final EditText id = (EditText) findViewById(R.id.et_id);
        final EditText name = (EditText) findViewById(R.id.et_name);
        final EditText phone = (EditText) findViewById(R.id.et_phone);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                idErr.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 9)
                {
                    idErr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nameErr.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().split(" ").length <= 1 || s.toString().split(" ").length > 3)
                {
                    nameErr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.bt_submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idErr.getVisibility() == View.GONE && nameErr.getVisibility() == View.GONE) {
                    SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
                    //settings.edit().putString(MainActivity.ID_PREF, id.getText().toString()).apply();
                    //settings.edit().putString(MainActivity.NAME_PREF, name.getText().toString()).apply();
                    //settings.edit().putString(MainActivity.LOCATION_PREF, getResources().getStringArray(R.array.location_array)[spinner.getSelectedItemPosition()]).apply();
                    //settings.edit().putString(MainActivity.PHONE_PREF, phone.getText().toString()).apply();

                    Student student = new Student(id.getText().toString(), name.getText().toString(), getResources().getStringArray(R.array.location_array)[spinner.getSelectedItemPosition()], phone.getText().toString());

                    settings.edit().putString(MainActivity.STUDENT_PREF, student.Serialze()).apply();
                    finish();
                }
            }
        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
