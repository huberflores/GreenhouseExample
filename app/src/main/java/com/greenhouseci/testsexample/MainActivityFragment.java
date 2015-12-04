package com.greenhouseci.testsexample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button btn;
    TextView tv;
    ListView lv;
    EditText editText;

    ArrayList<String> items = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn = (Button) view.findViewById(R.id.btn_add);
        tv = (TextView) view.findViewById(R.id.tv_empty);
        lv = (ListView) view.findViewById(R.id.lv_items);
        editText = (EditText) view.findViewById(R.id.edit_item);

        ListAdapter adapter = new ListAdapter(getActivity(), items);
        lv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = editText.getText().toString();
                if (itemName.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter something", Toast.LENGTH_SHORT).show();
                } else {
                    editText.setText("");
                    items.add(itemName);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if (items.size() > 0 && tv.getVisibility() == View.VISIBLE) {
                    tv.setVisibility(View.GONE);
                }
            }
        });
    }
}
