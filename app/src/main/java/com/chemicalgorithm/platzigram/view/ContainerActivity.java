package com.chemicalgorithm.platzigram.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.view.fragment.HomeFragment;
import com.chemicalgorithm.platzigram.view.fragment.ProfileFragment;
import com.chemicalgorithm.platzigram.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);

		BottomNavigationView bottomBar = (BottomNavigationView) findViewById(R.id.bottomBar);
		selectFragment(new HomeFragment());

		bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				switch (item.getItemId())
				{
					case R.id.search:
						selectFragment(new SearchFragment());
						break;

					case R.id.home:
						selectFragment(new HomeFragment());
						break;

					case R.id.profile:
						selectFragment(new ProfileFragment());
						break;
				}
				return true;
			}
		});
	}

	private void selectFragment(Fragment fragment)
	{
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.addToBackStack(null)
				.commit();
	}
}
