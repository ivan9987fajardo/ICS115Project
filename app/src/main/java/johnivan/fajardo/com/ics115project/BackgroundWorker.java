package johnivan.fajardo.com.ics115project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    String type = "";

    @Override
    protected String doInBackground(String... params) {
        String login_url = "http://ics115project.000webhostapp.com/Login115.php";
        String register_url = "http://ics115project.000webhostapp.com/Register115.php";
        if (params[0].toString().equals("Register")) {
            try {
                String Username = params[1];
                String Password = params[2];
                String Firstname = params[3];
                String Lastname = params[4];
                String Pic = params[5];
                type = params[0];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&"
                        + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8") + "&"
                        + URLEncoder.encode("Firstname", "UTF-8") + "=" + URLEncoder.encode(Firstname, "UTF-8") + "&"
                        + URLEncoder.encode("Lastname", "UTF-8") + "=" + URLEncoder.encode(Lastname, "UTF-8") + "&"
                        + URLEncoder.encode("Pic", "UTF-8") + "=" + URLEncoder.encode(Pic, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {

                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("An Error has occurred");
                alertDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("An Error has occurred");
                alertDialog.show();
            }

        }

        if (params[0].toString().equals("Login")) {
            try {
                String Username = params[1];
                String Password = params[2];
                type = params[0];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&"
                        + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {

                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("An Error has occurred");
                alertDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("An Error has occurred");
                alertDialog.show();
            }
        }
        return null;
    }







        protected void onPreExecute () {
            alertDialog = new AlertDialog.Builder(context).create();


        }

        @Override
        protected void onPostExecute (String result) {

            if (type.equals("Register")) {

                if (result.equals("Success")) {
                    context.startActivity(new Intent(context, Login.class));
                }
                else {
                    alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(result);
                    alertDialog.show();
                }
            }

            if(type.equals("Login")) {

                if(result.equals("Success")) {
                    context.startActivity(new Intent(context, Home.class));
                }

                else {
                    alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(result);
                    alertDialog.show();

                }
            }

        }





        @Override
        protected void onProgressUpdate (Void...values){
            super.onProgressUpdate(values);
        }



}
