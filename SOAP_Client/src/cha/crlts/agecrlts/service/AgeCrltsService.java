/**
 * AgeCrltsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.agecrlts.service;

public interface AgeCrltsService extends java.rmi.Remote {
    public void getAgeCrltsImage(cha.crlts.agecrlts.service.GetAgeCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
    public void getAgeCrltsList(cha.crlts.agecrlts.service.GetAgeCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
    public void getAgeCrltsDtls(cha.crlts.agecrlts.service.GetAgeCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.agecrlts.service.holders.GetAgeCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
}
