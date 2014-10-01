<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleServiceProxyid" scope="session" class="VideoLibrary.ServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleServiceProxyid.setEndpoint(request.getParameter("endpoint"));
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
        java.lang.String getEndpoint2mtemp = sampleServiceProxyid.getEndpoint();
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
        sampleServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        VideoLibrary.Service getService10mtemp = sampleServiceProxyid.getService();
if(getService10mtemp == null){
%>
<%=getService10mtemp %>
<%
}else{
        if(getService10mtemp!= null){
        String tempreturnp11 = getService10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String emailId_1id=  request.getParameter("emailId78");
            java.lang.String emailId_1idTemp = null;
        if(!emailId_1id.equals("")){
         emailId_1idTemp  = emailId_1id;
        }
        String pwd_2id=  request.getParameter("pwd80");
            java.lang.String pwd_2idTemp = null;
        if(!pwd_2id.equals("")){
         pwd_2idTemp  = pwd_2id;
        }
        entity.Person login13mtemp = sampleServiceProxyid.login(emailId_1idTemp,pwd_2idTemp);
if(login13mtemp == null){
%>
<%=login13mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">zipCode:</TD>
<TD>
<%
if(login13mtemp != null){
%>
<%=login13mtemp.getZipCode()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">state:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typestate18 = login13mtemp.getState();
        String tempResultstate18 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestate18));
        %>
        <%= tempResultstate18 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">registrationDate:</TD>
<TD>
<%
if(login13mtemp != null){
java.util.Calendar typeregistrationDate20 = login13mtemp.getRegistrationDate();
        java.text.DateFormat dateFormatregistrationDate20 = java.text.DateFormat.getDateInstance();
        java.util.Date dateregistrationDate20 = typeregistrationDate20.getTime();
        String tempResultregistrationDate20 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatregistrationDate20.format(dateregistrationDate20));
        %>
        <%= tempResultregistrationDate20 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">emailId:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typeemailId22 = login13mtemp.getEmailId();
        String tempResultemailId22 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeemailId22));
        %>
        <%= tempResultemailId22 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transaction:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">fineAmount:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getFineAmount()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
java.lang.String typemovieName28 = tebece0.getMovieName();
        String tempResultmovieName28 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName28));
        %>
        <%= tempResultmovieName28 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">actualReturnDate:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeactualReturnDate30 = tebece0.getActualReturnDate();
        java.text.DateFormat dateFormatactualReturnDate30 = java.text.DateFormat.getDateInstance();
        java.util.Date dateactualReturnDate30 = typeactualReturnDate30.getTime();
        String tempResultactualReturnDate30 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatactualReturnDate30.format(dateactualReturnDate30));
        %>
        <%= tempResultactualReturnDate30 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movie:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieBanner:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieBanner34 = tebece1.getMovieBanner();
        String tempResultmovieBanner34 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieBanner34));
        %>
        <%= tempResultmovieBanner34 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieName36 = tebece1.getMovieName();
        String tempResultmovieName36 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName36));
        %>
        <%= tempResultmovieName36 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">users:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
entity.Person[] typeusers38 = tebece1.getUsers();
        String tempusers38 = null;
        if(typeusers38 != null){
        java.util.List listusers38= java.util.Arrays.asList(typeusers38);
        tempusers38 = listusers38.toString();
        }
        %>
        <%=tempusers38%>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">availableCopies:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getAvailableCopies()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieCategory:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieCategory42 = tebece1.getMovieCategory();
        String tempResultmovieCategory42 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieCategory42));
        %>
        <%= tempResultmovieCategory42 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieReleaseDate:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.util.Calendar typemovieReleaseDate44 = tebece1.getMovieReleaseDate();
        java.text.DateFormat dateFormatmovieReleaseDate44 = java.text.DateFormat.getDateInstance();
        java.util.Date datemovieReleaseDate44 = typemovieReleaseDate44.getTime();
        String tempResultmovieReleaseDate44 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatmovieReleaseDate44.format(datemovieReleaseDate44));
        %>
        <%= tempResultmovieReleaseDate44 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieId()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieRent:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieRent()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMembershipId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMovieId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">transactionId:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getTransactionId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">dueDate:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typedueDate56 = tebece0.getDueDate();
        java.text.DateFormat dateFormatdueDate56 = java.text.DateFormat.getDateInstance();
        java.util.Date datedueDate56 = typedueDate56.getTime();
        String tempResultdueDate56 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatdueDate56.format(datedueDate56));
        %>
        <%= tempResultdueDate56 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">issueDate:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions tebece0=login13mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeissueDate58 = tebece0.getIssueDate();
        java.text.DateFormat dateFormatissueDate58 = java.text.DateFormat.getDateInstance();
        java.util.Date dateissueDate58 = typeissueDate58.getTime();
        String tempResultissueDate58 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatissueDate58.format(dateissueDate58));
        %>
        <%= tempResultissueDate58 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">userType:</TD>
