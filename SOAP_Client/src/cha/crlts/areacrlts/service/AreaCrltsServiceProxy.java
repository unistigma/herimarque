package cha.crlts.areacrlts.service;

public class AreaCrltsServiceProxy implements cha.crlts.areacrlts.service.AreaCrltsService {
  private String _endpoint = null;
  private cha.crlts.areacrlts.service.AreaCrltsService areaCrltsService = null;
  
  public AreaCrltsServiceProxy() {
    _initAreaCrltsServiceProxy();
  }
  
  public AreaCrltsServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAreaCrltsServiceProxy();
  }
  
  private void _initAreaCrltsServiceProxy() {
    try {
      areaCrltsService = (new cha.crlts.areacrlts.service.impl.AreaCrltsServiceImplServiceLocator()).getAreaCrltsServiceImplPort();
      if (areaCrltsService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)areaCrltsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)areaCrltsService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (areaCrltsService != null)
      ((javax.xml.rpc.Stub)areaCrltsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cha.crlts.areacrlts.service.AreaCrltsService getAreaCrltsService() {
    if (areaCrltsService == null)
      _initAreaCrltsServiceProxy();
    return areaCrltsService;
  }
  
  public void getAreaCrltsList(cha.crlts.areacrlts.service.GetAreaCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (areaCrltsService == null)
      _initAreaCrltsServiceProxy();
    areaCrltsService.getAreaCrltsList(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  public void getAreaCrltsImage(cha.crlts.areacrlts.service.GetAreaCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (areaCrltsService == null)
      _initAreaCrltsServiceProxy();
    areaCrltsService.getAreaCrltsImage(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  public void getAreaCrltsDtls(cha.crlts.areacrlts.service.GetAreaCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (areaCrltsService == null)
      _initAreaCrltsServiceProxy();
    areaCrltsService.getAreaCrltsDtls(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  
}