package cha.crlts.agecrlts.service;

public class AgeCrltsServiceProxy implements cha.crlts.agecrlts.service.AgeCrltsService {
  private String _endpoint = null;
  private cha.crlts.agecrlts.service.AgeCrltsService ageCrltsService = null;
  
  public AgeCrltsServiceProxy() {
    _initAgeCrltsServiceProxy();
  }
  
  public AgeCrltsServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAgeCrltsServiceProxy();
  }
  
  private void _initAgeCrltsServiceProxy() {
    try {
      ageCrltsService = (new cha.crlts.agecrlts.service.impl.AgeCrltsServiceImplServiceLocator()).getAgeCrltsServiceImplPort();
      if (ageCrltsService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ageCrltsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ageCrltsService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ageCrltsService != null)
      ((javax.xml.rpc.Stub)ageCrltsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cha.crlts.agecrlts.service.AgeCrltsService getAgeCrltsService() {
    if (ageCrltsService == null)
      _initAgeCrltsServiceProxy();
    return ageCrltsService;
  }
  
  public void getAgeCrltsImage(cha.crlts.agecrlts.service.GetAgeCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (ageCrltsService == null)
      _initAgeCrltsServiceProxy();
    ageCrltsService.getAgeCrltsImage(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  public void getAgeCrltsList(cha.crlts.agecrlts.service.GetAgeCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (ageCrltsService == null)
      _initAgeCrltsServiceProxy();
    ageCrltsService.getAgeCrltsList(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  public void getAgeCrltsDtls(cha.crlts.agecrlts.service.GetAgeCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException{
    if (ageCrltsService == null)
      _initAgeCrltsServiceProxy();
    ageCrltsService.getAgeCrltsDtls(parameters, comMsgHeader, result, comMsgHeader2);
  }
  
  
}