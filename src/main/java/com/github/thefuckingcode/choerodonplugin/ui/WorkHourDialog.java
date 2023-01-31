package com.github.thefuckingcode.choerodonplugin.ui;

import com.intellij.openapi.Disposable;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.github.thefuckingcode.choerodonplugin.util.DatePickerUtil.getDatePicker;

public class WorkHourDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel info;
    private final Disposable disposable;

    public WorkHourDialog(Disposable disposable, Long projectId, String issueId) {
        this.disposable = disposable;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        final JXDatePicker datepick;
        datepick = getDatePicker();

        info.add(datepick);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(datepick.getDate(), projectId, issueId);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(Date date, Long projectId, String issueId) {
        // add your code here
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.setTime(date);

        Calendar nowCalendar = Calendar.getInstance();
        selectedCalendar.setTime(new Date());

        selectedCalendar.set(Calendar.HOUR_OF_DAY, nowCalendar.get(Calendar.HOUR_OF_DAY));
        selectedCalendar.set(Calendar.MINUTE, nowCalendar.get(Calendar.MINUTE));
        selectedCalendar.set(Calendar.SECOND, nowCalendar.get(Calendar.SECOND));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        System.out.println(sdf.format(selectedCalendar.getTime()));

        System.out.println(date);
        System.out.println(issueId);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void showDialog() {
        this.setLocationRelativeTo(null);
        pack();
        setTitle("登记工时");
        setVisible(true);
        setModalityType(ModalityType.DOCUMENT_MODAL);
    }
}
