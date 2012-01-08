/**
 * AgeCrltsServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.agecrlts.service.impl;

public class AgeCrltsServiceImplServiceLocator extends org.apache.axis.client.Service implements cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplService {

    public AgeCrltsServiceImplServiceLocator() {
    }


    public AgeCrltsServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AgeCrltsServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AgeCrltsServiceImplPort
    private java.lang.String AgeCrltsServiceImplPort_address = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AgeCrltsService";

    public java.lang.String getAgeCrltsServiceImplPortAddress() {
        return AgeCrltsServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AgeCrltsServiceImplPortWSDDServiceName = "AgeCrltsServiceImplPort";

    public java.lang.String getAgeCrltsServiceImplPortWSDDServiceName() {
        return AgeCrltsServiceImplPortWSDDServiceName;
    }

    public void setAgeCrltsServiceImplPortWSDDServiceName(java.lang.String name) {
        AgeCrltsServiceImplPortWSDDServiceName = name;
    }

    public cha.crlts.agecrlts.service.AgeCrltsService getAgeCrltsServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AgeCrltsServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAgeCrltsServiceImplPort(endpoint);
    }

    public cha.crlts.agecrlts.service.AgeCrltsService getAgeCrltsServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplServiceSoapBindingStub _stub = new cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAgeCrltsServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAgeCrltsServiceImplPortEndpointAddress(java.lang.String address) {
        AgeCrltsServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cha.crlts.agecrlts.service.AgeCrltsService.class.isAssignableFrom(serviceEndpointInterface)) {
                cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplServiceSoapBindingStub _stub = new cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplServiceSoapBindingStub(new java.net.URL(AgeCrltsServiceImplPort_address), this);
                _stub.setPortName(getAgeCrltsServiceImplPortWSDDServiceName());
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
        if ("AgeCrltsServiceImplPort".equals(inputPortName)) {
            return getAgeCrltsServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.service.agecrlts.crlts.cha/", "AgeCrltsServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.service.agecrlts.crlts.cha/", "AgeCrltsServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AgeCrltsServiceImplPort".equals(portName)) {
            setAgeCrltsServiceImplPortEndpointAddress(address);
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
