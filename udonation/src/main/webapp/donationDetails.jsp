<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Donation Page</title>
</head>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap');
	
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
	background: #FFFFFF;
	padding: 5px 20px;
	display: flex;
	align-items: center;}
	
	nav a{
	color: #FFF;}
	
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
        
    div.a {
    position: relative;
    width: 190px;
    height:160px;
    border: 2px solid black;
    display: block;
    margin-left: 30px;
    }
    /*Fieldset and Legend*/
fieldset{
    margin-left: 20px;
	padding:5px;
	background-color: rgb(214, 234, 248);
	/*10px extra gap within grid container*/
}
legend{
	background-color:  rgb(180, 234, 248);
	padding:10px;
	border-radius: 25px;
}
/*Label*/
label{
    margin-left: 20px;
	padding:5px;
	color: black;
}
/*Input Text and Text Area*/
.text {
    margin-left: 20px;
	padding:5px;
	width:90%;
	border-radius: 8px;
    border-style: solid;
    border-width: 2px;
}

/*Submit and Reset Button*/

.date{
    margin-left: 20px;
	padding:5px;
	width:40%;
	border-radius: 8px;
    border-style: solid;
    border-width: 2px;
}
.radio{
    margin-left: 20px;
	padding:5px;
}
form{
   width: 70%;
}

h2,h1{
margin-left:30px;
}
</style>
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
	 
	<h2>Butiran Sumbangan</h2><br> 
    <form action="<%= request.getContextPath() %>/update" method="post">
    <fieldset>
    <legend><b>Informasi Sumbangan</b></legend>
             <label for="name">Nama Sumbangan:</label><br>
             <input class="text" type="text" id="donationName" name="donationName" value=Foodbank><br><br>
             
             <label for="name">Penerangan Derma dan Butiran Kelayakan:</label><br>
             <textarea class="text" name="donationDescription" id="donationDescription" rows="5" cols="150">Anda layak untuk memohon jika anda adalah salah seorang ahli kariah masjid/penerima adalah terdiri daripada ketua keluarga/Asnaf/Pendapatan isi rumah kurang RM1000 dan berdasarkan maklumat asnaf. Penuntut IPT yang keputusan bekalan makanan juga layak untuk Anda akan menerima bekalan makanan kering(Beras 5kg/Minyak masak 1kg/Gula 1kg/Tepung gandum 1kg/Bihun 1 pek/Kicap 1 botol)</textarea><br><br>
             
             <label for="name">Tarikh Buka:</label><br>
             <input class="date" type="date" id="donationOpenDate" name="donationOpenDate" value="18 Februari 2022" ><br><br>
             
             <label for="name">Tarikh Tutup:</label><br>
             <input class="date" type="date" id="donationCloseDate" name="donationCloseDate" value="31 Mac 2022"><br><br>
             
    </fieldset>
    <a href="<%=request.getContextPath()%>/donationList.jsp" class="button" >KEMBALI</a>
    <input class="button" type="submit" value="HANTAR">
    </form>
</body>
</html>
