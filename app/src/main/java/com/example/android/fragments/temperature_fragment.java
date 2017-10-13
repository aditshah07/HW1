package com.example.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class temperature_fragment extends Fragment {

        final static String ARG_POSITION = "position";
        int mCurrentPosition = -1;
        Button convert;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            // If activity recreated (such as from screen rotate), restore
            // the previous article selection set by onSaveInstanceState().
            // This is primarily necessary when in the two-pane layout.
            if (savedInstanceState != null) {
                mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
            }
            View rootView = inflater.inflate(R.layout.fragment_temperature_fragment, container, false);
            convert = (Button) rootView.findViewById(R.id.button);
            convert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculate(v);
                }
            });
            // Inflate the layout for this fragment
            return rootView;
        }

        // Calculate the conversion
        public void calculate(View view){
            TextView article = (TextView) getActivity().findViewById(R.id.mytextview);
            EditText msgTextField = (EditText) getActivity().findViewById(R.id.mytextfield);
            String temp;
            temp =  msgTextField.getText().toString();
            double temp_no = Double.parseDouble(temp);
            double curr;
            Bundle bundle = this.getArguments();
            int p = bundle.getInt("pos");
            //C-->F Conversion
            if(p == 0){
                curr = ((temp_no*9)/5)+32;
                article.setText("Temperature " + (int)temp_no + " (C) is " + (int)curr + " (F)");
            }
            //F-->C Conversion
            if(p == 1){
                curr = ((temp_no-32)*5)/9;
                article.setText("Temperature " + (int)temp_no + " (F) is " + (int)curr + " (C)");
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            // Save the current article selection in case we need to recreate the fragment
            outState.putInt(ARG_POSITION, mCurrentPosition);
        }
}
