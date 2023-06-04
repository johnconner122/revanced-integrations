package app.revanced.integrations.patches.components;

import android.view.View;

import app.revanced.integrations.adremover.AdRemoverAPI;
import app.revanced.integrations.settings.SettingsEnum;
import app.revanced.integrations.shared.PlayerType;
import app.revanced.integrations.utils.LogHelper;
import app.revanced.integrations.utils.ReVancedUtils;

public final class GeneralAdsPatch extends Filter {
    private final String[] EXCEPTIONS = {
            "home_video_with_context",
            "related_video_with_context",
            "comment_thread", // skip blocking anything in the comments
            "download_",
            "library_recent_shelf",
            "menu",
            "root",
            "-count",
            "-space",
            "-button",
            "playlist_add_to_option_wrapper" // do not block on "add to playlist" flyout menu
    };

    private final BlockRule custom = new CustomBlockRule(
            SettingsEnum.CUSTOM_FILTER,
            SettingsEnum.CUSTOM_FILTER_STRINGS
    );

    public GeneralAdsPatch() {
        final var communityPosts = new BlockRule(
                SettingsEnum.HIDE_COMMUNITY_POSTS,
                "post_base_wrapper"
        );

        final var communityGuidelines = new BlockRule(
                SettingsEnum.HIDE_COMMUNITY_GUIDELINES,
                "community_guidelines"
        );

        final var subscribersCommunityGuidelines = new BlockRule(
                SettingsEnum.HIDE_SUBSCRIBERS_COMMUNITY_GUIDELINES,
                "sponsorships_comments_upsell"
        );


        final var channelMemberShelf = new BlockRule(
                SettingsEnum.HIDE_CHANNEL_MEMBER_SHELF,
                "member_recognition_shelf"
        );

        final var compactBanner = new BlockRule(
                SettingsEnum.HIDE_COMPACT_BANNER,
                "compact_banner"
        );

        final var inFeedSurvey = new BlockRule(
                SettingsEnum.HIDE_FEED_SURVEY,
                "in_feed_survey",
                "slimline_survey"
        );

        final var medicalPanel = new BlockRule(
                SettingsEnum.HIDE_MEDICAL_PANELS,
                "medical_panel"
        );

        final var paidContent = new BlockRule(
                SettingsEnum.HIDE_PAID_CONTENT,
                "paid_content_overlay"
        );

        final var merchandise = new BlockRule(
                SettingsEnum.HIDE_MERCHANDISE_BANNERS,
                "product_carousel"
        );

        final var infoPanel = new BlockRule(
                SettingsEnum.HIDE_INFO_PANELS,
                "publisher_transparency_panel",
                "single_item_information_panel"
        );

        final var latestPosts = new BlockRule(
                SettingsEnum.HIDE_HIDE_LATEST_POSTS,
                "post_shelf"
        );

        final var channelGuidelines = new BlockRule(
                SettingsEnum.HIDE_HIDE_CHANNEL_GUIDELINES,
                "channel_guidelines_entry_banner"
        );

        final var audioTrackButton = new BlockRule(
                SettingsEnum.HIDE_AUDIO_TRACK_BUTTON,
                "multi_feed_icon_button"
        );

        final var artistCard = new BlockRule(
                SettingsEnum.HIDE_ARTIST_CARDS,
                "official_card"
        );

        final var selfSponsor = new BlockRule(
                SettingsEnum.HIDE_SELF_SPONSOR,
                "cta_shelf_card"
        );

        final var chapterTeaser = new BlockRule(
                SettingsEnum.HIDE_CHAPTER_TEASER,
                "expandable_metadata",
                "macro_markers_carousel"
        );

        final var viewProducts = new BlockRule(
                SettingsEnum.HIDE_PRODUCTS_BANNER,
                "product_item",
                "products_in_video"
        );

        final var webLinkPanel = new BlockRule(
                SettingsEnum.HIDE_WEB_SEARCH_RESULTS,
                "web_link_panel"
        );

        final var channelBar = new BlockRule(
                SettingsEnum.HIDE_CHANNEL_BAR,
                "channel_bar"
        );

        final var relatedVideos = new BlockRule(
                SettingsEnum.HIDE_RELATED_VIDEOS,
                "fullscreen_related_videos"
        );

        final var quickActions = new BlockRule(
                SettingsEnum.HIDE_QUICK_ACTIONS,
                "quick_actions"
        );

        final var imageShelf = new BlockRule(
                SettingsEnum.HIDE_IMAGE_SHELF,
                "image_shelf"
        );

        final var graySeparator = new BlockRule(
                SettingsEnum.HIDE_GRAY_SEPARATOR,
                "cell_divider" // layout residue (gray line above the buttoned ad),
        );

        var buttonedAd = new BlockRule(SettingsEnum.HIDE_BUTTONED_ADS,
                "_buttoned_layout",
                "full_width_square_image_layout",
                "_ad_with",
                "landscape_image_wide_button_layout"
        );

        var generalAds = new BlockRule(
                SettingsEnum.HIDE_GENERAL_ADS,
                "ads_video_with_context",
                "banner_text_icon",
                "square_image_layout",
                "watch_metadata_app_promo",
                "video_display_full_layout",
                "hero_promo_image",
                "statement_banner",
                "carousel_footered_layout",
                "text_image_button_layout",
                "primetime_promo",
                "product_details",
                "carousel_headered_layout",
                "full_width_portrait_image_layout",
                "brand_video_shelf"
        );
        var movieAds = new BlockRule(
                SettingsEnum.HIDE_MOVIES_SECTION,
                "browsy_bar",
                "compact_movie",
                "horizontal_movie_shelf",
                "movie_and_show_upsell_card"
        );

        this.pathRegister.registerAll(
                generalAds,
                buttonedAd,
                channelBar,
                communityPosts,
                paidContent,
                latestPosts,
                movieAds,
                chapterTeaser,
                communityGuidelines,
                quickActions,
                relatedVideos,
                compactBanner,
                inFeedSurvey,
                viewProducts,
                medicalPanel,
                merchandise,
                infoPanel,
                channelGuidelines,
                audioTrackButton,
                artistCard,
                selfSponsor,
                webLinkPanel,
                imageShelf,
                subscribersCommunityGuidelines,
                channelMemberShelf
        );

        var carouselAd = new BlockRule(SettingsEnum.HIDE_GENERAL_ADS,
                "carousel_ad"
        );

        var shorts = new BlockRule(SettingsEnum.HIDE_SHORTS,
                "reels_player_overlay",
                "shorts_shelf",
                "inline_shorts",
                "shorts_grid",
                "shorts_video"
        );

        final var shortsInfoPanel = new BlockRule(
                SettingsEnum.HIDE_SHORTS_INFO_PANEL,
                "shorts_info_panel_overview"
        );

        final var thanksButton = new BlockRule(
                SettingsEnum.HIDE_SHORTS_THANKS_BUTTON,
                "suggested_action"
        );

//        final var subscribeButton = new BlockRule(
//                SettingsEnum.HIDE_SHORTS_SUBSCRIBE_BUTTON,
//                "subscribe_button"
//        );

        final var joinButton = new BlockRule(
                SettingsEnum.HIDE_SHORTS_JOIN_BUTTON,
                "sponsor_button"
        );

        final var soundButton = new BlockRule(
                SettingsEnum.HIDE_SHORTS_SOUND_BUTTON,
                "reel_pivot_button"
        );

        final var shortsChannelBar = new BlockRule(
                SettingsEnum.HIDE_SHORTS_CHANNEL_BAR,
                "reel_channel_bar"
        );

        this.identifierRegister.registerAll(
                shorts,
                shortsInfoPanel,
                thanksButton,
               // subscribeButton,
                joinButton,
                soundButton,
                shortsChannelBar,
                graySeparator,
                carouselAd
        );
    }

