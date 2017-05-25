package vexatos.manualtabs.proxy;

import vexatos.manualtabs.util.BadConfigException;

/**
 * @author Vexatos
 */
public class CommonProxy {

    public void throwBadConfigException(String icon) {
        throw new BadConfigException(icon);
    }

    public void throwBadConfigException(String icon, Throwable t) {
        throw new BadConfigException(icon, t);
    }

}
