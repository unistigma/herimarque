/**
 * AreaCrltsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service;

public interface AreaCrltsService extends java.rmi.Remote {
    public void getAreaCrltsList(cha.crlts.areacrlts.service.GetAreaCrltsList parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsListResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
    public void getAreaCrltsImage(cha.crlts.areacrlts.service.GetAreaCrltsImage parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsImageResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
    public void getAreaCrltsDtls(cha.crlts.areacrlts.service.GetAreaCrltsDtls parameters, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader, cha.crlts.areacrlts.service.holders.GetAreaCrltsDtlsResponseHolder result, iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder comMsgHeader2) throws java.rmi.RemoteException;
}
