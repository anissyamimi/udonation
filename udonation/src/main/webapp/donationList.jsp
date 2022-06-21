<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <style>
          @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap');
table, td, th {
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th {
  height: 70px;
}
*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;}
	
	body{
	font-family: Arial, Helvetica, sans-serif;
	background-color: rgb(205, 234, 248);
    }
	

	a, a:hover{
	text-decoration: none;}
	
	a.button, input.button {
    background-color: #37a0a0;
    padding: 10px 15px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    border-radius: 25px;
    display: inline;
    margin-left: 20px;
    color: black;
    font-size: 15px;
    
    }
	.bottom-bar{
	display: flex;
	align-items: center;
	justify-content: space-betwwen;
	padding: 10px 25px;
	background: #BBE9FE;}
	
	.bottom-bar span{
	color: #FFFFFF;}
	
	.bottom-bar ul{
	list-style: none;
	display: flex;}
	
	.bottom-bar li{
	margin: 0px 5px;}
	
	.bottom-bar a{
	color: #FFF;}
	
	.bottom-bar a:hover{
	color: #222;}
	
	nav{
	display: block;
	position: relative;
	padding: 15px 20px;}
	
	nav a:hover{
	color: #045782;}
	
	.Logo{
	flex: 1;}
	
	.Logo a{
	display: flex;
	align-items: center;
	font-size: 20px;}
	
	.logo img{
	width: 40px;
	margin-right: 10px;}
	
	ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #BBE9FE;
	width:100%;}
	
	li {
	float: right;}
	
	li a {
	display: block;
	color: black;
	font-weight: bold;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;}
	
	li a:hover {
	background-color: #000000;}
	  
	nav{
	display: block;
	position: relative;
	padding: 15px 20px;}

        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box;}
        
        /* Set a style for all buttons */
        .button {
          background-color: #37a0a0;
          padding: 20px 25px;
          margin: 8px 0;
          border: none;
          cursor: pointer;
          border-radius: 25px;
          padding: 10px;
          display: block;
          margin-left: auto;
          margin-right: 20px;
        }
        
        button:hover {
          opacity:1;
        }

        img:hover {
          box-shadow: 0 0 10px 10px rgba(0, 140, 186, 0.5);
        }

        .img {
          display: block;
          margin-left: auto;
          margin-right: auto;
        }
        a{
        margin-left:10px;
        }
</style>
<head>
<meta charset="UTF-8">
<title>Create Donation Page</title>
</head>
<body>
	<nav>
		<ul>
		  <div class = "logo">
		  <img src = "Logo.jpg" alt = "logo">MASJID SULTAN AZLAN SHAH	
			<li><a href="#" onclick="document.location='LoginAccount.html'">LOGOUT</a>
			<li><a href="#" onclick="document.location='ViewAccount.html' ">AKAUN</a>
			<li><a href="#" onclick="document.location='buttonApplicationCommittee.html'">PERMOHONAN</a>
			<li><a href="#" onclick="document.location='createDonation2.html' ">BANTUAN</a>
		  </div>
		</ul>
	  </nav>
  <br>
  <table class="table table-bordered">
				<thead>
					<tr>
						<th>NAMA SUMBANGAN</th>
						<th>PENERANGAN DERMA DAN BUTIRAN KELAYAKAN</th>
						<th>TARIKH BUKA</th>
						<th>TARIKH TUTUP</th>
						<th></th>
						<th>TINDAKAN</th>

					</tr>
				</thead>
				<tbody>
				<tr>
						    <td><a href="<%=request.getContextPath()%>/donationDetails.jsp" >Foodbank</a></td>
							<td>Anda layak untuk memohon jika anda adalah salah seorang ahli kariah masjid/penerima adalah terdiri daripada ketua keluarga/Asnaf/Pendapatan isi 
							rumah kurang RM1000 dan berdasarkan maklumat asnaf. Penuntut IPT yang keputusan bekalan makanan juga layak untuk memohon.
							 Anda akan menerima bekalan makanan kering(Beras 5kg/Minyak masak 1kg/Gula 1kg/Tepung gandum 1kg/Bihun 1 pek/Kicap 1 botol)</td>
							<td>18 Februari 2022</td>
							<td>31 Mac 2022</td>
							<td></td>
							<td><a href="delete?id=<c:out value='${donation.id}' />">Padam</a></td>
						</tr>
					<c:forEach var="donation" items="${listDonation}">
						<tr>
						    <td><a href="select?id=<c:out value='${donation.donationID}' />"><c:out value="${donation.donationID}"/></a></td>
							<td><c:out value="${donation.donationName}" /></td>
							<td><c:out value="${donation.donationDescription}" /></td>
							<td><c:out value="${donation.donationOpenDate}" /></td>
							<td><c:out value="${donation.donationCloseDate}" /></td>
							<td> <a href="delete?id=<c:out value='${donation.donationID}' />">Padam</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
  <br><br><br><br><br><br><br><br><br><br>
  <a href="<%=request.getContextPath()%>/new" class="button" >CIPTA</a>
</body>
</html>