package consumer.device.common.entertainment;

/**
 * Game for playing on gaming console.
 * @param name Name of the game.
 * @param description Description of the game.
 * @param genre Genre of the game.
 */
public record Game(String name, String description, GameGenre genre) {}
