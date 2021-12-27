package xx.yy.testhttp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.button).setOnClickListener(v -> new Thread() {
      public void run() {
        try {
          User p = new User();
          p.setId(5);
          p.setAge(123);
          Utils.sendObject("oooo", "account", p);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start());


    findViewById(R.id.sendBig).setOnClickListener(v -> new Thread() {
      public void run() {
        try {
          Utils.sendObject("big", "big", new BigInteger("181237128738172831273817238123971631"));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start());
  }
}