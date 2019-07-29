import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.server.Server;

import java.util.concurrent.ExecutionException;

public class ServerBuilderHelper {
    public static IDHelper ID = new IDHelper();

    public boolean checkServerStructure(DiscordApi API) {
        Server server = API.getServerById(ID.SERVER).get();
        try {
            server.getChannelCategoriesByNameIgnoreCase("Moanistrative Centre").get(0);
            return false;
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println(e.getStackTrace() + "\nCouldn't find Moanistrative Centre Category !, building new one");
            ChannelCategory cc = buildChannelCategory(server);
            if (cc.getRawPosition() != 0) {
                cc.updateRawPosition(0);
            }
            if (cc != null) {
                ID.writeIDFile("0", cc.getId());
                //RULES
                ServerTextChannel rules = buildFrontRestrictedTextChannel(server, cc, "Follow or suffer consequences", "Rules");
                if (rules == null) {
                    System.out.println("Couldn't create Rules Text Channel !");
                } else {
                    ID.writeIDFile("1", rules.getId());
                    try {
                        server.getTextChannelsByNameIgnoreCase("rules").get(0).sendMessage("\uD83D\uDEA7!Rules under construction!\uD83D\uDEA7").get().pin();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (ExecutionException ex) {
                        ex.printStackTrace();
                    }
                }
                //ANNOUNCEMENTS
                ServerTextChannel announcements = buildFrontRestrictedTextChannel(server, cc, "Important news that you should know", "Announcements");
                if (announcements == null) {
                    System.out.println("Couldn't create Announcements Text Channel !");
                } else {
                    ID.writeIDFile("2", announcements.getId());
                    try {
                        server.getTextChannelsByNameIgnoreCase("announcements").get(0).sendMessage("\uD83D\uDCDCThere is no important news at the moment\uD83D\uDCDC").get().pin();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (ExecutionException ex) {
                        ex.printStackTrace();
                    }
                }
                //EVENTS
                ServerTextChannel events = buildBackRestrictedTextChannel(server, cc, "Offical Moaning Events (OME)\n\uD83D\uDCE2!Notice: Only Moaners can see this channel!\uD83D\uDCE2", "Events");
                if (events == null) {
                    System.out.println("Couldn't create Events Text Channel !");
                } else {
                    ID.writeIDFile("3", events.getId());
                }
                //POLLS
                ServerTextChannel polls = buildBackRestrictedTextChannel(server, cc, "Give your opinions with these Moaning polls\n\uD83D\uDCE2!Notice: Only true Moaners can see this channel!\uD83D\uDCE2", "Polls");
                if (polls == null) {
                    System.out.println("Couldn't create Polls Text Channel !");
                } else {
                    ID.writeIDFile("4", polls.getId());
                }
                //BOTS-COMMAND
                ServerTextChannel botscommand = buildBackRestrictedTextChannel(server, cc, "Communicate format of the bots\n\uD83D\uDCE2!Notice: Only true Moaners can see this channel!\uD83D\uDCE2", "Bots-command");
                if (botscommand == null) {
                    System.out.println("Couldn't create Bots-command Text Channel !");
                } else {
                    ID.writeIDFile("5", botscommand.getId());
                }
                //CONFERENCE ROOM
                if (buildBackRestrictedVoiceChannel(server, cc, "Conference Room") == null) {
                    System.out.println("Couldn't create Conference Room Voice Channel !");
                }
                ID = new IDHelper();
                return true;
            } else {
                System.out.println("Couldn't create Moanistrative Centre Category !");
                return false;
            }
        }
    }

    //Build Administrator Centre Category
    private ChannelCategory buildChannelCategory(Server server) {
        try {
            return server.createChannelCategoryBuilder()
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEADMINISTRATOR).get(), PermissionHelper.getAdministratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLESUPERMODERATOR).get(), PermissionHelper.getSuperModeratorPermissions())
                    //.addPermissionOverwrite(server.getRoleById(ID.ROLEMODERATOR).get(),PermissionHelper.getModeratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEMEMBER).get(), PermissionHelper.getMemberPermissionsRestricted())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLENONMEMBER).get(), PermissionHelper.getNonMemberPermissionsRestricted().toBuilder().setAllowed(PermissionType.READ_MESSAGES).build()).setName("Moanistrative Centre").create().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    //For Rules, Announcement channels
    private ServerTextChannel buildFrontRestrictedTextChannel(Server server, ChannelCategory channelCategory, String topic, String channelName) {
        try {
            return server.createTextChannelBuilder().setCategory(channelCategory).setTopic(topic)
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEADMINISTRATOR).get(), PermissionHelper.getAdministratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLESUPERMODERATOR).get(), PermissionHelper.getSuperModeratorPermissions())
                    //.addPermissionOverwrite(server.getRoleById(ID.ROLEMODERATOR).get(),PermissionHelper.getModeratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEMEMBER).get(), PermissionHelper.getMemberPermissionsRestricted())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLENONMEMBER).get(), PermissionHelper.getNonMemberPermissionsRestricted().toBuilder().setAllowed(PermissionType.READ_MESSAGES).build()).setName(channelName).create().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    //For Events, Polls
    private ServerTextChannel buildBackRestrictedTextChannel(Server server, ChannelCategory channelCategory, String topic, String channelName) {
        try {
            return server.createTextChannelBuilder().setCategory(channelCategory).setTopic(topic)
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEADMINISTRATOR).get(), PermissionHelper.getAdministratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLESUPERMODERATOR).get(), PermissionHelper.getSuperModeratorPermissions())
                    //.addPermissionOverwrite(server.getRoleById(ID.ROLEMODERATOR).get(),PermissionHelper.getModeratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEMEMBER).get(), PermissionHelper.getMemberPermissionsRestricted())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLENONMEMBER).get(), PermissionHelper.getNonMemberPermissionsRestricted()).setName(channelName).create().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ServerVoiceChannel buildBackRestrictedVoiceChannel(Server server, ChannelCategory channelCategory, String channelName) {
        try {
            return server.createVoiceChannelBuilder().setCategory(channelCategory).setBitrate(1024 * 64).setUserlimit(99)
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEADMINISTRATOR).get(), PermissionHelper.getAdministratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLESUPERMODERATOR).get(), PermissionHelper.getSuperModeratorPermissions())
                    //.addPermissionOverwrite(server.getRoleById(ID.ROLEMODERATOR).get(),PermissionHelper.getModeratorPermissions())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLEMEMBER).get(), PermissionHelper.getMemberPermissionsRestricted())
                    .addPermissionOverwrite(server.getRoleById(ID.ROLENONMEMBER).get(), PermissionHelper.getNonMemberPermissionsRestricted()).setName(channelName).create().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
