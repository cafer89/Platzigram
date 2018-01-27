package com.chemicalgorithm.platzigram.chemicalgorithm.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chemicalgorithm.platzigram.R;
import com.chemicalgorithm.platzigram.chemicalgorithm.model.Picture;

import java.util.ArrayList;

/**
 * Created by Usuario on 26/01/2018
 */

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>
{

	private ArrayList<Picture> pictures;
	private int resource;  //aqui llamamos al layout que hicimos (el CardView) R.layout.carview_picture.xml
	private Activity activity; //pasamos como parametro la actividad desde donde se está llamando ésta clase
								//ayuda bastante para llamar imagenes desde internet.


	public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity)
	{
		this.pictures = pictures;
		this.resource = resource;
		this.activity = activity;
	}

	@Override
	public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{

		View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
		return new PictureViewHolder(view);
	}

	@Override
	public void onBindViewHolder(PictureViewHolder holder, int position)
	{
		Picture picture = pictures.get(position);
		holder.usernameCard.setText(picture.getUserName());
		holder.timeCard.setText(picture.getTime());
		holder.likeNumberCard.setText(picture.getLike_number());
	}

	@Override
	public int getItemCount()
	{
		return pictures.size();
	}

	public class PictureViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView pictureCard;
		private TextView usernameCard;
		private TextView timeCard;
		private TextView likeNumberCard;

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
