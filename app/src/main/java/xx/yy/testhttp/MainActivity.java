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
//    findViewById(R.id.button).setOnClickListener(v -> new Thread() {
//      public void run() {
//        try {
//          User x = new User();
//
//          String obj = SerializeUtils.serialize(x);
//
//          Log.e("asdf", obj);
//
//          URL url = new URL("http://10.0.2.2:8080/oooo");
//
//          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//          httpURLConnection.setDoInput(true);
//          httpURLConnection.setDoOutput(true);
//          httpURLConnection.setRequestMethod("POST");
//          httpURLConnection.setUseCaches(false);
//          httpURLConnection.setConnectTimeout(1000);
//          httpURLConnection.setReadTimeout(1000);
//          httpURLConnection.connect();
//
//          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
//          String line;
//
//          StringBuilder stringBuilder = new StringBuilder();
//          while ((line = bufferedReader.readLine()) != null) {
//            stringBuilder.append(line);
//          }
//
//          Log.e("msg", stringBuilder.toString());
//          bufferedReader.close();
//          httpURLConnection.disconnect();
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }.start());
    findViewById(R.id.button).setOnClickListener(v -> new Thread() {
      public void run() {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = null;
        try {
          postUrl = new URL("http://10.0.2.2:8080/oooo");
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
        // 打开连接
        HttpURLConnection connection = null;
        try {
          assert postUrl != null;
          connection = (HttpURLConnection) postUrl.openConnection();
        } catch (IOException e) {
          e.printStackTrace();
        }

        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        assert connection != null;
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        try {
          connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
          e.printStackTrace();
        }

        // Post 请求不能使用缓存
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);

        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        try {
          connection.connect();
        } catch (IOException e) {
          e.printStackTrace();
        }
        DataOutputStream out = null;
        try {
          out = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
          e.printStackTrace();
        }
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = null;
        try {
          content = "account=" + URLEncoder.encode(SerializeUtils.serialize(new User()), "UTF-8");
        } catch (IOException e) {
          e.printStackTrace();
        }
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        try {
          assert out != null;
          out.writeBytes(content);
        } catch (IOException e) {
          e.printStackTrace();
        }

        try {
          out.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

        BufferedReader reader = null;
        try {
          reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
          e.printStackTrace();
        }
        String line = "";

        while (true) {
          try {
            assert reader != null;
            if ((line = reader.readLine()) == null) break;
          } catch (IOException e) {
            e.printStackTrace();
          }
          System.out.println(line);
        }

        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        connection.disconnect();
      }
    }.start());


    findViewById(R.id.sendBig).setOnClickListener(v -> new Thread() {
      public void run() {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = null;
        try {
          postUrl = new URL("http://10.0.2.2:8080/big");
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
        // 打开连接
        HttpURLConnection connection = null;
        try {
          assert postUrl != null;
          connection = (HttpURLConnection) postUrl.openConnection();
        } catch (IOException e) {
          e.printStackTrace();
        }

        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        assert connection != null;
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        try {
          connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
          e.printStackTrace();
        }

        // Post 请求不能使用缓存
        connection.setUseCaches(false);

        connection.setInstanceFollowRedirects(true);

        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        try {
          connection.connect();
        } catch (IOException e) {
          e.printStackTrace();
        }
        DataOutputStream out = null;
        try {
          out = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
          e.printStackTrace();
        }
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = null;
        try {
          content = "big=" + URLEncoder.encode(SerializeUtils.serialize(new BigInteger("99824435310000007")), "UTF-8");
        } catch (IOException e) {
          e.printStackTrace();
        }
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        try {
          assert out != null;
          out.writeBytes(content);
        } catch (IOException e) {
          e.printStackTrace();
        }

        try {
          out.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

        BufferedReader reader = null;
        try {
          reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
          e.printStackTrace();
        }
        String line = "";

        while (true) {
          try {
            assert reader != null;
            if ((line = reader.readLine()) == null) break;
          } catch (IOException e) {
            e.printStackTrace();
          }
          System.out.println(line);
        }

        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        connection.disconnect();
      }
    }.start());
  }
}