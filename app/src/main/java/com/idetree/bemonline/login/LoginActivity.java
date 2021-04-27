package com.idetree.bemonline.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.idetree.bemonline.MenuActivity;
import com.idetree.bemonline.MenuAdmActivity;
import com.idetree.bemonline.R;
import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.register.RegisterActivity;
import com.idetree.bemonline.response.LoginModelResponse;
import com.idetree.bemonline.session.SessionManager;
import com.idetree.bemonline.utils.AppValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginView {

    Button login, daftar;
    EditText username, password;
    ProgressBar progressBar;
    SessionManager sessionManager;
    TextView textView;
    LoginPresenter presenter;
    ImageView viewLoginGoogle;
    public static GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 007;
    private static final String TAG = "Hasil";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        presenter = new LoginPresenter(this);
        initComponent();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            firebaseAuthWithGoogle(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.GOOGLE_ACCOUNT, acct);

        startActivity(intent);
        finish();
    }

    private void initComponent() {
//        viewLoginGoogle = findViewById(R.id.login_google);
        progressBar = findViewById(R.id.progressBar_cyclic);
        login = findViewById(R.id.btn_login);
        daftar = findViewById(R.id.btn_daftar);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        textView = findViewById(R.id.tv_forgot);

//        viewLoginGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForgotPassword(LoginActivity.this);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().equals("") || username.getText().toString().trim().equals(null)) {
                    Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    username.setFocusable(true);
                } else if (password.getText().toString().trim().equals("") || password.getText().toString().trim().equals(null)) {
                    Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    password.setFocusable(true);
                } else if (!AppValidator.isValidEmail(username.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Email not valid", Toast.LENGTH_SHORT).show();
                    username.setFocusable(true);
                } else {
                    presenter.loginProccess(username.getText().toString(), password.getText().toString());
                }

            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(daftar);
            }
        });
    }

    public static void dialogForgotPassword(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forgot_password);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        LinearLayout layoutBatal = dialog.findViewById(R.id.ln_batal);
        LinearLayout layoutKirim = dialog.findViewById(R.id.ln_kirim);
        final EditText email = dialog.findViewById(R.id.et_email);

        layoutBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void successLogin(LoginModelResponse response) {
        if (response.getCode() == 0) {
             if (response.getData().getLevel().equals("Admin")) {
                 sessionManager.createLoginSession("bengkel", response.getData().getEmail(), response.getData().getToken(), response.getData().getLevel());
                 Intent login = new Intent(LoginActivity.this, MenuAdmActivity.class);
                startActivity(login);
            } else if (response.getData().getLevel().equals("pelanggan")){
                 sessionManager.createLoginSession("bengkel", response.getData().getEmail(), response.getData().getToken(), response.getData().getLevel());
                 Intent login = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(login);
            }


        } else {
            Toast.makeText(LoginActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorLogin(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
