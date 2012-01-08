<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleAreaCrltsServiceProxyid" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleAreaCrltsServiceProxyid.setEndpoint(request.getParameter("endpoint"));
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
        java.lang.String getEndpoint2mtemp = sampleAreaCrltsServiceProxyid.getEndpoint();
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
        sampleAreaCrltsServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        cha.crlts.areacrlts.service.AreaCrltsService getAreaCrltsService10mtemp = sampleAreaCrltsServiceProxyid.getAreaCrltsService();
if(getAreaCrltsService10mtemp == null){
%>
<%=getAreaCrltsService10mtemp %>
<%
}else{
        if(getAreaCrltsService10mtemp!= null){
        String tempreturnp11 = getAreaCrltsService10mtemp.toString();
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
        String yCntsBegin_5id=  request.getParameter("yCntsBegin24");
            java.lang.String yCntsBegin_5idTemp = null;
        if(!yCntsBegin_5id.equals("")){
         yCntsBegin_5idTemp  = yCntsBegin_5id;
        }
        String nowPageNo_6id=  request.getParameter("nowPageNo26");
        int nowPageNo_6idTemp  = Integer.parseInt(nowPageNo_6id);
        String crltsNoNm_7id=  request.getParameter("crltsNoNm28");
            java.lang.String crltsNoNm_7idTemp = null;
        if(!crltsNoNm_7id.equals("")){
         crltsNoNm_7idTemp  = crltsNoNm_7id;
        }
        String itemNm_8id=  request.getParameter("itemNm30");
            java.lang.String itemNm_8idTemp = null;
        if(!itemNm_8id.equals("")){
         itemNm_8idTemp  = itemNm_8id;
        }
        String ctrdCd_9id=  request.getParameter("ctrdCd32");
            java.lang.String ctrdCd_9idTemp = null;
        if(!ctrdCd_9id.equals("")){
         ctrdCd_9idTemp  = ctrdCd_9id;
        }
        String yCntsEnd_10id=  request.getParameter("yCntsEnd34");
            java.lang.String yCntsEnd_10idTemp = null;
        if(!yCntsEnd_10id.equals("")){
         yCntsEnd_10idTemp  = yCntsEnd_10id;
        }
        String pageMg_11id=  request.getParameter("pageMg36");
        int pageMg_11idTemp  = Integer.parseInt(pageMg_11id);
        String xCntsBegin_12id=  request.getParameter("xCntsBegin38");
            java.lang.String xCntsBegin_12idTemp = null;
        if(!xCntsBegin_12id.equals("")){
         xCntsBegin_12idTemp  = xCntsBegin_12id;
        }
        String xCntsEnd_13id=  request.getParameter("xCntsEnd40");
            java.lang.String xCntsEnd_13idTemp = null;
        if(!xCntsEnd_13id.equals("")){
         xCntsEnd_13idTemp  = xCntsEnd_13id;
        }
        String itemCd_14id=  request.getParameter("itemCd42");
            java.lang.String itemCd_14idTemp = null;
        if(!itemCd_14id.equals("")){
         itemCd_14idTemp  = itemCd_14id;
        }
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsListRequest_2id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsListRequest" />
        <%
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setCrltsNo(crltsNo_3idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setCrltsNm(crltsNm_4idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setYCntsBegin(yCntsBegin_5idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setNowPageNo(nowPageNo_6idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setCrltsNoNm(crltsNoNm_7idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setItemNm(itemNm_8idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setCtrdCd(ctrdCd_9idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setYCntsEnd(yCntsEnd_10idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setPageMg(pageMg_11idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setXCntsBegin(xCntsBegin_12idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setXCntsEnd(xCntsEnd_13idTemp);
        cha1crlts1areacrlts1service1AreaCrltsListRequest_2id.setItemCd(itemCd_14idTemp);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsList_1id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsList" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsList_1id.setArg0(cha1crlts1areacrlts1service1AreaCrltsListRequest_2id);
        String callBackURI_17id=  request.getParameter("callBackURI48");
            java.lang.String callBackURI_17idTemp = null;
        if(!callBackURI_17id.equals("")){
         callBackURI_17idTemp  = callBackURI_17id;
        }
        String requestMsgID_18id=  request.getParameter("requestMsgID50");
            java.lang.String requestMsgID_18idTemp = null;
        if(!requestMsgID_18id.equals("")){
         requestMsgID_18idTemp  = requestMsgID_18id;
        }
        String requestTime_19id=  request.getParameter("requestTime52");
            java.lang.String requestTime_19idTemp = null;
        if(!requestTime_19id.equals("")){
         requestTime_19idTemp  = requestTime_19id;
        }
        String serviceKey_20id=  request.getParameter("serviceKey54");
            java.lang.String serviceKey_20idTemp = null;
        if(!serviceKey_20id.equals("")){
         serviceKey_20idTemp  = serviceKey_20id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_16id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_16id.setCallBackURI(callBackURI_17idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_16id.setRequestMsgID(requestMsgID_18idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_16id.setRequestTime(requestTime_19idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_16id.setServiceKey(serviceKey_20idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_15id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_15id.value = iros1kma1ummd1iss1req1ComMsgHeader_16id;
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsListResponse_23id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsListResponse" />
        <%
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsListResponse_22id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsListResponse" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsListResponse_22id.set_return(cha1crlts1areacrlts1service1AreaCrltsListResponse_23id);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1holders1GetAreaCrltsListResponseHolder_21id" scope="session" class="cha.crlts.areacrlts.service.holders.GetAreaCrltsListResponseHolder" />
        <%
        cha1crlts1areacrlts1service1holders1GetAreaCrltsListResponseHolder_21id.value = cha1crlts1areacrlts1service1GetAreaCrltsListResponse_22id;
        String callBackURI_26id=  request.getParameter("callBackURI66");
            java.lang.String callBackURI_26idTemp = null;
        if(!callBackURI_26id.equals("")){
         callBackURI_26idTemp  = callBackURI_26id;
        }
        String requestMsgID_27id=  request.getParameter("requestMsgID68");
            java.lang.String requestMsgID_27idTemp = null;
        if(!requestMsgID_27id.equals("")){
         requestMsgID_27idTemp  = requestMsgID_27id;
        }
        String requestTime_28id=  request.getParameter("requestTime70");
            java.lang.String requestTime_28idTemp = null;
        if(!requestTime_28id.equals("")){
         requestTime_28idTemp  = requestTime_28id;
        }
        String serviceKey_29id=  request.getParameter("serviceKey72");
            java.lang.String serviceKey_29idTemp = null;
        if(!serviceKey_29id.equals("")){
         serviceKey_29idTemp  = serviceKey_29id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_25id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_25id.setCallBackURI(callBackURI_26idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_25id.setRequestMsgID(requestMsgID_27idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_25id.setRequestTime(requestTime_28idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_25id.setServiceKey(serviceKey_29idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_24id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_24id.value = iros1kma1ummd1iss1req1ComMsgHeader_25id;
        sampleAreaCrltsServiceProxyid.getAreaCrltsList(cha1crlts1areacrlts1service1GetAreaCrltsList_1id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_15id,cha1crlts1areacrlts1service1holders1GetAreaCrltsListResponseHolder_21id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_24id);
break;
case 74:
        gotMethod = true;
        String crltsNo_32id=  request.getParameter("crltsNo81");
            java.lang.String crltsNo_32idTemp = null;
        if(!crltsNo_32id.equals("")){
         crltsNo_32idTemp  = crltsNo_32id;
        }
        String crltsNm_33id=  request.getParameter("crltsNm83");
            java.lang.String crltsNm_33idTemp = null;
        if(!crltsNm_33id.equals("")){
         crltsNm_33idTemp  = crltsNm_33id;
        }
        String nowPageNo_34id=  request.getParameter("nowPageNo85");
        int nowPageNo_34idTemp  = Integer.parseInt(nowPageNo_34id);
        String crltsNoNm_35id=  request.getParameter("crltsNoNm87");
            java.lang.String crltsNoNm_35idTemp = null;
        if(!crltsNoNm_35id.equals("")){
         crltsNoNm_35idTemp  = crltsNoNm_35id;
        }
        String itemNm_36id=  request.getParameter("itemNm89");
            java.lang.String itemNm_36idTemp = null;
        if(!itemNm_36id.equals("")){
         itemNm_36idTemp  = itemNm_36id;
        }
        String ctrdCd_37id=  request.getParameter("ctrdCd91");
            java.lang.String ctrdCd_37idTemp = null;
        if(!ctrdCd_37id.equals("")){
         ctrdCd_37idTemp  = ctrdCd_37id;
        }
        String pageMg_38id=  request.getParameter("pageMg93");
        int pageMg_38idTemp  = Integer.parseInt(pageMg_38id);
        String itemCd_39id=  request.getParameter("itemCd95");
            java.lang.String itemCd_39idTemp = null;
        if(!itemCd_39id.equals("")){
         itemCd_39idTemp  = itemCd_39id;
        }
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsImageRequest" />
        <%
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setCrltsNo(crltsNo_32idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setCrltsNm(crltsNm_33idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setNowPageNo(nowPageNo_34idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setCrltsNoNm(crltsNoNm_35idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setItemNm(itemNm_36idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setCtrdCd(ctrdCd_37idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setPageMg(pageMg_38idTemp);
        cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id.setItemCd(itemCd_39idTemp);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsImage_30id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsImage" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsImage_30id.setArg0(cha1crlts1areacrlts1service1AreaCrltsImageRequest_31id);
        String callBackURI_42id=  request.getParameter("callBackURI101");
            java.lang.String callBackURI_42idTemp = null;
        if(!callBackURI_42id.equals("")){
         callBackURI_42idTemp  = callBackURI_42id;
        }
        String requestMsgID_43id=  request.getParameter("requestMsgID103");
            java.lang.String requestMsgID_43idTemp = null;
        if(!requestMsgID_43id.equals("")){
         requestMsgID_43idTemp  = requestMsgID_43id;
        }
        String requestTime_44id=  request.getParameter("requestTime105");
            java.lang.String requestTime_44idTemp = null;
        if(!requestTime_44id.equals("")){
         requestTime_44idTemp  = requestTime_44id;
        }
        String serviceKey_45id=  request.getParameter("serviceKey107");
            java.lang.String serviceKey_45idTemp = null;
        if(!serviceKey_45id.equals("")){
         serviceKey_45idTemp  = serviceKey_45id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_41id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_41id.setCallBackURI(callBackURI_42idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_41id.setRequestMsgID(requestMsgID_43idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_41id.setRequestTime(requestTime_44idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_41id.setServiceKey(serviceKey_45idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_40id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_40id.value = iros1kma1ummd1iss1req1ComMsgHeader_41id;
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsImageResponse_48id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsImageResponse" />
        <%
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsImageResponse_47id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsImageResponse" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsImageResponse_47id.set_return(cha1crlts1areacrlts1service1AreaCrltsImageResponse_48id);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1holders1GetAreaCrltsImageResponseHolder_46id" scope="session" class="cha.crlts.areacrlts.service.holders.GetAreaCrltsImageResponseHolder" />
        <%
        cha1crlts1areacrlts1service1holders1GetAreaCrltsImageResponseHolder_46id.value = cha1crlts1areacrlts1service1GetAreaCrltsImageResponse_47id;
        String callBackURI_51id=  request.getParameter("callBackURI119");
            java.lang.String callBackURI_51idTemp = null;
        if(!callBackURI_51id.equals("")){
         callBackURI_51idTemp  = callBackURI_51id;
        }
        String requestMsgID_52id=  request.getParameter("requestMsgID121");
            java.lang.String requestMsgID_52idTemp = null;
        if(!requestMsgID_52id.equals("")){
         requestMsgID_52idTemp  = requestMsgID_52id;
        }
        String requestTime_53id=  request.getParameter("requestTime123");
            java.lang.String requestTime_53idTemp = null;
        if(!requestTime_53id.equals("")){
         requestTime_53idTemp  = requestTime_53id;
        }
        String serviceKey_54id=  request.getParameter("serviceKey125");
            java.lang.String serviceKey_54idTemp = null;
        if(!serviceKey_54id.equals("")){
         serviceKey_54idTemp  = serviceKey_54id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_50id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_50id.setCallBackURI(callBackURI_51idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_50id.setRequestMsgID(requestMsgID_52idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_50id.setRequestTime(requestTime_53idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_50id.setServiceKey(serviceKey_54idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_49id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_49id.value = iros1kma1ummd1iss1req1ComMsgHeader_50id;
        sampleAreaCrltsServiceProxyid.getAreaCrltsImage(cha1crlts1areacrlts1service1GetAreaCrltsImage_30id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_40id,cha1crlts1areacrlts1service1holders1GetAreaCrltsImageResponseHolder_46id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_49id);
break;
case 127:
        gotMethod = true;
        String ctrdCd_57id=  request.getParameter("ctrdCd134");
            java.lang.String ctrdCd_57idTemp = null;
        if(!ctrdCd_57id.equals("")){
         ctrdCd_57idTemp  = ctrdCd_57id;
        }
        String crltsNo_58id=  request.getParameter("crltsNo136");
            java.lang.String crltsNo_58idTemp = null;
        if(!crltsNo_58id.equals("")){
         crltsNo_58idTemp  = crltsNo_58id;
        }
        String itemCd_59id=  request.getParameter("itemCd138");
            java.lang.String itemCd_59idTemp = null;
        if(!itemCd_59id.equals("")){
         itemCd_59idTemp  = itemCd_59id;
        }
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsDtlsRequest_56id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsDtlsRequest" />
        <%
        cha1crlts1areacrlts1service1AreaCrltsDtlsRequest_56id.setCtrdCd(ctrdCd_57idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsRequest_56id.setCrltsNo(crltsNo_58idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsRequest_56id.setItemCd(itemCd_59idTemp);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsDtls_55id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsDtls" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsDtls_55id.setArg0(cha1crlts1areacrlts1service1AreaCrltsDtlsRequest_56id);
        String callBackURI_62id=  request.getParameter("callBackURI144");
            java.lang.String callBackURI_62idTemp = null;
        if(!callBackURI_62id.equals("")){
         callBackURI_62idTemp  = callBackURI_62id;
        }
        String requestMsgID_63id=  request.getParameter("requestMsgID146");
            java.lang.String requestMsgID_63idTemp = null;
        if(!requestMsgID_63id.equals("")){
         requestMsgID_63idTemp  = requestMsgID_63id;
        }
        String requestTime_64id=  request.getParameter("requestTime148");
            java.lang.String requestTime_64idTemp = null;
        if(!requestTime_64id.equals("")){
         requestTime_64idTemp  = requestTime_64id;
        }
        String serviceKey_65id=  request.getParameter("serviceKey150");
            java.lang.String serviceKey_65idTemp = null;
        if(!serviceKey_65id.equals("")){
         serviceKey_65idTemp  = serviceKey_65id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_61id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_61id.setCallBackURI(callBackURI_62idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_61id.setRequestMsgID(requestMsgID_63idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_61id.setRequestTime(requestTime_64idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_61id.setServiceKey(serviceKey_65idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_60id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_60id.value = iros1kma1ummd1iss1req1ComMsgHeader_61id;
        String crltsNmChcrt_69id=  request.getParameter("crltsNmChcrt158");
            java.lang.String crltsNmChcrt_69idTemp = null;
        if(!crltsNmChcrt_69id.equals("")){
         crltsNmChcrt_69idTemp  = crltsNmChcrt_69id;
        }
        String locplc_70id=  request.getParameter("locplc160");
            java.lang.String locplc_70idTemp = null;
        if(!locplc_70id.equals("")){
         locplc_70idTemp  = locplc_70id;
        }
        String imageYn_71id=  request.getParameter("imageYn162");
            java.lang.String imageYn_71idTemp = null;
        if(!imageYn_71id.equals("")){
         imageYn_71idTemp  = imageYn_71id;
        }
        String ctrdNm_72id=  request.getParameter("ctrdNm164");
            java.lang.String ctrdNm_72idTemp = null;
        if(!ctrdNm_72id.equals("")){
         ctrdNm_72idTemp  = ctrdNm_72id;
        }
        String signguNm_73id=  request.getParameter("signguNm166");
            java.lang.String signguNm_73idTemp = null;
        if(!signguNm_73id.equals("")){
         signguNm_73idTemp  = signguNm_73id;
        }
        String itemCd_74id=  request.getParameter("itemCd168");
            java.lang.String itemCd_74idTemp = null;
        if(!itemCd_74id.equals("")){
         itemCd_74idTemp  = itemCd_74id;
        }
        String yCnts_75id=  request.getParameter("yCnts170");
            java.lang.String yCnts_75idTemp = null;
        if(!yCnts_75id.equals("")){
         yCnts_75idTemp  = yCnts_75id;
        }
        String crltsNoNm_76id=  request.getParameter("crltsNoNm172");
            java.lang.String crltsNoNm_76idTemp = null;
        if(!crltsNoNm_76id.equals("")){
         crltsNoNm_76idTemp  = crltsNoNm_76id;
        }
        String ctrdCd_77id=  request.getParameter("ctrdCd174");
            java.lang.String ctrdCd_77idTemp = null;
        if(!ctrdCd_77id.equals("")){
         ctrdCd_77idTemp  = ctrdCd_77id;
        }
        String crltsDc_78id=  request.getParameter("crltsDc176");
            java.lang.String crltsDc_78idTemp = null;
        if(!crltsDc_78id.equals("")){
         crltsDc_78idTemp  = crltsDc_78id;
        }
        String crltsNo_79id=  request.getParameter("crltsNo178");
            java.lang.String crltsNo_79idTemp = null;
        if(!crltsNo_79id.equals("")){
         crltsNo_79idTemp  = crltsNo_79id;
        }
        String signguCd_80id=  request.getParameter("signguCd180");
            java.lang.String signguCd_80idTemp = null;
        if(!signguCd_80id.equals("")){
         signguCd_80idTemp  = signguCd_80id;
        }
        String imageUrl_81id=  request.getParameter("imageUrl182");
            java.lang.String imageUrl_81idTemp = null;
        if(!imageUrl_81id.equals("")){
         imageUrl_81idTemp  = imageUrl_81id;
        }
        String crltsNm_82id=  request.getParameter("crltsNm184");
            java.lang.String crltsNm_82idTemp = null;
        if(!crltsNm_82id.equals("")){
         crltsNm_82idTemp  = crltsNm_82id;
        }
        String xCnts_83id=  request.getParameter("xCnts186");
            java.lang.String xCnts_83idTemp = null;
        if(!xCnts_83id.equals("")){
         xCnts_83idTemp  = xCnts_83id;
        }
        String listImageUrl_84id=  request.getParameter("listImageUrl188");
            java.lang.String listImageUrl_84idTemp = null;
        if(!listImageUrl_84id.equals("")){
         listImageUrl_84idTemp  = listImageUrl_84id;
        }
        String itemNm_85id=  request.getParameter("itemNm190");
            java.lang.String itemNm_85idTemp = null;
        if(!itemNm_85id.equals("")){
         itemNm_85idTemp  = itemNm_85id;
        }
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id" scope="session" class="cha.crlts.areacrlts.service.AreaCrltsDtlsResponse" />
        <%
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCrltsNmChcrt(crltsNmChcrt_69idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setLocplc(locplc_70idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setImageYn(imageYn_71idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCtrdNm(ctrdNm_72idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setSignguNm(signguNm_73idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setItemCd(itemCd_74idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setYCnts(yCnts_75idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCrltsNoNm(crltsNoNm_76idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCtrdCd(ctrdCd_77idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCrltsDc(crltsDc_78idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCrltsNo(crltsNo_79idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setSignguCd(signguCd_80idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setImageUrl(imageUrl_81idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setCrltsNm(crltsNm_82idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setXCnts(xCnts_83idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setListImageUrl(listImageUrl_84idTemp);
        cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id.setItemNm(itemNm_85idTemp);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1GetAreaCrltsDtlsResponse_67id" scope="session" class="cha.crlts.areacrlts.service.GetAreaCrltsDtlsResponse" />
        <%
        cha1crlts1areacrlts1service1GetAreaCrltsDtlsResponse_67id.set_return(cha1crlts1areacrlts1service1AreaCrltsDtlsResponse_68id);
        %>
        <jsp:useBean id="cha1crlts1areacrlts1service1holders1GetAreaCrltsDtlsResponseHolder_66id" scope="session" class="cha.crlts.areacrlts.service.holders.GetAreaCrltsDtlsResponseHolder" />
        <%
        cha1crlts1areacrlts1service1holders1GetAreaCrltsDtlsResponseHolder_66id.value = cha1crlts1areacrlts1service1GetAreaCrltsDtlsResponse_67id;
        String callBackURI_88id=  request.getParameter("callBackURI196");
            java.lang.String callBackURI_88idTemp = null;
        if(!callBackURI_88id.equals("")){
         callBackURI_88idTemp  = callBackURI_88id;
        }
        String requestMsgID_89id=  request.getParameter("requestMsgID198");
            java.lang.String requestMsgID_89idTemp = null;
        if(!requestMsgID_89id.equals("")){
         requestMsgID_89idTemp  = requestMsgID_89id;
        }
        String requestTime_90id=  request.getParameter("requestTime200");
            java.lang.String requestTime_90idTemp = null;
        if(!requestTime_90id.equals("")){
         requestTime_90idTemp  = requestTime_90id;
        }
        String serviceKey_91id=  request.getParameter("serviceKey202");
            java.lang.String serviceKey_91idTemp = null;
        if(!serviceKey_91id.equals("")){
         serviceKey_91idTemp  = serviceKey_91id;
        }
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1ComMsgHeader_87id" scope="session" class="iros.kma.ummd.iss.req.ComMsgHeader" />
        <%
        iros1kma1ummd1iss1req1ComMsgHeader_87id.setCallBackURI(callBackURI_88idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_87id.setRequestMsgID(requestMsgID_89idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_87id.setRequestTime(requestTime_90idTemp);
        iros1kma1ummd1iss1req1ComMsgHeader_87id.setServiceKey(serviceKey_91idTemp);
        %>
        <jsp:useBean id="iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_86id" scope="session" class="iros.kma.ummd.iss.req.holders.ComMsgHeaderHolder" />
        <%
        iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_86id.value = iros1kma1ummd1iss1req1ComMsgHeader_87id;
        sampleAreaCrltsServiceProxyid.getAreaCrltsDtls(cha1crlts1areacrlts1service1GetAreaCrltsDtls_55id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_60id,cha1crlts1areacrlts1service1holders1GetAreaCrltsDtlsResponseHolder_66id,iros1kma1ummd1iss1req1holders1ComMsgHeaderHolder_86id);
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