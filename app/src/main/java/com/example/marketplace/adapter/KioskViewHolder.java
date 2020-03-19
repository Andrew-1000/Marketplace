package com.example.marketplace.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;

public class KioskViewHolder extends RecyclerView.ViewHolder {
    View mView;
    public KioskViewHolder(@NonNull View itemView) {
        super( itemView );
        mView = itemView;
    }

    public void setKiosk(String kiosk_location) {
        TextView kioskLocation = mView.findViewById( R.id.kioskName );
        kioskLocation.setText( kiosk_location );
    }
}
