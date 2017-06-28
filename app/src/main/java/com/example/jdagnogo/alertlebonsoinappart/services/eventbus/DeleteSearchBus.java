package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.models.Search;

public class DeleteSearchBus {
    private Search search;

    public DeleteSearchBus(Search search){
        this.search = search;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}
