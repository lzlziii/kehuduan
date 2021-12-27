package xx.yy.testhttp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

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
    findViewById(R.id.sendAuto).setOnClickListener(v -> new Thread() {
      public void run() {
        try {
          Utils.sendAuto("auto", 123L);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }.start());
  }
}