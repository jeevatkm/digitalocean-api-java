package com.myjeeva.digitalocean;

/**
 * Describes possible droplet states.
 */
public enum DropletStatus {

    New("new"),
    Active("active"),
    Off("off"),
    Archive("archive");

    private String value;

    private DropletStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static DropletStatus fromValue(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");

        } else if ("new".equals(value)) {
            return DropletStatus.New;
        } else if ("active".equals(value)) {
            return DropletStatus.Active;
        } else if ("off".equals(value)) {
            return DropletStatus.Off;
        } else if ("archive".equals(value)) {
            return DropletStatus.Archive;
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}
