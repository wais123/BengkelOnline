package com.idetree.bemonline.admin_product;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.idetree.bemonline.R;
import com.idetree.bemonline.adapter.AdminProductAdapter;
import com.idetree.bemonline.adapter.ProductAdapter;
import com.idetree.bemonline.admin_product.tambah_product.TambahProductActivity;
import com.idetree.bemonline.model.JsonArrayProductModel;
import com.idetree.bemonline.session.SessionManager;
import com.idetree.bemonline.sparepart_fragment.SparepartPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdminProductFragment extends Fragment implements AdminProductView {

    RecyclerView recyclerView;
    AdminProductAdapter adapter;
    List<AdminProductModel> productModels = new ArrayList<AdminProductModel>();
    SessionManager sessionManager;
    AdminProductPresenter presenter;
    ProgressBar progressBar;
    LinearLayoutCompat tambahProduct;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AdminProductFragment() {
        // Required empty public constructor
    }

    public static AdminProductFragment newInstance(String param1, String param2) {
        AdminProductFragment fragment = new AdminProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_product, container, false);
        sessionManager = new SessionManager(getActivity());
        initComponent(view);
        return view;
    }

    private void initComponent(View view){
        recyclerView = view.findViewById(R.id.rv_sparepart);
        progressBar = view.findViewById(R.id.progressBar_cyclic);
        tambahProduct = view.findViewById(R.id.tambah_product);
        presenter = new AdminProductPresenter(this);
        presenter.getListAdminProduct("Bearer "+sessionManager.getToken(),"application/json");
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        tambahProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahProductActivity.class);
                startActivity(intent);
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
    public void getAdminProductSuccess(List<AdminProductModel> response) {
        productModels = response;
        adapter = new AdminProductAdapter(getActivity(), productModels);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getAdminProductError(String error) {

    }
}