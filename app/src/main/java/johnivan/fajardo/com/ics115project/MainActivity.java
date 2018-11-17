package johnivan.fajardo.com.ics115project;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void registerBtn(android.view.View v){
        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }

    public void loginBtn(android.view.View v){
        Intent i = new Intent(this,Login.class);
        startActivity(i);

    }



}
