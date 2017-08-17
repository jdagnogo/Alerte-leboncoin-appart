package com.example.jdagnogo.alertlebonsoinappart.models;

import java.util.ArrayList;
import java.util.List;

public class AppartDetails extends Appart {
    private List<String> imgsUrl;
    private String phone;

    public List<String> getImgsUrl() {
        return imgsUrl;
    }

    public AppartDetails(Appart appart) {
        super(appart);
        imgsUrl = new ArrayList<>();
    }

    public void setImgsUrl(List<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

