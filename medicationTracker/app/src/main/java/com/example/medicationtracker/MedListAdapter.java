package com.example.medicationtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedListAdapter extends RecyclerView.Adapter<MedListAdapter.MedViewHolder> {

  class MedViewHolder extends RecyclerView.ViewHolder {
    private final TextView medItemView;

    private MedViewHolder(View itemView) {
      super(itemView);
      medItemView = itemView.findViewById(R.id.textView);
    }
  }

  private final LayoutInflater mInflater;
  private List<Medication> mMeds;

  MedListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

  @Override
  public MedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new MedViewHolder(itemView);
  }

  @Override public void onBindViewHolder(MedViewHolder holder, int position) {
    if(mMeds != null) {
      Medication current = mMeds.get(position);
      holder.medItemView.setText(current.getName());
    } else {
      holder.medItemView.setText("No History Available");
    }
  }

  void setMeds(List<Medication> meds) {
    mMeds = meds;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if(mMeds != null)
      return mMeds.size();
    else return 0;
  }

}