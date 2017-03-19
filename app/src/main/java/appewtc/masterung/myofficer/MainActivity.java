package appewtc.masterung.myofficer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit นี่คือการประกาศตัวแปร นะจร้า
    private EditText userEditText, passwordEditText;
    private Button button;
    private TextView textView;
    private String strUser, strPassword, strTruePassword;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Controller
        controller();

    }   // Main Method

    private void controller() {
        button.setOnClickListener(MainActivity.this);
        textView.setOnClickListener(MainActivity.this);
    }

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnLogin);
        textView = (TextView) findViewById(R.id.txtRegister);
    }

    @Override
    public void onClick(View view) {

        //For Login
        if (view == button) {

            //Get Value From Edit text
            strUser = userEditText.getText().toString().trim();
            strPassword = passwordEditText.getText().toString().trim();

            //Check Space
            if (strUser.equals("") || strPassword.equals("")) {
                myAlert("มีช่องว่าง นะคะ");
            } else {

                try {

                    MyGetData myGetData = new MyGetData(MainActivity.this);
                    myGetData.execute("http://swiftcodingthai.com/4mar/getMaster.php");

                    String strJSON = myGetData.get();
                    Log.d("19MarchV1", "JSoN ==> " + strJSON);

                    JSONArray jsonArray = new JSONArray(strJSON);
                    for (int i=0;i<jsonArray.length();i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (strUser.equals(jsonObject.getString("User"))) {
                            aBoolean = false;
                            strTruePassword = jsonObject.getString("Password");
                        }

                    }   //for

                    if (aBoolean) {
                        myAlert("User False");
                    } else if (strPassword.equals(strTruePassword)) {
                        startActivity(new Intent(MainActivity.this, ShowOfficerActivity.class));
                        finish();
                    } else {
                        myAlert("Password False");
                    }


                } catch (Exception e) {
                    Log.d("19MarchV1", "e ==> " + e.toString());
                }


            }   // if


        }   // if


        //For Register
        if (view == textView) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        }

    }   // onClick

    private void myAlert(String strMessage) {
        Toast.makeText(MainActivity.this, strMessage, Toast.LENGTH_SHORT).show();
    }

}   // Main Class
