package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.models.AppartDetails;

public class UpdateAppartDetailedBus {

   private AppartDetails appartDetails;

    public UpdateAppartDetailedBus(AppartDetails appartDetails) {
        this.appartDetails = appartDetails;
    }

    public AppartDetails getAppartDetails() {
        return appartDetails;
    }

    public void setAppartDetails(AppartDetails appartDetails) {
        this.appartDetails = appartDetails;
    }
}
