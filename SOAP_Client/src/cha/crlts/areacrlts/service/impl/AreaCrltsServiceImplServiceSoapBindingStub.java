/**
 * AreaCrltsServiceImplServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service.impl;

public class AreaCrltsServiceImplServiceSoapBindingStub extends org.apache.axis.client.Stub implements cha.crlts.areacrlts.service.AreaCrltsService {
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
        oper.setName("getAreaCrltsList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsList"), cha.crlts.areacrlts.service.GetAreaCrltsList.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsListResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsListResponse"), cha.crlts.areacrlts.service.GetAreaCrltsListResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAreaCrltsImage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImage"), cha.crlts.areacrlts.service.GetAreaCrltsImage.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImageResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImageResponse"), cha.crlts.areacrlts.service.GetAreaCrltsImageResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAreaCrltsDtls");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtls"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtls"), cha.crlts.areacrlts.service.GetAreaCrltsDtls.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtlsResponse"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtlsResponse"), cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://apache.org/headers", "ComMsgHeader"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"), iros.kma.ummd.iss.req.ComMsgHeader.class, true, true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public AreaCrltsServiceImplServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AreaCrltsServiceImplServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AreaCrltsServiceImplServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", ">areaCrltsImageResponse>items");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsImageVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsImageVO");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", ">areaCrltsListResponse>items");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsListVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsListVO");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsDtlsRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsDtlsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsDtlsResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsDtlsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsImageRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsImageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsImageResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsImageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsImageVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsImageVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsListRequest");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsListResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsListVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.AreaCrltsListVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "baseResponseVO");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.BaseResponseVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtls");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsDtls.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtlsResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImage");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsImage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImageResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsImageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsList");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsListResponse");
            cachedSerQNames.add(qName);
            cls = cha.crlts.areacrlts.service.GetAreaCrltsListResponse.class;
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

    public void getAreaCrltsList(cha.crlts.areacrlts.service.GetAreaCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "getAreaCrltsList"));

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
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsListResponse) _output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsListResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsListResponse")), cha.crlts.areacrlts.service.GetAreaCrltsListResponse.class);
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

    public void getAreaCrltsImage(cha.crlts.areacrlts.service.GetAreaCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "getAreaCrltsImage"));

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
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsImageResponse) _output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImageResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsImageResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsImageResponse")), cha.crlts.areacrlts.service.GetAreaCrltsImageResponse.class);
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

    public void getAreaCrltsDtls(cha.crlts.areacrlts.service.GetAreaCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "getAreaCrltsDtls"));

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
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse) _output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtlsResponse"));
            } catch (java.lang.Exception _exception) {
                result.value = (cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "getAreaCrltsDtlsResponse")), cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse.class);
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
