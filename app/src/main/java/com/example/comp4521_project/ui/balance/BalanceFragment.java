package com.example.comp4521_project.ui.balance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp4521_project.AddBillActivity;
import com.example.comp4521_project.Bill;
import com.example.comp4521_project.CurrencyConverter;
import com.example.comp4521_project.DBHelper;
import com.example.comp4521_project.Mode;
import com.example.comp4521_project.bill_recycler_view.BillItem;
import com.example.comp4521_project.ManageFriendsActivity;
import com.example.comp4521_project.bill_recycler_view.BillAdapter;
import com.example.comp4521_project.MyApplication;
import com.example.comp4521_project.R;
import com.example.comp4521_project.databinding.FragmentBalanceBinding;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BalanceFragment extends Fragment {

    private FragmentBalanceBinding binding;

    Button btnManageFriends, btnAddBill;
    TextView tv_friends_number;

    MyApplication myApplication;
    DBHelper DB;
    String username;
    String currency;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myApplication = (MyApplication) requireActivity().getApplication();
        DB = myApplication.getDB();
        username = myApplication.getUser().getUsername();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        currency = sharedPref.getString(getString(R.string.text_currency), "HKD");

        getActivity().setTitle("Balance");

        btnManageFriends = (Button) root.findViewById(R.id.btn_manage_friends);
        btnAddBill = (Button) root.findViewById(R.id.btn_add_bill);
        tv_friends_number = (TextView) root.findViewById(R.id.tv_friends_number);
        tv_friends_number.setText(myApplication.getDB().countFriends(myApplication.getUser().getUsername()).toString());
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

        // Recycler View
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);

        List<BillItem> billItems = new ArrayList<BillItem>();
        Bill[] bills = DB.getBills();
        for (int i = 0; i < bills.length; ++i) {
            Bill thisBill = bills[i];
            String title = thisBill.getTitle();
            String people = ("For " + String.join(", ", thisBill.getPeople())).replace(username, "You");
            String mode = thisBill.getMode() == Mode.EVENLY ? "Split evenly" : "Split individually";
            String total = currency + String.valueOf(CurrencyConverter.hkdTo(currency, thisBill.getTotal()));
            String paidBy = thisBill.getPaidBy() == username ? "You" : thisBill.getPaidBy();
            String history = thisBill.historyFrom(Instant.now(), thisBill.getCreateInstant());
            billItems.add(new BillItem(title, people, mode, total, paidBy, history));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new BillAdapter(getActivity().getApplicationContext(), billItems));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}