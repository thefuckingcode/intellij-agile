// (C) 2021 PPI AG
package com.github.thefuckingcode.choerodonplugin.notify;

import com.github.thefuckingcode.choerodonplugin.constant.PluginConstant;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author PPI AG
 */
public class OauthConfigNotification extends Notification {
    public OauthConfigNotification(Project project) {
        super(getNotificationGroupId(), "未配置猪齿鱼oauth信息", NotificationType.INFORMATION);

        final AnAction openDialog = new AnAction("前往设置") {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                openDialogForUnmappedRemote(project);
                expire();
            }
        };
        addAction(openDialog);

//        whenExpired(() -> {
//            final boolean isOpen = !getAlreadyOpenNotifications(project).isEmpty();
//            logger.debug("Notifying project ", project, " if notifications are open with value ", isOpen);
//            project.getMessageBus().syncPublisher(UntrackedRemoteNotificationState.UNTRACKED_REMOTE_FOUND).handle(isOpen);
//        });
    }


    @NotNull
    private static String getNotificationGroupId() {
        return NotificationGroupManager.getInstance().getNotificationGroup("com.github.thefuckingcode.intellijagile.notify.OauthConfigNotification").getDisplayId();
    }


    public void openDialogForUnmappedRemote(Project project) {
        ShowSettingsUtil.getInstance().showSettingsDialog(project, PluginConstant.APPLICATION_CONFIGURABLE_DISPLAY_NAME);
    }
}
