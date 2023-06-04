package app.revanced.integrations.patches.components;

import app.revanced.integrations.settings.SettingsEnum;
import app.revanced.integrations.utils.LogHelper;

final class ButtonsPatch extends Filter {
    private final BlockRule actionButtonsRule;
    private final BlockRule likeDislikeRule;
    private final BlockRule actionBarRule;

    private final BlockRule[] rules;

    public ButtonsPatch() {
        likeDislikeRule = new BlockRule(
                SettingsEnum.HIDE_LIKE_DISLIKE_BUTTON,
                "|like_button",
                "dislike_button"
        );

        BlockRule download = new BlockRule(
                SettingsEnum.HIDE_DOWNLOAD_BUTTON,
                "download_button"
        );

        actionButtonsRule = new BlockRule(
                SettingsEnum.HIDE_ACTION_BUTTONS,
                "ContainerType|video_action_button",
                "|CellType|CollectionType|CellType|ContainerType|button.eml|"
        );

        BlockRule playlist = new BlockRule(
                SettingsEnum.HIDE_PLAYLIST_BUTTON,
                "save_to_playlist_button"
        );

        rules = new BlockRule[]{likeDislikeRule, download, actionButtonsRule, playlist};

        actionBarRule = new BlockRule(null, "video_action_bar");

        this.pathRegister.registerAll(
                likeDislikeRule,
                download,
                playlist
        );
    }

    private boolean hideActionBar() {
        for (BlockRule rule : rules) if (!rule.isEnabled()) return false;
        return true;
    }

    @Override
    public boolean filter(final String path, final String identifier) {
        if (hideActionBar() && actionBarRule.check(identifier).isBlocked()) return true;

        var currentIsActionButton = actionButtonsRule.check(path).isBlocked();

        if (likeDislikeRule.check(path).isBlocked()) ActionButton.doNotBlockCounter = 4;

        if ((currentIsActionButton && ActionButton.doNotBlockCounter <= 0 && actionButtonsRule.isEnabled()) || pathRegister.contains(path)) {
            LogHelper.printDebug(() -> "Blocked: " + path);
            return true;
        } else return false;
    }

    static class ActionButton {
        public static int doNotBlockCounter = 4;
    }
}
