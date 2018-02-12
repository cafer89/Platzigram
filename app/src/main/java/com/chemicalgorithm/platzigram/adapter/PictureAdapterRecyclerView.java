package com.chemicalgorithm.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.model.Picture;
import com.chemicalgorithm.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Usuario on 26/01/2018
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>
{

	private ArrayList<Picture> pictures;
	private int resource;  //aqui llamamos al layout que hicimos (el CardView) R.layout.carview_picture.xml
	private Activity activity; // activity o contexto, pasamos como parametro la actividad desde donde se está llamando ésta clase
								//ayuda bastante para llamar imagenes desde internet.


	public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity)
	{
		this.pictures = pictures;
		this.resource = resource;
		this.activity = activity;
	}

	@Override
	/**
	 * viewType: a viewType based on position for reuse/recycle of views.
	 * This method handles ViewHolder creation/recycle and view inflation based on itemViewType
	 * returned by getItemViewType(...).
	 */
	public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{

		View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
		return new PictureViewHolder(view);
	}

	@Override
	//this method binds data to the child views of the viewHolder.
	public void onBindViewHolder(PictureViewHolder Pholder, int position)
	{
		Picture picture = pictures.get(position);
		Pholder.usernameCard.setText(picture.getUserName());
		Pholder.timeCard.setText(picture.getTime());
		Pholder.likeNumberCard.setText(picture.getLike_number());
		Picasso.with(activity).load(picture.getPicture()).into(Pholder.pictureCard);

		Pholder.pictureCard.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(activity, PictureDetailActivity.class);
				activity.startActivity(i);
			}
		});


	}

	@Override
	/**
	 * this method is called several times to know the size limit of the list
	 * returns the size of the dataset used for the adapter.
	 */

	public int getItemCount()
	{
		return pictures.size();
	}

	//ViewHolder: parent class that handles layout inflation and child view use
	public class PictureViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView pictureCard;
		private TextView usernameCard;
		private TextView timeCard;
		private TextView likeNumberCard;

		//Click events belong into this method.
		public PictureViewHolder(View itemView)
		{
			super(itemView);
			pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard);
			usernameCard = (TextView) itemView.findViewById(R.id.userNameCard);
			timeCard = (TextView) itemView.findViewById(R.id.timeCard);
			likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
		}
	}
}
