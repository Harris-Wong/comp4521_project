package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Spinner snAddBillCurrency;
    Mode splitMode = Mode.EVENLY;
    String currency = "HKD";

    Button btnCreateBill;

    ToggleButton tbEvenly, tbIndividually;

    TextView dpFriendSelection;
    boolean[] selectedFriend;
    ArrayList<Integer> friendBuffer = new ArrayList<>();
    String[] friendOptions = new String[0];
    List<String> listViewItems;

    ListView listView;
    FriendSelectionAdapter friendSelectionAdapter;

    MyApplication myApplication;
    String username;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        myApplication = (MyApplication) getApplication();
        username = myApplication.getUser().getUsername();
        DB = myApplication.getDB();

        friendOptions = DB.getFriends(username);

        snAddBillCurrency = (Spinner) findViewById(R.id.sn_add_bill_currency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snAddBillCurrency.setAdapter(adapter);
        snAddBillCurrency.setSelection(0);
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        currency = sharedPref.getString(getString(R.string.text_currency), "HKD");
        switch (currency) {
            case "HKD":
                snAddBillCurrency.setSelection(0);
                break;
            case "USD":
                snAddBillCurrency.setSelection(1);
                break;
            case "JPY":
                snAddBillCurrency.setSelection(2);
                break;
            case "CNY":
                snAddBillCurrency.setSelection(3);
                break;
        }
        snAddBillCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newValue = (String) parent.getItemAtPosition(position);
                currency = newValue;
                Log.d("log", "Currency: " + currency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCreateBill = (Button) findViewById(R.id.btn_create_bill);
        tbEvenly = (ToggleButton) findViewById(R.id.tb_evenly);
        tbIndividually = (ToggleButton) findViewById(R.id.tb_individually);

        listViewItems = new ArrayList<>();
        listViewItems.add(username);

        friendSelectionAdapter = new FriendSelectionAdapter(this, listViewItems);
        listView = findViewById(R.id.friend_selection_view);
        listView.setAdapter(friendSelectionAdapter);

        tbEvenly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    splitMode = Mode.EVENLY;
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
                    splitMode = Mode.INDIVIDUALLY;
                    buttonView.setBackgroundResource(R.drawable.active_toggle_button_background_with_border);
                    tbEvenly.setChecked(false);
                    tbEvenly.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
                }
            }
        });

        dpFriendSelection = findViewById(R.id.dp_friend_selection);
        // initialize selected friend array
        selectedFriend = new boolean[friendOptions.length];
        dpFriendSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddBillActivity.this);
                builder.setTitle("The bill is for...");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(friendOptions, selectedFriend, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos, boolean checked) {
                        // check condition
                        if (checked) {
                            // when checkbox selected
                            // Add position  in friend list
                            friendBuffer.add(pos);
                            // Sort array list
                            Collections.sort(friendBuffer);
                        } else {
                            // when checkbox unselected
                            // Remove position from friendList
                            friendBuffer.remove(Integer.valueOf(pos));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            for (String s : listViewItems) {
                                if (!s.startsWith(username)) {
                                    listViewItems.remove(s);
                                }
                            }

                            StringBuilder stringBuilder = new StringBuilder();

                            for (int j = 0; j < friendBuffer.size(); j++) {
                                // concat array value
                                stringBuilder.append(friendOptions[friendBuffer.get(j)]);
                                listViewItems.add(friendOptions[friendBuffer.get(j)]);

                                // check condition
                                if (j != friendBuffer.size() - 1) {
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

                List<String[]> friendInfolist = new ArrayList<>();
                Log.d("log", splitMode.toString());

                List<FriendSelectionItem> friendSelectionItems = friendSelectionAdapter.getFriendSelectionItems();
                for (FriendSelectionItem friendSelectionItem : friendSelectionItems) {
                    String friendName = friendSelectionItem.getFriend();
                    String debt = friendSelectionItem.getDebt();
                    friendInfolist.add(new String[]{friendName, debt});
                    Log.d("log", "add friendInfoList " + friendName + " " + debt);
                }
                Log.d("log", Arrays.deepToString(friendInfolist.toArray()));


                // Print success message and Back to Home Activity
                Toast.makeText(getApplicationContext(), "Bill successfully created", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }
}
