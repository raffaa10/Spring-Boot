package fr.epsi.montpellier.wsshare.controller;

public abstract class BaseController {

    /** Affiche le message d'erreur et la pile d'appel dans les logs
     *
     * @param exception Exception déclenchée
     */
    protected void LogError(Exception exception) {
        System.err.printf("Error, Class=%s\n", this.getClass().getCanonicalName());
        exception.printStackTrace(System.err);
    }

    /** Affiche un message dans les logs
     *
     * @param message Message à afficher
     */
    protected void LogMessage(String message) {
        System.err.printf("Message=%s, Class=%s\n", message, this.getClass().getCanonicalName());
    }


}