<TD>
<%
if(login13mtemp != null){
%>
<%=login13mtemp.getUserType()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">city:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typecity62 = login13mtemp.getCity();
        String tempResultcity62 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecity62));
        %>
        <%= tempResultcity62 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(login13mtemp != null){
%>
<%=login13mtemp.getMembershipId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastLogin:</TD>
<TD>
<%
if(login13mtemp != null){
java.util.Calendar typelastLogin66 = login13mtemp.getLastLogin();
        java.text.DateFormat dateFormatlastLogin66 = java.text.DateFormat.getDateInstance();
        java.util.Date datelastLogin66 = typelastLogin66.getTime();
        String tempResultlastLogin66 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatlastLogin66.format(datelastLogin66));
        %>
        <%= tempResultlastLogin66 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">address:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typeaddress68 = login13mtemp.getAddress();
        String tempResultaddress68 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeaddress68));
        %>
        <%= tempResultaddress68 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">firstName:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typefirstName70 = login13mtemp.getFirstName();
        String tempResultfirstName70 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typefirstName70));
        %>
        <%= tempResultfirstName70 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">password:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typepassword72 = login13mtemp.getPassword();
        String tempResultpassword72 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typepassword72));
        %>
        <%= tempResultpassword72 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transactions:</TD>
<TD>
<%
if(login13mtemp != null){
entity.Transactions[] typetransactions74 = login13mtemp.getTransactions();
        String temptransactions74 = null;
        if(typetransactions74 != null){
        java.util.List listtransactions74= java.util.Arrays.asList(typetransactions74);
        temptransactions74 = listtransactions74.toString();
        }
        %>
        <%=temptransactions74%>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastName:</TD>
<TD>
<%
if(login13mtemp != null){
java.lang.String typelastName76 = login13mtemp.getLastName();
        String tempResultlastName76 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typelastName76));
        %>
        <%= tempResultlastName76 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
case 82:
        gotMethod = true;
        String memberId_3id=  request.getParameter("memberId85");
        long memberId_3idTemp  = Long.parseLong(memberId_3id);
        String fName_4id=  request.getParameter("fName87");
            java.lang.String fName_4idTemp = null;
        if(!fName_4id.equals("")){
         fName_4idTemp  = fName_4id;
        }
        String lName_5id=  request.getParameter("lName89");
            java.lang.String lName_5idTemp = null;
        if(!lName_5id.equals("")){
         lName_5idTemp  = lName_5id;
        }
        String emailId_6id=  request.getParameter("emailId91");
            java.lang.String emailId_6idTemp = null;
        if(!emailId_6id.equals("")){
         emailId_6idTemp  = emailId_6id;
        }
        String pwd_7id=  request.getParameter("pwd93");
            java.lang.String pwd_7idTemp = null;
        if(!pwd_7id.equals("")){
         pwd_7idTemp  = pwd_7id;
        }
        String address_8id=  request.getParameter("address95");
            java.lang.String address_8idTemp = null;
        if(!address_8id.equals("")){
         address_8idTemp  = address_8id;
        }
        String city_9id=  request.getParameter("city97");
            java.lang.String city_9idTemp = null;
        if(!city_9id.equals("")){
         city_9idTemp  = city_9id;
        }
        String state_10id=  request.getParameter("state99");
            java.lang.String state_10idTemp = null;
        if(!state_10id.equals("")){
         state_10idTemp  = state_10id;
        }
        String zipCode_11id=  request.getParameter("zipCode101");
        long zipCode_11idTemp  = Long.parseLong(zipCode_11id);
        String userType_12id=  request.getParameter("userType103");
        int userType_12idTemp  = Integer.parseInt(userType_12id);
        String monthlySubFee_13id=  request.getParameter("monthlySubFee105");
        int monthlySubFee_13idTemp  = Integer.parseInt(monthlySubFee_13id);
        String totalOutstandingMovies_14id=  request.getParameter("totalOutstandingMovies107");
        int totalOutstandingMovies_14idTemp  = Integer.parseInt(totalOutstandingMovies_14id);
        String rentForMoviesIssued_15id=  request.getParameter("rentForMoviesIssued109");
        int rentForMoviesIssued_15idTemp  = Integer.parseInt(rentForMoviesIssued_15id);
        java.lang.String createMember82mtemp = sampleServiceProxyid.createMember(memberId_3idTemp,fName_4idTemp,lName_5idTemp,emailId_6idTemp,pwd_7idTemp,address_8idTemp,city_9idTemp,state_10idTemp,zipCode_11idTemp,userType_12idTemp,monthlySubFee_13idTemp,totalOutstandingMovies_14idTemp,rentForMoviesIssued_15idTemp);
if(createMember82mtemp == null){
%>
<%=createMember82mtemp %>
<%
}else{
        String tempResultreturnp83 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createMember82mtemp));
        %>
        <%= tempResultreturnp83 %>
        <%
}
break;
case 111:
        gotMethod = true;
        String membershipId_16id=  request.getParameter("membershipId176");
        long membershipId_16idTemp  = Long.parseLong(membershipId_16id);
        entity.Person memberFromID111mtemp = sampleServiceProxyid.memberFromID(membershipId_16idTemp);
