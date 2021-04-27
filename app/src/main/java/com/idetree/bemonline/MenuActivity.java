package com.idetree.bemonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idetree.bemonline.antrian_fragment.DaftarAntrianFragment;
import com.idetree.bemonline.booking_fragment.BookingFragment;
import com.idetree.bemonline.login.LoginActivity;
import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.GeneralResponse;
import com.idetree.bemonline.session.SessionManager;
import com.idetree.bemonline.sparepart_fragment.SparePartFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    CircleImageView viewProfile;
    SessionManager sessionManager;
    BottomNavigationView navigation;
    TextView textView;
    public static final String GOOGLE_ACCOUNT = "google_account";

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_antrian:
                    fragment = new DaftarAntrianFragment();
                    loadFragment(fragment,"Daftar Antrian");
                    return true;
                case R.id.navigation_service:
                    fragment = new BookingFragment();
                    loadFragment(fragment,"Booking Service");
                    return true;
                case R.id.navigation_sparepart:
                    fragment = new SparePartFragment();
                    loadFragment(fragment,"Sparepart");
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.account:
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                sessionManager.logoutUser();
                finish();
                LoginActivity.googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(MenuActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sessionManager = new SessionManager(this);
        textView = findViewById(R.id.tv_title);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(listener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation.setSelectedItemId(R.id.navigation_antrian);
    }

    private void loadFragment(Fragment fragment, String title) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        textView.setText(title);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSelectedItem(navigation) != R.id.navigation_antrian) {
            navigation.setSelectedItemId(R.id.navigation_antrian);
        } else {
            super.onBackPressed();
        }
    }

    private int getSelectedItem(BottomNavigationView bottomNavigationView) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.isChecked()) {
                return menuItem.getItemId();
            }
        }
        return R.id.navigation_antrian;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkToken();
    }

    private void checkToken() {
        Api.getService().checkToken("Bearer " + sessionManager.getToken(), "application/json").enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() != 0) {
                        sessionManager.logoutUser();
                        finish();
                    } else {
                        System.out.println(response.body().getMessage());
                    }
                } else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
