package com.github.thefuckingcode.choerodonplugin.ui;

import com.github.thefuckingcode.choerodonplugin.config.ChoerodonPluginOauthConfigState;
import com.github.thefuckingcode.choerodonplugin.service.AgileService;
import com.github.thefuckingcode.choerodonplugin.service.ChoerodonBaseService;
import com.github.thefuckingcode.choerodonplugin.service.IamService;
import com.github.thefuckingcode.choerodonplugin.service.OauthService;
import com.github.thefuckingcode.choerodonplugin.vo.*;
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
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.*;
import java.util.function.Function;

public class ChoerodonPluginToolWindow {

    private static final String PAGE_NUM_TEMPLATE = "%d/%d";
    private static final Map<String, DefaultComboBoxModel<ProjectVO>> COMBO_BOX_MODEL_MAP = new HashMap<>();
    private static final List<String> INITIALIZED_ORG_IDS = new ArrayList<>();

    private Project project;
    private ChoerodonPluginOauthConfigState choerodonPluginOauthConfigState;

    private Banner banner;
    private JPanel toolWindowContent;
    private JTable issueTable;
    private JPanel tablePanel;

    private JComboBox<OrganizationVO> orgComboBox;
    private JComboBox<ProjectVO> projectComboBox;

    private JPanel actionPanel;
    private ChoerodonBaseService choerodonBaseService;
    private AgileService agileService;
    private IamService iamService;
    private OauthService oauthService;
    private IssueTableModel issueTableModel;
    private AnActionButton lastPage;
    private AnActionButton nextPage;
    private JLabel issuePageLabel;
    private JPanel pagePanel;
    private ActionToolbar turnPageActionToolbar;
    private JLabel projectInfoLabel;
    private JBScrollPane jbScrollPane;

