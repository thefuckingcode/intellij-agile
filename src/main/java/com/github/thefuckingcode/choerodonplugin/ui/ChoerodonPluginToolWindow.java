package com.github.thefuckingcode.choerodonplugin.ui;

import com.github.thefuckingcode.choerodonplugin.config.ChoerodonPluginOauthConfigState;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.feign.IamClientApi;
import com.github.thefuckingcode.choerodonplugin.service.IamService;
import com.github.thefuckingcode.choerodonplugin.service.LoginService;
import com.github.thefuckingcode.choerodonplugin.vo.IssueVO;
import com.github.thefuckingcode.choerodonplugin.vo.OrganizationVO;
import com.github.thefuckingcode.choerodonplugin.vo.ProjectVO;
import com.intellij.icons.AllIcons;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.MessageType;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ChoerodonPluginToolWindow {
    private Project project;
    private ChoerodonPluginOauthConfigState choerodonPluginOauthConfigState;

    private Banner banner;
    private JPanel toolWindowContent;
    private JTable issueTable;
    private JPanel tablePanel;
    private JScrollPane tableScrollPanel;

    private JComboBox<OrganizationVO> orgComboBox;
    private JComboBox<ProjectVO> projectComboBox;

    private JPanel actionPanel;

    public ChoerodonPluginToolWindow(Project project) {
        this.project = project;
        choerodonPluginOauthConfigState = ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class);
        tryLogin(project);


        IssueTableModel issueTableModel = new IssueTableModel();

        issueTable = new JBTable(issueTableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                TableCellRenderer cellRenderer = super.getCellRenderer(row, column);
//                if (column == 0) {
//                    return getProjectCellRenderer();
//                }
//                if (column == 1) {
//                    return getBranchCellRenderer();
//                }
//                if (column == 2) {
//                    return getStatusCellRenderer();
//                }
//                if (column == 3) {
//                    return getDateCellRenderer();
//                }
//                if (column == 4 || column == 5) {
//                    return getLinkCellRenderer();
//                }
                return cellRenderer;
            }

            @NotNull
            @Override
            public Component prepareRenderer(@NotNull TableCellRenderer renderer, int rowIndex, int columnIndex) {
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn column = getColumnModel().getColumn(columnIndex);
                column.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, column.getPreferredWidth()));
                return component;
            }
        };

        createTablePanel();
    }


    public JPanel getToolWindowContent() {
        return toolWindowContent;
    }

    private void createTablePanel() {
        AnActionButton refreshActionButton = new AnActionButton("Refresh", AllIcons.Actions.Refresh) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                NotificationGroup notificationGroup = new NotificationGroup("testid", NotificationDisplayType.BALLOON, false);
                /**
                 * content :  通知内容
                 * type  ：通知的类型，warning,info,error
                 */
                Notification notification = notificationGroup.createNotification("测试通知", MessageType.INFO);
                Notifications.Bus.notify(notification);
            }

            @Override
            public JComponent getContextComponent() {
                return issueTable;
            }
        };

        DefaultActionGroup actionGroup = new DefaultActionGroup(refreshActionButton);

        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLBAR, actionGroup, true);
        actionToolbar.setTargetComponent(this.getToolWindowContent());

        orgComboBox = new ComboBox<>();
        orgComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                System.out.println(itemEvent.getItem());
            }
        });
        orgComboBox.setSize(200, -1);
        setOrg(orgComboBox);
        projectComboBox = new ComboBox<>();
        projectComboBox.setSize(200, -1);
        projectComboBox.addItem(new ProjectVO("proj1"));
        projectComboBox.addItem(new ProjectVO("proj2"));

        SearchTextField searchTextField = new SearchTextField(false, null);
        searchTextField.getTextEditor().setToolTipText("Filter by substrings of branch names");

        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println(e);
                super.focusLost(e);
            }

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });

        actionPanel = new JPanel(new MigLayout("ins 0", "1[left]10[left]10[left]10[right]", "center"));
        actionPanel.add(orgComboBox);
        actionPanel.add(projectComboBox);
        actionPanel.add(searchTextField);
        actionPanel.add(actionToolbar.getComponent());

        tablePanel.add(actionPanel, BorderLayout.NORTH, 0);
        tablePanel.add(new JBScrollPane(issueTable), BorderLayout.CENTER, 1);
    }

    private void setOrg(JComboBox orgComboBox) {
        IamClientApi iamClientApi = ClientBuilder.buildClient(ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class).getChoerodonHost(), IamClientApi.class);
        List<OrganizationVO> organizationVOS = project.getService(IamService.class).listOrganizations();
        organizationVOS.forEach(organizationVO -> {
            orgComboBox.addItem(organizationVO);
        });
    }

    private static class IssueTableModel extends AbstractTableModel {
        public java.util.List<IssueVO> rows = new ArrayList<>();
        public List<TableRowDefinition> definitions = Arrays.asList(
                new TableRowDefinition("工作项编号", IssueVO::getIssueNum),
                new TableRowDefinition("概要", IssueVO::getSummary),
                new TableRowDefinition("状态", IssueVO::getStatus),
                new TableRowDefinition("已登记时间", IssueVO::getRegisteredHours),
                new TableRowDefinition("剩余预估时间", IssueVO::getRemainHours));

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return definitions.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            IssueVO row = rows.get(rowIndex);
            return definitions.get(columnIndex).tableModelRowFunction.apply(row);
        }

        @Override
        public String getColumnName(int columnIndex) {
            return definitions.get(columnIndex).title;
        }

    }

    private void tryLogin(Project project) {
        LoginService loginService = project.getService(LoginService.class);
        try {
            loginService.Login();
        } catch (Exception e) {
            // todo 通知用户oauth认证信息失效，打开设置重新配置
            e.printStackTrace();
        }
    }


    private static class TableRowDefinition {
        public String title;
        public Function<IssueVO, Object> tableModelRowFunction;

        public TableRowDefinition(String title, Function<IssueVO, Object> tableModelRowFunction) {
            this.title = title;
            this.tableModelRowFunction = tableModelRowFunction;
        }
    }

    private static class SelectedCell {
        private final int rowIndex;
        private final int columnIndex;
        private final Object cellContent;

        public SelectedCell(int rowIndex, int columnIndex, Object cellContent) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
            this.cellContent = cellContent;
        }
    }
}
