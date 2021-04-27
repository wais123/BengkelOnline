package com.idetree.bemonline.booking_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.idetree.bemonline.MenuActivity;
import com.idetree.bemonline.R;
import com.idetree.bemonline.network.Api;
import com.idetree.bemonline.response.GeneralResponse;
import com.idetree.bemonline.session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingFragment extends Fragment implements BookingView{
    int tuneUpp = 0;
    int gantiOlii = 0;
    Button btnSubmit;
    EditText etName;
    EditText etStnk;
    EditText etKeluhan;

    ProgressBar progressBar;

    SessionManager sessionManager;

    BookingPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_booking, container, false);
        sessionManager = new SessionManager(getActivity());
        initComponent(view);
        return view;
    }

    private void initComponent(View view){
        presenter = new BookingPresenter(this);
        final CheckBox tuneUp = (CheckBox) view.findViewById(R.id.tune_up);
        final CheckBox gantiOli = (CheckBox) view.findViewById(R.id.ganti_oli);
        progressBar = view.findViewById(R.id.progressBar);
        System.out.println("tuneup : " + tuneUp);
        System.out.println("gantioli : " + gantiOli);
        etKeluhan = view.findViewById(R.id.et_keluhan);
        etStnk = view.findViewById(R.id.et_stnk);
        etName = view.findViewById(R.id.et_name);
        btnSubmit = view.findViewById(R.id.btn_submit);
        tuneUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    tuneUpp = 1;
                    tuneUp.setTextColor(Color.parseColor("#000000"));
                } else {
                    tuneUpp = 0;
                    tuneUp.setTextColor(Color.parseColor("#BCB9B9"));
                }
            }
        });
        gantiOli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkedd = ((CheckBox) v).isChecked();
                if (checkedd) {
                    gantiOlii = 1;
                    gantiOli.setTextColor(Color.parseColor("#000000"));
                } else {
                    gantiOlii = 0;
                    gantiOli.setTextColor(Color.parseColor("#BCB9B9"));
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().trim().equals("") || etName.getText().toString().trim().equals(null)) {
                    etName.setError("Name is required");
                } else if (etStnk.getText().toString().trim().equals("") || etStnk.getText().toString().trim().equals(null)) {
                    etStnk.setError("STNK is required");
                } else if (etKeluhan.getText().toString().trim().equals("") || etKeluhan.getText().toString().trim().equals(null)) {
                    etKeluhan.setError("Keluhan is required");
                } else {
                    presenter.postBooking("Bearer " + sessionManager.getToken(),"application/json",etName.getText().toString(),etStnk.getText().toString(),tuneUpp,gantiOlii,etKeluhan.getText().toString());
                }

            }
        });
    }

    @Override
    public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void postBookingSuccess(final GeneralResponse response) {
        if (response.getCode() == 0){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        }
    }

    @Override
    public void postBookingError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
