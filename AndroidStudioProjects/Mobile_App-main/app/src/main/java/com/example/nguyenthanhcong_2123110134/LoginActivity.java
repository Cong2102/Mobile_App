package com.example.nguyenthanhcong_2123110134;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;
import com.android.volley.RequestQueue;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://fakestoreapi.com/products";
    EditText edtEmail, edtPassword;
    Button btnLogin, btnToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnToRegister = findViewById(R.id.btn_to_register);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            String loginUrl = "https://fakestoreapi.com/auth/login";

            StringRequest loginRequest = new StringRequest(Request.Method.POST, loginUrl,
                    response -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String token = jsonObject.getString("token");

                            // Sau khi đăng nhập thành công -> gọi API lấy user info
                            String userInfoUrl = "https://fakestoreapi.com/users/1"; // Giả sử user id là 1

                            StringRequest userInfoRequest = new StringRequest(Request.Method.GET, userInfoUrl,
                                    userResponse -> {
                                        try {
                                            JSONObject userObj = new JSONObject(userResponse);
                                            String firstName = userObj.getJSONObject("name").getString("firstname");
                                            String lastName = userObj.getJSONObject("name").getString("lastname");
                                            String fullName = firstName + " " + lastName;

                                            // Lưu thông tin người dùng
                                            SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
                                            editor.putBoolean("isLoggedIn", true);
                                            editor.putString("fullName", fullName);
                                            editor.apply();

                                            // Chuyển sang MainActivity
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    },
                                    error -> Toast.makeText(this, "Lỗi lấy thông tin người dùng", Toast.LENGTH_SHORT).show()
                            );

                            Volley.newRequestQueue(LoginActivity.this).add(userInfoRequest);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", email);
                    params.put("password", password);
                    return params;
                }
            };


            Volley.newRequestQueue(LoginActivity.this).add(loginRequest);
        });


        btnToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        getData();
    }

    // Tự động chuyển sang MainActivity nếu đã đăng nhập
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
