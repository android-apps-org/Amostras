# [Adapter](https://developer.android.com/reference/android/widget/Adapter)

## Responsibilities

- Acts as a bridge between an AdapterView and the underlying data for that view
- Provides access to the data items
- Responsible for making a View for each item in the data set
- Provide new view items when needed
- Binds data from data source to view
- Sends views in ViewHolder object and information about data
- onCreateViewHolder: called when RecyclerView instantiates new ViewHolder instance
- onBindViewHolder: called when RecyclerView wants to populate view with data
    - so user can see it, effectively binding it to data source
- getItemCount: returns count of items in data source

