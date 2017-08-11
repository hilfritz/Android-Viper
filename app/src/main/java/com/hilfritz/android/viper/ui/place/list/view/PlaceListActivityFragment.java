package com.hilfritz.android.viper.ui.place.list.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hilfritz.android.viper.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceListActivityFragment extends Fragment {

    public PlaceListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_list, container, false);
    }
}
