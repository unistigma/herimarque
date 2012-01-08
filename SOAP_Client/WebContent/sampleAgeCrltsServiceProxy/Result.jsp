<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleAgeCrltsServiceProxyid" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleAgeCrltsServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleAgeCrltsServiceProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleAgeCrltsServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        cha.crlts.agecrlts.service.AgeCrltsService getAgeCrltsService10mtemp = sampleAgeCrltsServiceProxyid.getAgeCrltsService();
if(getAgeCrltsService10mtemp == null){
%>
<%=getAgeCrltsService10mtemp %>
<%
}else{
        if(getAgeCrltsService10mtemp!= null){
        String tempreturnp11 = getAgeCrltsService10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String crltsNo_3id=  request.getParameter("crltsNo20");
            java.lang.String crltsNo_3idTemp = null;
        if(!crltsNo_3id.equals("")){
         crltsNo_3idTemp  = crltsNo_3id;
        }
        String crltsNm_4id=  request.getParameter("crltsNm22");
            java.lang.String crltsNm_4idTemp = null;
        if(!crltsNm_4id.equals("")){
         crltsNm_4idTemp  = crltsNm_4id;
        }
        String nowPageNo_5id=  request.getParameter("nowPageNo24");
        int nowPageNo_5idTemp  = Integer.parseInt(nowPageNo_5id);
        String crltsNoNm_6id=  request.getParameter("crltsNoNm26");
            java.lang.String crltsNoNm_6idTemp = null;
        if(!crltsNoNm_6id.equals("")){
         crltsNoNm_6idTemp  = crltsNoNm_6id;
        }
        String itemNm_7id=  request.getParameter("itemNm28");
            java.lang.String itemNm_7idTemp = null;
        if(!itemNm_7id.equals("")){
         itemNm_7idTemp  = itemNm_7id;
        }
        String ctrdCd_8id=  request.getParameter("ctrdCd30");
            java.lang.String ctrdCd_8idTemp = null;
        if(!ctrdCd_8id.equals("")){
         ctrdCd_8idTemp  = ctrdCd_8id;
        }
        String pageMg_9id=  request.getParameter("pageMg32");
        int pageMg_9idTemp  = Integer.parseInt(pageMg_9id);
        String itemCd_10id=  request.getParameter("itemCd34");
            java.lang.String itemCd_10idTemp = null;
        if(!itemCd_10id.equals("")){
         itemCd_10idTemp  = itemCd_10id;
        }
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsImageRequest" />
        <%
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setCrltsNo(crltsNo_3idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setCrltsNm(crltsNm_4idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setNowPageNo(nowPageNo_5idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setCrltsNoNm(crltsNoNm_6idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setItemNm(itemNm_7idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setCtrdCd(ctrdCd_8idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setPageMg(pageMg_9idTemp);
        cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id.setItemCd(itemCd_10idTemp);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsImage_1id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsImage" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsImage_1id.setArg0(cha1crlts1agecrlts1service1AgeCrltsImageRequest_2id);
        String callBackURI_13id=  request.getParameter("callBackURI40");
            java.lang.String callBackURI_13idTemp = null;
        if(!callBackURI_13id.equals("")){
         callBackURI_13idTemp  = callBackURI_13id;
        }
        String requestMsgID_14id=  request.getParameter("requestMsgID42");
            java.lang.String requestMsgID_14idTemp = null;
        if(!requestMsgID_14id.equals("")){
         requestMsgID_14idTemp  = requestMsgID_14id;
        }
        String requestTime_15id=  request.getParameter("requestTime44");
            java.lang.String requestTime_15idTemp = null;
        if(!requestTime_15id.equals("")){
         requestTime_15idTemp  = requestTime_15id;
        }
        String serviceKey_16id=  request.getParameter("serviceKey46");
            java.lang.String serviceKey_16idTemp = null;
        if(!serviceKey_16id.equals("")){
         serviceKey_16idTemp  = serviceKey_16id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_12id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_12id.setCallBackURI(callBackURI_13idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_12id.setRequestMsgID(requestMsgID_14idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_12id.setRequestTime(requestTime_15idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_12id.setServiceKey(serviceKey_16idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_11id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_11id.value = iros1kma1ummd1iss1req1ComMsgHeader_12id;
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsImageResponse_19id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsImageResponse" />
        <%
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsImageResponse_18id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsImageResponse" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsImageResponse_18id.set_return(cha1crlts1agecrlts1service1AgeCrltsImageResponse_19id);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1holders1GetAgeCrltsImageResponseHolder_17id" scope="session" class="cha.crlts.agecrlts.service.holders.GetAgeCrltsImageResponseHolder" />
        <%
        cha1crlts1agecrlts1service1holders1GetAgeCrltsImageResponseHolder_17id.value = cha1crlts1agecrlts1service1GetAgeCrltsImageResponse_18id;
        String callBackURI_22id=  request.getParameter("callBackURI58");
            java.lang.String callBackURI_22idTemp = null;
        if(!callBackURI_22id.equals("")){
         callBackURI_22idTemp  = callBackURI_22id;
        }
        String requestMsgID_23id=  request.getParameter("requestMsgID60");
            java.lang.String requestMsgID_23idTemp = null;
        if(!requestMsgID_23id.equals("")){
         requestMsgID_23idTemp  = requestMsgID_23id;
        }
        String requestTime_24id=  request.getParameter("requestTime62");
            java.lang.String requestTime_24idTemp = null;
        if(!requestTime_24id.equals("")){
         requestTime_24idTemp  = requestTime_24id;
        }
        String serviceKey_25id=  request.getParameter("serviceKey64");
            java.lang.String serviceKey_25idTemp = null;
        if(!serviceKey_25id.equals("")){
         serviceKey_25idTemp  = serviceKey_25id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_21id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_21id.setCallBackURI(callBackURI_22idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_21id.setRequestMsgID(requestMsgID_23idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_21id.setRequestTime(requestTime_24idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_21id.setServiceKey(serviceKey_25idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_20id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_20id.value = iros1kma1ummd1iss1req1ComMsgHeader_21id;
        sampleAgeCrltsServiceProxyid.getAgeCrltsImage(cha1crlts1agecrlts1service1GetAgeCrltsImage_1id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_11id,cha1crlts1agecrlts1service1holders1GetAgeCrltsImageResponseHolder_17id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_20id);
break;
case 66:
        gotMethod = true;
        String crltsNo_28id=  request.getParameter("crltsNo73");
            java.lang.String crltsNo_28idTemp = null;
        if(!crltsNo_28id.equals("")){
         crltsNo_28idTemp  = crltsNo_28id;
        }
        String crltsNm_29id=  request.getParameter("crltsNm75");
            java.lang.String crltsNm_29idTemp = null;
        if(!crltsNm_29id.equals("")){
         crltsNm_29idTemp  = crltsNm_29id;
        }
        String yCntsBegin_30id=  request.getParameter("yCntsBegin77");
            java.lang.String yCntsBegin_30idTemp = null;
        if(!yCntsBegin_30id.equals("")){
         yCntsBegin_30idTemp  = yCntsBegin_30id;
        }
        String nowPageNo_31id=  request.getParameter("nowPageNo79");
        int nowPageNo_31idTemp  = Integer.parseInt(nowPageNo_31id);
        String crltsNoNm_32id=  request.getParameter("crltsNoNm81");
            java.lang.String crltsNoNm_32idTemp = null;
        if(!crltsNoNm_32id.equals("")){
         crltsNoNm_32idTemp  = crltsNoNm_32id;
        }
        String itemNm_33id=  request.getParameter("itemNm83");
            java.lang.String itemNm_33idTemp = null;
        if(!itemNm_33id.equals("")){
         itemNm_33idTemp  = itemNm_33id;
        }
        String ctrdCd_34id=  request.getParameter("ctrdCd85");
            java.lang.String ctrdCd_34idTemp = null;
        if(!ctrdCd_34id.equals("")){
         ctrdCd_34idTemp  = ctrdCd_34id;
        }
        String yCntsEnd_35id=  request.getParameter("yCntsEnd87");
            java.lang.String yCntsEnd_35idTemp = null;
        if(!yCntsEnd_35id.equals("")){
         yCntsEnd_35idTemp  = yCntsEnd_35id;
        }
        String pageMg_36id=  request.getParameter("pageMg89");
        int pageMg_36idTemp  = Integer.parseInt(pageMg_36id);
        String xCntsBegin_37id=  request.getParameter("xCntsBegin91");
            java.lang.String xCntsBegin_37idTemp = null;
        if(!xCntsBegin_37id.equals("")){
         xCntsBegin_37idTemp  = xCntsBegin_37id;
        }
        String xCntsEnd_38id=  request.getParameter("xCntsEnd93");
            java.lang.String xCntsEnd_38idTemp = null;
        if(!xCntsEnd_38id.equals("")){
         xCntsEnd_38idTemp  = xCntsEnd_38id;
        }
        String itemCd_39id=  request.getParameter("itemCd95");
            java.lang.String itemCd_39idTemp = null;
        if(!itemCd_39id.equals("")){
         itemCd_39idTemp  = itemCd_39id;
        }
        String ageCd_40id=  request.getParameter("ageCd97");
            java.lang.String ageCd_40idTemp = null;
        if(!ageCd_40id.equals("")){
         ageCd_40idTemp  = ageCd_40id;
        }
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsListRequest_27id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsListRequest" />
        <%
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setCrltsNo(crltsNo_28idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setCrltsNm(crltsNm_29idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setYCntsBegin(yCntsBegin_30idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setNowPageNo(nowPageNo_31idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setCrltsNoNm(crltsNoNm_32idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setItemNm(itemNm_33idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setCtrdCd(ctrdCd_34idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setYCntsEnd(yCntsEnd_35idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setPageMg(pageMg_36idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setXCntsBegin(xCntsBegin_37idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setXCntsEnd(xCntsEnd_38idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setItemCd(itemCd_39idTemp);
        cha1crlts1agecrlts1service1AgeCrltsListRequest_27id.setAgeCd(ageCd_40idTemp);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsList_26id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsList" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsList_26id.setArg0(cha1crlts1agecrlts1service1AgeCrltsListRequest_27id);
        String callBackURI_43id=  request.getParameter("callBackURI103");
            java.lang.String callBackURI_43idTemp = null;
        if(!callBackURI_43id.equals("")){
         callBackURI_43idTemp  = callBackURI_43id;
        }
        String requestMsgID_44id=  request.getParameter("requestMsgID105");
            java.lang.String requestMsgID_44idTemp = null;
        if(!requestMsgID_44id.equals("")){
         requestMsgID_44idTemp  = requestMsgID_44id;
        }
        String requestTime_45id=  request.getParameter("requestTime107");
            java.lang.String requestTime_45idTemp = null;
        if(!requestTime_45id.equals("")){
         requestTime_45idTemp  = requestTime_45id;
        }
        String serviceKey_46id=  request.getParameter("serviceKey109");
            java.lang.String serviceKey_46idTemp = null;
        if(!serviceKey_46id.equals("")){
         serviceKey_46idTemp  = serviceKey_46id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_42id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_42id.setCallBackURI(callBackURI_43idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_42id.setRequestMsgID(requestMsgID_44idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_42id.setRequestTime(requestTime_45idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_42id.setServiceKey(serviceKey_46idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_41id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_41id.value = iros1kma1ummd1iss1req1ComMsgHeader_42id;
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsListResponse_49id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsListResponse" />
        <%
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsListResponse_48id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsListResponse" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsListResponse_48id.set_return(cha1crlts1agecrlts1service1AgeCrltsListResponse_49id);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1holders1GetAgeCrltsListResponseHolder_47id" scope="session" class="cha.crlts.agecrlts.service.holders.GetAgeCrltsListResponseHolder" />
        <%
        cha1crlts1agecrlts1service1holders1GetAgeCrltsListResponseHolder_47id.value = cha1crlts1agecrlts1service1GetAgeCrltsListResponse_48id;
        String callBackURI_52id=  request.getParameter("callBackURI121");
            java.lang.String callBackURI_52idTemp = null;
        if(!callBackURI_52id.equals("")){
         callBackURI_52idTemp  = callBackURI_52id;
        }
        String requestMsgID_53id=  request.getParameter("requestMsgID123");
            java.lang.String requestMsgID_53idTemp = null;
        if(!requestMsgID_53id.equals("")){
         requestMsgID_53idTemp  = requestMsgID_53id;
        }
        String requestTime_54id=  request.getParameter("requestTime125");
            java.lang.String requestTime_54idTemp = null;
        if(!requestTime_54id.equals("")){
         requestTime_54idTemp  = requestTime_54id;
        }
        String serviceKey_55id=  request.getParameter("serviceKey127");
            java.lang.String serviceKey_55idTemp = null;
        if(!serviceKey_55id.equals("")){
         serviceKey_55idTemp  = serviceKey_55id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_51id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_51id.setCallBackURI(callBackURI_52idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_51id.setRequestMsgID(requestMsgID_53idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_51id.setRequestTime(requestTime_54idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_51id.setServiceKey(serviceKey_55idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_50id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_50id.value = iros1kma1ummd1iss1req1ComMsgHeader_51id;
        sampleAgeCrltsServiceProxyid.getAgeCrltsList(cha1crlts1agecrlts1service1GetAgeCrltsList_26id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_41id,cha1crlts1agecrlts1service1holders1GetAgeCrltsListResponseHolder_47id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_50id);
break;
case 129:
        gotMethod = true;
        String ctrdCd_58id=  request.getParameter("ctrdCd136");
            java.lang.String ctrdCd_58idTemp = null;
        if(!ctrdCd_58id.equals("")){
         ctrdCd_58idTemp  = ctrdCd_58id;
        }
        String crltsNo_59id=  request.getParameter("crltsNo138");
            java.lang.String crltsNo_59idTemp = null;
        if(!crltsNo_59id.equals("")){
         crltsNo_59idTemp  = crltsNo_59id;
        }
        String itemCd_60id=  request.getParameter("itemCd140");
            java.lang.String itemCd_60idTemp = null;
        if(!itemCd_60id.equals("")){
         itemCd_60idTemp  = itemCd_60id;
        }
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsDtlsRequest_57id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsDtlsRequest" />
        <%
        cha1crlts1agecrlts1service1AgeCrltsDtlsRequest_57id.setCtrdCd(ctrdCd_58idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsRequest_57id.setCrltsNo(crltsNo_59idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsRequest_57id.setItemCd(itemCd_60idTemp);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsDtls_56id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsDtls" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsDtls_56id.setArg0(cha1crlts1agecrlts1service1AgeCrltsDtlsRequest_57id);
        String callBackURI_63id=  request.getParameter("callBackURI146");
            java.lang.String callBackURI_63idTemp = null;
        if(!callBackURI_63id.equals("")){
         callBackURI_63idTemp  = callBackURI_63id;
        }
        String requestMsgID_64id=  request.getParameter("requestMsgID148");
            java.lang.String requestMsgID_64idTemp = null;
        if(!requestMsgID_64id.equals("")){
         requestMsgID_64idTemp  = requestMsgID_64id;
        }
        String requestTime_65id=  request.getParameter("requestTime150");
            java.lang.String requestTime_65idTemp = null;
        if(!requestTime_65id.equals("")){
         requestTime_65idTemp  = requestTime_65id;
        }
        String serviceKey_66id=  request.getParameter("serviceKey152");
            java.lang.String serviceKey_66idTemp = null;
        if(!serviceKey_66id.equals("")){
         serviceKey_66idTemp  = serviceKey_66id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_62id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_62id.setCallBackURI(callBackURI_63idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_62id.setRequestMsgID(requestMsgID_64idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_62id.setRequestTime(requestTime_65idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_62id.setServiceKey(serviceKey_66idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_61id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_61id.value = iros1kma1ummd1iss1req1ComMsgHeader_62id;
        String crltsNmChcrt_70id=  request.getParameter("crltsNmChcrt160");
            java.lang.String crltsNmChcrt_70idTemp = null;
        if(!crltsNmChcrt_70id.equals("")){
         crltsNmChcrt_70idTemp  = crltsNmChcrt_70id;
        }
        String locplc_71id=  request.getParameter("locplc162");
            java.lang.String locplc_71idTemp = null;
        if(!locplc_71id.equals("")){
         locplc_71idTemp  = locplc_71id;
        }
        String imageYn_72id=  request.getParameter("imageYn164");
            java.lang.String imageYn_72idTemp = null;
        if(!imageYn_72id.equals("")){
         imageYn_72idTemp  = imageYn_72id;
        }
        String ctrdNm_73id=  request.getParameter("ctrdNm166");
            java.lang.String ctrdNm_73idTemp = null;
        if(!ctrdNm_73id.equals("")){
         ctrdNm_73idTemp  = ctrdNm_73id;
        }
        String signguNm_74id=  request.getParameter("signguNm168");
            java.lang.String signguNm_74idTemp = null;
        if(!signguNm_74id.equals("")){
         signguNm_74idTemp  = signguNm_74id;
        }
        String itemCd_75id=  request.getParameter("itemCd170");
            java.lang.String itemCd_75idTemp = null;
        if(!itemCd_75id.equals("")){
         itemCd_75idTemp  = itemCd_75id;
        }
        String yCnts_76id=  request.getParameter("yCnts172");
            java.lang.String yCnts_76idTemp = null;
        if(!yCnts_76id.equals("")){
         yCnts_76idTemp  = yCnts_76id;
        }
        String crltsNoNm_77id=  request.getParameter("crltsNoNm174");
            java.lang.String crltsNoNm_77idTemp = null;
        if(!crltsNoNm_77id.equals("")){
         crltsNoNm_77idTemp  = crltsNoNm_77id;
        }
        String ctrdCd_78id=  request.getParameter("ctrdCd176");
            java.lang.String ctrdCd_78idTemp = null;
        if(!ctrdCd_78id.equals("")){
         ctrdCd_78idTemp  = ctrdCd_78id;
        }
        String crltsDc_79id=  request.getParameter("crltsDc178");
            java.lang.String crltsDc_79idTemp = null;
        if(!crltsDc_79id.equals("")){
         crltsDc_79idTemp  = crltsDc_79id;
        }
        String crltsNo_80id=  request.getParameter("crltsNo180");
            java.lang.String crltsNo_80idTemp = null;
        if(!crltsNo_80id.equals("")){
         crltsNo_80idTemp  = crltsNo_80id;
        }
        String signguCd_81id=  request.getParameter("signguCd182");
            java.lang.String signguCd_81idTemp = null;
        if(!signguCd_81id.equals("")){
         signguCd_81idTemp  = signguCd_81id;
        }
        String imageUrl_82id=  request.getParameter("imageUrl184");
            java.lang.String imageUrl_82idTemp = null;
        if(!imageUrl_82id.equals("")){
         imageUrl_82idTemp  = imageUrl_82id;
        }
        String crltsNm_83id=  request.getParameter("crltsNm186");
            java.lang.String crltsNm_83idTemp = null;
        if(!crltsNm_83id.equals("")){
         crltsNm_83idTemp  = crltsNm_83id;
        }
        String xCnts_84id=  request.getParameter("xCnts188");
            java.lang.String xCnts_84idTemp = null;
        if(!xCnts_84id.equals("")){
         xCnts_84idTemp  = xCnts_84id;
        }
        String listImageUrl_85id=  request.getParameter("listImageUrl190");
            java.lang.String listImageUrl_85idTemp = null;
        if(!listImageUrl_85id.equals("")){
         listImageUrl_85idTemp  = listImageUrl_85id;
        }
        String itemNm_86id=  request.getParameter("itemNm192");
            java.lang.String itemNm_86idTemp = null;
        if(!itemNm_86id.equals("")){
         itemNm_86idTemp  = itemNm_86id;
        }
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id" scope="session" class="cha.crlts.agecrlts.service.AgeCrltsDtlsResponse" />
        <%
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCrltsNmChcrt(crltsNmChcrt_70idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setLocplc(locplc_71idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setImageYn(imageYn_72idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCtrdNm(ctrdNm_73idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setSignguNm(signguNm_74idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setItemCd(itemCd_75idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setYCnts(yCnts_76idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCrltsNoNm(crltsNoNm_77idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCtrdCd(ctrdCd_78idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCrltsDc(crltsDc_79idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCrltsNo(crltsNo_80idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setSignguCd(signguCd_81idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setImageUrl(imageUrl_82idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setCrltsNm(crltsNm_83idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setXCnts(xCnts_84idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setListImageUrl(listImageUrl_85idTemp);
        cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id.setItemNm(itemNm_86idTemp);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1GetAgeCrltsDtlsResponse_68id" scope="session" class="cha.crlts.agecrlts.service.GetAgeCrltsDtlsResponse" />
        <%
        cha1crlts1agecrlts1service1GetAgeCrltsDtlsResponse_68id.set_return(cha1crlts1agecrlts1service1AgeCrltsDtlsResponse_69id);
        %>
        <jsp:useBean id="cha1crlts1agecrlts1service1holders1GetAgeCrltsDtlsResponseHolder_67id" scope="session" class="cha.crlts.agecrlts.service.holders.GetAgeCrltsDtlsResponseHolder" />
        <%
        cha1crlts1agecrlts1service1holders1GetAgeCrltsDtlsResponseHolder_67id.value = cha1crlts1agecrlts1service1GetAgeCrltsDtlsResponse_68id;
        String callBackURI_89id=  request.getParameter("callBackURI198");
            java.lang.String callBackURI_89idTemp = null;
        if(!callBackURI_89id.equals("")){
         callBackURI_89idTemp  = callBackURI_89id;
        }
        String requestMsgID_90id=  request.getParameter("requestMsgID200");
            java.lang.String requestMsgID_90idTemp = null;
        if(!requestMsgID_90id.equals("")){
         requestMsgID_90idTemp  = requestMsgID_90id;
        }
        String requestTime_91id=  request.getParameter("requestTime202");
            java.lang.String requestTime_91idTemp = null;
        if(!requestTime_91id.equals("")){
         requestTime_91idTemp  = requestTime_91id;
        }
        String serviceKey_92id=  request.getParameter("serviceKey204");
            java.lang.String serviceKey_92idTemp = null;
        if(!serviceKey_92id.equals("")){
         serviceKey_92idTemp  = serviceKey_92id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_88id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_88id.setCallBackURI(callBackURI_89idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_88id.setRequestMsgID(requestMsgID_90idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_88id.setRequestTime(requestTime_91idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_88id.setServiceKey(serviceKey_92idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_87id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_87id.value = iros1kma1ummd1iss1req1ComMsgHeader_88id;
        sampleAgeCrltsServiceProxyid.getAgeCrltsDtls(cha1crlts1agecrlts1service1GetAgeCrltsDtls_56id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_61id,cha1crlts1agecrlts1service1holders1GetAgeCrltsDtlsResponseHolder_67id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_87id);
break;
}
} catch (Exception e) { 
%>
exception: <%= e %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>