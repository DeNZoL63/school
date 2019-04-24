package com.practice.school.views;

import com.practice.school.MainUI;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "")
public class MainWindowView extends Composite implements View {

    public MainWindowView() {
        VerticalLayout form = new VerticalLayout();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("MainHeaderText");

        HorizontalLayout buttonsLocale = LocaleElement.getButtonsGroup();
        Label header = new Label(headerText);
        header.setStyleName(ValoTheme.LABEL_H1);

        form.addComponents(buttonsLocale, header);
        form.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }
}
