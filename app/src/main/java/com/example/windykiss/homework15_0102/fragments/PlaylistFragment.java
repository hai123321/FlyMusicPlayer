package com.example.windykiss.homework15_0102.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.adapters.MusicLoveAdapter;
import com.example.windykiss.homework15_0102.managers.DBContext;
import com.example.windykiss.homework15_0102.managers.RecyclerViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {

    @BindView(R.id.rv_love)
    RecyclerView rv_love;

    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, v);
        setupUI();
        return v;
    }

    private void setupUI() {
        GridLayoutManager manager = new GridLayoutManager(
                getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });
        rv_love.setLayoutManager(manager);
        rv_love.setAdapter(new MusicLoveAdapter());

        rv_love.addOnItemTouchListener(new RecyclerViewListener(
                getActivity(), rv_love, new RecyclerViewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SongFragment songFragment = new SongFragment();
                songFragment.setIdPos(DBContext.getInstance().findAllMusicLove().get(position).getId());
                changeFragment(songFragment, true);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    private void changeFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

}
