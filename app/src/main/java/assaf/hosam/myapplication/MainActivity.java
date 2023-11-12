package assaf.hosam.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView tasks ;
TextView header;
Button done,due,add_task;
EditText task_name;
String selectedDate;
CalendarView calendarView;
int counter =0;
    ArrayAdapter<String> adapter;
    ArrayList<String> stringList = new ArrayList<>();

    Gson gson=new Gson();
SharedPreferences preferences ;
SharedPreferences.Editor editor ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
 preferences = getSharedPreferences("DATA", MODE_PRIVATE);
         editor = preferences.edit();
        tasks=findViewById(R.id.tasks_ui);
        done=findViewById(R.id.done);
        due=findViewById(R.id.due);
        add_task=findViewById(R.id.add_task);
        task_name=findViewById(R.id.name);
        calendarView=findViewById(R.id.calendarView);

       adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, stringList);
        tasks.setAdapter(adapter);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

            }
        });
        header=findViewById(R.id.header);







        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             add_click();
            }
        });

    }
    public void add_click(){
        String name=task_name.getText().toString();
        if(name.isEmpty() || selectedDate==null){
                Toast.makeText(this, "task name and date is requierd"+selectedDate
                        , Toast.LENGTH_SHORT).show();

        }
        else{
            counter+=1;
         Task newTask=new Task(selectedDate,name,counter,"due");
            Toast.makeText(this, newTask.getDeadLine(), Toast.LENGTH_SHORT).show();
            String task= gson.toJson(newTask);
            editor.putString(counter+"",task);
            editor.commit();
            stringList.add(newTask.getTaskId()+"-"+newTask.getTaskName()+"  "+newTask.getStatus()+"  "+newTask.getDeadLine());
            adapter.notifyDataSetChanged();

        }

        }
    }
