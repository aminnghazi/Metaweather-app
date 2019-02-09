package ir.jdeiut.jdeiut.model.caller;

import ir.jdeiut.jdeiut.model.entities.SearchEntity;

public interface OnSearchResultReceived {
    void onSearchResultReceived(SearchEntity[] entities);
}
