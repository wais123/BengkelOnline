package com.idetree.bemonline.admin_product.tambah_product;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.idetree.bemonline.R;
import com.idetree.bemonline.response.GeneralResponse;
import com.idetree.bemonline.session.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TambahProductActivity extends AppCompatActivity implements TambahProductView {
    EditText etName, etDescription, etQuantity;
    ImageView imageProduct;
    Button btnSubmit;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String imagePath;
    ProgressBar progressBar;
    TambahProductPresenter presenter;
    SessionManager sessionManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);

                //encode
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                byte[] imageBytes = baos.toByteArray();
                imagePath = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                imageBytes = Base64.decode(imagePath, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageProduct.setImageBitmap(decodedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_product);
        presenter = new TambahProductPresenter(this);
        sessionManager = new SessionManager(this);
        initComponent(this);

    }

    private void initComponent(Context context) {
        etName = findViewById(R.id.et_name);
        etDescription = findViewById(R.id.et_description);
        etQuantity = findViewById(R.id.et_quantity);
        imageProduct = findViewById(R.id.imageProduct);
        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressBar);

        imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TambahProductActivity.this, "On Progress", Toast.LENGTH_SHORT).show();
                if (etName.getText().toString().trim().equals("") || etName.getText().toString().trim().equals(null)) {
                    etName.setError("Name is required");
                } else if (etDescription.getText().toString().trim().equals("") || etDescription.getText().toString().trim().equals(null)) {
                    etDescription.setError("STNK is required");
                } else if (etQuantity.getText().toString().trim().equals("") || etQuantity.getText().toString().trim().equals(null)) {
                    etQuantity.setError("Keluhan is required");
                } else {
                    presenter.createProduct("Bearer " + sessionManager.getToken(),"application/json",etName.getText().toString(), etDescription.getText().toString(), etQuantity.getText().toString(), imagePath);
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
    public void getAdminProductSuccess(GeneralResponse response) {
        if (response.getCode() == 0) {
            onBackPressed();
        } else {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAdminProductError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}