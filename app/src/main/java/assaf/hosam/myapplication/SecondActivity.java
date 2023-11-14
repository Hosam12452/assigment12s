package assaf.hosam.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Gson gson=new Gson();
    int id;
    SharedPreferences preferences ;
    SharedPreferences.Editor editor ;
    EditText taskname;
    EditText taskdate;
    EditText status;
    Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.taskstatus);
        preferences = getSharedPreferences("DATA", MODE_PRIVATE);

        editor = preferences.edit();
        taskname=findViewById(R.id.taskname);
        taskdate=findViewById(R.id.date);
        status=findViewById(R.id.status);
        save=findViewById(R.id.save);
        Intent intent = getIntent();

        int selectedItem = intent.getIntExtra("selectedItem", 0);
        id=selectedItem;
        String selectedJson=preferences.getString(selectedItem+"",null);
        Task newTask=gson.fromJson(selectedJson,Task.class);
        taskname.setText(newTask.getTaskName());
        taskdate.setText(newTask.getDeadLine());
        status.setText(newTask.getStatus());
        status.setHint(newTask.getStatus());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savechanges();
            }
        });



    }
public void savechanges(){
        if(status.getText().equals("")||taskname.getText().equals("")||taskdate.getText().equals("")){
            Toast.makeText(this, "please fill the blocks", Toast.LENGTH_SHORT).show();
        }else{

            Task newTask=new Task(String.valueOf(taskdate.getText()),String.valueOf(taskname.getText()),id,String.valueOf(status.getText()));
            String gsonTask=gson.toJson(newTask,Task.class);
            editor.putString(id+"",gsonTask);
            editor.commit();
            Intent intent = new Intent( SecondActivity.this,MainActivity.class);
            startActivity(intent);
        }

}
}
