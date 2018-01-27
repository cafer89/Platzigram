package com.chemicalgorithm.platzigram.chemicalgorithm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.chemicalgorithm.adapter.PictureAdapterRecyclerView;
import com.chemicalgorithm.platzigram.chemicalgorithm.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{


	public HomeFragment()
	{
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		showToolbar("Home", false, view);
		RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

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
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Danielita Rica", "4 días", "3"));
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Juan Pablo", "3 días", "10"));
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Carlos Pinzón", "2 días", "20"));
		pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Anahí Salgado", "3 días", "0"));
		return pictures;




	}

	public void showToolbar(String title , boolean upButton, View view)
	{
		Toolbar toolbar = (Toolbar) view.findViewById(R.id.toobar);
		//soporte para versiones anteriores a Lollipop
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
		//en caso de que tenga botón de regreso o subir
		((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
	}
}