if(memberFromID111mtemp == null){
%>
<%=memberFromID111mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">zipCode:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
%>
<%=memberFromID111mtemp.getZipCode()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">state:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typestate116 = memberFromID111mtemp.getState();
        String tempResultstate116 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestate116));
        %>
        <%= tempResultstate116 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">registrationDate:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.util.Calendar typeregistrationDate118 = memberFromID111mtemp.getRegistrationDate();
        java.text.DateFormat dateFormatregistrationDate118 = java.text.DateFormat.getDateInstance();
        java.util.Date dateregistrationDate118 = typeregistrationDate118.getTime();
        String tempResultregistrationDate118 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatregistrationDate118.format(dateregistrationDate118));
        %>
        <%= tempResultregistrationDate118 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">emailId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typeemailId120 = memberFromID111mtemp.getEmailId();
        String tempResultemailId120 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeemailId120));
        %>
        <%= tempResultemailId120 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transaction:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">fineAmount:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getFineAmount()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
java.lang.String typemovieName126 = tebece0.getMovieName();
        String tempResultmovieName126 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName126));
        %>
        <%= tempResultmovieName126 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">actualReturnDate:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeactualReturnDate128 = tebece0.getActualReturnDate();
        java.text.DateFormat dateFormatactualReturnDate128 = java.text.DateFormat.getDateInstance();
        java.util.Date dateactualReturnDate128 = typeactualReturnDate128.getTime();
        String tempResultactualReturnDate128 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatactualReturnDate128.format(dateactualReturnDate128));
        %>
        <%= tempResultactualReturnDate128 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movie:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieBanner:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieBanner132 = tebece1.getMovieBanner();
        String tempResultmovieBanner132 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieBanner132));
        %>
        <%= tempResultmovieBanner132 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieName134 = tebece1.getMovieName();
        String tempResultmovieName134 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName134));
        %>
        <%= tempResultmovieName134 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">users:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
entity.Person[] typeusers136 = tebece1.getUsers();
        String tempusers136 = null;
        if(typeusers136 != null){
        java.util.List listusers136= java.util.Arrays.asList(typeusers136);
        tempusers136 = listusers136.toString();
        }
        %>
        <%=tempusers136%>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">availableCopies:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getAvailableCopies()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieCategory:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieCategory140 = tebece1.getMovieCategory();
        String tempResultmovieCategory140 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieCategory140));
        %>
        <%= tempResultmovieCategory140 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieReleaseDate:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.util.Calendar typemovieReleaseDate142 = tebece1.getMovieReleaseDate();
        java.text.DateFormat dateFormatmovieReleaseDate142 = java.text.DateFormat.getDateInstance();
        java.util.Date datemovieReleaseDate142 = typemovieReleaseDate142.getTime();
        String tempResultmovieReleaseDate142 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatmovieReleaseDate142.format(datemovieReleaseDate142));
        %>
        <%= tempResultmovieReleaseDate142 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieId()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieRent:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieRent()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMembershipId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMovieId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">transactionId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getTransactionId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">dueDate:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typedueDate154 = tebece0.getDueDate();
        java.text.DateFormat dateFormatdueDate154 = java.text.DateFormat.getDateInstance();
        java.util.Date datedueDate154 = typedueDate154.getTime();
        String tempResultdueDate154 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatdueDate154.format(datedueDate154));
        %>
        <%= tempResultdueDate154 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">issueDate:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions tebece0=memberFromID111mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeissueDate156 = tebece0.getIssueDate();
        java.text.DateFormat dateFormatissueDate156 = java.text.DateFormat.getDateInstance();
        java.util.Date dateissueDate156 = typeissueDate156.getTime();
        String tempResultissueDate156 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatissueDate156.format(dateissueDate156));
        %>
        <%= tempResultissueDate156 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">userType:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
%>
<%=memberFromID111mtemp.getUserType()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">city:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typecity160 = memberFromID111mtemp.getCity();
        String tempResultcity160 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecity160));
        %>
        <%= tempResultcity160 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
%>
<%=memberFromID111mtemp.getMembershipId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastLogin:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.util.Calendar typelastLogin164 = memberFromID111mtemp.getLastLogin();
        java.text.DateFormat dateFormatlastLogin164 = java.text.DateFormat.getDateInstance();
        java.util.Date datelastLogin164 = typelastLogin164.getTime();
        String tempResultlastLogin164 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatlastLogin164.format(datelastLogin164));
        %>
        <%= tempResultlastLogin164 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">address:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typeaddress166 = memberFromID111mtemp.getAddress();
        String tempResultaddress166 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeaddress166));
        %>
        <%= tempResultaddress166 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">firstName:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typefirstName168 = memberFromID111mtemp.getFirstName();
        String tempResultfirstName168 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typefirstName168));
        %>
        <%= tempResultfirstName168 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">password:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typepassword170 = memberFromID111mtemp.getPassword();
        String tempResultpassword170 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typepassword170));
        %>
        <%= tempResultpassword170 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transactions:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
entity.Transactions[] typetransactions172 = memberFromID111mtemp.getTransactions();
        String temptransactions172 = null;
        if(typetransactions172 != null){
        java.util.List listtransactions172= java.util.Arrays.asList(typetransactions172);
        temptransactions172 = listtransactions172.toString();
        }
        %>
        <%=temptransactions172%>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastName:</TD>
<TD>
<%
if(memberFromID111mtemp != null){
java.lang.String typelastName174 = memberFromID111mtemp.getLastName();
        String tempResultlastName174 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typelastName174));
        %>
        <%= tempResultlastName174 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
