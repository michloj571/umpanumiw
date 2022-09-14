package pl.polsl.umpa;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.url")
public class ComponentUrlConfiguration {
    private String pump;
    private String poolThermometer;
    private String sprinkler;
    private String gate;
    private String moveDetector;
    private String roomThermometer;

    public String getPump() {
        return pump;
    }

    public void setPump(String pump) {
        this.pump = pump;
    }

    public String getPoolThermometer() {
        return poolThermometer;
    }

    public void setPoolThermometer(String poolThermometer) {
        this.poolThermometer = poolThermometer;
    }

    public String getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(String sprinkler) {
        this.sprinkler = sprinkler;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getMoveDetector() {
        return moveDetector;
    }

    public void setMoveDetector(String moveDetector) {
        this.moveDetector = moveDetector;
    }

    public String getRoomThermometer() {
        return roomThermometer;
    }

    public void setRoomThermometer(String roomThermometer) {
        this.roomThermometer = roomThermometer;
    }
}
