package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.ButtonAdapter;
import com.example.musicapp.Model.ListButton;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ListButton> listButtons;
    private ButtonAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerViewHome = v.findViewById(R.id.recyclerViewHome);
        listButtons = new ArrayList<>();
        initsData();
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ButtonAdapter(getContext(), listButtons);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
        recyclerViewHome.addItemDecoration(decoration);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setAdapter(adapter);


        return v;
    }

    private void initsData() {
        listButtons.add(new ListButton(1, "Song"));
        listButtons.add(new ListButton(2, "Singer"));
        listButtons.add(new ListButton(3, "Album"));
        listButtons.add(new ListButton(4, "Charts"));
        listButtons.add(new ListButton(5, "What do you hear today?"));
        listButtons.add(new ListButton(6, "Music Player"));

    }
}