    public ChoerodonPluginToolWindow(Project project) {
        this.project = project;
        this.agileService = project.getService(AgileService.class);
        this.iamService = project.getService(IamService.class);
        this.oauthService = project.getService(OauthService.class);
        this.choerodonBaseService = project.getService(ChoerodonBaseService.class);
        choerodonPluginOauthConfigState = ApplicationManager.getApplication().getService(ChoerodonPluginOauthConfigState.class);
        tryLogin(project);

        issueTableModel = new IssueTableModel();

        issueTable = new JBTable(issueTableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                TableCellRenderer cellRenderer = super.getCellRenderer(row, column);
                if (column == 5) {
                    return getRegisterCellRenderer();
                }
                return cellRenderer;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                }
                return false;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                TableCellEditor tableCellEditor = super.getCellEditor(row, column);
                if (column == 5) {
                    return getTableCellEditor();
                }
                return tableCellEditor;
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

        tablePanel = new JPanel(new BorderLayout());
        jbScrollPane = new JBScrollPane(issueTable);
        jbScrollPane.setSize(new Dimension(300, 300));

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
        orgComboBox.setSize(200, -1);

        projectComboBox = new ComboBox<>();
        projectComboBox.setSize(200, -1);

        setOrg(orgComboBox);
        orgComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED && !Objects.equals(itemEvent.getItem().toString(), "---请选择组织---")) {
                OrganizationVO organizationVO = (OrganizationVO) itemEvent.getItem();
                if (INITIALIZED_ORG_IDS.contains(organizationVO.getTenantId())) {
                    DefaultComboBoxModel<ProjectVO> defaultComboBoxModel = COMBO_BOX_MODEL_MAP.get(organizationVO.getTenantId());
                    projectComboBox.setModel(defaultComboBoxModel);
                } else {
                    INITIALIZED_ORG_IDS.add(organizationVO.getTenantId());
                    // 查询选中的组织下的项目信息
                    DefaultComboBoxModel<ProjectVO> defaultComboBoxModel = COMBO_BOX_MODEL_MAP.get(organizationVO.getTenantId());
                    PageVO<ProjectVO> pageProjects = choerodonBaseService.pageProjects(((OrganizationVO) itemEvent.getItem()).getTenantId(), 0);
                    defaultComboBoxModel.addAll(pageProjects.getContent());
                    if (pageProjects.getTotalPages() > 1) {
                        ProjectVO clickForMoreProject = new ProjectVO("加载更多", organizationVO.getTenantId());
                        Map<String, Object> additionalInfo = new HashMap<>();
                        additionalInfo.put("currentPage", 0);
                        clickForMoreProject.setAdditionalInfo(additionalInfo);
                        defaultComboBoxModel.addElement(clickForMoreProject);
                    }
                    COMBO_BOX_MODEL_MAP.put(organizationVO.getTenantId(), defaultComboBoxModel);
                    projectComboBox.setModel(defaultComboBoxModel);
                }
            }
        });

        projectComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                if (Objects.equals(itemEvent.getItem().toString(), "加载更多")) {
                    ProjectVO clickForMoreProject = projectComboBox.getModel().getElementAt(projectComboBox.getModel().getSize() - 1);

                    Map<String, Object> additionalInfo = clickForMoreProject.getAdditionalInfo();
                    Integer currentPage = (Integer) additionalInfo.get("currentPage");
                    PageVO<ProjectVO> pageProjects = choerodonBaseService.pageProjects(clickForMoreProject.getTenantId(), currentPage + 1);
                    DefaultComboBoxModel<ProjectVO> defaultComboBoxModel = (DefaultComboBoxModel) projectComboBox.getModel();
                    defaultComboBoxModel.removeElement(clickForMoreProject);
                    defaultComboBoxModel.addAll(pageProjects.getContent());
                    if (currentPage + 2 < pageProjects.getTotalPages()) {
                        defaultComboBoxModel.addElement(clickForMoreProject);
                        additionalInfo.put("currentPage", currentPage + 1);
                    }
                    COMBO_BOX_MODEL_MAP.put(clickForMoreProject.getTenantId(), defaultComboBoxModel);
                } else {
                    if (turnPageActionToolbar != null) {
                        actionPanel.remove(turnPageActionToolbar.getComponent());
                        turnPageActionToolbar = null;
                    }

                    if (pagePanel != null) {
                        actionPanel.remove(pagePanel);
                        pagePanel = null;
                        issuePageLabel = null;
                    }
                    ProjectVO projectVO = (ProjectVO) itemEvent.getItem();
                    PageVO<IssueListFieldKVVO> issueListFieldKVVOPageVO = agileService.pageIssues(projectVO.getId(), 0);
                    List<IssueVO> issueVOS = IssueListFieldKVVO.toIssueVO(issueListFieldKVVOPageVO.getContent());
                    issueTableModel.rows.clear();
                    issueTableModel.rows.addAll(issueVOS);
                    issueTableModel.fireTableDataChanged();
                    if (issueListFieldKVVOPageVO.getTotalPages() > 1) {
                        createPagePanel(issueListFieldKVVOPageVO.getTotalPages());
                    }
                }
            }
        });

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

        actionPanel = new JPanel();
        actionPanel.add(orgComboBox);
        actionPanel.add(projectComboBox);
        actionPanel.add(searchTextField);
        actionPanel.add(actionToolbar.getComponent());

        tablePanel.add(actionPanel, BorderLayout.NORTH, 0);
        tablePanel.add(jbScrollPane, BorderLayout.CENTER, 1);

        toolWindowContent.add(tablePanel);
    }

    private void setOrg(JComboBox orgComboBox) {
        orgComboBox.addItem(new OrganizationVO("---请选择组织---"));
        List<OrganizationVO> organizationVOS = iamService.listOrganizations();
        organizationVOS.forEach(organizationVO -> {
            DefaultComboBoxModel<ProjectVO> defaultComboBoxModel = new DefaultComboBoxModel<>();
            defaultComboBoxModel.addElement(new ProjectVO("---请选择项目---"));
            COMBO_BOX_MODEL_MAP.put(organizationVO.getTenantId(), defaultComboBoxModel);
            orgComboBox.addItem(organizationVO);
        });
    }

    private static class IssueTableModel extends AbstractTableModel {
        public java.util.List<IssueVO> rows = new ArrayList<>();
        public List<TableRowDefinition> definitions = Arrays.asList(
                new TableRowDefinition("工作项编号", IssueVO::getIssueNum),
                new TableRowDefinition("概要", IssueVO::getSummary),
                new TableRowDefinition("状态", IssueVO::getStatus),
                new TableRowDefinition("已登记时间", IssueVO::getSpentWorkTime),
                new TableRowDefinition("剩余预估时间", IssueVO::getRemainingTime),
                new TableRowDefinition("登记工时", IssueVO::getSubmitWorkTimeButton));

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
        try {
            // 登录
            oauthService.Login();
            // 设置用户信息
            iamService.currentUser();
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

    private TableCellRenderer getRegisterCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return (JButton) value;
            }
        };
    }

    private TableCellEditor getTableCellEditor() {
        return new IssueVO.SubmitWorkHourEditor(new JTextField());
    }

    private void createPagePanel(int totalPage) {
        issuePageLabel = new JLabel();
        issuePageLabel.setText(String.format(PAGE_NUM_TEMPLATE, 1, totalPage));

        lastPage = new AnActionButton("上一页", AllIcons.General.ArrowLeft) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                updateTablePanelAndPagePanel(false);
            }

            @Override
            public JComponent getContextComponent() {
                return issueTable;
            }
        };

        lastPage.setEnabled(false);

        nextPage = new AnActionButton("下一页", AllIcons.General.ArrowRight) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                updateTablePanelAndPagePanel(true);
            }

            @Override
            public JComponent getContextComponent() {
                return issueTable;
            }
        };

        DefaultActionGroup turnPageActionGroup = new DefaultActionGroup(lastPage, nextPage);

        turnPageActionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLBAR, turnPageActionGroup, true);
        turnPageActionToolbar.setTargetComponent(this.getToolWindowContent());

        pagePanel = new JPanel();
        pagePanel.add(issuePageLabel, BorderLayout.CENTER);

        actionPanel.add(turnPageActionToolbar.getComponent());
        actionPanel.add(pagePanel);
        actionPanel.revalidate();
    }

    private void updateTablePanelAndPagePanel(Boolean add) {
        String[] page = issuePageLabel.getText().split("/");
        Integer oldCurrentPage = Integer.parseInt(page[0]);
        Integer totalPage = Integer.parseInt(page[1]);

        int newCurrentPage;
        if (add) {
            newCurrentPage = oldCurrentPage + 1;
            if (newCurrentPage == totalPage) {
                nextPage.setEnabled(false);
            }
            // 比如这是第一页的情况，上一页被禁用了，现在需要启用
            lastPage.setEnabled(true);
        } else {
            newCurrentPage = oldCurrentPage - 1;
            if (newCurrentPage == 1) {
                lastPage.setEnabled(false);
            }
            nextPage.setEnabled(true);
        }
        issuePageLabel.setText(String.format(PAGE_NUM_TEMPLATE, newCurrentPage, totalPage));

        ProjectVO projectVO = (ProjectVO) projectComboBox.getSelectedItem();
        if (projectVO != null) {
            PageVO<IssueListFieldKVVO> issueListFieldKVVOPageVO = agileService.pageIssues(projectVO.getId(), newCurrentPage - 1);
            List<IssueVO> issueVOS = IssueListFieldKVVO.toIssueVO(issueListFieldKVVOPageVO.getContent());
            issueTableModel.rows.clear();
            issueTableModel.rows.addAll(issueVOS);
            issueTableModel.fireTableDataChanged();
        }
        pagePanel.revalidate();
    }
}
