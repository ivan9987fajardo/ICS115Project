package johnivan.fajardo.com.ics115project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Register extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE=1;
    ImageView profile,profile2;
    Button upload;
    Uri selectedImage;
    EditText user,pass,pass2,first_name,last_name,email_add,number;
    int count=0;

    String username,password,picstring,firstname,lastname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        profile=(ImageView)findViewById(R.id.Pic);
        user = (EditText)findViewById(R.id.user_name);
        pass = (EditText)findViewById(R.id.pass_word);
        pass2 = (EditText)findViewById(R.id.pass_word2);






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data !=null){
            selectedImage = data.getData();
            profile.setImageURI(selectedImage);

        }
    }

    public void uploadImage(android.view.View v){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,RESULT_LOAD_IMAGE);

    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }



    public void regNext(android.view.View v){
        if( TextUtils.isEmpty(user.getText())||TextUtils.isEmpty(pass.getText())){

            Toast.makeText(this,"Username/Password must be filled!",Toast.LENGTH_LONG).show();

        }else{
            if(pass.getText().toString().equals(pass2.getText().toString())) {

                username=user.getText().toString();
                password=pass.getText().toString();
                //Bitmap bm = BitmapFactory.decodeFile(String.valueOf(selectedImage));

                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    picstring = encodeImage(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                
                


              // ByteArrayOutputStream baos = new ByteArrayOutputStream();
              // bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
               //byte[] byteArrayImage = baos.toByteArray();
               // picstring = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                setContentView(R.layout.register2);
                first_name=(EditText)findViewById(R.id.fname);
                last_name=(EditText)findViewById(R.id.lname);
                email_add=(EditText)findViewById(R.id.email);
                number=(EditText)findViewById(R.id.mobile);
                profile2 = (ImageView) findViewById(R.id.Pic2);
                profile2.setImageURI(selectedImage);
                count=1;

            }
            else{
                Toast.makeText(this,"Password do not match!",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void onBackPressed(){
        if(count==1){
            Intent i = new Intent(this,Register.class);
            startActivity(i);



        }
        else{
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

        }

    }

    public void createSubmit(android.view.View v){
          firstname= first_name.getText().toString();
          lastname= last_name.getText().toString();
          String type = "Register";

        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password,firstname,lastname,picstring);
        Toast.makeText(this,"Register Successful",Toast.LENGTH_LONG).show();
    }





}
