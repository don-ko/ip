public class Donk {
    private boolean isExited = false;

    public String getWelcomeMessage() {
        String logo = "  _____   ____  _   _ _  __\n" +
                " |  __ \\ / __ \\| \\ | | |/ /\n" +
                " | |  | | |  | |  \\| | ' / \n" +
                " | |  | | |  | | . ` |  <  \n" +
                " | |__| | |__| | |\\  | . \\ \n" +
                " |_____/ \\____/|_| \\_|_|\\_\\\n\n";
        String welcome = "Hello! What can I do for you today?";
        return logo + welcome;
    }

    public void exit() {
        System.out.println("Bye!!!!");
    }
}
