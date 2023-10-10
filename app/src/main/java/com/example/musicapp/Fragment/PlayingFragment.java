package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.SongPlayingAdapter;
import com.example.musicapp.R;
import com.example.musicapp.SubActivity.SongPlayingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayingFragment extends Fragment {
    View view;
    RecyclerView recyclerViewSongPlaying;
    public static SongPlayingAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayingFragment newInstance(String param1, String param2) {
        PlayingFragment fragment = new PlayingFragment();
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
        view = inflater.inflate(R.layout.fragment_song_playing, container, false);
        recyclerViewSongPlaying = view.findViewById(R.id.recyclerViewSongPlaying);
        adapter = new SongPlayingAdapter(getActivity(), SongPlayingActivity.songList);
        recyclerViewSongPlaying.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSongPlaying.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewSongPlaying.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}