case 178:
        gotMethod = true;
        String membershipId_17id=  request.getParameter("membershipId181");
        long membershipId_17idTemp  = Long.parseLong(membershipId_17id);
        String firstName_18id=  request.getParameter("firstName183");
            java.lang.String firstName_18idTemp = null;
        if(!firstName_18id.equals("")){
         firstName_18idTemp  = firstName_18id;
        }
        String lastName_19id=  request.getParameter("lastName185");
            java.lang.String lastName_19idTemp = null;
        if(!lastName_19id.equals("")){
         lastName_19idTemp  = lastName_19id;
        }
        String emailId_20id=  request.getParameter("emailId187");
            java.lang.String emailId_20idTemp = null;
        if(!emailId_20id.equals("")){
         emailId_20idTemp  = emailId_20id;
        }
        String password_21id=  request.getParameter("password189");
            java.lang.String password_21idTemp = null;
        if(!password_21id.equals("")){
         password_21idTemp  = password_21id;
        }
        String address_22id=  request.getParameter("address191");
            java.lang.String address_22idTemp = null;
        if(!address_22id.equals("")){
         address_22idTemp  = address_22id;
        }
        String city_23id=  request.getParameter("city193");
            java.lang.String city_23idTemp = null;
        if(!city_23id.equals("")){
         city_23idTemp  = city_23id;
        }
        String state_24id=  request.getParameter("state195");
            java.lang.String state_24idTemp = null;
        if(!state_24id.equals("")){
         state_24idTemp  = state_24id;
        }
        String zipCode_25id=  request.getParameter("zipCode197");
        long zipCode_25idTemp  = Long.parseLong(zipCode_25id);
        boolean updatePerson178mtemp = sampleServiceProxyid.updatePerson(membershipId_17idTemp,firstName_18idTemp,lastName_19idTemp,emailId_20idTemp,password_21idTemp,address_22idTemp,city_23idTemp,state_24idTemp,zipCode_25idTemp);
        String tempResultreturnp179 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updatePerson178mtemp));
        %>
        <%= tempResultreturnp179 %>
        <%
break;
case 199:
        gotMethod = true;
        entity.Movie[] displayMovies199mtemp = sampleServiceProxyid.displayMovies();
if(displayMovies199mtemp == null){
%>
<%=displayMovies199mtemp %>
<%
}else{
        String tempreturnp200 = null;
        if(displayMovies199mtemp != null){
        java.util.List listreturnp200= java.util.Arrays.asList(displayMovies199mtemp);
        tempreturnp200 = listreturnp200.toString();
        }
        %>
        <%=tempreturnp200%>
        <%
}
break;
case 202:
        gotMethod = true;
        entity.PremiumMembers[] displayPremiumMembers202mtemp = sampleServiceProxyid.displayPremiumMembers();
if(displayPremiumMembers202mtemp == null){
%>
<%=displayPremiumMembers202mtemp %>
<%
}else{
        String tempreturnp203 = null;
        if(displayPremiumMembers202mtemp != null){
        java.util.List listreturnp203= java.util.Arrays.asList(displayPremiumMembers202mtemp);
        tempreturnp203 = listreturnp203.toString();
        }
        %>
        <%=tempreturnp203%>
        <%
}
break;
case 205:
        gotMethod = true;
        entity.Person[] displayPerson205mtemp = sampleServiceProxyid.displayPerson();
if(displayPerson205mtemp == null){
%>
<%=displayPerson205mtemp %>
<%
}else{
        String tempreturnp206 = null;
        if(displayPerson205mtemp != null){
        java.util.List listreturnp206= java.util.Arrays.asList(displayPerson205mtemp);
        tempreturnp206 = listreturnp206.toString();
        }
        %>
        <%=tempreturnp206%>
        <%
}
break;
case 208:
        gotMethod = true;
        entity.SimpleMembers[] displaySimpleMembers208mtemp = sampleServiceProxyid.displaySimpleMembers();
if(displaySimpleMembers208mtemp == null){
%>
<%=displaySimpleMembers208mtemp %>
<%
}else{
        String tempreturnp209 = null;
        if(displaySimpleMembers208mtemp != null){
        java.util.List listreturnp209= java.util.Arrays.asList(displaySimpleMembers208mtemp);
        tempreturnp209 = listreturnp209.toString();
        }
        %>
        <%=tempreturnp209%>
        <%
}
break;
case 211:
        gotMethod = true;
        String movieName_26id=  request.getParameter("movieName214");
            java.lang.String movieName_26idTemp = null;
        if(!movieName_26id.equals("")){
         movieName_26idTemp  = movieName_26id;
        }
        String movieBanner_27id=  request.getParameter("movieBanner216");
            java.lang.String movieBanner_27idTemp = null;
        if(!movieBanner_27id.equals("")){
         movieBanner_27idTemp  = movieBanner_27id;
        }
        String movieReleaseDate_28id=  request.getParameter("movieReleaseDate218");
            java.util.Date movieReleaseDate_28idTemp = null;
        if(!movieReleaseDate_28id.equals("")){
        java.text.DateFormat dateFormatmovieReleaseDate218 = java.text.DateFormat.getDateInstance();
        movieReleaseDate_28idTemp= dateFormatmovieReleaseDate218.parse(movieReleaseDate_28id);
        }
        String movieRent_29id=  request.getParameter("movieRent220");
        float movieRent_29idTemp  = Float.parseFloat(movieRent_29id);
        String movieCategory_30id=  request.getParameter("movieCategory222");
            java.lang.String movieCategory_30idTemp = null;
        if(!movieCategory_30id.equals("")){
         movieCategory_30idTemp  = movieCategory_30id;
        }
        String availableCopies_31id=  request.getParameter("availableCopies224");
        int availableCopies_31idTemp  = Integer.parseInt(availableCopies_31id);
        boolean createMovie211mtemp = sampleServiceProxyid.createMovie(movieName_26idTemp,movieBanner_27idTemp,movieReleaseDate_28idTemp,movieRent_29idTemp,movieCategory_30idTemp,availableCopies_31idTemp);
        String tempResultreturnp212 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createMovie211mtemp));
        %>
        <%= tempResultreturnp212 %>
        <%
