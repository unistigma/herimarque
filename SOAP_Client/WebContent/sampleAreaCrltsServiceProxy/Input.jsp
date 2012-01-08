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
<TD COLSPAN="1" ALIGN="LEFT">yCntsBegin:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCntsBegin24" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">nowPageNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="nowPageNo26" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm28" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm30" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd32" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">yCntsEnd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCntsEnd34" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">pageMg:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="pageMg36" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">xCntsBegin:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCntsBegin38" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">xCntsEnd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCntsEnd40" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd42" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI48" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID50" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime52" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey54" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI66" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID68" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime70" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey72" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 74:
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo81" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNm83" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">nowPageNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="nowPageNo85" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm87" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm89" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd91" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">pageMg:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="pageMg93" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd95" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI101" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID103" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime105" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey107" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI119" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID121" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime123" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey125" SIZE=20></TD>
</TR>
</TABLE>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Invoke">
<INPUT TYPE="RESET" VALUE="Clear">
</FORM>
<%
break;
case 127:
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd134" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo136" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd138" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI144" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID146" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime148" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey150" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNmChcrt158" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">locplc:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="locplc160" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">imageYn:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="imageYn162" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">ctrdNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdNm164" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">signguNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="signguNm166" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">itemCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemCd168" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">yCnts:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="yCnts170" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNoNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNoNm172" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">ctrdCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="ctrdCd174" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsDc:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsDc176" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNo:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNo178" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">signguCd:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="signguCd180" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">imageUrl:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="imageUrl182" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">crltsNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="crltsNm184" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">xCnts:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="xCnts186" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">listImageUrl:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="listImageUrl188" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-2" ALIGN="LEFT">itemNm:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="itemNm190" SIZE=20></TD>
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
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="callBackURI196" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestMsgID:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestMsgID198" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">requestTime:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="requestTime200" SIZE=20></TD>
</TR>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="-1" ALIGN="LEFT">serviceKey:</TD>
<TD ALIGN="left"><INPUT TYPE="TEXT" NAME="serviceKey202" SIZE=20></TD>
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
