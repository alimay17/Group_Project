package com.example.medicationtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/********************************************************************
 * A class to define a recycler view for medication display
 *******************************************************************/
public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.MedViewHolder> {

  // member variables of layout, medication data, and click listener
  private final LayoutInflater mInflater;
  private List<Medication> mMeds;
  private OnItemClickListener clickListener;

  // constructor
  MedListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

  /********************************************************************
   * Handles when view is created
   * @param parent the holder on activity page
   * @param viewType an integer to define view type
   * @return the new view
   *******************************************************************/
  @Override
  public MedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new MedViewHolder(itemView);
  }

  /********************************************************************
   * bind the view and set the items.
   * @param holder container for all items
   * @param position where to place items
   *******************************************************************/
  @Override public void onBindViewHolder(MedViewHolder holder, int position) {
    if(mMeds != null) {
      Medication current = mMeds.get(position);
      holder.medItemView.setText(current.getName());
    } else {
      holder.medItemView.setText("No History Available");
    }
  }

  /********************************************************************
   * sets medications to updated list, and sends notification of change
   * @param meds list of all current meds
   *******************************************************************/
  void setMeds(List<Medication> meds) {
    mMeds = meds;
    notifyDataSetChanged();
  }

  /********************************************************************
   * Sets the listener
   *******************************************************************/
  public void setClickListener(OnItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  /********************************************************************
   * gets a count of how may items exist
   * @return total number of items in med list
   *******************************************************************/
  @Override
  public int getItemCount() {
    if(mMeds != null)
      return mMeds.size();
    else return 0;
  }

  /********************************************************************
   * nested class to hold the view
   *******************************************************************/
  class MedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView medItemView;

    // initialized new view
    private MedViewHolder(View itemView) {
      super(itemView);
      medItemView = itemView.findViewById(R.id.textView);
      itemView.setOnClickListener(this);
    }

    // click listener
    @Override
    public void onClick(View view) {
      if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
  }
}