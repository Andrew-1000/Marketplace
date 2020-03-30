package com.example.marketplace.ui.SellFragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.example.marketplace.R;
import com.example.marketplace.model.Product;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private static final int REQUEST_TO_PICK_IMAGE = 1;
    private ValueEventListener valueEventListener;
    private ArrayAdapter<String> kioskAdapter;
    private ArrayAdapter<String> categoriesAdapter;


    private ArrayList<String> kioskSpinnerData;
    private ArrayList<String> categoriesSpinnerData;


    private DatabaseReference kioskDatabaseReference;
    private DatabaseReference categoryDatabaseReference;

    private StorageReference mStorageReference;
    private DatabaseReference productsDatabaseReference;

    @BindView( R.id.product_name ) TextInputEditText productName;
    @BindView( R.id.product_description ) TextInputEditText productDescription;
    @BindView( R.id.product_price ) TextInputEditText productPrice;
    @BindView( R.id.kiosk_location ) AppCompatSpinner kioskLocation;
    @BindView( R.id.product_category ) AppCompatSpinner productCategory;
    @BindView( R.id.imageView ) ImageView imageView;

    @BindView( R.id.saveProduct ) MaterialButton btnSaveProduct;
    @BindView( R.id.progressBar )
    ProgressBar progressBar;

    private Uri imageUri;
    private StorageTask savingTask;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        kioskSpinnerData = new ArrayList<>();
        categoriesSpinnerData = new ArrayList<>();

        kioskAdapter = new ArrayAdapter<>( Objects.requireNonNull( getActivity() ), android.R.layout.simple_spinner_dropdown_item, kioskSpinnerData );
        categoriesAdapter = new ArrayAdapter<>( getActivity() , android.R.layout.simple_spinner_dropdown_item, categoriesSpinnerData );

        kioskDatabaseReference = FirebaseDatabase.getInstance().getReference("Kiosk");
        categoryDatabaseReference = FirebaseDatabase.getInstance().getReference("Category");

        mStorageReference = FirebaseStorage.getInstance().getReference("Products");
        productsDatabaseReference = FirebaseDatabase.getInstance().getReference("Products");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Unbinder unbinder = ButterKnife.bind( this, view );
        imageView.setOnClickListener( this );
        btnSaveProduct.setOnClickListener( this );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatSpinner kioskSpinner = view.findViewById( R.id.kiosk_location );
        AppCompatSpinner categorySpinner = view.findViewById( R.id.product_category );

        kioskSpinner.setAdapter( kioskAdapter );
        categorySpinner.setAdapter( categoriesAdapter );
        getKiosksToSpinners();
        getCategoriesToSpinners();

        super.onViewCreated( view, savedInstanceState );

    }

            private void getKiosksToSpinners() {
                Query valueEventListener = kioskDatabaseReference.orderByChild( "kiosk_location" );
                valueEventListener.addChildEventListener( new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        for (DataSnapshot item: dataSnapshot.getChildren()) {
                            kioskSpinnerData.add( Objects.requireNonNull( item.getValue() ).toString() );
                        }
                        kioskAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }

    private void getCategoriesToSpinners(){
        Query valueEventListener = categoryDatabaseReference.orderByChild( "category_name" );
        valueEventListener.addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot item: dataSnapshot.getChildren()){
                    categoriesSpinnerData.add( Objects.requireNonNull( item.getValue().toString() ) );
                }
                categoriesAdapter.notifyDataSetChanged();

            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
    @Override
    public void onClick(View v) {
        if (v == imageView) {
            openImageChooserToPickImage();
        }
        if (v == btnSaveProduct) {

            if (savingTask != null && savingTask.isInProgress()){
                Toast.makeText( getActivity(), "Saving in progress", Toast.LENGTH_LONG ).show();
            }else{
                postProduct();
            }

        }
    }

    private void openImageChooserToPickImage() {
        Intent intent = new Intent();
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT  );
        startActivityForResult( intent, REQUEST_TO_PICK_IMAGE );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == REQUEST_TO_PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            Picasso.get().load( imageUri ).into( imageView );
        }
    }

    //gets the file extension from the image
    private String getExtensionOfImage(Uri uri) {
        ContentResolver contentResolver = Objects.requireNonNull( getActivity() ).getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType( contentResolver.getType( uri ) );
    }
    private void postProduct() {
        if (imageUri!= null) {
            StorageReference fileReference = mStorageReference.child( System.currentTimeMillis()
            + "." + getExtensionOfImage( imageUri ));

            savingTask = fileReference.putFile( imageUri )
                    .addOnSuccessListener( (UploadTask.TaskSnapshot taskSnapshot) -> {
                        Handler handler = new Handler();
                        handler.postDelayed( () -> progressBar.setProgress( 0 ), 500 );
                        progressBar.setVisibility( View.GONE );
                        Toast.makeText( getActivity(), "Product successfully posted", Toast.LENGTH_LONG ).show();
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();

                        //Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString());
                        // use if testing...don't need this line.
                        assert downloadUrl != null;
                        Product product = new Product(
                                Objects.requireNonNull( productName.getText() ).toString().trim(),
                                Objects.requireNonNull( productDescription.getText() ).toString().trim(),
                                Objects.requireNonNull( productPrice.getText() ).toString().trim(),
                                kioskLocation.getSelectedItem().toString(),
                                productCategory.getSelectedItem().toString(),
                                downloadUrl.toString());

                                String id = productsDatabaseReference.push().getKey();
                                assert id != null;
                                productsDatabaseReference.child( id ).setValue( product );


                    } ).addOnFailureListener( e -> Toast.makeText( getActivity(), e.getMessage(),
                            Toast.LENGTH_SHORT ).show()
                    ). addOnProgressListener( taskSnapshot -> {
                progressBar.setVisibility( View.VISIBLE );
                Toast.makeText( getActivity(), "Posting Product", Toast.LENGTH_SHORT ).show();

            } );
        } else {
            Toast.makeText( getActivity(), "No image selected", Toast.LENGTH_SHORT ).show();
        }
    }


}


