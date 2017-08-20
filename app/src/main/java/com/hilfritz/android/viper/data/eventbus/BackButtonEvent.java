package com.hilfritz.android.viper.data.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Hilfritz Camallere on 20/8/17.
 */

public class BackButtonEvent {
    int eventType = -100;
    public static final int BACK_PRESSED = 1;

    public BackButtonEvent(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public static void fireBackButtonPressedEvent(){
        EventBus.getDefault().post(
                new BackButtonEvent(
                        BACK_PRESSED)
        );
    }
}