    public boolean filter(final String path, final String identifier) {
        BlockResult result;

        if (custom.isEnabled() && custom.check(path).isBlocked())
            result = BlockResult.CUSTOM;
        else if (ReVancedUtils.containsAny(path, EXCEPTIONS))
            result = BlockResult.IGNORED;
        else if (pathRegister.contains(path) || identifierRegister.contains(identifier))
            result = BlockResult.DEFINED;
        else
            result = BlockResult.UNBLOCKED;

        LogHelper.printDebug(() -> String.format("%s (ID: %s): %s", result.message, identifier, path)
        );

        return result.filter;
    }

    private enum BlockResult {
        UNBLOCKED(false, "Unblocked"),
        IGNORED(false, "Ignored"),
        DEFINED(true, "Blocked"),
        CUSTOM(true, "Custom");

        final Boolean filter;
        final String message;

        BlockResult(boolean filter, String message) {
            this.filter = filter;
            this.message = message;
        }
    }

    /**
     * Hide the specific view, which shows ads in the homepage.
     *
     * @param view The view, which shows ads.
     */
    public static void hideAdAttributionView(View view) {
        if (!SettingsEnum.HIDE_GENERAL_ADS.getBoolean()) return;
        AdRemoverAPI.HideViewWithLayout1dp(view);
    }
}
