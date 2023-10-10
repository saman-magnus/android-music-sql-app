package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.API.APIService;
import com.example.musicapp.API.Dataservice;
import com.example.musicapp.Adapter.SongAdapter;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    RecyclerView recyclerViewSearch;
    TextView tvResultSearch;
    EditText txtKeyword;
    List<Song> songList;
    List<Song> filterSongs;
    SongAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        songList = new ArrayList<>();
        filterSongs = new ArrayList<>();
        getAllSong();
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        tvResultSearch = view.findViewById(R.id.tvResultSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);

        txtKeyword = view.findViewById(R.id.txtKeyword);
        adapter = new SongAdapter(getContext(), filterSongs);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewSearch.setAdapter(adapter);
        txtKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String key = txtKeyword.getText().toString();
                if (key.length() < 2) {
                    filterSongs.clear();
                } else {
                    filterData(key);
                }
            }
        });

        return view;
    }

    private void filterData(String key) {
        filterSongs.clear();
        for (Song s : songList) {
            if (s.getTenNS().toLowerCase().contains(key.toLowerCase()) || s.getTenBH().toLowerCase().contains(key.toLowerCase())) {
                filterSongs.add(s);
            }
        }
        tvResultSearch.setText("Có " + filterSongs.size() + " matching results");
        tvResultSearch.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();

    }

    private void getAllSong() {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getAllMusic();
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                if (result != null) {
                    songList.clear();
                    for (Song s : result) {
                        songList.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}