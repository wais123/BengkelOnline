package com.idetree.bemonline.booking_fragment;

import com.idetree.bemonline.response.GeneralResponse;

public interface BookingView {
    void showProgress();
    void hideProgress();
    void postBookingSuccess(GeneralResponse response);
    void postBookingError(String error);
}
