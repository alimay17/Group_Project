package com.example.medicationtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.MedViewHolder> {

  // layout
  private final LayoutInflater mInflater;

  // medication data
  private List<Medication> mMeds;

  // listener
  private OnItemClickListener clickListener;

  /**
   * constructor
   * @param context
   */
  MedListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

  /**
   * When the view is created
   * @param parent
   * @param viewType
   * @return
   */
  @Override
  public MedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new MedViewHolder(itemView);
  }

  /**
   * bind the view and set the items.
   * @param holder
   * @param position
   */
  @Override public void onBindViewHolder(MedViewHolder holder, int position) {
    if(mMeds != null) {
      Medication current = mMeds.get(position);
      holder.medItemView.setText(current.getName());
    } else {
      holder.medItemView.setText("No History Available");
    }
  }

  /**
   * sets medications to updated list.
   * @param meds
   */
  void setMeds(List<Medication> meds) {
    mMeds = meds;
    notifyDataSetChanged();
  }

  /**
   * Sets the listener
   */
  public void setClickListener(OnItemClickListener itemClickListener) {
    this.clickListener = itemClickListener;
  }

  /**
   * gets a count of how may items exist
   * @return
   */
  @Override
  public int getItemCount() {
    if(mMeds != null)
      return mMeds.size();
    else return 0;
  }

  /**
   * Class to hold the view
   */
  class MedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView medItemView;

    private MedViewHolder(View itemView) {
      super(itemView);
      medItemView = itemView.findViewById(R.id.textView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
    }
  }

}