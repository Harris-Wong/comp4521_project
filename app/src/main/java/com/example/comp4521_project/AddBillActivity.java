package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.comp4521_project.friend_selection_view.FriendSelectionAdapter;
import com.example.comp4521_project.friend_selection_view.FriendSelectionItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddBillActivity extends AppCompatActivity {

    EditText etTitle, etTotal;
    Button btnCreateBill;
    Spinner snAddBillCurrency;
    ToggleButton tbEvenly, tbIndividually;

    TextView dpFriendSelection;
    boolean[] selectedFriend;
    ArrayList<Integer> forList = new ArrayList<>();
    String[] friendArray = new String[0];

    ListView listView;
    FriendSelectionAdapter friendSelectionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        btnCreateBill = (Button) findViewById(R.id.btn_create_bill);
        tbEvenly = (ToggleButton) findViewById(R.id.tb_evenly);
        tbIndividually = (ToggleButton) findViewById(R.id.tb_individually);
        snAddBillCurrency = (Spinner) findViewById(R.id.sn_add_bill_currency);

//        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        List<String> items = new ArrayList<>();

        friendSelectionAdapter = new FriendSelectionAdapter(this, items);
        listView = findViewById(R.id.friend_selection_view);
        listView.setAdapter(friendSelectionAdapter);

        tbEvenly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundResource(R.drawable.active_toggle_button_background_with_border);
                    tbIndividually.setChecked(false);
                    tbIndividually.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
                }
            }
        });

        tbIndividually.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundResource(R.drawable.active_toggle_button_background_with_border);
                    tbEvenly.setChecked(false);
                    tbEvenly.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
                }
            }
        });

        dpFriendSelection = findViewById(R.id.dp_friend_selection);
        // initialize selected friend array
        selectedFriend = new boolean[friendArray.length];
        dpFriendSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddBillActivity.this);
                builder.setTitle("The bill is for...");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(friendArray, selectedFriend, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos, boolean checked) {
                        // check condition
                        if (checked) {
                            // when checkbox selected
                            // Add position  in friend list
                            forList.add(pos);
                            // Sort array list
                            Collections.sort(forList);
                        } else {
                            // when checkbox unselected
                            // Remove position from friendList
                            forList.remove(Integer.valueOf(pos));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            items.clear();

                            StringBuilder stringBuilder = new StringBuilder();

                            for (int j = 0; j < forList.size(); j++) {
                                // concat array value
                                stringBuilder.append(friendArray[forList.get(j)]);
                                items.add(friendArray[forList.get(j)]);

                                // check condition
                                if (j != forList.size() - 1) {
                                    // When j value  not equal
                                    // to friend list size - 1
                                    // add comma
                                    stringBuilder.append(", ");
                                }
                            }
                            // set text on textView
//                            dpFriendSelection.setText(stringBuilder.toString());

                            friendSelectionAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            Log.e("AddBillActivity", "Error in onClick(): " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                /*
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedFriend.length; j++) {
                            // remove all selection
                            selectedFriend[j] = false;
                            // clear language list
                            forList.clear();
                            // clear text view value
                            dpFriendSelection.setText("");
                        }

                        items.clear();
                        friendSelectionAdapter.notifyDataSetChanged();
                    }
                });
                */
                // show dialog
                builder.show();
            }
        });

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Todo: Check whether all view is filled + Add Bill info to Bill Table in database

                String splitMode = tbEvenly.isChecked() ? "Evenly" : "Individually";
                Log.d("log", splitMode);
                List<String[]> friendInfolist = new ArrayList<>();

                List<FriendSelectionItem> friendSelectionItems = friendSelectionAdapter.getFriendSelectionItems();
                for (FriendSelectionItem friendSelectionItem : friendSelectionItems) {
                    String friend = friendSelectionItem.getFriend();
                    String debt = friendSelectionItem.getDebt();
                    friendInfolist.add(new String[]{friend, debt});
                }
                Log.i("Add Bill", Arrays.deepToString(friendInfolist.toArray()));


                // Print success message and Back to Home Activity
                Toast.makeText(getApplicationContext(), "Bill successfully created", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }
}
