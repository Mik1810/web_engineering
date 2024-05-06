package it.univaq.webmarket.framework.data;

/**
 *
 * @author giuse
 */
public interface DataItemProxy {

    boolean isModified();

    void setModified(boolean dirty);

}
