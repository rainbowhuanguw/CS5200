package aireats.model;

public class Hosts {
    protected Integer HostId;
    protected String HostName;

    public Hosts(Integer hostId, String hostName) {
        super();
        HostName = hostName;
        HostId = hostId;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public Integer getHostId() {
        return HostId;
    }

    public void setHostId(Integer hostId) {
        HostId = hostId;
    }

}
