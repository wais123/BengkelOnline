package com.idetree.bemonline.antrian_fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.idetree.bemonline.R;
import com.idetree.bemonline.adapter.AntrianAdapter;
import com.idetree.bemonline.model.AntrianModel;
import com.idetree.bemonline.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class DaftarAntrianFragment extends Fragment implements DaftarAntrianView{

    RecyclerView recyclerView;
    AntrianAdapter adapter;
    List<AntrianModel> antrianModel = new ArrayList<>();
    SessionManager sessionManager;
    LottieAnimationView lottieAnimationView;
    DaftarAntrianPresenter presenter;
    ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_daftar_antrian, container, false);
        sessionManager = new SessionManager(getActivity());
        initComponent(view);
        return view;
    }

    private void initComponent(View view){
        recyclerView = view.findViewById(R.id.rv_antrian);
        progressBar = view.findViewById(R.id.progressBar_cyclic);
        presenter = new DaftarAntrianPresenter(this);
        presenter.getListAntrian("Bearer "+sessionManager.getToken(),"application/json");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
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
    public void getAntrianSuccess(List<AntrianModel> data) {
        antrianModel = data;
        adapter = new AntrianAdapter(getActivity(), antrianModel);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getAntrianError(String error) {

    }
}
