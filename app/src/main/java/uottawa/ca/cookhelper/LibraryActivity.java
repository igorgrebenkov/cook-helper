package uottawa.ca.cookhelper;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {

    ArrayList<String> test = new ArrayList<String>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        test.add("test1");
        test.add("test2");
        test.add("test3");
        test.add("test4");
        test.add("test5");
        listView = (ListView) findViewById(R.id.recipeListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, test);
        listView.setAdapter(adapter);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
    }




}
