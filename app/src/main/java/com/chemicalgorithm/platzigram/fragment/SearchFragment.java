package com.chemicalgorithm.platzigram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.adapter.PictureAdapterRecyclerView;
import com.chemicalgorithm.platzigram.model.Picture;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment
{

	RecyclerView recyclerView;
	private static final String TAG ="Search Fragment";


	public SearchFragment()
	{
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		FirebaseCrash.log("iniciaizando" + TAG);
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		SearchView searchView = (SearchView) view.findViewById(R.id.search_view);
		searchView.setSubmitButtonEnabled(true);

		recyclerView = (RecyclerView) view.findViewById(R.id.search_recycler);
		recyclerView.setVisibility(View.INVISIBLE);

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
		{
			@Override
			public boolean onQueryTextSubmit(String query)
			{
				if(query.equals("platzigram"))
				{
					GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
					recyclerView.setLayoutManager(gridLayoutManager);
					PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
					recyclerView.setAdapter(pictureAdapterRecyclerView);
					recyclerView.setVisibility(View.VISIBLE);
					return true;
				}
				else
				{
					Toast.makeText(getContext(),"Busqueda incorreta", Toast.LENGTH_SHORT).show();
				}
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText)
			{

				return true;
			}
		});
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

}
