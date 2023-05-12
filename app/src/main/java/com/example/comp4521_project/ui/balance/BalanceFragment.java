package com.example.comp4521_project.ui.balance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp4521_project.AddBillActivity;
import com.example.comp4521_project.Item;
import com.example.comp4521_project.ManageFriendsActivity;
import com.example.comp4521_project.MyAdapter;
import com.example.comp4521_project.MyApplication;
import com.example.comp4521_project.R;
import com.example.comp4521_project.RegisterActivity;
import com.example.comp4521_project.databinding.FragmentBalanceBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BalanceFragment extends Fragment {

    private FragmentBalanceBinding binding;

    Button btnManageFriends, btnAddBill;
    TextView tv_friends_number;

    MyApplication myApplication;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myApplication = (MyApplication) requireActivity().getApplication();

        String title = getActivity().getString(R.string.title_balance);
        getActivity().setTitle(title);

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

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("McDonald's", "For You", "Split Evenly", "HKD 200", "Paid by You", "1d ago"));
        items.add(new Item("KFC", "For Her", "Split Individually", "HKD 100", "Paid by He, You", "2d ago"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(getActivity().getApplicationContext(), items));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}