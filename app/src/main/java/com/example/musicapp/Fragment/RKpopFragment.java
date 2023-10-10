package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Use the {@link RKpopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RKpopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerViewRankKpop;
    private SongAdapter adapter;
    private List<Song> songs;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RKpopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RKpopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RKpopFragment newInstance(String param1, String param2) {
        RKpopFragment fragment = new RKpopFragment();
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
        View v = inflater.inflate(R.layout.fragment_r_kpop, container, false);
        recyclerViewRankKpop = v.findViewById(R.id.recyclerViewRankKpop);
        songs = new ArrayList<>();
        adapter = new SongAdapter(getContext(), songs);
        recyclerViewRankKpop.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewRankKpop.addItemDecoration(decoration);
        recyclerViewRankKpop.setHasFixedSize(true);
        recyclerViewRankKpop.setAdapter(adapter);
        getSongsRankingKorean();
        // Inflate the layout for this fragment
        return v;
    }

    private void getSongsRankingKorean() {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getSongByRankingId(3);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                if (result != null) {
                    songs.clear();
                    for (Song s : result) {
                        Log.e("TAG", s.getTenBH());
                        songs.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));
                        adapter.notifyDataSetChanged();
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