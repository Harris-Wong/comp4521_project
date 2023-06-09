package com.example.comp4521_project.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.comp4521_project.Currency;
import com.example.comp4521_project.MyApplication;
import com.example.comp4521_project.R;
import com.example.comp4521_project.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getActivity().getString(R.string.title_settings);
        getActivity().setTitle(title);

        Spinner spinner = (Spinner) getView().findViewById(R.id.currencySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        Currency currentCurrency = Currency.valueOf(sharedPref.getString(getString(R.string.text_currency), Currency.HKD.toString()));
        switch (currentCurrency) {
            case HKD:
                spinner.setSelection(0);
                break;
            case USD:
                spinner.setSelection(1);
                break;
            case JPY:
                spinner.setSelection(2);
                break;
            case CNY:
                spinner.setSelection(3);
                break;
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCurrency = (String) parent.getItemAtPosition(position);
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE).edit();
                editor.putString(getString(R.string.text_currency), selectedCurrency);
                editor.apply();
//                Toast.makeText(getContext(), "Currency set to " + selectedCurrency, Toast.LENGTH_SHORT).show();
                Log.d("log", selectedCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button logoutButton = (Button) getView().findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("log", "Logout clicked");
                ((MyApplication) getActivity().getApplication()).getUser().setUsername("");
                Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}