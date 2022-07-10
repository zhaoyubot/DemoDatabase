package sg.edu.rp.c346.id20011119.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    EditText etTasks, etDate;
    boolean asc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTask);
        tvResults = findViewById(R.id.textViewResults);
        etTasks = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);
        lv = findViewById(R.id.listview);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask(etTasks.getText().toString(),etDate.getText().toString());
            }
        });

        asc = true;

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> data = dbh.getTaskContent();

                String txt = "";
                for(int i = 0; i < data.size(); i++){
                    txt += i + " . "+data.get(i) + " \n";
                }
                tvResults.setText(txt);
                if (asc){
                    asc = false;
                }else{
                    asc = true;
                }

                al.clear();
                al.addAll(dbh.getTasks(asc));
                aa.notifyDataSetChanged();
            }

        });
    }
}