break;
case 226:
        gotMethod = true;
        String movieId_32id=  request.getParameter("movieId229");
        int movieId_32idTemp  = Integer.parseInt(movieId_32id);
        String movieName_33id=  request.getParameter("movieName231");
            java.lang.String movieName_33idTemp = null;
        if(!movieName_33id.equals("")){
         movieName_33idTemp  = movieName_33id;
        }
        boolean deleteMovie226mtemp = sampleServiceProxyid.deleteMovie(movieId_32idTemp,movieName_33idTemp);
        String tempResultreturnp227 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteMovie226mtemp));
        %>
        <%= tempResultreturnp227 %>
        <%
break;
case 233:
        gotMethod = true;
        String membershipId_34id=  request.getParameter("membershipId236");
        long membershipId_34idTemp  = Long.parseLong(membershipId_34id);
        boolean deleteMember233mtemp = sampleServiceProxyid.deleteMember(membershipId_34idTemp);
        String tempResultreturnp234 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteMember233mtemp));
        %>
        <%= tempResultreturnp234 %>
        <%
break;
case 238:
        gotMethod = true;
        String movieId_35id=  request.getParameter("movieId241");
        int movieId_35idTemp  = Integer.parseInt(movieId_35id);
        String movieName_36id=  request.getParameter("movieName243");
            java.lang.String movieName_36idTemp = null;
        if(!movieName_36id.equals("")){
         movieName_36idTemp  = movieName_36id;
        }
        String movieBanner_37id=  request.getParameter("movieBanner245");
            java.lang.String movieBanner_37idTemp = null;
        if(!movieBanner_37id.equals("")){
         movieBanner_37idTemp  = movieBanner_37id;
        }
        String movieReleaseDate_38id=  request.getParameter("movieReleaseDate247");
            java.util.Date movieReleaseDate_38idTemp = null;
        if(!movieReleaseDate_38id.equals("")){
        java.text.DateFormat dateFormatmovieReleaseDate247 = java.text.DateFormat.getDateInstance();
        movieReleaseDate_38idTemp= dateFormatmovieReleaseDate247.parse(movieReleaseDate_38id);
        }
        String movieRent_39id=  request.getParameter("movieRent249");
        float movieRent_39idTemp  = Float.parseFloat(movieRent_39id);
        String movieCategory_40id=  request.getParameter("movieCategory251");
            java.lang.String movieCategory_40idTemp = null;
        if(!movieCategory_40id.equals("")){
         movieCategory_40idTemp  = movieCategory_40id;
        }
        String availableCopies_41id=  request.getParameter("availableCopies253");
        int availableCopies_41idTemp  = Integer.parseInt(availableCopies_41id);
        boolean updateMovie238mtemp = sampleServiceProxyid.updateMovie(movieId_35idTemp,movieName_36idTemp,movieBanner_37idTemp,movieReleaseDate_38idTemp,movieRent_39idTemp,movieCategory_40idTemp,availableCopies_41idTemp);
        String tempResultreturnp239 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateMovie238mtemp));
        %>
        <%= tempResultreturnp239 %>
        <%
break;
case 255:
        gotMethod = true;
        String movieId_42id=  request.getParameter("movieId258");
        int movieId_42idTemp  = Integer.parseInt(movieId_42id);
        entity.Movie[] updateMovieRequest255mtemp = sampleServiceProxyid.updateMovieRequest(movieId_42idTemp);
if(updateMovieRequest255mtemp == null){
%>
<%=updateMovieRequest255mtemp %>
<%
}else{
        String tempreturnp256 = null;
        if(updateMovieRequest255mtemp != null){
        java.util.List listreturnp256= java.util.Arrays.asList(updateMovieRequest255mtemp);
        tempreturnp256 = listreturnp256.toString();
        }
        %>
        <%=tempreturnp256%>
        <%
}
break;
case 260:
        gotMethod = true;
        String attributeName_43id=  request.getParameter("attributeName263");
            java.lang.String attributeName_43idTemp = null;
        if(!attributeName_43id.equals("")){
         attributeName_43idTemp  = attributeName_43id;
        }
        String attributeValue_44id=  request.getParameter("attributeValue265");
            java.lang.String attributeValue_44idTemp = null;
        if(!attributeValue_44id.equals("")){
         attributeValue_44idTemp  = attributeValue_44id;
        }
        entity.Movie[] searchMovie260mtemp = sampleServiceProxyid.searchMovie(attributeName_43idTemp,attributeValue_44idTemp);
