package appewtc.masterung.myofficer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowOfficerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_officer);

        try {

            ListView listView = (ListView) findViewById(R.id.livOfficer);

            MyGetData myGetData = new MyGetData(ShowOfficerActivity.this);
            myGetData.execute("http://swiftcodingthai.com/4mar/getMaster.php");
            String strJSON = myGetData.get();

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                imageStrings[i] = jsonObject.getString("Image");

            }

            MyAdapter myAdapter = new MyAdapter(ShowOfficerActivity.this,
                    nameStrings, imageStrings);
            listView.setAdapter(myAdapter);


        } catch (Exception e) {
            Log.d("19MarchV1", "e show ==> " + e.toString());
        }

    }   // Main Method
}   // Main Class
