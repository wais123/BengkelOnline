package com.idetree.bemonline.sparepart_fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import com.idetree.bemonline.R;
import com.idetree.bemonline.adapter.ProductAdapter;
import com.idetree.bemonline.model.JsonArrayProductModel;
import com.idetree.bemonline.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SparePartFragment extends Fragment implements SparePartView{
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<JsonArrayProductModel> productModels = new ArrayList<JsonArrayProductModel>();
    SessionManager sessionManager;
    SparepartPresenter presenter;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_spare_part, container, false);
        sessionManager = new SessionManager(getActivity());
        initComponent(view);
        return view;
    }

    private void initComponent(View view){
        recyclerView = view.findViewById(R.id.rv_sparepart);
        progressBar = view.findViewById(R.id.progressBar_cyclic);
        presenter = new SparepartPresenter(this);
        presenter.getListProduct("Bearer "+sessionManager.getToken(),"application/json");
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
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
    public void getSparePartSuccess(List<JsonArrayProductModel> response) {
        productModels = response;
        adapter = new ProductAdapter(getActivity(), productModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getSparePartError(String error) {

    }
}