if(searchMovie260mtemp == null){
%>
<%=searchMovie260mtemp %>
<%
}else{
        String tempreturnp261 = null;
        if(searchMovie260mtemp != null){
        java.util.List listreturnp261= java.util.Arrays.asList(searchMovie260mtemp);
        tempreturnp261 = listreturnp261.toString();
        }
        %>
        <%=tempreturnp261%>
        <%
}
break;
case 267:
        gotMethod = true;
        String attributeName_45id=  request.getParameter("attributeName270");
            java.lang.String attributeName_45idTemp = null;
        if(!attributeName_45id.equals("")){
         attributeName_45idTemp  = attributeName_45id;
        }
        String attributeValue_46id=  request.getParameter("attributeValue272");
            java.lang.String attributeValue_46idTemp = null;
        if(!attributeValue_46id.equals("")){
         attributeValue_46idTemp  = attributeValue_46id;
        }
        entity.Person[] searchPerson267mtemp = sampleServiceProxyid.searchPerson(attributeName_45idTemp,attributeValue_46idTemp);
if(searchPerson267mtemp == null){
%>
<%=searchPerson267mtemp %>
<%
}else{
        String tempreturnp268 = null;
        if(searchPerson267mtemp != null){
        java.util.List listreturnp268= java.util.Arrays.asList(searchPerson267mtemp);
        tempreturnp268 = listreturnp268.toString();
        }
        %>
        <%=tempreturnp268%>
        <%
}
break;
case 274:
        gotMethod = true;
        String movieId_47id=  request.getParameter("movieId277");
        int movieId_47idTemp  = Integer.parseInt(movieId_47id);
        String memberId_48id=  request.getParameter("memberId279");
        long memberId_48idTemp  = Long.parseLong(memberId_48id);
        boolean addToCart274mtemp = sampleServiceProxyid.addToCart(movieId_47idTemp,memberId_48idTemp);
        String tempResultreturnp275 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(addToCart274mtemp));
        %>
        <%= tempResultreturnp275 %>
        <%
break;
case 281:
        gotMethod = true;
        String membershipId_49id=  request.getParameter("membershipId284");
        long membershipId_49idTemp  = Long.parseLong(membershipId_49id);
        String dueAmount_50id=  request.getParameter("dueAmount286");
        float dueAmount_50idTemp  = Float.parseFloat(dueAmount_50id);
        boolean dopayment281mtemp = sampleServiceProxyid.dopayment(membershipId_49idTemp,dueAmount_50idTemp);
        String tempResultreturnp282 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(dopayment281mtemp));
        %>
        <%= tempResultreturnp282 %>
        <%
break;
case 288:
        gotMethod = true;
        String movieId_51id=  request.getParameter("movieId307");
        int movieId_51idTemp  = Integer.parseInt(movieId_51id);
        entity.Movie getMovieInformationWithIssuedByUsers288mtemp = sampleServiceProxyid.getMovieInformationWithIssuedByUsers(movieId_51idTemp);
if(getMovieInformationWithIssuedByUsers288mtemp == null){
%>
<%=getMovieInformationWithIssuedByUsers288mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieBanner:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
java.lang.String typemovieBanner291 = getMovieInformationWithIssuedByUsers288mtemp.getMovieBanner();
        String tempResultmovieBanner291 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieBanner291));
        %>
        <%= tempResultmovieBanner291 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
java.lang.String typemovieName293 = getMovieInformationWithIssuedByUsers288mtemp.getMovieName();
        String tempResultmovieName293 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName293));
        %>
        <%= tempResultmovieName293 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">users:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
entity.Person[] typeusers295 = getMovieInformationWithIssuedByUsers288mtemp.getUsers();
        String tempusers295 = null;
        if(typeusers295 != null){
        java.util.List listusers295= java.util.Arrays.asList(typeusers295);
        tempusers295 = listusers295.toString();
        }
        %>
        <%=tempusers295%>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">availableCopies:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
%>
<%=getMovieInformationWithIssuedByUsers288mtemp.getAvailableCopies()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieCategory:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
java.lang.String typemovieCategory299 = getMovieInformationWithIssuedByUsers288mtemp.getMovieCategory();
        String tempResultmovieCategory299 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieCategory299));
        %>
        <%= tempResultmovieCategory299 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieReleaseDate:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
java.util.Calendar typemovieReleaseDate301 = getMovieInformationWithIssuedByUsers288mtemp.getMovieReleaseDate();
        java.text.DateFormat dateFormatmovieReleaseDate301 = java.text.DateFormat.getDateInstance();
        java.util.Date datemovieReleaseDate301 = typemovieReleaseDate301.getTime();
        String tempResultmovieReleaseDate301 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatmovieReleaseDate301.format(datemovieReleaseDate301));
        %>
        <%= tempResultmovieReleaseDate301 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
