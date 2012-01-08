/**
 * AreaCrltsServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service.impl;

public class AreaCrltsServiceImplServiceLocator extends org.apache.axis.client.Service implements cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplService {

    public AreaCrltsServiceImplServiceLocator() {
    }


    public AreaCrltsServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AreaCrltsServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AreaCrltsServiceImplPort
    private java.lang.String AreaCrltsServiceImplPort_address = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService";

    public java.lang.String getAreaCrltsServiceImplPortAddress() {
        return AreaCrltsServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AreaCrltsServiceImplPortWSDDServiceName = "AreaCrltsServiceImplPort";

    public java.lang.String getAreaCrltsServiceImplPortWSDDServiceName() {
        return AreaCrltsServiceImplPortWSDDServiceName;
    }

    public void setAreaCrltsServiceImplPortWSDDServiceName(java.lang.String name) {
        AreaCrltsServiceImplPortWSDDServiceName = name;
    }

    public cha.crlts.areacrlts.service.AreaCrltsService getAreaCrltsServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AreaCrltsServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAreaCrltsServiceImplPort(endpoint);
    }

    public cha.crlts.areacrlts.service.AreaCrltsService getAreaCrltsServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplServiceSoapBindingStub _stub = new cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAreaCrltsServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAreaCrltsServiceImplPortEndpointAddress(java.lang.String address) {
        AreaCrltsServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cha.crlts.areacrlts.service.AreaCrltsService.class.isAssignableFrom(serviceEndpointInterface)) {
                cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplServiceSoapBindingStub _stub = new cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplServiceSoapBindingStub(new java.net.URL(AreaCrltsServiceImplPort_address), this);
                _stub.setPortName(getAreaCrltsServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AreaCrltsServiceImplPort".equals(inputPortName)) {
            return getAreaCrltsServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.service.areacrlts.crlts.cha/", "AreaCrltsServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.service.areacrlts.crlts.cha/", "AreaCrltsServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AreaCrltsServiceImplPort".equals(portName)) {
            setAreaCrltsServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
