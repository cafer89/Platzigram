package com.chemicalgorithm.platzigram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.adapter.PictureAdapterRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment
{


	public SearchFragment()
	{
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		SearchView searchView = (SearchView) view.findViewById(R.id.search_view);
		searchView.setSubmitButtonEnabled(true);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.search_recycler);
		GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), GridLayoutManager.DEFAULT_SPAN_COUNT);
		recyclerView.setLayoutManager(gridLayoutManager);
		return view;

	}

}