%>
<%=getMovieInformationWithIssuedByUsers288mtemp.getMovieId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">movieRent:</TD>
<TD>
<%
if(getMovieInformationWithIssuedByUsers288mtemp != null){
%>
<%=getMovieInformationWithIssuedByUsers288mtemp.getMovieRent()
%><%}%>
</TD>
</TABLE>
<%
}
break;
case 309:
        gotMethod = true;
        String membershipId_52id=  request.getParameter("membershipId312");
        long membershipId_52idTemp  = Long.parseLong(membershipId_52id);
        entity.Cart[] displayCart309mtemp = sampleServiceProxyid.displayCart(membershipId_52idTemp);
if(displayCart309mtemp == null){
%>
<%=displayCart309mtemp %>
<%
}else{
        String tempreturnp310 = null;
        if(displayCart309mtemp != null){
        java.util.List listreturnp310= java.util.Arrays.asList(displayCart309mtemp);
        tempreturnp310 = listreturnp310.toString();
        }
        %>
        <%=tempreturnp310%>
        <%
}
break;
case 314:
        gotMethod = true;
        String cartId_53id=  request.getParameter("cartId317");
        int cartId_53idTemp  = Integer.parseInt(cartId_53id);
        boolean removeMovieFromCart314mtemp = sampleServiceProxyid.removeMovieFromCart(cartId_53idTemp);
        String tempResultreturnp315 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(removeMovieFromCart314mtemp));
        %>
        <%= tempResultreturnp315 %>
        <%
break;
case 319:
        gotMethod = true;
        String membershipId_54id=  request.getParameter("membershipId322");
        long membershipId_54idTemp  = Long.parseLong(membershipId_54id);
        float isPaymentDue319mtemp = sampleServiceProxyid.isPaymentDue(membershipId_54idTemp);
        String tempResultreturnp320 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(isPaymentDue319mtemp));
        %>
        <%= tempResultreturnp320 %>
        <%
break;
case 324:
        gotMethod = true;
        String membershipId_55id=  request.getParameter("membershipId327");
        long membershipId_55idTemp  = Long.parseLong(membershipId_55id);
        boolean emptyCart324mtemp = sampleServiceProxyid.emptyCart(membershipId_55idTemp);
        String tempResultreturnp325 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(emptyCart324mtemp));
        %>
        <%= tempResultreturnp325 %>
        <%
break;
case 329:
        gotMethod = true;
        String membershipId_56id=  request.getParameter("membershipId332");
        long membershipId_56idTemp  = Long.parseLong(membershipId_56id);
        entity.Transactions[] issuedMovieList329mtemp = sampleServiceProxyid.issuedMovieList(membershipId_56idTemp);
if(issuedMovieList329mtemp == null){
%>
<%=issuedMovieList329mtemp %>
<%
}else{
        String tempreturnp330 = null;
        if(issuedMovieList329mtemp != null){
        java.util.List listreturnp330= java.util.Arrays.asList(issuedMovieList329mtemp);
        tempreturnp330 = listreturnp330.toString();
        }
        %>
        <%=tempreturnp330%>
        <%
}
break;
case 334:
        gotMethod = true;
        String movieId_57id=  request.getParameter("movieId337");
        int movieId_57idTemp  = Integer.parseInt(movieId_57id);
        String membershipId_58id=  request.getParameter("membershipId339");
        long membershipId_58idTemp  = Long.parseLong(membershipId_58id);
        String userType_59id=  request.getParameter("userType341");
        int userType_59idTemp  = Integer.parseInt(userType_59id);
        boolean returnMovie334mtemp = sampleServiceProxyid.returnMovie(movieId_57idTemp,membershipId_58idTemp,userType_59idTemp);
        String tempResultreturnp335 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(returnMovie334mtemp));
        %>
        <%= tempResultreturnp335 %>
        <%
break;
case 343:
        gotMethod = true;
        String membershipId_60id=  request.getParameter("membershipId408");
        long membershipId_60idTemp  = Long.parseLong(membershipId_60id);
        entity.Person getUserInformationWithIssuedMovies343mtemp = sampleServiceProxyid.getUserInformationWithIssuedMovies(membershipId_60idTemp);
if(getUserInformationWithIssuedMovies343mtemp == null){
%>
<%=getUserInformationWithIssuedMovies343mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">zipCode:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
%>
<%=getUserInformationWithIssuedMovies343mtemp.getZipCode()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">state:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typestate348 = getUserInformationWithIssuedMovies343mtemp.getState();
        String tempResultstate348 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestate348));
        %>
        <%= tempResultstate348 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">registrationDate:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.util.Calendar typeregistrationDate350 = getUserInformationWithIssuedMovies343mtemp.getRegistrationDate();
        java.text.DateFormat dateFormatregistrationDate350 = java.text.DateFormat.getDateInstance();
        java.util.Date dateregistrationDate350 = typeregistrationDate350.getTime();
        String tempResultregistrationDate350 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatregistrationDate350.format(dateregistrationDate350));
        %>
        <%= tempResultregistrationDate350 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">emailId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typeemailId352 = getUserInformationWithIssuedMovies343mtemp.getEmailId();
        String tempResultemailId352 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeemailId352));
        %>
        <%= tempResultemailId352 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transaction:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">fineAmount:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getFineAmount()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
