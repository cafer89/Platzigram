package com.chemicalgorithm.platzigram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import com.chemicalgorithm.platzigram.R;
import android.view.ViewGroup;
import com.chemicalgorithm.platzigram.adapter.PictureAdapterRecyclerView;
import com.chemicalgorithm.platzigram.model.Picture;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment
{
	private static final String TAG = "Profile Fragment";

	public ProfileFragment()
	{
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		FirebaseCrash.log("Inicializando" + TAG);
		View view = inflater.inflate(R.layout.fragment_profile, container, false);
		showToolbar("", false, view);

		RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		picturesRecycler.setLayoutManager(linearLayoutManager);

		PictureAdapterRecyclerView pictureAdapterRecyclerView =
				new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
		picturesRecycler.setAdapter(pictureAdapterRecyclerView);
		return view;
	}

	public ArrayList<Picture> buildPictures()
	{
		ArrayList<Picture> pictures = new ArrayList<>();
		pictures.add(new Picture("https://pbs.twimg.com/profile_images/953823390695088129/3HlXltqB_400x400.jpg",
				"Danielita hermosa", "4 días", "3 me gusta"));
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg",
				"Juan Pablo", "3 días", "10 me gusta"));
		pictures.add(new Picture("https://i.pinimg.com/564x/fd/91/9a/fd919ae49b855848f2c407f363d44aef--gato-cheshire-the-cheshire.jpg",
				"Carlos Pinzón", "2 días", "20 me gusta"));
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg",
				"Anahí Salgado", "3 días", "0 me gusta"));
		return pictures;
	}

	public void showToolbar(String title , boolean upButton, View view)
	{
		Toolbar toolbar = (Toolbar) view.findViewById(R.id.ProfileToolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
		((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
	}

}
