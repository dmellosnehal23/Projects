/**
 * ServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package VideoLibrary;

public interface ServiceService extends javax.xml.rpc.Service {
    public java.lang.String getServiceAddress();

    public VideoLibrary.Service getService() throws javax.xml.rpc.ServiceException;

    public VideoLibrary.Service getService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
