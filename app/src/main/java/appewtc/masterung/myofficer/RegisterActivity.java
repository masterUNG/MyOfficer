package appewtc.masterung.myofficer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText nameEditText, userEditText, passwordEditText;
    private Button button;
    private Uri uri;
    private String tag = "18MarchV1";
    private String pathImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();

        controller();

    }   // Main Method

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //For Setup Choose Image to ImageView
            try {

                uri = data.getData();
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // if

        //Find Path Choose Image
        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            pathImageString = cursor.getString(index);
        } else {
            pathImageString = uri.getPath();
        }

        Log.d(tag, "pathImage ==> " + pathImageString);


    }   // onActivity

    private void controller() {
        imageView.setOnClickListener(RegisterActivity.this);
        button.setOnClickListener(RegisterActivity.this);
    }

    private void bindWidget() {

        imageView = (ImageView) findViewById(R.id.imvHumen);
        nameEditText = (EditText) findViewById(R.id.edtName);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnRegister);

    }

    @Override
    public void onClick(View view) {

        //for Image
        if (view == imageView) {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);

        }   // if

    }   // onClick

}   // Main Class
