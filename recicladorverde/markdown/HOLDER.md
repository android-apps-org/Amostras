# [ViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)

## Responsibilities

- Cache view objects to populate with data or images
- Contains reference to root view object for item
    - use it to cache view objects represented in layout
    - makes it less costly to update views with new data
    - findViewById gets called only for each data view when new item is created
    - NOT each time you want to populate views in item with data

## Example to Consider
```
    Each item in RecyclerView list contains 4 individual data views
    Without caching views in a ViewHolder
    8 items fit on screen and 2 extra items are needed for smooth scrolling
    how many findViewById calls are made when scrolling through 30 items?
    
    10 items are needed with 8 that fit on screen and 2 for smooth scrolling
    That would be 40 calls to findViewById (10 * 4 individual data views per item)
    
    30 items scrolled would be 120 calls to findViewById (30 * 4)
    
    Difference would 120 to 40 on using ViewHolder or not
    
    Android phones today are so fast the optimization would not be noticeable
    But it will give you slightly better battery usage
    Which would be noticeable when scrolling very large lists
```

