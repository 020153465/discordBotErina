import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.PermissionsBuilder;

public class PermissionHelper {
    protected static Permissions getErinaPermissions() {
        return new PermissionsBuilder().setAllDenied().setAllowed(PermissionType.ADD_REACTIONS).setAllowed(PermissionType.ATTACH_FILE).setAllowed(PermissionType.EMBED_LINKS)
                .setAllowed(PermissionType.MANAGE_CHANNELS).setAllowed(PermissionType.MANAGE_EMOJIS).setAllowed(PermissionType.MANAGE_MESSAGES).setAllowed(PermissionType.MANAGE_WEBHOOKS)
                .setAllowed(PermissionType.MANAGE_ROLES).setAllowed(PermissionType.MENTION_EVERYONE).setAllowed(PermissionType.READ_MESSAGES).setAllowed(PermissionType.SEND_MESSAGES)
                .setAllowed(PermissionType.SEND_TTS_MESSAGES).build();
    }

    protected static Permissions getAdministratorPermissions() {
        return new PermissionsBuilder().setAllAllowed().build();
    }

    protected static Permissions getSuperModeratorPermissions() {
        return new PermissionsBuilder().setAllAllowed().setDenied(PermissionType.ADMINISTRATOR).setDenied(PermissionType.MANAGE_SERVER).build();
    }

    protected static Permissions getModeratorPermissions() {
        return new PermissionsBuilder().setAllAllowed().setDenied(PermissionType.ADMINISTRATOR).setDenied(PermissionType.MANAGE_SERVER).setDenied(PermissionType.MANAGE_ROLES)
                .setDenied(PermissionType.MANAGE_CHANNELS).build();
    }

    protected static Permissions getMemberPermissionsNormal() {
        return new PermissionsBuilder().setAllDenied().setAllowed(PermissionType.CREATE_INSTANT_INVITE).setAllowed(PermissionType.CHANGE_NICKNAME).setAllowed(PermissionType.READ_MESSAGES)
                .setAllowed(PermissionType.SEND_MESSAGES).setAllowed(PermissionType.SEND_TTS_MESSAGES).setAllowed(PermissionType.EMBED_LINKS).setAllowed(PermissionType.ATTACH_FILE)
                .setAllowed(PermissionType.READ_MESSAGE_HISTORY).setAllowed(PermissionType.MENTION_EVERYONE).setAllowed(PermissionType.USE_EXTERNAL_EMOJIS)
                .setAllowed(PermissionType.ADD_REACTIONS).setAllowed(PermissionType.CONNECT).setAllowed(PermissionType.SPEAK).setAllowed(PermissionType.USE_VOICE_ACTIVITY).build();
    }

    protected static Permissions getMemberPermissionsRestricted() {
        return new PermissionsBuilder().setAllDenied().setAllowed(PermissionType.READ_MESSAGES).setAllowed(PermissionType.READ_MESSAGE_HISTORY).setAllowed(PermissionType.CONNECT).build();
    }

    protected static Permissions getNonMemberPermissionNormal() {
        return new PermissionsBuilder().setAllDenied().setAllowed(PermissionType.READ_MESSAGES)
                .setAllowed(PermissionType.SEND_MESSAGES).setAllowed(PermissionType.EMBED_LINKS).setAllowed(PermissionType.ATTACH_FILE).setAllowed(PermissionType.READ_MESSAGE_HISTORY)
                .setAllowed(PermissionType.MENTION_EVERYONE).setAllowed(PermissionType.USE_EXTERNAL_EMOJIS).setAllowed(PermissionType.ADD_REACTIONS).setAllowed(PermissionType.CONNECT)
                .setAllowed(PermissionType.SPEAK).setAllowed(PermissionType.USE_VOICE_ACTIVITY).build();
    }

    protected static Permissions getNonMemberPermissionsRestricted() {
        return new PermissionsBuilder().setAllDenied().build();
    }
}
