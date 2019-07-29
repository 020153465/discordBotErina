import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;

import java.util.Random;


public class Core {
    //ERINA
    private static final String TOKEN = "NjAzMjY1NjAwOTM1OTUyMzk0.XT6Rkg.kL80DNgW5V8raHS8TNq_0tToJfQ";
    private static final DiscordApi API = new DiscordApiBuilder().setToken(TOKEN).login().join();
    private static final User ERINA = API.getYourself();
    private static final String NOPERMISSIONS = "Sorry you don't have permission to do that >.< !";
    private static volatile String RANDOMKEY = String.valueOf(new Random().nextInt(99999999));//Double.toString(Math.random());

    //Core's main thread//
    public static void main(String args[]) {
        new Initializing();
        //Ping listener//
        API.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!erina")) {
                event.getChannel().sendMessage("Erina is here !");
            }
        });
        //Poll Listener//
        API.addMessageCreateListener(event -> {
            if (event.getMessageContent().toLowerCase().contains("!poll ")) {
                //User is member
                if (event.getMessageAuthor().canCreateInstantInviteToTextChannel()) {

                } else {
                    event.getChannel().sendMessage(NOPERMISSIONS);
                }
            }
        });

        API.addMessageCreateListener(event -> {
            if (event.getMessageContent().trim().toLowerCase().contains("!erina build") && event.getMessageAuthor().isServerAdmin()) {
                if (event.getMessageContent().trim().equalsIgnoreCase("!erina build " + RANDOMKEY)) {
                    event.getChannel().sendMessage("\uD83D\uDEA7Building Moanistrative Centre\uD83D\uDEA7");
                    ServerBuilderHelper sbh = new ServerBuilderHelper();
                    if (sbh.checkServerStructure(API)) {
                        event.getChannel().sendMessage("\uD83D\uDC77Finished Building Moanistrative Centre\uD83D\uDC77");
                    } else {
                        event.getChannel().sendMessage("Couldn't build Moanistrative Centre!\nMaybe it's already existed");
                    }
                    RANDOMKEY = String.valueOf(new Random().nextInt(99999999));
                } else {
                    RANDOMKEY = String.valueOf(new Random().nextInt(99999999));
                    event.getChannel().sendMessage("say \"!Erina Build " + RANDOMKEY + "\" to confirm building");
                }
            }
        });
        //Bot ready//
        System.out.println("Logged in!\nUse the following URL to invite Erina Bot to your channel : " + API.createBotInvite(PermissionHelper.getErinaPermissions()));
    }


}
