package johnivan.fajardo.com.ics115project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    EditText user_login,pass_login;
    String Loginstr, Passstr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user_login = (EditText)findViewById(R.id.user_name_login);
        pass_login = (EditText)findViewById(R.id.pass_word_login);
    }

    public void login(android.view.View v) {
        Loginstr= user_login.getText().toString();
        Passstr= pass_login.getText().toString();
        String type = "Login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,Loginstr,Passstr);


    }
    }


