package net.minecraft.launcher;

import java.net.URI;
import java.net.URISyntaxException;

public class LauncherConstants {
    public static final String VERSION_NAME = "1.0"; //Version du Launcher
    public static final int VERSION_NUMERIC = 7;
    public static final String DEFAULT_PROFILE_NAME = "Redpack 3"; //Nom de Profil par Defaut.
    public static final String SERVER_NAME = "Les ptits lapins roses"; //Nom de votre Serveur.
    public static final URI URL_REGISTER = constantURI("https://account.mojang.com/register"); //Lien géré par le bouton Register dans le Launcher.
    // public static final String URL_DOWNLOAD_BASE =
    // "https://s3.amazonaws.com/Minecraft.Download/";
    public static final String URL_DOWNLOAD_BASE = "http://maohicraft.crystal-serv.com/launcher/"; //Dossier principal de votre launcher sur votre serveur FTP.
    public static final boolean useModResource = true; //Si vous utilisez des mods hébergés sur FTP, mettez sur true. Sinon mettez false.
    public static final String URL_DOWNLOAD_MODS = "http://maohicraft.crystal-serv.com/ressources/"; //Dossier principal de vos ressouces perso sur votre serveur FTP.
    public static final String URL_RESOURCE_BASE = "http://https://s3.amazonaws.com/MinecraftResources/"; //Dossier principal des ressources officielles du jeu. (par defaut "https://s3.amazonaws.com/Minecraft.Resources/" ou "http://resources.download.minecraft.net/")
    public static final String LIBRARY_DOWNLOAD_BASE = "https://libraries.minecraft.net/"; //Dossier principal des librairies utilisées par le jeu. (par defaut https://libraries.minecraft.net/ ou "https://s3.amazonaws.com/Minecraft.Download/libraries/")
    public static final String URL_BLOG = "http://maohicraft.crystal-serv.com/"; //Blog affiché dans le launcher au démarrage. (par defaut "http://mcupdate.tumblr.com")
    public static final String URL_STATUS_CHECKER = "http://status.mojang.com/check"; //Lien de Vérification de Compte chez MOJANG. ("http://status.mojang.com/check")
    public static final String URL_BOOTSTRAP_DOWNLOAD = "http://maohicraft.crystal-serv.com/launcher/bootstrap.jar"; //Adresse de votre fichier bootstrap.jar sur votre serveur FTP.
    public static final URI URL_FORGOT_USERNAME = constantURI("http://help.mojang.com/customer/portal/articles/1233873"); //Lien URL de "Pseudo oublié ?". ("http://help.mojang.com/customer/portal/articles/1233873")
    public static final URI URL_FORGOT_PASSWORD_MINECRAFT = constantURI("http://help.mojang.com/customer/portal/articles/329524-change-or-forgot-password"); //Lien URL de "Mot de passe oublié ?". ("http://help.mojang.com/customer/portal/articles/329524-change-or-forgot-password")
    public static final URI URL_FORGOT_MIGRATED_EMAIL = constantURI("http://help.mojang.com/customer/portal/articles/1205055-minecraft-launcher-error---migrated-account"); //Lien URL de Migration Mail. ("http://help.mojang.com/customer/portal/articles/1205055-minecraft-launcher-error---migrated-account")
	public static final String URL_AUTHENTICATION_SERVER = "https://authserver.mojang.com/"; //Serveur d'authentification, par defaut : "https://authserver.mojang.com/"
	
    public static URI constantURI(final String input) {
        try {
            return new URI(input);
        }
        catch(final URISyntaxException e) {
            throw new Error(e);
        }
    }
}