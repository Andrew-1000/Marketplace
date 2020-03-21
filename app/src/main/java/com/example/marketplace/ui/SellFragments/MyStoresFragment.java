package com.example.marketplace.ui.SellFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.adapter.KioskViewHolder;
import com.example.marketplace.model.Kiosk;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static com.google.firebase.database.FirebaseDatabase.getInstance;


public class MyStoresFragment extends Fragment {
    private DatabaseReference kioskReference;

    private TextInputEditText kioskLocation;
    private RecyclerView kiosksRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        kioskReference = FirebaseDatabase.getInstance().getReference("Kiosk");

        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_my_stores, container, false );

    }
    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {

        super.onViewCreated( view, savedInstanceState );

        view.findViewById( R.id.btn_back_home ).setOnClickListener( v -> addNewStore() );
        kioskLocation = view.findViewById( R.id.store_location );
        kioskReference  = getInstance().getReference("Kiosk");
        kiosksRecyclerView = view.findViewById( R.id.myStores );

        kiosksRecyclerView.setHasFixedSize( true );
        kiosksRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity() ) );
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Kiosk, KioskViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Kiosk, KioskViewHolder>(Kiosk.class, R.layout.kiosk_card, KioskViewHolder.class,kioskReference ) {
            @Override
            protected void populateViewHolder(KioskViewHolder kioskViewHolder, Kiosk kiosk, int i) {
                kioskViewHolder.setKiosk(kiosk.getKiosk_location());
                kioskViewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                } );
            }
        };
        kiosksRecyclerView.setAdapter( firebaseRecyclerAdapter );
    }
    private void addNewStore() {
        String kiosk_location = Objects.requireNonNull( kioskLocation.getText() ).toString().trim();
        if (!TextUtils.isEmpty( kiosk_location)) {
            String id = kioskReference.push().getKey();
            Kiosk kiosk = new Kiosk(kiosk_location);
            assert id != null;
            kioskReference.child( id ).setValue( kiosk );
            Toast.makeText( getActivity(), "New Kiosk Location Created", Toast.LENGTH_SHORT).show();
            kioskLocation.setText( "" );
        }else  {
            Toast.makeText( getActivity(), "Please provide location", Toast.LENGTH_SHORT ).show();
        }
    }

}
