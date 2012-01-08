<%@page contentType="text/html;charset=UTF-8"%>
<HTML>
<HEAD>
<TITLE>Inputs</TITLE>
</HEAD>
<BODY>
<H1>Inputs</H1>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

boolean valid = true;

if(methodID != -1) methodID = Integer.parseInt(method);
switch (methodID){ 
case 2:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 5:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">endpoint:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="endpoint8" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 10:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 13:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">parameters:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">arg0:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo20" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNm22" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">nowPageNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="nowPageNo24" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm26" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm28" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd30" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">pageMg:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="pageMg32" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd34" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI40" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID42" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime44" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey46" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">result:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">_return:</TD>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader2:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI58" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID60" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime62" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey64" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 66:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">parameters:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">arg0:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo73" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNm75" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">yCntsBegin:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCntsBegin77" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">nowPageNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="nowPageNo79" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm81" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm83" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd85" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">yCntsEnd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCntsEnd87" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">pageMg:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="pageMg89" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">xCntsBegin:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCntsBegin91" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">xCntsEnd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCntsEnd93" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd95" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ageCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ageCd97" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI103" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID105" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime107" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey109" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">result:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">_return:</TD>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader2:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI121" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID123" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime125" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey127" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 129:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">parameters:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">arg0:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd136" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo138" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd140" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI146" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID148" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime150" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey152" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">result:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">_return:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNmChcrt:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNmChcrt160" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">locplc:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="locplc162" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">imageYn:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="imageYn164" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">ctrdNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdNm166" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">signguNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="signguNm168" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd170" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">yCnts:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCnts172" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm174" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd176" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsDc:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsDc178" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo180" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">signguCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="signguCd182" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">imageUrl:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="imageUrl184" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNm186" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">xCnts:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCnts188" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">listImageUrl:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="listImageUrl190" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm192" SIZE=20></TD>
</TR>
</TABLE>
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">comMsgHeader2:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">value:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">callBackURI:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI198" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID200" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime202" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey204" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 1111111111:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<TABLE>
<TR>
<TD COLSPAN="1" ALIGN="LEFT">URLString:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="url1111111111" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 1111111112:
valid = false;
%>
<FORM METHOD="POST" ACTION="Result.jsp" TARGET="result">
<INPUT TYPE="HIDDEN" NAME="method" VALUE="<%=method%>">
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
}
if (valid) {
%>
Select a method to test.
<%
}
%>

</BODY>
</HTML>