java.lang.String typemovieName358 = tebece0.getMovieName();
        String tempResultmovieName358 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName358));
        %>
        <%= tempResultmovieName358 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">actualReturnDate:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeactualReturnDate360 = tebece0.getActualReturnDate();
        java.text.DateFormat dateFormatactualReturnDate360 = java.text.DateFormat.getDateInstance();
        java.util.Date dateactualReturnDate360 = typeactualReturnDate360.getTime();
        String tempResultactualReturnDate360 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatactualReturnDate360.format(dateactualReturnDate360));
        %>
        <%= tempResultactualReturnDate360 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movie:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieBanner:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieBanner364 = tebece1.getMovieBanner();
        String tempResultmovieBanner364 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieBanner364));
        %>
        <%= tempResultmovieBanner364 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieName:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieName366 = tebece1.getMovieName();
        String tempResultmovieName366 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieName366));
        %>
        <%= tempResultmovieName366 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">users:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
entity.Person[] typeusers368 = tebece1.getUsers();
        String tempusers368 = null;
        if(typeusers368 != null){
        java.util.List listusers368= java.util.Arrays.asList(typeusers368);
        tempusers368 = listusers368.toString();
        }
        %>
        <%=tempusers368%>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">availableCopies:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getAvailableCopies()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieCategory:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.lang.String typemovieCategory372 = tebece1.getMovieCategory();
        String tempResultmovieCategory372 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typemovieCategory372));
        %>
        <%= tempResultmovieCategory372 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieReleaseDate:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
java.util.Calendar typemovieReleaseDate374 = tebece1.getMovieReleaseDate();
        java.text.DateFormat dateFormatmovieReleaseDate374 = java.text.DateFormat.getDateInstance();
        java.util.Date datemovieReleaseDate374 = typemovieReleaseDate374.getTime();
        String tempResultmovieReleaseDate374 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatmovieReleaseDate374.format(datemovieReleaseDate374));
        %>
        <%= tempResultmovieReleaseDate374 %>
        <%
}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieId()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="0" ALIGN="LEFT">movieRent:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
entity.Movie tebece1=tebece0.getMovie();
if(tebece1 != null){
%>
<%=tebece1.getMovieRent()
%><%}}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMembershipId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">movieId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getMovieId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">transactionId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
%>
<%=tebece0.getTransactionId()
%><%}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">dueDate:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typedueDate386 = tebece0.getDueDate();
        java.text.DateFormat dateFormatdueDate386 = java.text.DateFormat.getDateInstance();
        java.util.Date datedueDate386 = typedueDate386.getTime();
        String tempResultdueDate386 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatdueDate386.format(datedueDate386));
        %>
        <%= tempResultdueDate386 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD WIDTH="5%"></TD>
<TD COLSPAN="1" ALIGN="LEFT">issueDate:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions tebece0=getUserInformationWithIssuedMovies343mtemp.getTransaction();
if(tebece0 != null){
java.util.Calendar typeissueDate388 = tebece0.getIssueDate();
        java.text.DateFormat dateFormatissueDate388 = java.text.DateFormat.getDateInstance();
        java.util.Date dateissueDate388 = typeissueDate388.getTime();
        String tempResultissueDate388 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatissueDate388.format(dateissueDate388));
        %>
        <%= tempResultissueDate388 %>
        <%
}}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">userType:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
%>
<%=getUserInformationWithIssuedMovies343mtemp.getUserType()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">city:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typecity392 = getUserInformationWithIssuedMovies343mtemp.getCity();
        String tempResultcity392 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecity392));
        %>
        <%= tempResultcity392 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">membershipId:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
%>
<%=getUserInformationWithIssuedMovies343mtemp.getMembershipId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastLogin:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.util.Calendar typelastLogin396 = getUserInformationWithIssuedMovies343mtemp.getLastLogin();
        java.text.DateFormat dateFormatlastLogin396 = java.text.DateFormat.getDateInstance();
        java.util.Date datelastLogin396 = typelastLogin396.getTime();
        String tempResultlastLogin396 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatlastLogin396.format(datelastLogin396));
        %>
        <%= tempResultlastLogin396 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">address:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typeaddress398 = getUserInformationWithIssuedMovies343mtemp.getAddress();
        String tempResultaddress398 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeaddress398));
        %>
        <%= tempResultaddress398 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">firstName:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typefirstName400 = getUserInformationWithIssuedMovies343mtemp.getFirstName();
        String tempResultfirstName400 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typefirstName400));
        %>
        <%= tempResultfirstName400 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">password:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typepassword402 = getUserInformationWithIssuedMovies343mtemp.getPassword();
        String tempResultpassword402 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typepassword402));
        %>
        <%= tempResultpassword402 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">transactions:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
entity.Transactions[] typetransactions404 = getUserInformationWithIssuedMovies343mtemp.getTransactions();
        String temptransactions404 = null;
        if(typetransactions404 != null){
        java.util.List listtransactions404= java.util.Arrays.asList(typetransactions404);
        temptransactions404 = listtransactions404.toString();
        }
        %>
        <%=temptransactions404%>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">lastName:</TD>
<TD>
<%
if(getUserInformationWithIssuedMovies343mtemp != null){
java.lang.String typelastName406 = getUserInformationWithIssuedMovies343mtemp.getLastName();
        String tempResultlastName406 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typelastName406));
        %>
        <%= tempResultlastName406 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
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