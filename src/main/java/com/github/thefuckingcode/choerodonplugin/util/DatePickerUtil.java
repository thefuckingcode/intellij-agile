package com.github.thefuckingcode.choerodonplugin.util;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.*;
import java.util.Date;

public class DatePickerUtil {

    public static JXDatePicker getDatePicker() {
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setFormats("yyyy-MM-dd HH:mm:ss");
        datePicker.setDate(new Date(System.currentTimeMillis()));
        datePicker.getEditor().setEditable(false);
        datePicker.setPreferredSize(new Dimension(300, datePicker.getPreferredSize().height));
        datePicker.setMinimumSize(new Dimension(300, datePicker.getPreferredSize().height));
        return datePicker;
    }
}
