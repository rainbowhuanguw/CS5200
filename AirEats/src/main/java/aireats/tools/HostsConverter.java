package aireats.tools;

import aireats.model.Hosts;

import java.util.List;

public class HostsConverter implements ObjectConverter {

    // strs columns: host id, host name
    @Override
    public Hosts listToObject(List<String> strs) {
        return new Hosts(Integer.parseInt(strs.get(0)), strs.get(1));
    }
}
