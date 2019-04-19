package com.practice.school.views.elements;

import com.practice.school.MainUI;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LocaleElement {

    public static HorizontalLayout getButtonsGroup() {

        HorizontalLayout buttonsGroup = new HorizontalLayout();

        Button ru = new Button("рус");
        Button en = new Button("eng");

        en.addClickListener((Button.ClickListener) click -> {
            MainUI.setResourceBundleEN();
            Page.getCurrent().reload();
        });
        ru.addClickListener((Button.ClickListener) click -> {
            MainUI.setResourceBundleRU();
            Page.getCurrent().reload();
        });

        en.setStyleName(ValoTheme.BUTTON_TINY);
        ru.setStyleName(ValoTheme.BUTTON_TINY);

        buttonsGroup.addComponents(ru, en);
        buttonsGroup.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        buttonsGroup.setWidthUndefined();
        buttonsGroup.setHeight("100%");

        return buttonsGroup;
    }
}
