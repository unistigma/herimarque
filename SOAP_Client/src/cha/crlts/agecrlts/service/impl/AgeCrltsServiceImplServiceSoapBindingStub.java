/**
 * AgeCrltsServiceImplServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.agecrlts.service.impl;

public class AgeCrltsServiceImplServiceSoapBindingStub extends org.apache.axis.client.Stub implements cha.crlts.agecrlts.service.AgeCrltsService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAgeCrltsImage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImage"), cha.crlts.agecrlts.service.GetAgeCrltsImage.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImageResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImageResponse"), cha.crlts.agecrlts.service.GetAgeCrltsImageResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAgeCrltsList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsList"), cha.crlts.agecrlts.service.GetAgeCrltsList.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsListResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsListResponse"), cha.crlts.agecrlts.service.GetAgeCrltsListResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAgeCrltsDtls");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtls"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtls"), cha.crlts.agecrlts.service.GetAgeCrltsDtls.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtlsResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtlsResponse"), cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public AgeCrltsServiceImplServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AgeCrltsServiceImplServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AgeCrltsServiceImplServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader");
            cachedSerQNames.add(qName);
            cls = iros.kma.ummd.iss.req.ComMsgHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", ">ageCrltsImageResponse>items");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsImageVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageVO");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", ">ageCrltsListResponse>items");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsListVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsListVO");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsDtlsRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsDtlsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsDtlsResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsDtlsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsImageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsImageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsImageVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsListRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsListResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsListVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.AgeCrltsListVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "baseResponseVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.BaseResponseVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtls");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsDtls.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtlsResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImage");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsImage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImageResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsImageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsList");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsListResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.agecrlts.service.GetAgeCrltsListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void getAgeCrltsImage(cha.crlts.agecrlts.service.GetAgeCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAgeCrltsImage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters, comMsgHeader.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
            try {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsImageResponse) _output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImageResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsImageResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsImageResponse")), cha.crlts.agecrlts.service.GetAgeCrltsImageResponse.class);
            }
            try {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getAgeCrltsList(cha.crlts.agecrlts.service.GetAgeCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAgeCrltsList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters, comMsgHeader.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
            try {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsListResponse) _output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsListResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsListResponse")), cha.crlts.agecrlts.service.GetAgeCrltsListResponse.class);
            }
            try {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getAgeCrltsDtls(cha.crlts.agecrlts.service.GetAgeCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAgeCrltsDtls"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters, comMsgHeader.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
            try {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse) _output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtlsResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "getAgeCrltsDtlsResponse")), cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse.class);
            }
            try {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) _output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"));
            } catch (java.lang.Exception _exception) {
                comMsgHeader2.value = (iros.kma.ummd.iss.req.ComMsgHeader) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader")), iros.kma.ummd.iss.req.ComMsgHeader.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
