package com.example.comp4521_project.ui.balance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.comp4521_project.AddBillActivity;
import com.example.comp4521_project.ManageFriendsActivity;
import com.example.comp4521_project.R;
import com.example.comp4521_project.RegisterActivity;
import com.example.comp4521_project.databinding.FragmentBalanceBinding;

public class BalanceFragment extends Fragment {

    private FragmentBalanceBinding binding;

    Button btnManageFriends, btnAddBill;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnManageFriends = (Button) root.findViewById(R.id.btn_manage_friends);
        btnAddBill = (Button) root.findViewById(R.id.btn_add_bill);

        btnManageFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManageFriendsActivity.class);
                startActivity(intent);
            }
        });

        btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddBillActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}