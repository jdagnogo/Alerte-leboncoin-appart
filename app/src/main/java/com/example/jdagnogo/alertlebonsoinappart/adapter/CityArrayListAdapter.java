package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CityArrayListAdapter extends ArrayAdapter<String> {
    int resource;
    List<String> items, tempItems, suggestions;

    public CityArrayListAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return resultValue.toString();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();

                boolean foundSome = Boolean.FALSE;

                for (String item : tempItems) {
                    if (item.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(item);
                        foundSome = Boolean.TRUE;
                    } else if (foundSome) { //List sorted alphabetically, so when no more id found means that there is no more.
                        break;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<String> filterList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0) {
                clear();
                addAll(filterList);
                notifyDataSetChanged();
            }
        }
    };
}
