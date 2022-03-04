package edu.neu.madcourse.numad22sp_yizhang;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebServiceActivity extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";
    private EditText mURLEditText;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private ArrayList<CountryCard> countryList = new ArrayList<>();
    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        createRecyclerView();

        mURLEditText = (EditText)findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void callWebserviceButtonHandler(View view) {
        PingWebTask task = new PingWebTask();
        try {
            name = mURLEditText.getText().toString();
            String url = convertToUrl("https://api.nationalize.io/?name=" + name);
            task.executeAsync(url); // This is a security risk.  Don't let your user enter the URL in a real app.
        } catch (InvalidParameterException e) {
            Toast.makeText(getApplication(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private String convertToUrl(String url) throws InvalidParameterException {
        if (Patterns.WEB_URL.matcher(url).matches() || URLUtil.isValidUrl(url)) {
            if(!(url.startsWith("https://")||url.startsWith("http://"))){
                return "https://" + url;
            }
            return url;
        }
        throw new InvalidParameterException("Invalid Input");
    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.country_view);
        recyclerView.setHasFixedSize(true);

        countryAdapter = new CountryAdapter(countryList);

        recyclerView.setAdapter(countryAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void addItem(int position, String name, String country, String prob) {
        countryList.add(position, new CountryCard(name, country, prob));
        countryAdapter.notifyItemInserted(position);
    }

    private class PingWebTask {
        private ExecutorService executor = Executors.newSingleThreadExecutor();
        private final Handler handler = new Handler(Looper.getMainLooper());
        private JSONObject jObject = new JSONObject();

        public void executeAsync(String address) {
            progressBar.setVisibility(View.VISIBLE);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(address);
                        // Get String response from the url address
                        String resp = getHttpResponse(url);
                        //Log.i("resp",resp);

                        // JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                        // Transform String into JSONObject
                        jObject = new JSONObject(resp);
                        progressBar.setVisibility(View.INVISIBLE);

                    } catch (MalformedURLException e) {
                        Log.e(TAG,"MalformedURLException");
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        Log.e(TAG,"ProtocolException");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e(TAG,"IOException");
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Log.e(TAG,"JSONException");
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String code = jObject.getJSONArray("country").getJSONObject(0).getString("country_id");
                                String prob = jObject.getJSONArray("country").getJSONObject(0).getString("probability");
                                prob = prob.substring(0, 5);
                                Locale loc = new Locale("", code);
                                loc.getDisplayCountry();
                                addItem(0, "Name: " + name, "Country: " + loc.getDisplayCountry(),"Probability: " + prob);
                            } catch (JSONException e) {
                                Snackbar.make(recyclerView, "Invalid Name",
                                        Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }
                    });
                }
            });
        }

        private String getHttpResponse(URL url) throws IOException {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Read response.
            InputStream inputStream = conn.getInputStream();
            StringBuilder stringBuilder=new StringBuilder();
            try {
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String len;
                while((len=bufferedReader.readLine())!=null){
                    stringBuilder.append(len);
                }
                bufferedReader.close();
                return stringBuilder.toString().replace(",", ",\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
