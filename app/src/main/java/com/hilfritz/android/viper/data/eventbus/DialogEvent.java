package com.hilfritz.android.viper.data.eventbus;

import com.hilfritz.android.viper.data.sephoraApi.pojo.category.Category;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hilfritz on 2/12/17.
 */

public class DialogEvent {
    int eventType = -100;
    public static final int CLOSE = 1;
    public static final int CATEGORY_CLICK = 2;
    Category category;



    public DialogEvent(int eventType) {
        this.eventType = eventType;
    }
    public DialogEvent(int eventType, Category category) {
        this.eventType = eventType;
        this.category = category;
    }


    public int getEventType() {
        return eventType;
    }

    public static void fireEvent(int clickEventType){
        EventBus.getDefault().post(
                new DialogEvent(
                        clickEventType)
        );
    }

    public static void fireEvent(int clickEventType, Category category){
        EventBus.getDefault().post(
                new DialogEvent(
                        clickEventType,
                        category
                        )
        );
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
