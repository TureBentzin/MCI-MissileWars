package de.mcimpact.missilewars.errors;


import java.util.UUID;


public class PlayerResolvingException extends Exception{

    private UUID uuid;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public PlayerResolvingException(UUID uuid) {
        setUuid(uuid);
    }

    @Override
    public String getMessage() {
        return "cant resolve netplayer: " + getUuid().toString();
    }
}
