package pl.wsb.projekt_m_gnyba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

import pl.wsb.projekt_m_gnyba.databinding.ActivityLoginBinding;

public class BMIFragment extends Fragment {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_bmi, container, false);

        EditText wagaTextView = (EditText)rootView.findViewById(R.id.waga);
        EditText wzrostTextView = (EditText)rootView.findViewById(R.id.wzrost);

        Button submitButton = (Button)rootView.findViewById(R.id.submit);
        TextView resultTextView = (TextView)rootView.findViewById(R.id.result);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String testString = wagaTextView.getText().toString();

                double wagaValue;
                double wzrostValue;
                double bmi = 0.0;

                try {
                    wagaValue = Double.parseDouble(testString);
                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getActivity(), "Podano błędną wagę.", Toast.LENGTH_LONG).show();
                    return;
                }

                testString = wzrostTextView.getText().toString();

                try {
                    wzrostValue = Double.parseDouble(testString);
                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getActivity(), "Podano błędny wzrost.", Toast.LENGTH_LONG).show();
                    return;
                }

                double wzrostValueAsMeters = wzrostValue/100;

                bmi = wagaValue / (wzrostValueAsMeters*wzrostValueAsMeters);

                resultTextView.setText("Twoje BMI to: " + df.format(bmi));
            }
        });

        return rootView;
    }


}