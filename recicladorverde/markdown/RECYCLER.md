# [RecyclerView](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView)

## Problem

- For large lists each item (view) has to handle:
  - interactions (i.e. assigning onclick handlers)
  - all this setup turns into delays
  - which user experiences before seeing anything

## Responsibilities

- Recycled list of Views (simplistic overview)
- Keep a set of list items in queue (recycling bin)
- List items are re-used for memory conservation and performance
- When scrolling, returns previously created list item(s)
- Code binds list item with new content and this can be scrolled in
- Views that are scrolled out are placed back into queue for re-use
- Have to deal with fast flings, animations, and lots of other conditions

## [Layout Manager](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.LayoutManager)

- Determines how collection of items is displayed
- Determines when to recycle item views that are no longer visible
- LinearLayoutManager, GridLayoutManager, StaggeredGridLayoutManager

## Flow (Relationship)

- When recycler view is first being layed out and drawn
    - asks adapter for number of items it will be displaying
- RecyclerView asks Adapter to create ViewHolder objects
    - inflate view item(s) from XML by calling onCreateViewHolder
    - creates as many ViewHolder(s) needed to display all item(s)
        - or fill and scroll screen
- RecyclerView calls onBindViewHolder to populate each item with data
- When scrolling, RecyclerView re-uses ViewHolder(s) 
    - asking Adapter to bind new data to them

