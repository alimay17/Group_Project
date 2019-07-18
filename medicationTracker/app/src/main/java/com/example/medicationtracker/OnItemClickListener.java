package com.example.medicationtracker;

import android.view.View;

/********************************************************************
 * interface to define a click listener used in MedListAdapter
 *******************************************************************/
public interface OnItemClickListener {
  public void onClick(View view, int position);
}
